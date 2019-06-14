
package aterrizarv2.aerolinea.aerolineaOceanic;

public class AerolineaOceanicI {
    
    public String[][] asientosDisponiblesParaOrigen(String codigoOrigenOceanic,String fechaSalida){
        
    }
    
    public String[][] asientosDisponiblesParaOrigenYDestino(String codigoOrigenOceanic,String fechaSalida,String codigoDestinoOceanic){
        
    }
    
    public boolean estaReservado(String codigoDeVuelo, Integer numeroDeAsiento){
        return true;
    }
    
    public boolean comprarSiHayDisponibilidad(String dni, String codigoVuelo, Integer numeroDeAsiento){
        return true;
    }
    
    public boolean reservar(String dni, String codigoVuelo, Integer numeroDeAsiento){
        return true;
    }
    
    
}
