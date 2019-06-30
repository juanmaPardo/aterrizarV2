package ModeloVistas;

import aterrizarv2.usuarios.Usuario;
import aterrizarv2.vuelos.AsientoVueloFullData;
import java.util.LinkedList;


public class ModeloReservas extends ModeloComprasReservas{

    public ModeloReservas(Usuario usuario) {
        super(usuario);
    }

    @Override
    public LinkedList<AsientoVueloFullData> obtenerAsientosUsuario() {
        return usuario.getAsientosReservados();
    }
    
}
