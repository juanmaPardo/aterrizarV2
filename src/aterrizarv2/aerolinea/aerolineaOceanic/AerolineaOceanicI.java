
package aterrizarv2.aerolinea.aerolineaOceanic;

import aterrizarv2.vuelos.AsientoDTO;
import java.util.LinkedList;

public interface AerolineaOceanicI {
    
    LinkedList<AsientoDTO> asientosDisponiblesParaOrigen(String codigoOrigenOceanic,String fechaSalida);
    
    LinkedList<AsientoDTO> asientosDisponiblesParaOrigenYDestino(String codigoOrigenOceanic,String fechaSalida,String codigoDestinoOceanic);
    
    boolean estaReservado(String codigoDeVuelo, Integer numeroDeAsiento);
    
    boolean comprarSiHayDisponibilidad(String dni, String codigoVuelo, Integer numeroDeAsiento);
    
    boolean reservar(String dni, String codigoVuelo, Integer numeroDeAsiento);
    
    
}
