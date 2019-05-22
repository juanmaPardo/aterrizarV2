package aterrizarv2.usuarios;

import aterrizarv2.asientos.Asiento;
import java.util.LinkedList;


public class Usuario {
    protected PerfilUsuario perfil;
    protected LinkedList<Asiento> asientosComprados;
    protected LinkedList<Asiento> asientosReservados;
    protected LinkedList<Asiento> asientosSobreReservados;
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
    
    public void agregarAsientoReservado(Asiento asiento){
        asientosReservados.add(asiento);
    }
    
    public void marcarComoComprado(Asiento asiento){
        asientosComprados.add(asiento);
    }
    
    public void efectuarCompra(double costoCompra){
        perfil.incrementarDineroGastado(costoCompra);
    }
    
    public boolean esVip(){return false;}
    /*
    public void reservarAsiento(String codigoAsiento, AerolineaGeneral aerolinea) throws AsientoReservadoException, CodigoAsientoException, UsuarioNoEncontradoException{
        aerolinea.reservar(codigoAsiento, dni);
    }
    
     public void agregarAsientoSobrereservado(AsientoGeneralVuelo asiento){
        asientosReservados.add(asiento);
    }
        
    public void comprarAsiento(String codigoAsiento, AerolineaGeneral aerolinea) throws CodigoAsientoException{
        aerolinea.comprar(codigoAsiento, this);
    }
    
    @Override
    public int compareTo(Usuario otroUsuario) {
        return Integer.compare(this.dni,otroUsuario.dni);
    }

    public void quitarASientoReservado(AsientoGeneralVuelo asiento) {
        asientosReservados.remove(asiento);
    }*/
}
