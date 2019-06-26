
package Controladores;

import aterrizarv2.usuarios.Usuario;

public class ControladorComprasReservas {
    String tipoVentana;
    private Usuario usuario;
    
    public ControladorComprasReservas (Usuario usuario, String tipoVentana){
        this.usuario = usuario;
        this.tipoVentana = tipoVentana;
    }

    public String getTipoVentana() {
        return tipoVentana;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    
    
    
}
