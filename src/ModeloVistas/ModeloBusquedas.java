package ModeloVistas;

import aterrizarv2.AterrizarV2;
import aterrizarv2.aerolinea.Aerolinea;
import aterrizarv2.asientos.Asiento;
import aterrizarv2.asientos.ClaseAsiento;
import aterrizarv2.asientos.CodigoAsiento;
import aterrizarv2.asientos.EstadoAsiento;
import aterrizarv2.asientos.PrecioAsiento;
import aterrizarv2.asientos.UbicacionAsiento;
import aterrizarv2.asientos.excepcionesAsiento.AsientoNoDisponibleException;
import aterrizarv2.asientos.excepcionesAsiento.AsientoReservadoException;
import aterrizarv2.asientos.excepcionesAsiento.ClaseAsientoInvalidaException;
import aterrizarv2.asientos.excepcionesAsiento.CodigoAsientoException;
import aterrizarv2.asientos.excepcionesAsiento.PrecioNegativoException;
import aterrizarv2.asientos.excepcionesAsiento.UbicacionAsientoInvalidaException;
import aterrizarv2.busquedas.Busqueda;
import aterrizarv2.busquedas.ordenamientoBusqueda.OrdenPorPrecioDescendente;
import aterrizarv2.fecha.FechaFlexible;
import aterrizarv2.fecha.excepcionesFecha.FechaNoValidaException;
import aterrizarv2.fecha.excepcionesFecha.FormatoFechaIncorrectoException;
import aterrizarv2.usuarios.Usuario;
import aterrizarv2.vuelos.AsientoVueloFullData;
import java.util.List;


public class ModeloBusquedas {
    private AterrizarV2 buscador;
    private Usuario usuario;
    private Asiento asientoSeleccionado;
    
    public ModeloBusquedas(AterrizarV2 buscador, Usuario usuario) {
        this.buscador = buscador;
        this.usuario = usuario;
        asientoSeleccionado = null;
    }
    
    public void establecerAsientoSeleccionadoTabla(String codigoAsiento,String clase,String ubicacion,String precio) throws  PrecioNegativoException, UbicacionAsientoInvalidaException, ClaseAsientoInvalidaException{
        CodigoAsiento codigoAs = new CodigoAsiento(codigoAsiento);
        PrecioAsiento precioAs = new PrecioAsiento(Double.parseDouble(precio));
        UbicacionAsiento ubicacionAs = new UbicacionAsiento(ubicacion);
        ClaseAsiento claseAs = new ClaseAsiento(clase);
        EstadoAsiento estado = buscador.obtenerAsientoAerolinea(codigoAsiento).getEstado();
        asientoSeleccionado = new Asiento(claseAs, codigoAs, estado, precioAs, ubicacionAs);
        System.out.println(codigoAs);
    }
    
    public List<AsientoVueloFullData> realizarBusqueda(Busqueda filtrosBusqueda){
        return buscador.asientosCumplenParametro(usuario, filtrosBusqueda, new OrdenPorPrecioDescendente());
    }
    
    public String codigoAsientoSeleccionado(){
        return asientoSeleccionado.getCodigo().getCodigo();
    }
    
    public void comprarAsientoSeleccionado() throws CodigoAsientoException, AsientoReservadoException{
        if(asientoSeleccionado == null){
            throw new CodigoAsientoException("Ningun asiento ah sido seleccionado");
        }
        String codigoAsientoSeleccionado = codigoAsientoSeleccionado();
        Aerolinea aerolinea = buscador.obtenerAerolineaTieneAsiento(codigoAsientoSeleccionado);
        aerolinea.comprarAsiento(codigoAsientoSeleccionado, usuario);
    }
    
    
    public void reservarAsientoSeleccionado() throws CodigoAsientoException,AsientoNoDisponibleException{
        if(asientoSeleccionado == null){
            throw new CodigoAsientoException("Ningun asiento ah sido seleccionado");
        }
        String codigoAsientoSeleccionado = asientoSeleccionado.getCodigo().getCodigo();
        Aerolinea aerolinea = buscador.obtenerAerolineaTieneAsiento(codigoAsientoSeleccionado);
        aerolinea.reservarAsiento(codigoAsientoSeleccionado, usuario);
    }
    
    public void sobrereservarAsientoSeleccionado() throws AsientoReservadoException{
        buscador.sobrereservarAsiento(codigoAsientoSeleccionado(), usuario);
    }
    
    public AterrizarV2 getBuscador() {
        return buscador;
    }

    public void setAsientoSeleccionado(Asiento asientoSeleccionado) {
        this.asientoSeleccionado = asientoSeleccionado;
    }
    
    
    
}
