package modeloVistas;

import Vistas.BusquedaAsientos;
import Vistas.CompraExitosa;
import Vistas.ErrorCompra;
import Vistas.ErrorReserva;
import Vistas.ReservaExitosa;
import Vistas.Sobrereservar;
import aterrizarv2.AterrizarV2;
import aterrizarv2.aerolinea.Aerolinea;
import aterrizarv2.asientos.excepcionesAsiento.AsientoReservadoException;
import aterrizarv2.asientos.excepcionesAsiento.CodigoAsientoException;
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
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BusquedaModel {
    /* Esto ahora veo si lo hago global o lo trigereo e
    private ReservaExitosa reservaExitosa;
    private ErrorReserva errorReserva;
    private Sobrereservar sobrereserva;
    */
    
    private BusquedaAsientos busqueda;
    private Usuario usuarioBusca;
    private AterrizarV2 pagina;
    private ActualizadorVistas actualizador;

    public BusquedaModel(Usuario usuarioBusca, AterrizarV2 pagina,ActualizadorVistas actualizador) {
        this.usuarioBusca = usuarioBusca;
        this.pagina = pagina;
        this.busqueda = new BusquedaAsientos();
        this.busqueda.agregarFuncionalidadBotonBuscar(new ObtieneBusquedas());
        this.busqueda.agregarFuncionalidadBotonComprar(new CompraAsiento());
        this.busqueda.agregarFuncionalidadBotonReserva(new ReservaAsiento());
        this.actualizador = actualizador;
    }
    
    class CompraAsiento implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(busqueda.seSeleccionoFila()){
                try {
                    String codigoVuelo = busqueda.obtenerCodigoVueloFilaSeleccionada();
                    String numeroAsiento = busqueda.obtenerNumeroAsientoFilaSeleccionada();
                    String codigoAsiento= codigoVuelo + "-" + numeroAsiento;
                    Aerolinea aerolinea = pagina.obtenerAerolineaTieneAsiento(codigoAsiento);
                    aerolinea.comprarAsiento(codigoAsiento, usuarioBusca);
                    displayExitoCompra(codigoAsiento);
                    actualizador.actualizarVistas();
                    busqueda.eliminarFilaSeleccionada();
                } catch (CodigoAsientoException | AsientoReservadoException ex) {
                    displayErrorCompra(ex.getMessage());
                }
            }
            else{
                //Aca no va a llegar nunca en realidad, pero bueno, se deja 
                displayErrorCompra("No se selecciono ningun asiento a comprar.");
            }
        }
        
        public void displayExitoCompra(String codigoAsiento){
            CompraExitosa exitoCompra = new CompraExitosa(codigoAsiento);
            exitoCompra.setVisible(true);
        }
        
        public void displayErrorCompra(String mensajeFallo){
            ErrorCompra exitoCompra = new ErrorCompra(mensajeFallo);
            exitoCompra.setVisible(true);
        }
    }
    
    class ReservaAsiento implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(busqueda.seSeleccionoFila()){
                try {
                    String codigoVuelo = busqueda.obtenerCodigoVueloFilaSeleccionada();
                    String numeroAsiento = busqueda.obtenerNumeroAsientoFilaSeleccionada();
                    String codigoAsiento= codigoVuelo + "-" + numeroAsiento;
                    Aerolinea aerolinea = pagina.obtenerAerolineaTieneAsiento(codigoAsiento);
                    aerolinea.reservarAsiento(codigoAsiento, usuarioBusca);
                    displayExitoReserva(codigoAsiento);
                    actualizador.actualizarVistas();
                } catch (CodigoAsientoException ex) {
                    displayErrorReserva(ex.getMessage());
                }
            }
            else{
                //Aca no va a llegar nunca en realidad, pero bueno, se deja 
                displayErrorReserva("No se selecciono ningun asiento a comprar.");
            }
        }
        
        public void displayExitoReserva(String codigoAsiento){
            ReservaExitosa exitoReserva = new ReservaExitosa(codigoAsiento);
            exitoReserva.setVisible(true);
        }
        
        public void displayErrorReserva(String mensajeFallo){
            ErrorReserva falloReserva = new ErrorReserva(mensajeFallo);
            falloReserva.setVisible(true);
        }
    }
    
    class ObtieneBusquedas implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                busqueda.eliminarCeldasTabla();
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
                busqueda.cambiarTextoTextField(ex.getMessage());
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
    }
    
}