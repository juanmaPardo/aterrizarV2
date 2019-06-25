package controladorVistas;

import Vistas.BusquedaAsientos;
import Vistas.CompraExitosa;
import Vistas.ErrorCompra;
import Vistas.ErrorReserva;
import Vistas.ReservaExitosa;
import Vistas.Sobrereserva;
import aterrizarv2.AterrizarV2;
import aterrizarv2.aerolinea.Aerolinea;
import aterrizarv2.asientos.excepcionesAsiento.AsientoNoDisponibleException;
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
import java.util.List;


public class BusquedaController {
    /* Esto ahora veo si lo hago global o lo trigereo e
    private ReservaExitosa reservaExitosa;
    private ErrorReserva errorReserva;
    private Sobrereservar sobrereserva;
    */
    
    private BusquedaAsientos busqueda;
    private Usuario usuarioBusca;
    private AterrizarV2 pagina;
    private ActualizadorVistas actualizador;

    public BusquedaController(Usuario usuarioBusca, AterrizarV2 pagina,ActualizadorVistas actualizador) {
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
                    aerolinea.comprarAsiento(codigoAsiento,usuarioBusca);
                    displayExitoCompra(codigoAsiento);
                    actualizador.actualizarVistas();
                    busqueda.eliminarFilaSeleccionada();
                } catch (AsientoReservadoException | CodigoAsientoException ex) {
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
            exitoCompra.setLocation(500,200);
        }
        
        public void displayErrorCompra(String mensajeFallo){
            ErrorCompra errorCompra = new ErrorCompra(mensajeFallo);
            errorCompra.setVisible(true);
            errorCompra.setLocation(500,200);
        }
    }
    
    class ReservaAsiento implements ActionListener{
        Sobrereserva sobrereserva;
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            if(busqueda.seSeleccionoFila()){
                String codigoVuelo = busqueda.obtenerCodigoVueloFilaSeleccionada();
                String numeroAsiento = busqueda.obtenerNumeroAsientoFilaSeleccionada();
                String codigoAsiento= codigoVuelo + "-" + numeroAsiento;
                try {
                    Aerolinea aerolinea = pagina.obtenerAerolineaTieneAsiento(codigoAsiento);
                    aerolinea.reservarAsiento(codigoAsiento, usuarioBusca);
                    displayExitoReserva(codigoAsiento);
                    actualizador.actualizarVistas();
                } catch (CodigoAsientoException ex) {
                    displayErrorReserva(ex.getMessage());
                } catch(AsientoNoDisponibleException ex){
                    displayOpcionSobrereserva(codigoAsiento);
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
            exitoReserva.setLocation(500,200);
        }
        
        public void displayErrorReserva(String mensajeFallo){
            ErrorReserva falloReserva = new ErrorReserva(mensajeFallo);
            falloReserva.setVisible(true);
            falloReserva.setLocation(500,200);
        }
        
        public void displayOpcionSobrereserva(String codigoAsiento){
            sobrereserva =new Sobrereserva(codigoAsiento);
            sobrereserva.setVisible(true);
            sobrereserva.setLocation(500,200);
            sobrereserva.agregarFuncionalidadBotonSobrereserva(new ManejaSobrereservas(codigoAsiento));
        }
        
        class ManejaSobrereservas implements ActionListener{
            private String codigoAsiento;

            public ManejaSobrereservas(String codigoAsiento) {
                this.codigoAsiento = codigoAsiento;
            }
            
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    pagina.sobrereservarAsiento(codigoAsiento, usuarioBusca);
                    sobrereserva.cerrarVentana();
                    
                } catch (AsientoReservadoException | CodigoAsientoException ex) {
                    busqueda.cambiarTextoTextField(ex.getMessage());
                }
            }
            
        }
        
    }
    
    class ObtieneBusquedas implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                busqueda.cambiarTextoTextField("");
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
