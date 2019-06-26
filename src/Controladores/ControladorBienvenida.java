
package Controladores;

import aterrizarv2.usuarios.Usuario;

public class ControladorBienvenida {
    Usuario usuarioLogeado;

    public ControladorBienvenida(Usuario usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
    }

    public Usuario getUsuarioLogeado() {
        return usuarioLogeado;
    }
 
}
