package aterrizarv2.aerolinea;

import aterrizarv2.aerolinea.aerolineaLanchita.AerolineaLanchitaI;
import aterrizarv2.aerolinea.exceptionesAerolinea.DatosVueloIncorrectoException;
import aterrizarv2.asientos.Asiento;
import aterrizarv2.asientos.excepcionesAsiento.ClaseAsientoInvalidaException;
import aterrizarv2.asientos.excepcionesAsiento.CodigoAsientoException;
import aterrizarv2.asientos.excepcionesAsiento.EstadoAsientoInvalidaException;
import aterrizarv2.asientos.excepcionesAsiento.PrecioNegativoException;
import aterrizarv2.asientos.excepcionesAsiento.UbicacionAsientoInvalidaException;
import aterrizarv2.busquedas.Busqueda;
import aterrizarv2.usuarios.Usuario;
import aterrizarv2.vuelos.Vuelo;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Aerolinea implements AerolineaLanchitaI{
    protected LinkedList<Vuelo> vuelos;

    public Aerolinea() {
        this.vuelos = new LinkedList<>();
    }
    
    public void agregarVuelo(Vuelo vuelo) throws DatosVueloIncorrectoException{
        try {
            vuelo.cargarAsientos(this);
            vuelos.add(vuelo);
        } catch (CodigoAsientoException | PrecioNegativoException | ClaseAsientoInvalidaException | UbicacionAsientoInvalidaException | EstadoAsientoInvalidaException ex) {
            throw new DatosVueloIncorrectoException("Los datos de los asientos obtenidos con el vuelo no son correctos");
        }
    }

    public LinkedList<Vuelo> getVuelos() {
        return vuelos;
    }
    
    
    @Override
    public String[][] asientosDisponibles(String origen, String destino, String fechaSalida, String fechaLLegada, String horaSalida, String horaLlegada) {
        //Implementado por el sistema, no nosotros
    }

    @Override
    public void comprar(String codigoAsiento) {
        //Implementado por el sistema, no nosotros
    }
    
}
