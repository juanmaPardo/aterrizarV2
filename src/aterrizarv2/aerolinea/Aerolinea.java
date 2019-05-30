package aterrizarv2.aerolinea;

import aterrizarv2.AterrizarV2;
import aterrizarv2.aerolinea.aerolineaLanchita.AerolineaLanchitaI;
import aterrizarv2.aerolinea.exceptionesAerolinea.DatosVueloIncorrectoException;
import aterrizarv2.asientos.Asiento;
import aterrizarv2.asientos.CodigoAsiento;
import aterrizarv2.asientos.EnumClaseAsiento;
import aterrizarv2.asientos.excepcionesAsiento.ClaseAsientoInvalidaException;
import aterrizarv2.asientos.excepcionesAsiento.CodigoAsientoException;
import aterrizarv2.asientos.excepcionesAsiento.EstadoAsientoInvalidaException;
import aterrizarv2.asientos.excepcionesAsiento.PrecioNegativoException;
import aterrizarv2.asientos.excepcionesAsiento.UbicacionAsientoInvalidaException;
import aterrizarv2.busquedas.Busqueda;
import aterrizarv2.usuarios.Usuario;
import aterrizarv2.usuarios.UsuarioNoPaga;
import aterrizarv2.vuelos.AsientoVueloFullData;
import aterrizarv2.vuelos.Vuelo;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Aerolinea implements AerolineaLanchitaI{
    protected LinkedList<Vuelo> vuelos;
    protected LinkedList<Asiento> asientosComprados;
    protected TreeMap<String,Integer> asientosVendidosVuelo;//Key:Numero vuelo, Value: cantidadVendidos
    protected static double RECARGO_AEROLINEA;

    public Aerolinea(double recargo) {
        this.vuelos = new LinkedList<>();
        this.asientosComprados = new LinkedList<>();
        this.asientosVendidosVuelo = new TreeMap<>();
        this.RECARGO_AEROLINEA = recargo;
    }
    
    public void agregarVuelo(Vuelo vuelo) throws DatosVueloIncorrectoException{
        try {
            vuelo.cargarAsientos(this);
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
    
    @Override
    public String[][] asientosDisponibles(String origen, String destino, String fechaSalida, String fechaLLegada, String horaSalida, String horaLlegada) {
        //Implementado por el sistema, no nosotros
    }

    @Override
    public void comprar(String codigoAsiento) {
        //Implementado por el sistema, no nosotros
    }
    
    public AsientoVueloFullData obtenerAsiento(String codigoAsiento) throws CodigoAsientoException {
        LinkedList<AsientoVueloFullData> asientosVuelos = AterrizarV2.obtenerAsientosVuelos(vuelos);
        return asientosVuelos.stream().filter(asiento -> asiento.getAsiento().getCodigo().getCodigo().equals(codigoAsiento))
                .collect(Collectors.toList()).get(0);
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
    
    public void comprarAsiento(String codigoAsiento, Usuario usuarioCompra) throws CodigoAsientoException{
        AsientoVueloFullData asientoComprado = obtenerAsiento(codigoAsiento);
        this.comprar(codigoAsiento);
        usuarioCompra.efectuarCompra(asientoComprado.getAsiento().getPrecio().getPrecioAsiento());
        usuarioCompra.marcarComoComprado(asientoComprado.getAsiento());
        this.asientosComprados.add(asientoComprado.getAsiento());
        this.actualizaAsientosVendidosVuelo(asientoComprado.getAsiento());
    }

    public TreeMap<String, Integer> getAsientosVendidosVuelo() {
        return asientosVendidosVuelo;
    }
    
    
}
