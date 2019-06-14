package modeloVistas;

import Vistas.Bienvenida;
import aterrizarv2.AterrizarV2;
import aterrizarv2.usuarios.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BienvenidaModel {
    private Bienvenida bienvenida;
    private Usuario usuarioLogeado;
    private ComprasModel ventanaCompras;
    private ReservasModel ventanaReservas;
    private BusquedaModel ventanaBusquedas;
    private ActualizadorVistas actualizador;

    public BienvenidaModel(Usuario usuarioLogeado, AterrizarV2 pagina) {
        this.usuarioLogeado = usuarioLogeado;
        this.ventanaCompras = new ComprasModel(usuarioLogeado,pagina);
        this.ventanaReservas = new ReservasModel(usuarioLogeado,pagina);
        this.actualizador = new ActualizadorVistas(ventanaCompras, ventanaReservas);
        this.ventanaBusquedas = new BusquedaModel(usuarioLogeado,pagina,actualizador);
        this.bienvenida = new Bienvenida(usuarioLogeado.getNombre());
        setearListeners();
    }
    
    
    private void setearListeners(){
        setearListenerVentanaCompras();
        setearListenerVentanaReservas();
        setearListenerVentanaBusquedas();
    }
    
    private void setearListenerVentanaCompras(){
        bienvenida.agregarAbridorCompras(new AbridorCompras());
    }
    
    private void setearListenerVentanaReservas(){
        bienvenida.agregarAbridorReservas(new AbridorReservas());
    }
    
    private void setearListenerVentanaBusquedas(){
        bienvenida.agregarAbridorBusquedas(new AbridorBusquedas());
    }
    
    class AbridorCompras implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            ventanaCompras.display();
        }
        
    }
    
    class AbridorReservas implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            ventanaReservas.display();
        }
        
    }
    
    class AbridorBusquedas implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            ventanaBusquedas.display();
        }
        
    }
        
    
}
