package Controladores;

import ModeloVistas.ModeloBusquedas;
import AterrizarDummy.AterrizarDummy;
import aterrizarv2.busquedas.Busqueda;
import aterrizarv2.busquedas.exceptionesBusqueda.ParametrosInsuficienteException;
import aterrizarv2.fecha.FechaFlexible;
import aterrizarv2.fecha.excepcionesFecha.FechaNoValidaException;
import aterrizarv2.fecha.excepcionesFecha.FormatoFechaIncorrectoException;
import aterrizarv2.filtrosBusqueda.FiltroDestino;
import aterrizarv2.filtrosBusqueda.FiltroFecha;
import aterrizarv2.filtrosBusqueda.FiltroOrigen;
import aterrizarv2.vuelos.AsientoVueloFullData;
import java.util.List;

public class ControladorBusquedas {
    private ModeloBusquedas modelo;

    public ControladorBusquedas() {
        modelo = new ModeloBusquedas(AterrizarDummy.getPagina(),AterrizarDummy.getUsuario());
    }

    public ModeloBusquedas getModelo() {
        return modelo;
    }
    
    public List<AsientoVueloFullData> realizarBusquedaFiltros(String origen,String destino,String fecha) throws FormatoFechaIncorrectoException, FechaNoValidaException, ParametrosInsuficienteException{
        FiltroOrigen filtroOrigen = new FiltroOrigen(origen);
        FiltroDestino filtroDestino = new FiltroDestino(destino);
        FiltroFecha filtroFecha = new FiltroFecha(new FechaFlexible(fecha));
        Busqueda busquedaRealizar = new Busqueda(filtroOrigen,filtroDestino,filtroFecha);
        return modelo.realizarBusqueda(busquedaRealizar);
    }
}
