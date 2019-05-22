package aterrizarv2.vuelos;

import aterrizarv2.aerolinea.Aerolinea;
import aterrizarv2.asientos.Asiento;
import aterrizarv2.asientos.ClaseAsiento;
import aterrizarv2.asientos.CodigoAsiento;
import aterrizarv2.asientos.EstadoAsiento;
import aterrizarv2.asientos.PrecioAsiento;
import aterrizarv2.asientos.UbicacionAsiento;
import aterrizarv2.asientos.excepcionesAsiento.ClaseAsientoInvalidaException;
import aterrizarv2.asientos.excepcionesAsiento.CodigoAsientoException;
import aterrizarv2.asientos.excepcionesAsiento.EstadoAsientoInvalidaException;
import aterrizarv2.asientos.excepcionesAsiento.PrecioNegativoException;
import aterrizarv2.asientos.excepcionesAsiento.UbicacionAsientoInvalidaException;
import aterrizarv2.fecha.FechaFlexible;
import aterrizarv2.hora.Hora;
import java.util.LinkedList;


public class Vuelo {
    private LinkedList<Asiento> asientos;
    private String origen;
    private String destino;
    private FechaFlexible fechaSalida;
    private FechaFlexible fechaLlegada;
    private Hora horaSalida;
    private Hora horaLlegada;

    public Vuelo(String origen, String destino, FechaFlexible fechaSalida, FechaFlexible fechaLlegada, Hora horaSalida, Hora horaLlegada) {
        this.asientos = new LinkedList<>();
        this.origen = origen;
        this.destino = destino;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
    }

    
    public LinkedList<AsientoVueloFullData> getDatosAsientoVuelo(){
        LinkedList<AsientoVueloFullData> datoAsientoVuelo = new LinkedList<>();
        asientos.forEach(asiento ->{
            datoAsientoVuelo.add(new AsientoVueloFullData(asiento, destino, origen, fechaSalida, horaSalida));
        });
        return datoAsientoVuelo;
    }
   
    
    public void cargarAsientos(Aerolinea aerolinea) throws CodigoAsientoException, PrecioNegativoException, ClaseAsientoInvalidaException, UbicacionAsientoInvalidaException, EstadoAsientoInvalidaException{
        String[][] asientosVuelo = aerolinea.asientosDisponibles(origen, destino, fechaSalida.representacionEnIso()
                , fechaLlegada.representacionEnIso(), horaSalida.getHoraFormatoString(), horaLlegada.getHoraFormatoString());
        
        for(int i = 0 ; i < asientosVuelo.length ; i++){
            CodigoAsiento codigo = new CodigoAsiento(asientosVuelo[i][0]);
            PrecioAsiento precio = new PrecioAsiento(Double.parseDouble(asientosVuelo[i][1]));
            ClaseAsiento clase = new ClaseAsiento(asientosVuelo[i][2]);
            UbicacionAsiento ubicacion = new UbicacionAsiento(asientosVuelo[i][3]);
            EstadoAsiento estado = new EstadoAsiento(asientosVuelo[i][4]);
            this.agregarAsiento(new Asiento(clase, codigo, estado, precio, ubicacion));
        }
    }
    
    public void agregarAsiento(Asiento asientoVuelo){
        asientos.add(asientoVuelo);
    }

    public LinkedList<Asiento> getAsientosVuelo() {
        return asientos;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public String getFechaSalida() {
        return fechaSalida.representacionEnIso();
    }
    
    public String getFechaLlegada() {
        return fechaLlegada.representacionEnIso();
    }

    public String getHoraLLegada() {
        return horaLlegada.getHoraFormatoString();
    }
    
    
}
