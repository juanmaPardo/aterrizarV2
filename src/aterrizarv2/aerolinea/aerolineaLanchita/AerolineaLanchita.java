package aterrizarv2.aerolinea.aerolineaLanchita;

import aterrizarv2.aerolinea.Aerolinea;
import aterrizarv2.asientos.Asiento;
import aterrizarv2.asientos.ClaseAsiento;
import aterrizarv2.asientos.CodigoAsiento;
import aterrizarv2.asientos.EstadoAsiento;
import aterrizarv2.asientos.PrecioAsiento;
import aterrizarv2.asientos.UbicacionAsiento;
import aterrizarv2.asientos.excepcionesAsiento.AsientoNoDisponibleException;
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

public class AerolineaLanchita extends Aerolinea implements AerolineaLanchitaI{
    
    public AerolineaLanchita(){
        //0,15 es el recargo de lanchita
        super(0.15,10);
    }
    
    @Override
    public String[][] asientosDisponibles(String origen, String destino, String fechaSalida, String fechaLLegada, String horaSalida, String horaLlegada) {
        return null;
        //Implementado por el sistema, no nosotros
    }

    @Override
    public void comprar(String codigoAsiento) {
        //Implementado por el sistema, no nosotros
    }
    
    @Override
    public void comprarAsiento(String codigoAsiento, Usuario usuarioAComprar) throws CodigoAsientoException, AsientoReservadoException{
        AsientoVueloFullData asientoAComprar = obtenerAsiento(codigoAsiento);
        Asiento asiento = asientoAComprar.getAsiento();
        if(estaReservadoAsiento(asiento) && !usuarioAComprar.asientoReservadoPorMi(asiento)){
            throw new AsientoReservadoException("asiento ya esta reservado por otra persona");
        }
        else if(asiento.getEstado().asientoVendido()){
            throw new AsientoNoDisponibleException("asiento ya vendido");
        }
        this.comprar(codigoAsiento);
        if (usuarioAComprar.asientoReservadoPorMi(asiento)){
            usuarioAComprar.eliminarAsientoReservado(asiento);
        }
        asientosComprados.add(asiento);
        actualizaAsientosVendidosVuelo(asiento);
        usuarioAComprar.efectuarCompra(asientoAComprar.getAsiento().getPrecio().getPrecioAsiento());
        usuarioAComprar.marcarComoComprado(asientoAComprar.getAsiento());
        cambiarEstadoAsientoAVendido(asientoAComprar.getAsiento());

    }
    
   

    @Override
    public String[][] asientosDisponibles(Vuelo vuelo, String parametroNoInteresaEnLanchitaSoloParaPolimorfismo) {
        String origen = vuelo.getOrigen();
        String destino = vuelo.getDestino();
        String fechaSalida = vuelo.getFechaSalida();
        String fechaLlegada = vuelo.getFechaLlegada();
        String horaSalida = vuelo.getHoraSalida();
        String horaLlegada = vuelo.getHoraLLegada();
        return asientosDisponibles(origen,destino,fechaSalida,fechaLlegada,horaSalida,horaLlegada);
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
    public void reservar(String codigo, String dni) {
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void reservarAsiento(String codigoAsiento, Usuario usuarioReserva) throws CodigoAsientoException {
        AsientoVueloFullData asiento = obtenerAsiento(codigoAsiento);
        asiento.getAsiento().getEstado().reservarAsiento();
        usuarioReserva.agregarAsientoReservado(asiento.getAsiento());
        String codigoVuelo = asiento.getAsiento().getCodigo().getCodigo();
        String dni = Integer.toString(usuarioReserva.getDni());
        this.reservar(codigoVuelo, dni);
    }

    
}
