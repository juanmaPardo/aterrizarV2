package aterrizarv2.aerolinea.aerolineaLanchita;

import aterrizarv2.aerolinea.Aerolinea;
import aterrizarv2.asientos.Asiento;
import aterrizarv2.busquedas.Busqueda;
import aterrizarv2.usuarios.Usuario;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class AerolineaLanchita extends Aerolinea implements AerolineaLanchitaI {
    
    public AerolineaLanchita(){
        super();
    }
    
    @Override
    public String[][] asientosDisponibles(String origen, String destino, String fechaSalida, String fechaLLegada, String horaSalida, String horaLlegada) {
    }

    @Override
    public void comprar(String codigoAsiento) {
    }
    
    public Asiento asientosCumplenParametro(Usuario usuario,Busqueda filtroBusqueda){
        LinkedList<Asiento> asientosVuelos = obtenerAsientosVuelos();
        
    }
    
    public LinkedList<Asiento> obtenerAsientosVuelos(){
        List<String[][]> asientosVuelos = vuelos.stream().map(vuelo -> 
                this.asientosDisponibles(vuelo.getOrigen(), vuelo.getDestino(),vuelo.getFechaSalida(),vuelo.getFechaLlegada(),
                vuelo.getFechaSalida(),vuelo.getHoraLLegada())
                            ).collect(Collectors.toList());
        
    }
}
