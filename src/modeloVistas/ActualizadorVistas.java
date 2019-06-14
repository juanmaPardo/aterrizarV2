package modeloVistas;


public class ActualizadorVistas {
    private ComprasModel ventanaCompras;
    private ReservasModel ventanaReservas;

    public ActualizadorVistas(ComprasModel ventanaCompras, ReservasModel ventanaReservas) {
        this.ventanaCompras = ventanaCompras;
        this.ventanaReservas = ventanaReservas;
    }
    
    public void actualizarAsientosCompradosUsuario(){
        ventanaCompras.setearAsientosCompradosEnVista();
    }
    
    public void actualizarAsientosReservadosUsuario(){
        ventanaReservas.setearAsientosReservadosEnVista();
    }
     
    public void actualizarVistas(){
        ventanaCompras.setearAsientosCompradosEnVista();
        ventanaReservas.setearAsientosReservadosEnVista();
    } 
}
