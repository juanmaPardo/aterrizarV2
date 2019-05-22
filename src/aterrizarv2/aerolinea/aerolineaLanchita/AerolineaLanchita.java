package aterrizarv2.aerolinea.aerolineaLanchita;

import aterrizarv2.aerolinea.Aerolinea;
import aterrizarv2.asientos.Asiento;
import aterrizarv2.busquedas.Busqueda;
import aterrizarv2.usuarios.Usuario;
import java.util.ArrayList;

public class AerolineaLanchita extends Aerolinea implements AerolineaLanchitaI {
    
    public AerolineaLanchita(){
        super();
    }
    
    @Override
    public ArrayList<ArrayList<String>> asientosDisponibles(String origen, String destino, String fechaSalida, String fechaLLegada, String horaSalida, String horaLlegada) {
    }

    @Override
    public void comprar(String codigoAsiento) {
    }
    
    public Asiento asientosCumplenParametro(Usuario usuario,Busqueda filtroBusqueda){
        
    }
    
}
