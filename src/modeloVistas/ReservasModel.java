package modeloVistas;

import Vistas.ErrorReserva;
import Vistas.ReservaExitosa;
import Vistas.Reservas;
import Vistas.Sobrereserva;
import aterrizarv2.AterrizarV2;
import aterrizarv2.asientos.Asiento;
import aterrizarv2.asientos.excepcionesAsiento.CodigoAsientoException;
import aterrizarv2.usuarios.Usuario;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ReservasModel {
    private Reservas vistaReservas;
    private Usuario usuario;
    private AterrizarV2 pagina;

    public ReservasModel( Usuario usuario, AterrizarV2 pagina) {
        this.usuario = usuario;
        this.vistaReservas = new Reservas(usuario.getNombre());
        this.pagina = pagina;
        this.setearAsientosReservadosEnVista();
    }
    
    public void setearAsientosReservadosEnVista(){
        vistaReservas.eliminarCeldasTabla();
        LinkedList<Asiento> asientosReservados = new LinkedList();
        asientosReservados.addAll(usuario.getAsientosReservados());
        asientosReservados.forEach(asiento -> {
            try {
                String codigoAsiento = asiento.getCodigo().getCodigo();
                String codigoVuelo = asiento.getCodigo().getNumeroVuelo();
                String numeroAsiento = asiento.getCodigo().getNumeroAsiento();
                String fechaSalida = pagina.obtenerFechaSalidaAsiento(codigoAsiento);
                String aerolinea = pagina.obtenerAerolineaAsiento(codigoAsiento);
                String precio = Double.toString(asiento.getPrecio().getPrecioAsiento());
                vistaReservas.rellenarFilaReservados(fechaSalida,aerolinea,codigoVuelo,numeroAsiento,precio);
            } catch (CodigoAsientoException ex) {
                //Nunca va a entrar a este catch en realidad asi que no pasa nada
                Logger.getLogger(ComprasModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
  
    public void display(){
        this.vistaReservas.setVisible(true);
        this.vistaReservas.setLocation(500, 200);
    }
}
