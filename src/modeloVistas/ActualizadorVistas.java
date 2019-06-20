package modeloVistas;


public class ActualizadorVistas {
    private ComprasModel ventanaCompras;
    private ReservasModel ventanaReservas;

    public ActualizadorVistas(ComprasModel ventanaCompras, ReservasModel ventanaReservas) {
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
