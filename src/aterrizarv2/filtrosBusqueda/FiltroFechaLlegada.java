package aterrizarv2.filtrosBusqueda;

import aterrizarv2.fecha.FechaFlexible;


public class FiltroFechaLlegada implements FiltroBusqueda{
    private FechaFlexible fecha;

    public FiltroFechaLlegada(FechaFlexible fecha) {
        this.fecha = fecha;
    }

    public String fechaIso(){
        return fecha.representacionEnIso();
    }
    
    
}
