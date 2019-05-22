package aterrizarv2.filtrosBusqueda;

import aterrizarv2.hora.Hora;


public class FiltroHoraLLegada implements FiltroBusqueda {
    private Hora horaLlegada;

    public FiltroHoraLLegada(Hora horaLlegada) {
        this.horaLlegada = horaLlegada;
    }
    
    public String horaDeLLegada(){
        return horaLlegada.getHoraFormatoString();
    }
}
