package aterrizarv2;

import aterrizarv2.aerolinea.Aerolinea;
import aterrizarv2.asientos.Asiento;
import aterrizarv2.busquedas.Busqueda;
import aterrizarv2.usuarios.Usuario;
import aterrizarv2.vuelos.AsientoVueloFullData;
import aterrizarv2.vuelos.Vuelo;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public class AterrizarV2 {
    private LinkedList<Aerolinea> aerolineas;
    private LinkedList<Usuario> usuarios;

    public AterrizarV2() {
        aerolineas = new LinkedList<>();
        usuarios = new LinkedList<>();
    }
    
    public void agregarAerolinea(Aerolinea aerolinea){
        aerolineas.add(aerolinea);
    }
    
    public void agregarUsuario(Usuario usuario){
        usuarios.add(usuario);
    }
    
    public List<AsientoVueloFullData> asientosCumplenParametro(Usuario usuario, Busqueda busqueda){
        List<Vuelo> vuelosDisponibles = obtenerVuelosDisponibles();
        List<AsientoVueloFullData> asientosVuelos = obtenerAsientosVuelos(vuelosDisponibles);
        List<AsientoVueloFullData> asientosCumplenRequisitos = busqueda.asientosCumplenRequisitoBusqueda(asientosVuelos);
        List<AsientoVueloFullData> noSuperOferta = asientosNoSuperOferta(asientosCumplenRequisitos,usuario);
        return (usuario.esVip()) ? asientosCumplenRequisitos : noSuperOferta;
    }
    
    public List<AsientoVueloFullData> asientosNoSuperOferta(List<AsientoVueloFullData> asientosCumplenRequisitos,Usuario user){
        return asientosCumplenRequisitos.stream().
                filter(asiento -> !Aerolinea.esSuperOferta(asiento.getAsiento(), user)).collect(Collectors.toList());
    }
    
    protected LinkedList<AsientoVueloFullData> obtenerAsientosVuelos(List<Vuelo> vuelosDisponibles){
        LinkedList<AsientoVueloFullData> asientosVuelos = new LinkedList<>();
        vuelosDisponibles.forEach(vuelo ->{
            asientosVuelos.addAll(vuelo.getDatosAsientoVuelo());
        });
        return asientosVuelos;
    }
    
    
    private List<Vuelo> obtenerVuelosDisponibles(){
        ArrayList<Vuelo> vuelos = new ArrayList<>();
        List<LinkedList<Vuelo>> vuelosAerolineas=aerolineas.stream().map(aerolinea -> aerolinea.getVuelos()).collect(Collectors.toList());
        vuelosAerolineas.forEach(listaVuelos -> vuelos.addAll(listaVuelos));
        return vuelos;
    }
    
}
