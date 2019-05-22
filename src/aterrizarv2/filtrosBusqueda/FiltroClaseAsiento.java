package aterrizarv2.filtrosBusqueda;

import aterrizarv2.asientos.ClaseAsiento;

public class FiltroClaseAsiento implements FiltroBusqueda{
    private ClaseAsiento clase;

    public FiltroClaseAsiento(ClaseAsiento clase) {
        this.clase = clase;
    }
    
}
