package aterrizarv2.aerolinea.aerolineaLanchita;


public interface AerolineaLanchitaI {
    
    public String[][] asientosDisponibles(String origen, String destino, String fechaSalida,String fechaLLegada, String horaSalida, String horaLlegada);
    
    public void comprar(String codigoAsiento);
       
    public void reservar(String codigo, String dni);
}
