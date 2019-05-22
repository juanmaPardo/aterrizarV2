package aterrizarv2.vuelos;

import aterrizarv2.asientos.Asiento;
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

    public Vuelo(LinkedList<Asiento> asientos, String origen, String destino, FechaFlexible fechaSalida, FechaFlexible fechaLlegada, Hora horaSalida, Hora horaLlegada) {
        this.asientos = new LinkedList<>();
        this.origen = origen;
        this.destino = destino;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
    }

    
    
    /*
    //Sacarla, quizas
    public LinkedList<Asiento> getDatosAsientoVuelo(){
        LinkedList<Asiento> datoAsientoVuelo = new LinkedList<>();
        asientos.forEach(asiento ->{
            datoAsientoVuelo.add(new Asiento(asiento, destino, origen, fechaSalida, horaSalida));
        });
        return datoAsientoVuelo;
    }*/
    
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
