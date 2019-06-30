
package Controladores;

import ModeloVistas.ModeloCompras;
import ModeloVistas.ModeloComprasReservas;
import ModeloVistas.ModeloReservas;
import aterrizarv2.usuarios.Usuario;
import AterrizarDummy.AterrizarDummy;

public class ControladorComprasReservas {
    private String tipoVentana;
    private ModeloComprasReservas modelo;
    
    public ControladorComprasReservas (String tipoVentana){
        this.tipoVentana = tipoVentana;
        if(tipoVentana == "compras"){
            modelo = new ModeloCompras(AterrizarDummy.getUsuario());
        }
        else if (tipoVentana == "reservas"){
            modelo = new ModeloReservas(AterrizarDummy.getUsuario());
        }
    }

    public String getTipoVentana() {
        return tipoVentana;
    }

    public ModeloComprasReservas getModelo() {
        return modelo;
    }

    
    
}
