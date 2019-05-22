package aterrizarv2.filtrosBusqueda;

import aterrizarv2.hora.Hora;


public class FiltroHora implements FiltroBusqueda {
    private Hora horaSalida;

    public FiltroHora(Hora horaSalida) {
        this.horaSalida = horaSalida;
    }
    
    public String horaDeLLegada(){
        return horaSalida.getHoraFormatoString();
    }
}
