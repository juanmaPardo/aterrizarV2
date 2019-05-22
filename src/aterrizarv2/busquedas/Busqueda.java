package aterrizarv2.busquedas;

import aterrizarv2.asientos.Asiento;
import aterrizarv2.asientos.ClaseAsiento;
import aterrizarv2.filtrosBusqueda.FiltroBusqueda;
import aterrizarv2.busquedas.exceptionesBusqueda.*;
import aterrizarv2.filtrosBusqueda.FiltroClaseAsiento;
import aterrizarv2.filtrosBusqueda.FiltroDestino;
import aterrizarv2.filtrosBusqueda.FiltroFecha;
import aterrizarv2.filtrosBusqueda.FiltroOrigen;
import aterrizarv2.vuelos.AsientoVueloFullData;
import aterrizarv2.vuelos.Vuelo;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Busqueda {
    private LinkedList<FiltroBusqueda> filtroBusqueda;
    private LinkedList<FiltroClaseAsiento> filtroClaseAsiento;
    
    public Busqueda(FiltroOrigen partida, FiltroFecha fecha){
        filtroBusqueda = new LinkedList<>();
        filtroBusqueda.add(partida);
        filtroBusqueda.add(fecha);
    }

    public Busqueda(FiltroBusqueda... args) throws ParametrosInsuficienteException {
        filtroBusqueda = new LinkedList<>();
        filtroClaseAsiento = new LinkedList<>();
        if(!contieneCiudadOrigen(Arrays.asList(args))){
            throw new ParametrosInsuficienteException("Se debe propiciar la ciudad de origen");
        }
        if(!contieneCiudadDestino(Arrays.asList(args))){
            throw new ParametrosInsuficienteException("Se debe propiciar la ciudad de destino");
        }
        if(!contieneFecha(Arrays.asList(args))){
            throw new ParametrosInsuficienteException("Se debe propiciar una fecha");
        }
        for(int i=0 ; i < args.length ; i++){
            if(args[i] instanceof FiltroClaseAsiento){
                filtroClaseAsiento.add((FiltroClaseAsiento)args[i]);
            }
            else{
                filtroBusqueda.add(args[i]);
            }    
        }
    }
    
    private boolean contieneCiudadOrigen(List<FiltroBusqueda> parametros){
        return parametros.stream().anyMatch(elem -> elem instanceof FiltroOrigen);
    }
    
    private boolean contieneFecha(List<FiltroBusqueda> parametros){
        return parametros.stream().anyMatch(elem -> elem instanceof FiltroFecha);
    }
    
    private boolean contieneCiudadDestino(List<FiltroBusqueda> parametros){
        return parametros.stream().anyMatch(elem -> elem instanceof FiltroDestino);
    }
    
    public List<AsientoVueloFullData> asientosCumplenRequisitoBusqueda(List<AsientoVueloFullData> disponibles){
        List<AsientoVueloFullData> cumplenFiltroCaracterUnico = disponibles.stream()
                .filter(asiento -> cumpleTodosRequisitos(asiento)).collect(Collectors.toList());
        
        List<AsientoVueloFullData> asientosCumplenTodosFiltros = cumplenFiltroCaracterUnico.stream()
                .filter(vuelo -> claseAsientoAceptable(vuelo)).collect(Collectors.toList());
        
        return asientosCumplenTodosFiltros;
    }
    
    private boolean claseAsientoAceptable(AsientoVueloFullData asiento){
        List<ClaseAsiento> clasesAsiento =  filtroClaseAsiento.stream().map(filtro -> filtro.getClase()).collect(Collectors.toList());
        return clasesAsiento.contains(asiento.getAsiento().getClase());
    }
    
    private boolean cumpleTodosRequisitos(AsientoVueloFullData asiento){
        return filtroBusqueda.stream().
                allMatch(filtroBusq -> filtroBusq.asientoVueloCumpleParametro(asiento));
    }
    
    
}
