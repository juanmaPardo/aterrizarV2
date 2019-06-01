
package aterrizarv2.aerolinea.aerolineaOceanic;

import aterrizarv2.aerolinea.aerolineaLanchita.AerolineaLanchita;
import aterrizarv2.asientos.Asiento;
import aterrizarv2.asientos.ClaseAsiento;
import aterrizarv2.asientos.CodigoAsiento;
import aterrizarv2.asientos.EnumClaseAsiento;
import aterrizarv2.asientos.EnumUbicacionAsiento;
import aterrizarv2.asientos.EstadoAsiento;
import aterrizarv2.asientos.PrecioAsiento;
import aterrizarv2.asientos.UbicacionAsiento;
import aterrizarv2.asientos.excepcionesAsiento.ClaseAsientoInvalidaException;
import aterrizarv2.asientos.excepcionesAsiento.CodigoAsientoException;
import aterrizarv2.asientos.excepcionesAsiento.EstadoAsientoInvalidaException;
import aterrizarv2.asientos.excepcionesAsiento.PrecioNegativoException;
import aterrizarv2.asientos.excepcionesAsiento.UbicacionAsientoInvalidaException;
import aterrizarv2.fecha.FechaFlexible;
import aterrizarv2.fecha.FechaFormatoLatinoamericano;
import aterrizarv2.fecha.excepcionesFecha.FechaNoValidaException;
import aterrizarv2.fecha.excepcionesFecha.FormatoFechaIncorrectoException;
import aterrizarv2.hora.Hora;
import aterrizarv2.hora.excepcionesHora.FormatoHoraIncorrectoException;
import aterrizarv2.hora.excepcionesHora.HoraInvalidaException;
import aterrizarv2.vuelos.AsientoDTO;
import aterrizarv2.vuelos.Vuelo;
import java.util.LinkedList;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;

public class AerolineaOceanicTest {
    
    public AerolineaOceanicTest() {
    }
    
    EnumClaseAsiento claseTurista = EnumClaseAsiento.TURISTA;
    EnumClaseAsiento clasePrimeraClase = EnumClaseAsiento.PRIMERA_CLASE;
    EnumClaseAsiento claseEjecutivo = EnumClaseAsiento.EJECUTIVO;
    EnumUbicacionAsiento ubicacionVentana = EnumUbicacionAsiento.VENTANA;
    EnumUbicacionAsiento ubicacionPasillo = EnumUbicacionAsiento.PASILLO;
    EnumUbicacionAsiento ubicacionCentro = EnumUbicacionAsiento.CENTRO;
    Vuelo vueloBsAsLima;
    Vuelo vueloBsAsMadrid;
    Asiento asiento2;
    Asiento asiento1;
    AerolineaOceanic aerolineaOc;
    
