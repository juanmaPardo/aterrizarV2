package aterrizarv2.aerolinea.aerolineaLanchita;

import aterrizarv2.aerolinea.Aerolinea;
import aterrizarv2.asientos.Asiento;
import aterrizarv2.busquedas.Busqueda;
import aterrizarv2.usuarios.Usuario;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class AerolineaLanchita extends Aerolinea{
    
    public AerolineaLanchita(){
        //0,15 es el recargo de lanchita
        super(0.15);
    }
    
}
