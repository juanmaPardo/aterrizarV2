package aterrizarv2.filtrosBusqueda;

import aterrizarv2.fecha.FechaFlexible;

public class FiltroFecha implements FiltroBusqueda{
    private FechaFlexible fechaSalida;

    public FiltroFecha(FechaFlexible fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
    
    public String fechaIso(){
        return fechaSalida.representacionEnIso();
    }
}