    @Before
    public void setUp() throws FormatoFechaIncorrectoException, FechaNoValidaException, FormatoHoraIncorrectoException, HoraInvalidaException, CodigoAsientoException, PrecioNegativoException, EstadoAsientoInvalidaException, ClaseAsientoInvalidaException, UbicacionAsientoInvalidaException, TipoPedidoInvalidaException {
        String origenBuenosAires = "BUE";
        String destinoMadrid = "MAD";
        String origenRioJaneiro = "RIO";
        String destinoLima = "LIM";
        FechaFlexible fechaSalida1Junio2018 = new FechaFlexible("01/06/2018");
        FechaFlexible fechaLlegada2Junio2018 = new FechaFlexible("02/06/2018");
        String fechaSalida13Noviembre2018 = (new FechaFlexible("13/11/2018")).representacionEnLatinoamericano();
        String fechaLlegada13Noviembre2018 = (new FechaFlexible("13/11/2018")).representacionEnLatinoamericano();
        Hora horaSalida23hs = new Hora("23:05");
        Hora horaLlegada11hs = new Hora("11:30");
        String horaSalida12hs = (new Hora("12:20")).getHoraFormatoString();
        String horaLlegada21hs = (new Hora("21:15")).getHoraFormatoString();
        
        
        aerolineaOc = Mockito.mock(AerolineaOceanic.class);
        
        
        vueloBsAsLima = new Vuelo(origenBuenosAires, destinoLima, new FechaFlexible(fechaSalida13Noviembre2018), new FechaFlexible(fechaLlegada13Noviembre2018), new Hora(horaSalida12hs), new Hora(horaLlegada21hs));
        
        String[][] asientosDisponiblesBueLim = {{"EC0344-42","13",fechaSalida13Noviembre2018,horaSalida12hs,"565.60","P","P"},{"EC0344-66","23",fechaSalida13Noviembre2018,horaSalida12hs,"565.60","T","D"}};
        
        LinkedList<Asiento> asientosBueLim = new LinkedList<>();
        
        CodigoAsiento codigo = new CodigoAsiento(asientosDisponiblesBueLim[0][0]);
        String fechaSalida = asientosDisponiblesBueLim[0][1];
        String horaSalida = asientosDisponiblesBueLim[0][2];
        PrecioAsiento precio = new PrecioAsiento(Double.parseDouble(asientosDisponiblesBueLim[0][3]));
        ClaseAsiento clase = new ClaseAsiento(asientosDisponiblesBueLim[0][4]);
        UbicacionAsiento ubicacion = new UbicacionAsiento(asientosDisponiblesBueLim[0][5]);
        AsientoDTO asientoUno = new AsientoDTO(new FechaFormatoLatinoamericano(fechaSalida),new Hora(horaSalida),clase,codigo,null,precio,ubicacion);
        
        CodigoAsiento codigoDos = new CodigoAsiento(asientosDisponiblesBueLim[1][0]);
        String fechaSalidaDos = asientosDisponiblesBueLim[1][1];
        String horaSalidaDos = asientosDisponiblesBueLim[1][2];
        PrecioAsiento precioDos = new PrecioAsiento(Double.parseDouble(asientosDisponiblesBueLim[1][3]));
        ClaseAsiento claseDos = new ClaseAsiento(asientosDisponiblesBueLim[1][4]);
        UbicacionAsiento ubicacionDos = new UbicacionAsiento(asientosDisponiblesBueLim[1][5]);
        AsientoDTO asientoDos = new AsientoDTO(new FechaFormatoLatinoamericano(fechaSalidaDos),new Hora(horaSalidaDos),claseDos,codigoDos,null,precioDos,ubicacionDos);
        
        asientosBueLim.add(asientoUno);
        asientosBueLim.add(asientoDos);
        
        Mockito.when(aerolineaOc.devolverAsiento(asientosDisponiblesBueLim)).thenReturn(asientosBueLim);
        
        Mockito.when(aerolineaOc.asientosDisponibles(vueloBsAsLima,"Origen")).thenReturn(asientosDisponiblesBueLim);      
    }


    @Test
    public void convertirFormatoCiudad_SeIngresaUnaCiudadDeDosCaracteresYSeModificaCorrectamente() {
        AerolineaOceanic aerolineaO = new AerolineaOceanic();
        String codigoCiudadOceanic = aerolineaO.convertirFormatoCiudad("LR");
        assertEquals("El codigo de ciudad no esta en formato oceanic", "LR_", codigoCiudadOceanic);
    }
    
    @Test
    public void convertirFormatoCiudad_SeIngresaLaCiudadLAYSeModificaCorrectamente(){
        AerolineaOceanic aerolineaO = new AerolineaOceanic();
        String codigoCiudadOceanic = aerolineaO.convertirFormatoCiudad("LA");
        assertEquals("El codigo de ciudad no esta en formato oceanic", "SLA", codigoCiudadOceanic); 
    }
    
    @Test
    public void convertirFormatoCiudad_SeIngresaCiudadDeTresCaracteresYNoSeModifica(){
        AerolineaOceanic aerolineaO = new AerolineaOceanic();
        String codigoCiudadOceanic = aerolineaO.convertirFormatoCiudad("BUE");
        assertEquals("El codigo de ciudad no esta en formato oceanic", "BUE", codigoCiudadOceanic); 
    }
    
    
    
    
}
