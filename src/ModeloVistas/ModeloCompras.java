package ModeloVistas;

import aterrizarv2.asientos.Asiento;
import aterrizarv2.usuarios.Usuario;
import aterrizarv2.vuelos.AsientoVueloFullData;
import java.util.LinkedList;


public class ModeloCompras extends ModeloComprasReservas {

    public ModeloCompras(Usuario usuario) {
        super(usuario);
    }

    @Override
    public LinkedList<AsientoVueloFullData> obtenerAsientosUsuario() {
        return usuario.getAsientosComprados();
    }
    
    
    
}
