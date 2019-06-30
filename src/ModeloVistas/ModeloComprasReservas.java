package ModeloVistas;

import aterrizarv2.usuarios.Usuario;
import aterrizarv2.vuelos.AsientoVueloFullData;
import java.util.LinkedList;


public abstract class ModeloComprasReservas {
    protected Usuario usuario;

    public ModeloComprasReservas(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String getNombreUsuario(){
        return usuario.getNombre();
    }
    
    public abstract LinkedList<AsientoVueloFullData> obtenerAsientosUsuario();
}
