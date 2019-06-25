package controladorVistas;

public class ActualizadorVistas {
    private ComprasController ventanaCompras;
    private ReservasController ventanaReservas;

    public ActualizadorVistas(ComprasController ventanaCompras, ReservasController ventanaReservas) {
        this.ventanaCompras = ventanaCompras;
        this.ventanaReservas = ventanaReservas;
    }
    
    public void actualizarAsientosCompradosUsuario(){
        ventanaCompras.setearAsientosEnVista(ventanaCompras.asientosCompradosUsuarioVista());
    }
    
    public void actualizarAsientosReservadosUsuario(){
        ventanaReservas.setearAsientosEnVista(ventanaReservas.asientosReservadosUsuarioVista());
    }
     
    public void actualizarVistas(){
        ventanaCompras.setearAsientosEnVista(ventanaCompras.asientosCompradosUsuarioVista());
        ventanaReservas.setearAsientosEnVista(ventanaReservas.asientosReservadosUsuarioVista());
    } 
}
