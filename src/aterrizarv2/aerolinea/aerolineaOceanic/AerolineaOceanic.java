
package aterrizarv2.aerolinea.aerolineaOceanic;

import aterrizarv2.aerolinea.Aerolinea;
import aterrizarv2.asientos.Asiento;
import aterrizarv2.asientos.excepcionesAsiento.ClaseAsientoInvalidaException;
import aterrizarv2.asientos.excepcionesAsiento.CodigoAsientoException;
import aterrizarv2.asientos.excepcionesAsiento.EstadoAsientoInvalidaException;
import aterrizarv2.asientos.excepcionesAsiento.PrecioNegativoException;
import aterrizarv2.asientos.excepcionesAsiento.UbicacionAsientoInvalidaException;
import aterrizarv2.usuarios.Usuario;
import aterrizarv2.vuelos.AsientoDTO;
import aterrizarv2.vuelos.Vuelo;
import java.util.LinkedList;

public class AerolineaOceanic extends Aerolinea implements AerolineaOceanicI{

    
    public AerolineaOceanic() {
        super(0.20);
    }

    String convertirFormatoCiudad(String ciudad){
        String codigoCiudadOceanic = ciudad;
        if (ciudad.length() == 2){
            codigoCiudadOceanic = ciudad + '_';
            if (ciudad == "LA"){
                codigoCiudadOceanic = "SLA";
            }
        }
        return codigoCiudadOceanic;
    }

    @Override
    public void comprarAsiento(String codigoAsiento, Usuario usuarioCompra) throws CodigoAsientoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[][] asientosDisponibles(Vuelo vuelo, String tipoPedido) throws TipoPedidoInvalidaException {
        String vueloOrigen = convertirFormatoCiudad(vuelo.getOrigen());
        String vueloDestino = convertirFormatoCiudad(vuelo.getDestino());
        String fechaSalida = vuelo.obtenerSalidaLatinoamericano();
        if (tipoPedido != "Origen" && tipoPedido != "OrigenYDestino"){
            throw new TipoPedidoInvalidaException("El tipo de pedido no existe");
        }
        if (tipoPedido == "Origen"){
            return asientosDisponiblesParaOrigen(vueloOrigen,fechaSalida);
        }
        else{
            return asientosDisponiblesParaOrigenYDestino(vueloOrigen,fechaSalida, vueloDestino);
        }    
    }

    @Override
    public LinkedList<Asiento> devolverAsiento(String[][] asientosVuelo) throws CodigoAsientoException, PrecioNegativoException, ClaseAsientoInvalidaException, UbicacionAsientoInvalidaException, EstadoAsientoInvalidaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[][] asientosDisponiblesParaOrigen(String codigoOrigenOceanic, String fechaSalida) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[][] asientosDisponiblesParaOrigenYDestino(String codigoOrigenOceanic, String fechaSalida, String codigoDestinoOceanic) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean estaReservado(String codigoDeVuelo, Integer numeroDeAsiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean comprarSiHayDisponibilidad(String dni, String codigoVuelo, Integer numeroDeAsiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean reservar(String dni, String codigoVuelo, Integer numeroDeAsiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
