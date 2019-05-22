package aterrizarv2.filtrosBusqueda;


public class FiltroDestino implements FiltroBusqueda{
    private String destino;
    
    public FiltroDestino(String destino){
        this.destino = destino;
    }

    public String getDestino() {
        return destino;
    }
    
    
}
