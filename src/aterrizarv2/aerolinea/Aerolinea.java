package aterrizarv2.aerolinea;

import aterrizarv2.asientos.Asiento;
import aterrizarv2.busquedas.Busqueda;
import aterrizarv2.usuarios.Usuario;
import aterrizarv2.vuelos.Vuelo;
import java.util.LinkedList;

public class Aerolinea {
    protected LinkedList<Vuelo> vuelos;

    public Aerolinea() {
        this.vuelos = new LinkedList<>();
    }
    
    public void agregarVuelo(Vuelo vuelo){
        vuelos.add(vuelo);
    }
    
}
