package controladorVistas;

import aterrizarv2.AterrizarV2;
import aterrizarv2.asientos.Asiento;
import aterrizarv2.usuarios.Usuario;
import java.util.LinkedList;


public class ReservasController extends ControladorTablaComprasReservas{
    
    public ReservasController(Usuario usuario, AterrizarV2 pagina) {
        super(usuario,pagina,"reservas");
    } 
    
    public LinkedList<Asiento> asientosReservadosUsuarioVista(){
        return usuario.getAsientosReservados();
    }
}
