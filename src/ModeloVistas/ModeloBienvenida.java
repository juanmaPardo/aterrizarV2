package ModeloVistas;

import aterrizarv2.usuarios.Usuario;


public class ModeloBienvenida {
    private Usuario usuarioLogeado;

    public ModeloBienvenida(Usuario usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
    }
    
    public String obtenerNombreUsuarioLogeado(){
        return usuarioLogeado.getNombre();
    }
}
