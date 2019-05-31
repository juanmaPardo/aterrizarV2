package aterrizarv2.usuarios;

import aterrizarv2.asientos.Asiento;
import java.util.LinkedList;

public class UsuarioNoPaga extends Usuario{

    public UsuarioNoPaga(String nombre, String apellido, Integer dni) throws DniInvalidoException {
        super(nombre, apellido, dni);
    }

    @Override
    public boolean esVip() {
        return false;
    }
   
}
    

