package aterrizarv2.usuarios;

import aterrizarv2.asientos.Asiento;
import java.util.LinkedList;


public class UsuarioPaga extends Usuario{
    private double cuotaPorMes;
    
    public UsuarioPaga(String nombre, String apellido, Integer dni,double cuotaPorMes) throws DniInvalidoException {
        super(nombre, apellido, dni);
        this.cuotaPorMes = cuotaPorMes;
    }
    
    @Override
    public boolean esVip(){
        return perfil.dineroGastado() >= 100000;
    }
    
}

