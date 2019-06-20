package aterrizarv2.aerolinea.aerolineaOceanic;

import aterrizarv2.aerolinea.Aerolinea;
import aterrizarv2.aerolinea.RequisitoCargaAsientos;
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
import aterrizarv2.hora.Hora;
import aterrizarv2.hora.excepcionesHora.FormatoHoraIncorrectoException;
import aterrizarv2.hora.excepcionesHora.HoraInvalidaException;
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

    public String convertirFormatoCiudad(String ciudad){
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
    public void comprarAsiento(String codigoAsiento, Usuario usuario) throws CodigoAsientoException, AsientoReservadoException {
        AsientoVueloFullData asientoAComprar = obtenerAsiento(codigoAsiento);
        Asiento asiento = asientoAComprar.getAsiento();
        revisionesCompra(asiento,usuario);
        String dni = Integer.toString(usuario.getDni());
        comunicacionOceanic.comprarSiHayDisponibilidad(dni, codigoAsiento.split("-")[0],Integer.parseInt(codigoAsiento.split("-")[1]));
        postVentaCambioEstadoAsiento(asiento,usuario);
        asientosComprados.add(asiento);
        actualizaAsientosVendidosVuelo(asiento);
        usuario.efectuarCompra(asientoAComprar.getAsiento().getPrecio().getPrecioAsiento());
        usuario.marcarComoComprado(asientoAComprar.getAsiento());
        cambiarEstadoAsientoAVendido(asientoAComprar.getAsiento());
    }
    

    @Override
    public String[][] asientosDisponibles(Vuelo vuelo, RequisitoCargaAsientos tipoPedido) throws TipoPedidoInvalidaException {
        String vueloOrigen = convertirFormatoCiudad(vuelo.getOrigen());
        String vueloDestino = convertirFormatoCiudad(vuelo.getDestino());
        String fechaSalida = vuelo.obtenerSalidaLatinoamericano();
        
        if (tipoPedido.getTipoCarga() == "Origen"){
            return comunicacionOceanic.asientosDisponiblesParaOrigen(vueloOrigen,fechaSalida);
        }
        else{
            return comunicacionOceanic.asientosDisponiblesParaOrigenYDestino(vueloOrigen,fechaSalida, vueloDestino);
        }    
    }

    @Override
    public LinkedList<Asiento> devolverAsiento(String[][] asientosVuelo) throws CodigoAsientoException, PrecioNegativoException, ClaseAsientoInvalidaException, UbicacionAsientoInvalidaException, EstadoAsientoInvalidaException, FormatoFechaIncorrectoException, FechaNoValidaException, FormatoHoraIncorrectoException, HoraInvalidaException {
        LinkedList<AsientoDTO> listaAsientos = new LinkedList();
        LinkedList<Asiento> listaFinal = new LinkedList();
        for (int i = 0; i < asientosVuelo.length; i++){
            String numeroVuelo = asientosVuelo[i][0];
            String numeroAsiento = asientosVuelo[i][1];
            CodigoAsiento codigo = new CodigoAsiento(numeroVuelo,numeroAsiento);
            String fechaSalida = asientosVuelo[i][2];
            FechaFormatoLatinoamericano fechaLatam = new FechaFormatoLatinoamericano(fechaSalida);
            String horaSalida = asientosVuelo[i][3];
            Hora horaSal = new Hora(horaSalida);
            PrecioAsiento precio = new PrecioAsiento(Double.parseDouble(asientosVuelo[i][4]));
            ClaseAsiento clase = new ClaseAsiento(asientosVuelo[i][5]);
            UbicacionAsiento ubicacion = new UbicacionAsiento(asientosVuelo[i][6]);
            AsientoDTO asiento = new AsientoDTO(fechaLatam,horaSal, clase, codigo, new EstadoAsiento("D"), precio, ubicacion);
            listaAsientos.add(asiento);
        }
        listaFinal.addAll(listaAsientos);
        return listaFinal;
    }

    @Override
    public void reservarAsiento(String codigoAsiento, Usuario usuarioReserva) throws CodigoAsientoException, AsientoReservadoException {
        AsientoVueloFullData asientoEntero = obtenerAsiento(codigoAsiento);
        Asiento asiento = asientoEntero.getAsiento();
        if(asiento.getEstado().estaReservado() || asiento.getEstado().estaSobrereservado()){
            throw new AsientoReservadoException("El asiento ya esta reservado");
        }
        asiento.getEstado().reservarAsiento();
        usuarioReserva.agregarAsientoReservado(asiento);
        String codigoVuelo = asiento.getCodigo().getCodigo();
        Integer numeroAsiento = Integer.parseInt(asiento.getCodigo().getNumeroAsiento());
        String dni = Integer.toString(usuarioReserva.getDni());
        comunicacionOceanic.reservar(dni,codigoVuelo,numeroAsiento);
    }
    
    
    @Override
    public String getNombre(){
        return "Oceanic";
    }

}
