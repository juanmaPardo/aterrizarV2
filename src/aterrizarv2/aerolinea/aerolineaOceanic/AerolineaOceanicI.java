
package aterrizarv2.aerolinea.aerolineaOceanic;

public interface AerolineaOceanicI {
    
    asientosDisponiblesParaOrigen(String codigoOrigenOceanic,String fechaSalida);
    
    asientosDisponiblesParaOrigenYDestino(String codigoOrigenOceanic,String fechaSalida,String codigoDestinoOceanic);
    
    boolean estaReservado(String codigoDeVuelo, Integer numeroDeAsiento);
    
    boolean comprarSiHayDisponibilidad(String dni, String codigoVuelo, Integer numeroDeAsiento);
    
    boolean reservar(String dni, String codigoVuelo, Integer numeroDeAsiento);
    
    
}
