package aterrizarv2.busquedas;

import aterrizarv2.filtrosBusqueda.FiltroBusqueda;
import aterrizarv2.filtrosBusqueda.FiltroClaseAsiento;
import aterrizarv2.filtrosBusqueda.FiltroCodigoAsiento;
import aterrizarv2.filtrosBusqueda.FiltroDestino;
import aterrizarv2.filtrosBusqueda.FiltroHoraSalida;
import aterrizarv2.filtrosBusqueda.FiltroOrigen;
import aterrizarv2.filtrosBusqueda.FiltroPrecio;
import aterrizarv2.filtrosBusqueda.FiltroPrecioMax;
import aterrizarv2.filtrosBusqueda.FiltroPrecioMin;
import aterrizarv2.filtrosBusqueda.FiltroUbicacionAsiento;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Busqueda {
    private FiltroClaseAsiento filtroClase;
    private FiltroUbicacionAsiento filtroUbicacionAsiento;
    private FiltroCodigoAsiento filtroCodigo;
    private FiltroDestino filtroDestino;
    private FiltroOrigen filtroOrigen;
    private FiltroHoraSalida filtroHora;
    private FiltroPrecio filtroPrecio;
    private FiltroPrecioMin filtroPrecioMin;
    private FiltroPrecioMax filtroPrecioMax;
    private List<FiltroBusqueda> filtroBusqueda;

    public Busqueda(FiltroClaseAsiento filtroClase, FiltroUbicacionAsiento filtroUbicacionAsiento, FiltroCodigoAsiento filtroCodigo, FiltroDestino filtroDestino, FiltroOrigen filtroOrigen, FiltroHoraSalida filtroHora, FiltroPrecio filtroPrecio, FiltroPrecioMin filtroPrecioMin, FiltroPrecioMax filtroPrecioMax) {
        this.filtroClase = filtroClase;
        this.filtroUbicacionAsiento = filtroUbicacionAsiento;
        this.filtroCodigo = filtroCodigo;
        this.filtroDestino = filtroDestino;
        this.filtroOrigen = filtroOrigen;
        this.filtroHora = filtroHora;
        this.filtroPrecio = filtroPrecio;
        this.filtroPrecioMin = filtroPrecioMin;
        this.filtroPrecioMax = filtroPrecioMax;
        this.filtroBusqueda = this.agregarFiltrosNotNull();
    }
    
    private List<FiltroBusqueda> agregarFiltrosNotNull(){
        LinkedList<FiltroBusqueda> busqueda = new LinkedList();
        this.agregarTodosFiltros(busqueda);
        return busqueda.stream().filter(filtro -> filtro != null).collect(Collectors.toList());
       
    }
    
    private void agregarTodosFiltros(LinkedList<FiltroBusqueda> busqueda){
        busqueda.add(filtroClase);
        busqueda.add(filtroUbicacionAsiento);
        busqueda.add(filtroCodigo);
        busqueda.add(filtroDestino);
        busqueda.add(filtroOrigen);
        busqueda.add(filtroHora);
        busqueda.add(filtroPrecio);
        busqueda.add(filtroPrecioMin);
        busqueda.add(filtroPrecioMax);
    }
    
    
}
