package controladorVistas;

import Vistas.Bienvenida;
import aterrizarv2.AterrizarV2;
import aterrizarv2.usuarios.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BienvenidaController {
    private Bienvenida bienvenida;
    private Usuario usuarioLogeado;
    private ComprasController ventanaCompras;
    private ReservasController ventanaReservas;
    private BusquedaController ventanaBusquedas;
    private ActualizadorVistas actualizador;

    public BienvenidaController(Usuario usuarioLogeado, AterrizarV2 pagina) {
        this.usuarioLogeado = usuarioLogeado;
        this.ventanaCompras = new ComprasController(usuarioLogeado,pagina);
        this.ventanaReservas = new ReservasController(usuarioLogeado,pagina);
        this.actualizador = new ActualizadorVistas(ventanaCompras, ventanaReservas);
        this.ventanaBusquedas = new BusquedaController(usuarioLogeado,pagina,actualizador);
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
