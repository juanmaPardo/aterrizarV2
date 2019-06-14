package aterrizarv2.aerolinea.aerolineaLanchita;

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
import aterrizarv2.usuarios.Usuario;
import aterrizarv2.vuelos.AsientoVueloFullData;
import aterrizarv2.vuelos.Vuelo;
import java.util.LinkedList;

public class AerolineaLanchita extends Aerolinea{
    private AerolineaLanchitaI comunicacionLanchita;
    
    public AerolineaLanchita(AerolineaLanchitaI comunicacionLanchita){
        //0,15 es el recargo de lanchita
        super(0.15,10);
        this.comunicacionLanchita = comunicacionLanchita;
    }

    @Override
    public void comprarAsiento(String codigoAsiento, Usuario usuarioAComprar) throws CodigoAsientoException, AsientoReservadoException{
        AsientoVueloFullData asientoAComprar = obtenerAsiento(codigoAsiento);
        Asiento asiento = asientoAComprar.getAsiento();
        revisionesCompra(asiento,usuarioAComprar);
        comunicacionLanchita.comprar(codigoAsiento);
        postVentaCambioEstadoAsiento(asiento,usuarioAComprar);
        asientosComprados.add(asiento);
        actualizaAsientosVendidosVuelo(asiento);
        usuarioAComprar.efectuarCompra(asiento.getPrecio().getPrecioAsiento());
        usuarioAComprar.marcarComoComprado(asiento);
        cambiarEstadoAsientoAVendido(asiento);
    }
   
    
    
    @Override
    public String[][] asientosDisponibles(Vuelo vuelo, RequisitoCargaAsientos noInteresa) {
        String origen = vuelo.getOrigen();
        String destino = vuelo.getDestino();
        String fechaSalida = vuelo.getFechaSalida();
        String fechaLlegada = vuelo.getFechaLlegada();
        String horaSalida = vuelo.getHoraSalida();
        String horaLlegada = vuelo.getHoraLLegada();
        return comunicacionLanchita.asientosDisponibles(origen,destino,fechaSalida,fechaLlegada,horaSalida,horaLlegada);
    }

    @Override
    public LinkedList<Asiento> devolverAsiento(String[][] asientosVuelo) throws CodigoAsientoException, PrecioNegativoException, ClaseAsientoInvalidaException, UbicacionAsientoInvalidaException, EstadoAsientoInvalidaException {
        LinkedList<Asiento> disponibles = new LinkedList<>();
        for(int i = 0 ; i < asientosVuelo.length ; i++){
            CodigoAsiento codigo = new CodigoAsiento(asientosVuelo[i][0]);
            PrecioAsiento precio = new PrecioAsiento(Double.parseDouble(asientosVuelo[i][1]));
            ClaseAsiento clase = new ClaseAsiento(asientosVuelo[i][2]);
            UbicacionAsiento ubicacion = new UbicacionAsiento(asientosVuelo[i][3]);
            EstadoAsiento estado = new EstadoAsiento(asientosVuelo[i][4]);
            disponibles.add(new Asiento(clase, codigo, estado, precio, ubicacion));
        }
        return disponibles;
    }

    @Override
    public void reservarAsiento(String codigoAsiento, Usuario usuarioReserva) throws CodigoAsientoException, AsientoReservadoException {
        AsientoVueloFullData asiento = obtenerAsiento(codigoAsiento);
        if(asiento.getAsiento().getEstado().estaReservado()){
            throw new AsientoReservadoException("El asiento ya esta reservado");
        }
        asiento.getAsiento().getEstado().reservarAsiento();
        usuarioReserva.agregarAsientoReservado(asiento.getAsiento());
        String codigoVuelo = asiento.getAsiento().getCodigo().getCodigo();
        String dni = Integer.toString(usuarioReserva.getDni());
        comunicacionLanchita.reservar(codigoVuelo, dni);
    }

    
}
