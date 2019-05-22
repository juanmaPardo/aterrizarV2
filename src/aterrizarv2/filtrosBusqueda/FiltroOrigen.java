package aterrizarv2.filtrosBusqueda;


public class FiltroOrigen implements FiltroBusqueda {
    private String origen;
    
    public FiltroOrigen(String origen){
        this.origen = origen;
    }

    public String getDestino() {
        return origen;
    }
}
