package aterrizarv2.usuarios;

import aterrizarv2.aerolinea.Aerolinea;
import aterrizarv2.asientos.Asiento;
import aterrizarv2.asientos.excepcionesAsiento.AsientoReservadoException;
import aterrizarv2.asientos.excepcionesAsiento.CodigoAsientoException;
import aterrizarv2.vuelos.AsientoVueloFullData;
import java.util.LinkedList;
import java.util.stream.Collectors;


public abstract class Usuario {
    protected PerfilUsuario perfil;
    protected LinkedList<AsientoVueloFullData> asientosComprados;
    protected LinkedList<AsientoVueloFullData> asientosReservados;
    protected String nombre;
    protected String apellido;
    protected Integer dni;

    public Usuario(String nombre, String apellido, Integer dni) throws DniInvalidoException {
        if(dni <= 0){
            throw new DniInvalidoException("El dni no puede ser negativo");
        }
        
        perfil = new PerfilUsuario();
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;//Al dni lo asumimos como valido si no es negativo
        asientosComprados = new LinkedList<>();
        asientosReservados = new LinkedList<>();
    }

    public PerfilUsuario getPerfil() {
        return perfil;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Integer getDni() {
        return dni;
    }
    
    public void agregarAsientoReservado(AsientoVueloFullData asiento){
        asientosReservados.add(asiento);
    }
    
    public void marcarComoComprado(AsientoVueloFullData asiento){
        asientosComprados.add(asiento);
    }
    
    public void efectuarCompra(double costoCompra){
        perfil.incrementarDineroGastado(costoCompra);
    }
    
    public boolean asientoReservadoPorMi(Asiento asiento){
        return asientosReservados.stream().anyMatch(as -> as.getAsiento().getCodigo().getCodigo().equals(asiento.getCodigo().getCodigo()));
    }
    
    public abstract boolean esVip();
    
    public void reservarAsiento(String codigoAsiento, Aerolinea aerolinea) throws CodigoAsientoException, AsientoReservadoException {
        aerolinea.reservarAsiento(codigoAsiento, this);
    }
    
    public void comprarAsiento(String codigoAsiento, Aerolinea aerolinea) throws CodigoAsientoException, AsientoReservadoException{
        aerolinea.comprarAsiento(codigoAsiento, this);
    }

    public void eliminarAsientoReservado(Asiento asiento) {
        asientosReservados.removeIf(as -> as.getAsiento().getCodigo().getCodigo().equals(asiento.getCodigo().getCodigo()));
    }

    public LinkedList<AsientoVueloFullData> getAsientosComprados() {
        return asientosComprados;
    }

    public LinkedList<AsientoVueloFullData> getAsientosReservados() {
        return asientosReservados;
    }
    
    
}
