package aterrizarv2.busquedas;

import aterrizarv2.asientos.ClaseAsiento;
import aterrizarv2.filtrosBusqueda.FiltroBusqueda;
import aterrizarv2.busquedas.exceptionesBusqueda.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Busqueda {
    private LinkedList<FiltroBusqueda> filtroBusqueda;
    private LinkedList<ClaseAsiento> filtroClaseAsiento;
    
    public Busqueda(CiudadPartida partida, Fecha fecha){
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
            if(args[i] instanceof ClaseAsientoVuelo){
                filtroClaseAsiento.add((ClaseAsientoVuelo)args[i]);
            }
            else{
                filtroBusqueda.add(args[i]);
            }    
        }
    }
    
    private boolean contieneCiudadOrigen(List<FiltroBusqueda> parametros){
        return parametros.stream().anyMatch(elem -> elem instanceof CiudadPartida);
    }
    
    private boolean contieneFecha(List<FiltroBusqueda> parametros){
        return parametros.stream().anyMatch(elem -> elem instanceof DatosFecha);
    }
    
    private boolean contieneCiudadDestino(List<FiltroBusqueda> parametros){
        return parametros.stream().anyMatch(elem -> elem instanceof CiudadDestino);
    }
    
    public List<AsientoGeneralVuelo> asientosCumplenRequisitoBusqueda(LinkedList<AsientoGeneralVuelo> disponibles){
        List<AsientoGeneralVuelo> cumplenFiltroCaracterUnico = disponibles.stream()
                .filter(asientoVuelo -> cumpleTodosRequisitos(asientoVuelo)).collect(Collectors.toList());
        
        List<AsientoGeneralVuelo> asientosCumplenTodosFiltros = cumplenFiltroCaracterUnico.stream()
                .filter(asientoVuelo -> claseAsientoAceptable(asientoVuelo)).collect(Collectors.toList());
        
        return asientosCumplenTodosFiltros;
    }
    
    private boolean claseAsientoAceptable(AsientoGeneral asiento){
        List<ClasesAsientoVuelo> clasesAsiento =  filtroClaseAsiento.stream().map(clase -> clase.getClaseAsiento()).collect(Collectors.toList());
        return clasesAsiento.contains(asiento.getDatosAsiento().getClaseAsiento().getClaseAsiento());
    }
    
    private boolean cumpleTodosRequisitos(AsientoGeneralVuelo asientoVuelo){
        return filtroBusqueda.stream().
                allMatch(filtroBusq -> filtroBusq.asientoVueloCumpleParametro(asientoVuelo));
    }
    
    
}
