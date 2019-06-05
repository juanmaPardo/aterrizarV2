package aterrizarv2.aerolinea;

import aterrizarv2.AterrizarV2;
import aterrizarv2.aerolinea.aerolineaOceanic.TipoPedidoInvalidaException;
import aterrizarv2.aerolinea.exceptionesAerolinea.DatosVueloIncorrectoException;
import aterrizarv2.asientos.Asiento;
import aterrizarv2.asientos.EnumClaseAsiento;
import aterrizarv2.asientos.excepcionesAsiento.AsientoNoDisponibleException;
import aterrizarv2.asientos.excepcionesAsiento.AsientoReservadoException;
import aterrizarv2.asientos.excepcionesAsiento.ClaseAsientoInvalidaException;
import aterrizarv2.asientos.excepcionesAsiento.CodigoAsientoException;
import aterrizarv2.asientos.excepcionesAsiento.EstadoAsientoInvalidaException;
import aterrizarv2.asientos.excepcionesAsiento.PrecioNegativoException;
import aterrizarv2.asientos.excepcionesAsiento.UbicacionAsientoInvalidaException;
import aterrizarv2.fecha.excepcionesFecha.FechaNoValidaException;
import aterrizarv2.fecha.excepcionesFecha.FormatoFechaIncorrectoException;
import aterrizarv2.usuarios.Usuario;
import aterrizarv2.usuarios.UsuarioNoPaga;
import aterrizarv2.vuelos.AsientoVueloFullData;
import aterrizarv2.vuelos.Vuelo;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public abstract class Aerolinea{
    protected LinkedList<Vuelo> vuelos;
    protected LinkedList<Asiento> asientosComprados;
    protected TreeMap<String,Integer> asientosVendidosVuelo;//Key:Numero vuelo, Value: cantidadVendidos
    protected static double RECARGO_AEROLINEA;
    protected final int DIAS_EXPIRE_RESERVA;

    public Aerolinea(double recargo, int diasReserva) {
        this.vuelos = new LinkedList<>();
        this.asientosComprados = new LinkedList<>();
        this.asientosVendidosVuelo = new TreeMap<>();
        this.RECARGO_AEROLINEA = recargo;
        this.DIAS_EXPIRE_RESERVA = diasReserva;
    }
    
    public void agregarVueloYaCargado(Vuelo vuelo){
        vuelos.add(vuelo);
    }
    
    public void agregarVuelo(Vuelo vuelo,String tipoCarga) throws DatosVueloIncorrectoException, TipoPedidoInvalidaException, FechaNoValidaException, FormatoFechaIncorrectoException{
        try {
            vuelo.cargarAsientos(this,tipoCarga);
            vuelos.add(vuelo);
        } catch (CodigoAsientoException | PrecioNegativoException | ClaseAsientoInvalidaException | UbicacionAsientoInvalidaException | EstadoAsientoInvalidaException ex) {
            throw new DatosVueloIncorrectoException("Los datos de los asientos obtenidos con el vuelo no son correctos");
        }
    }

    public LinkedList<Vuelo> getVuelos() {
        return vuelos;
    }
    
    public static double precioTotal(Asiento asiento, Usuario usuario){
        if(usuario instanceof UsuarioNoPaga){
            return 20 + asiento.getPrecio().getPrecioAsiento() + (asiento.getPrecio().getPrecioAsiento() * RECARGO_AEROLINEA);
        }
        else{
            return asiento.getPrecio().getPrecioAsiento() + (asiento.getPrecio().getPrecioAsiento() * RECARGO_AEROLINEA);
        }
    }
    
    public static boolean esSuperOferta(Asiento asiento,Usuario usuario){
        boolean esPrimeraClase = asiento.getClase().getClaseAsiento() == EnumClaseAsiento.PRIMERA_CLASE;
        boolean esClaseEjecutiva = asiento.getClase().getClaseAsiento() == EnumClaseAsiento.EJECUTIVO;
        double precioAsiento = precioTotal(asiento,usuario);
        return (esPrimeraClase && precioAsiento < 8000) || (esClaseEjecutiva && precioAsiento < 4000);
    }
    
    
    public AsientoVueloFullData obtenerAsiento(String codigoAsiento) throws CodigoAsientoException {
        LinkedList<AsientoVueloFullData> asientosVuelos = obtenerAsientosVuelosDisponibles(vuelos);
        return asientosVuelos.stream().filter(asiento -> asiento.getAsiento().getCodigo().getCodigo().equals(codigoAsiento))
                .collect(Collectors.toList()).get(0);
    }
    
    public AsientoVueloFullData obtenerAsientoConCodigoVuelo(String codigoVuelo){
        LinkedList<AsientoVueloFullData> asientosVuelos = obtenerAsientosVuelosDisponibles(vuelos);
        return asientosVuelos.stream().filter(asiento -> asiento.getAsiento().getCodigo().getNumeroVuelo().equals(codigoVuelo))
                .collect(Collectors.toList()).get(0);
    }
    
    public boolean aerolineaTieneAsiento(String codigoAsiento){
        LinkedList<AsientoVueloFullData> asientosVuelos = obtenerAsientosVuelosDisponibles(vuelos);
        return !asientosVuelos.stream().filter(asiento -> asiento.getAsiento().getCodigo().getCodigo().equals(codigoAsiento))
                .collect(Collectors.toList()).isEmpty();
    }
    
    public LinkedList<AsientoVueloFullData> obtenerAsientosVuelosDisponibles(List<Vuelo> vuelosDisponibles){
        LinkedList<AsientoVueloFullData> asientosVuelos = new LinkedList<>();
        vuelosDisponibles.forEach(vuelo ->{
            asientosVuelos.addAll(vuelo.getDatosAsientoVuelo());
        });
        return asientosVuelos;
    }
    
    public void actualizaAsientosVendidosVuelo(Asiento asiento){
        String numeroVuelo = asiento.getCodigo().getNumeroVuelo();
        boolean yaTieneVentas = asientosVendidosVuelo.containsKey(numeroVuelo);
        if(yaTieneVentas){
            Integer cantidadVendidos = asientosVendidosVuelo.get(numeroVuelo);
            Integer nuevaCantidad = cantidadVendidos + 1 ;
            asientosVendidosVuelo.put(numeroVuelo, nuevaCantidad);    
        }
        else{
            asientosVendidosVuelo.put(numeroVuelo,1);//Si no se vendio ninguno, lo inicializamos en 1 xk ya se hizo la venta
        }
    }
    
    public abstract void reservarAsiento(String codigoAsiento, Usuario usuario) throws CodigoAsientoException;
  
    public boolean estaReservadoAsiento(Asiento asiento){
        return asiento.getEstado().estaReservado();
    }
    
    public void cambiarEstadoAsientoAVendido(Asiento asiento){
        asiento.getEstado().venderAsiento();
    }
    
    public void expiroReserva(Asiento asiento, Usuario usuario) throws CodigoAsientoException{
        usuario.eliminarAsientoReservado(asiento);
        if(asiento.getEstado().estaSobrereservado()){
            String codigoAsiento = asiento.getCodigo().getCodigo();
            Usuario usuarioSobrereserva = AterrizarV2.usuarioSobrereserva(codigoAsiento);
            reservarAsiento(codigoAsiento, usuarioSobrereserva);
        }
        else{
            asiento.getEstado().asientoDisponible();
        }
    }
    
    public void postVentaCambioEstadoAsiento(Asiento asiento, Usuario usuario){
        if (usuario.asientoReservadoPorMi(asiento)){
            usuario.eliminarAsientoReservado(asiento);
        }
        if (asiento.getEstado().estaSobrereservado()){
            AterrizarV2.eliminarSobrereservaUsuario(asiento.getCodigo().getCodigo());
        }
    }
    
    public void revisionesCompra(Asiento asiento,Usuario usuario) throws AsientoReservadoException{
        if(estaReservadoAsiento(asiento) && !usuario.asientoReservadoPorMi(asiento)){
            throw new AsientoReservadoException("asiento ya esta reservado por otra persona");
        }
        else if(asiento.getEstado().asientoVendido()){
            throw new AsientoNoDisponibleException("asiento ya vendido");
        }
    }
    
    public abstract void comprarAsiento(String codigoAsiento, Usuario usuarioCompra) throws CodigoAsientoException, AsientoReservadoException;
    
    public abstract String[][] asientosDisponibles(Vuelo vuelo, String tipoPedido) throws TipoPedidoInvalidaException;
    
    public abstract LinkedList<Asiento> devolverAsiento(String[][] asientosVuelo) throws CodigoAsientoException, PrecioNegativoException, ClaseAsientoInvalidaException, UbicacionAsientoInvalidaException, EstadoAsientoInvalidaException, FechaNoValidaException,FormatoFechaIncorrectoException;
    

    public TreeMap<String, Integer> getAsientosVendidosVuelo() {
        return asientosVendidosVuelo;
    }
    
    
}
