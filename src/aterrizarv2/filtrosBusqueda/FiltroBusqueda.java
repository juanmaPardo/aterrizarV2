package aterrizarv2.filtrosBusqueda;

import aterrizarv2.vuelos.AsientoVueloFullData;
import aterrizarv2.vuelos.Vuelo;

public interface FiltroBusqueda {
    
    public boolean asientoVueloCumpleParametro(AsientoVueloFullData asiento);
}
