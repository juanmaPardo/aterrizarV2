package controladorVistas;

import Vistas.ReservasCompras;
import aterrizarv2.AterrizarV2;
import aterrizarv2.asientos.Asiento;
import aterrizarv2.asientos.excepcionesAsiento.CodigoAsientoException;
import aterrizarv2.usuarios.Usuario;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ControladorTablaComprasReservas {
    private ReservasCompras vista;
    protected Usuario usuario;
    private AterrizarV2 pagina;

    public ControladorTablaComprasReservas(Usuario usuario, AterrizarV2 pagina,String tipoVentana) {
        this.usuario = usuario;
        this.vista = new ReservasCompras(usuario.getNombre(),tipoVentana);
        this.pagina = pagina;
        if(tipoVentana.equals("reservas")){
            this.setearAsientosEnVista(usuario.getAsientosReservados());
        }
        else if(tipoVentana.equals("compras")){
            this.setearAsientosEnVista(usuario.getAsientosComprados());
        }
    }
    
    public void setearAsientosEnVista(LinkedList<Asiento> asientosRellenar){
        vista.eliminarCeldasTabla();
        asientosRellenar.forEach(asiento -> {
            try {
                String codigoAsiento = asiento.getCodigo().getCodigo();
                String codigoVuelo = asiento.getCodigo().getNumeroVuelo();
                String numeroAsiento = asiento.getCodigo().getNumeroAsiento();
                String fechaSalida = pagina.obtenerFechaSalidaAsiento(codigoAsiento);
                String aerolinea = pagina.obtenerAerolineaAsiento(codigoAsiento);
                String precio = Double.toString(asiento.getPrecio().getPrecioAsiento());
                vista.rellenarFilaTabla(fechaSalida,aerolinea,codigoVuelo,numeroAsiento,precio);
            } catch (CodigoAsientoException ex) {
                //Nunca va a entrar a este catch en realidad asi que no pasa nada
                Logger.getLogger(ComprasController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
   
  
    public void display(){
        this.vista.setVisible(true);
        this.vista.setLocation(500, 200);
    }
}
