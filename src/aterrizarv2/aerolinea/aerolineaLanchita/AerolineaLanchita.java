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
import aterrizarv2.busquedas.Busqueda;
import aterrizarv2.usuarios.Usuario;
import aterrizarv2.vuelos.AsientoVueloFullData;
import aterrizarv2.vuelos.Vuelo;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class AerolineaLanchita extends Aerolinea implements AerolineaLanchitaI{
    
    public AerolineaLanchita(){
        //0,15 es el recargo de lanchita
        super(0.15, 10);
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
    public void comprarAsiento(String codigoAsiento, Usuario usuarioCompra) throws CodigoAsientoException, AsientoReservadoException{
        AsientoVueloFullData asientoComprado = obtenerAsiento(codigoAsiento);
        if(estaReservadoAsiento(asientoComprado.getAsiento()) && !usuarioCompra.asientoReservadoPorMi(asientoComprado.getAsiento())){
            throw new AsientoReservadoException("asiento ya esta reservado");
        }
        this.comprar(codigoAsiento);
        usuarioCompra.efectuarCompra(asientoComprado.getAsiento().getPrecio().getPrecioAsiento());
        usuarioCompra.marcarComoComprado(asientoComprado.getAsiento());
        asientosComprados.add(asientoComprado.getAsiento());
        actualizaAsientosVendidosVuelo(asientoComprado.getAsiento());
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
        if(!estaDisponibleAsiento(asiento.getAsiento())){
            throw new AsientoNoDisponibleException("El asiento ya no esta disponible");
        }
        usuarioReserva.agregarAsientoReservado(asiento.getAsiento());
        asiento.getAsiento().getEstado().reservarAsiento();
        String codigoVuelo = asiento.getAsiento().getCodigo().getCodigo();
        String dni = Integer.toString(usuarioReserva.getDni());
        this.reservar(codigoVuelo, dni);
    }

    
}
