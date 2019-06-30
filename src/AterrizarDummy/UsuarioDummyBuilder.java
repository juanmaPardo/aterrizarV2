package AterrizarDummy;

import aterrizarv2.usuarios.DniInvalidoException;
import aterrizarv2.usuarios.UsuarioPaga;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UsuarioDummyBuilder {
    
    public static UsuarioPaga getUsuarioDummy(){
        try {
            UsuarioPaga usuario = new UsuarioPaga("Carlos", "Monaj", 1111111, 350);
            usuario.efectuarCompra(500000);
            return usuario;
        } catch (DniInvalidoException ex) {
            Logger.getLogger(UsuarioDummyBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        //No va a llegar aca xk es valido todo.
        return null;
    }
}
