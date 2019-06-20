package modeloVistas;

import Vistas.ReservasCompras;
import aterrizarv2.AterrizarV2;
import aterrizarv2.asientos.Asiento;
import aterrizarv2.asientos.excepcionesAsiento.CodigoAsientoException;
import aterrizarv2.usuarios.Usuario;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComprasModel extends ControladorTablaComprasReservas{

    public ComprasModel(Usuario usuario, AterrizarV2 pagina) {
        super(usuario,pagina,"compras");
    }   
    
       
    public LinkedList<Asiento> asientosCompradosUsuarioVista(){
        return usuario.getAsientosComprados();
    }
}
