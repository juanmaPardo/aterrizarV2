
package Controladores;

import ModeloVistas.ModeloBienvenida;
import aterrizarv2.usuarios.Usuario;

public class ControladorBienvenida {
    private ModeloBienvenida modelo;

    public ControladorBienvenida(Usuario usuario) {
        this.modelo = new ModeloBienvenida(usuario);
    }

    public ModeloBienvenida getModelo() {
        return modelo;
    }

    public void setModelo(ModeloBienvenida modelo) {
        this.modelo = modelo;
    }
   
 
}
