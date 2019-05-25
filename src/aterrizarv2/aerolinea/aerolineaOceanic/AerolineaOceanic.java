
package aterrizarv2.aerolinea.aerolineaOceanic;

import aterrizarv2.vuelos.AsientoDTO;
import java.util.LinkedList;

public class AerolineaOceanic implements AerolineaOceanicI{

    public AerolineaOceanic() {
    }

    
    String convertirFormatoCiudad(String ciudad){
        String codigoCiudadOceanic = ciudad;
        if (ciudad.length() == 2){
            codigoCiudadOceanic = ciudad + '_';
            if (ciudad == "LA"){
                codigoCiudadOceanic = "SLA";
            }
        }
        return codigoCiudadOceanic;
    }

    @Override
    public LinkedList<AsientoDTO> asientosDisponiblesParaOrigen(String codigoOrigenOceanic, String fechaSalida) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LinkedList<AsientoDTO> asientosDisponiblesParaOrigenYDestino(String codigoOrigenOceanic, String fechaSalida, String codigoDestinoOceanic) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean estaReservado(String codigoDeVuelo, Integer numeroDeAsiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean comprarSiHayDisponibilidad(String dni, String codigoVuelo, Integer numeroDeAsiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean reservar(String dni, String codigoVuelo, Integer numeroDeAsiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
