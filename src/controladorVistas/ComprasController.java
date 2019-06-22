package controladorVistas;

import aterrizarv2.AterrizarV2;
import aterrizarv2.asientos.Asiento;
import aterrizarv2.usuarios.Usuario;
import java.util.LinkedList;

public class ComprasController extends ControladorTablaComprasReservas{

    public ComprasController(Usuario usuario, AterrizarV2 pagina) {
        super(usuario,pagina,"compras");
    }   
    
       
    public LinkedList<Asiento> asientosCompradosUsuarioVista(){
        return usuario.getAsientosComprados();
    }
}
