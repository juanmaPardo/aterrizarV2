package modeloVistas;

import Vistas.Compras;
import aterrizarv2.AterrizarV2;
import aterrizarv2.asientos.Asiento;
import aterrizarv2.asientos.excepcionesAsiento.CodigoAsientoException;
import aterrizarv2.usuarios.Usuario;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComprasModel {
    private Compras vistaCompras;
    private Usuario usuario;
    private AterrizarV2 pagina;

    public ComprasModel(Usuario usuario, AterrizarV2 pagina) {
        this.usuario = usuario;
        this.vistaCompras = new Compras(usuario.getNombre());
        this.pagina = pagina;
        setearAsientosCompradosEnVista();
    }
    
    public void setearAsientosCompradosEnVista(){
        vistaCompras.eliminarCeldasTabla();
        LinkedList<Asiento> asientosComprados = new LinkedList();
        asientosComprados.addAll(usuario.getAsientosComprados());
        asientosComprados.forEach(asiento -> {
            try {
                String codigoAsiento = asiento.getCodigo().getCodigo();
                String codigoVuelo = asiento.getCodigo().getNumeroVuelo();
                String numeroAsiento = asiento.getCodigo().getNumeroAsiento();
                String fechaSalida = pagina.obtenerFechaSalidaAsiento(codigoAsiento);
                String aerolinea = pagina.obtenerAerolineaAsiento(codigoAsiento);
                String precio = Double.toString(asiento.getPrecio().getPrecioAsiento());
                vistaCompras.rellenarFilaComprados(fechaSalida,aerolinea,codigoVuelo,numeroAsiento,precio);
            } catch (CodigoAsientoException ex) {
                //Nunca va a entrar a este catch en realidad asi que no pasa nada
                Logger.getLogger(ComprasModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
  
    
    public void display(){
        this.vistaCompras.setVisible(true);
        this.vistaCompras.setLocation(500, 200);
    }
    
    
}
