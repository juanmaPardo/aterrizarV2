package modeloVistas;

import Vistas.BusquedaAsientos;
import aterrizarv2.AterrizarV2;
import aterrizarv2.busquedas.Busqueda;
import aterrizarv2.busquedas.exceptionesBusqueda.ParametrosInsuficienteException;
import aterrizarv2.busquedas.ordenamientoBusqueda.OrdenPorPrecioDescendente;
import aterrizarv2.fecha.FechaFlexible;
import aterrizarv2.fecha.excepcionesFecha.FechaNoValidaException;
import aterrizarv2.fecha.excepcionesFecha.FormatoFechaIncorrectoException;
import aterrizarv2.filtrosBusqueda.FiltroDestino;
import aterrizarv2.filtrosBusqueda.FiltroFecha;
import aterrizarv2.filtrosBusqueda.FiltroOrigen;
import aterrizarv2.usuarios.Usuario;
import aterrizarv2.vuelos.AsientoVueloFullData;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class BusquedaModel {
    /* Esto ahora veo si lo hago global o lo trigereo e
    private ReservaExitosa reservaExitosa;
    private ErrorReserva errorReserva;
    private Sobrereservar sobrereserva;
    */
    
    private BusquedaAsientos busqueda;
    private Usuario usuarioBusca;
    private AterrizarV2 pagina;

    public BusquedaModel(Usuario usuarioBusca, AterrizarV2 pagina) {
        this.usuarioBusca = usuarioBusca;
        this.pagina = pagina;
        this.busqueda = new BusquedaAsientos();
        this.busqueda.agregarFuncionalidadBotonBuscar(new ObtieneBusquedas());
    }
    
    class ObtieneBusquedas implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                String origen = busqueda.obtenerTextoOrigen();
                String destino = busqueda.obtenerTextoDestino();
                String fecha = busqueda.obtenerTextoFecha();
                List<AsientoVueloFullData> disponibles = obtenerAsientosDisponibles(origen,destino,fecha);
                if(disponibles.size() != 0){
                    rellenarConDisponibles(disponibles);
                }
                else{
                    busqueda.cambiarTextoTextField("No se encontro ningun asiento con estos parametros");
                }
            } catch (FormatoFechaIncorrectoException | FechaNoValidaException | ParametrosInsuficienteException ex) {
                busqueda.cambiarTextoTextField("No se cumple con el formato fecha");
            }
        }
        
        public void rellenarConDisponibles(List<AsientoVueloFullData> disponibles){
            disponibles.forEach(asiento -> {
                String codigoAsiento = asiento.getAsiento().getCodigo().getCodigo();
                String codigoVuelo = asiento.getAsiento().getCodigo().getNumeroVuelo();
                String numeroAsiento = asiento.getAsiento().getCodigo().getNumeroAsiento();
                String aerolinea = pagina.obtenerAerolineaAsiento(codigoAsiento);
                String precio = Double.toString(asiento.getAsiento().getPrecio().getPrecioAsiento());
                String ubicacion = asiento.getAsiento().getUbicacion().ubicacionFormatoString();
                String clase = asiento.getAsiento().getClase().claseFormatoString();
                busqueda.rellenarTablaConDisponibles(aerolinea, codigoVuelo, numeroAsiento, precio, ubicacion, clase);
            });
        }
        
        public List<AsientoVueloFullData> obtenerAsientosDisponibles(String origen, String destino, String fecha) throws FormatoFechaIncorrectoException, FechaNoValidaException, ParametrosInsuficienteException{
            FiltroOrigen filtroOrigen = new FiltroOrigen(origen);
            FiltroDestino filtroDestino = new FiltroDestino(destino);
            FiltroFecha filtroFecha = new FiltroFecha(new FechaFlexible(fecha));
            Busqueda busquedaRealizar = new Busqueda(filtroOrigen,filtroDestino,filtroFecha);
            return pagina.asientosCumplenParametro(usuarioBusca, busquedaRealizar, new OrdenPorPrecioDescendente());
        }
        
    }
      
    public void display(){
        this.busqueda.setVisible(true);
        this.busqueda.setLocation(500, 200);
    }
}
