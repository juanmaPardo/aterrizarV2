package aterrizarv2.aerolinea.aerolineaOceanic;

import aterrizarv2.aerolinea.Aerolinea;
import aterrizarv2.asientos.Asiento;
import aterrizarv2.asientos.ClaseAsiento;
import aterrizarv2.asientos.CodigoAsiento;
import aterrizarv2.asientos.EstadoAsiento;
import aterrizarv2.asientos.PrecioAsiento;
import aterrizarv2.asientos.UbicacionAsiento;
import aterrizarv2.asientos.excepcionesAsiento.AsientoReservadoException;
import aterrizarv2.asientos.excepcionesAsiento.ClaseAsientoInvalidaException;
import aterrizarv2.asientos.excepcionesAsiento.CodigoAsientoException;
import aterrizarv2.asientos.excepcionesAsiento.EstadoAsientoInvalidaException;
import aterrizarv2.asientos.excepcionesAsiento.PrecioNegativoException;
import aterrizarv2.asientos.excepcionesAsiento.UbicacionAsientoInvalidaException;
import aterrizarv2.fecha.FechaFormatoLatinoamericano;
import aterrizarv2.fecha.excepcionesFecha.FechaNoValidaException;
import aterrizarv2.fecha.excepcionesFecha.FormatoFechaIncorrectoException;
import aterrizarv2.usuarios.Usuario;
import aterrizarv2.vuelos.AsientoDTO;
import aterrizarv2.vuelos.AsientoVueloFullData;
import aterrizarv2.vuelos.Vuelo;
import java.util.LinkedList;

public class AerolineaOceanic extends Aerolinea{
    private AerolineaOceanicI comunicacionOceanic;
    
    public AerolineaOceanic(AerolineaOceanicI comunicacionOceanic) {
        super(0.20,10);
        this.comunicacionOceanic = comunicacionOceanic;
    }

    String convertirFormatoCiudad(String ciudad){
        String codigoCiudadOceanic = ciudad;
        if (ciudad.length() == 2){
            codigoCiudadOceanic = ciudad + '_';
            if (ciudad == "LA"){
                codigoCiudadOceanic = "SLA";
            }
        }
        return codigoCiudadOceanic;
    }

    @Override
    public void comprarAsiento(String codigoVuelo, Usuario usuario) throws CodigoAsientoException, AsientoReservadoException {
        AsientoVueloFullData asientoAComprar = obtenerAsientoConCodigoVuelo(codigoVuelo);
        Asiento asiento = asientoAComprar.getAsiento();
        revisionesCompra(asiento,usuario);
        Integer numeroAsiento = Integer.parseInt(asiento.getCodigo().getNumeroAsiento());
        String dni = Integer.toString(usuario.getDni());
        comunicacionOceanic.comprarSiHayDisponibilidad(dni, codigoVuelo,numeroAsiento);
        postVentaCambioEstadoAsiento(asiento,usuario);
        asientosComprados.add(asiento);
        actualizaAsientosVendidosVuelo(asiento);
        usuario.efectuarCompra(asientoAComprar.getAsiento().getPrecio().getPrecioAsiento());
        usuario.marcarComoComprado(asientoAComprar.getAsiento());
        cambiarEstadoAsientoAVendido(asientoAComprar.getAsiento());
    }
    

    @Override
    public String[][] asientosDisponibles(Vuelo vuelo, String tipoPedido) throws TipoPedidoInvalidaException {
        String vueloOrigen = convertirFormatoCiudad(vuelo.getOrigen());
        String vueloDestino = convertirFormatoCiudad(vuelo.getDestino());
        String fechaSalida = vuelo.obtenerSalidaLatinoamericano();
        if (tipoPedido != "Origen" && tipoPedido != "OrigenYDestino"){
            throw new TipoPedidoInvalidaException("El tipo de pedido no existe");
        }
        if (tipoPedido == "Origen"){
            return comunicacionOceanic.asientosDisponiblesParaOrigen(vueloOrigen,fechaSalida);
        }
        else{
            return comunicacionOceanic.asientosDisponiblesParaOrigenYDestino(vueloOrigen,fechaSalida, vueloDestino);
        }    
    }

    @Override
    public LinkedList<Asiento> devolverAsiento(String[][] asientosVuelo) throws CodigoAsientoException, PrecioNegativoException, ClaseAsientoInvalidaException, UbicacionAsientoInvalidaException, EstadoAsientoInvalidaException, FormatoFechaIncorrectoException, FechaNoValidaException {
        LinkedList<AsientoDTO> listaAsientos = new LinkedList();
        LinkedList<Asiento> listaFinal = new LinkedList();
        for (int i = 0; i < asientosVuelo.length; i++){
            String numeroVuelo = asientosVuelo[i][0];
            String numeroAsiento = asientosVuelo[i][1];
            CodigoAsiento codigo = new CodigoAsiento(numeroVuelo,numeroAsiento);
            String fechaSalida = asientosVuelo[i][2];
            FechaFormatoLatinoamericano fechaLatam = new FechaFormatoLatinoamericano(fechaSalida);
            PrecioAsiento precio = new PrecioAsiento(Double.parseDouble(asientosVuelo[i][3]));
            ClaseAsiento clase = new ClaseAsiento(asientosVuelo[i][4]);
            UbicacionAsiento ubicacion = new UbicacionAsiento(asientosVuelo[i][5]);
            AsientoDTO asiento = new AsientoDTO(fechaLatam,null, clase, codigo, new EstadoAsiento("D"), precio, ubicacion);
        }
        listaFinal.addAll(listaAsientos);
        return listaFinal;
    }

    @Override
    public void reservarAsiento(String codigoAsiento, Usuario usuarioReserva) throws CodigoAsientoException {
        AsientoVueloFullData asientoEntero = obtenerAsiento(codigoAsiento);
        Asiento asiento = asientoEntero.getAsiento();
        asiento.getEstado().reservarAsiento();
        usuarioReserva.agregarAsientoReservado(asiento);
        String codigoVuelo = asiento.getCodigo().getCodigo();
        Integer numeroAsiento = Integer.parseInt(asiento.getCodigo().getNumeroAsiento());
        String dni = Integer.toString(usuarioReserva.getDni());
        comunicacionOceanic.reservar(dni,codigoVuelo,numeroAsiento);
    }

}
