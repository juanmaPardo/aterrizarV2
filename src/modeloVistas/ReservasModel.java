package modeloVistas;

import Vistas.ErrorReserva;
import Vistas.ReservaExitosa;
import Vistas.ReservasCompras;
import Vistas.Sobrereserva;
import aterrizarv2.AterrizarV2;
import aterrizarv2.asientos.Asiento;
import aterrizarv2.asientos.excepcionesAsiento.CodigoAsientoException;
import aterrizarv2.usuarios.Usuario;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ReservasModel extends ControladorTablaComprasReservas{
    
    public ReservasModel(Usuario usuario, AterrizarV2 pagina) {
        super(usuario,pagina,"reservas");
    } 
    
    public LinkedList<Asiento> asientosReservadosUsuarioVista(){
        return usuario.getAsientosReservados();
    }
}
