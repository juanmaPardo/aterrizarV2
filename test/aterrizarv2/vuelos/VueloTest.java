
package aterrizarv2.vuelos;

import aterrizarv2.aerolinea.Aerolinea;
import aterrizarv2.aerolinea.exceptionesAerolinea.DatosVueloIncorrectoException;
import aterrizarv2.asientos.Asiento;
import aterrizarv2.asientos.ClaseAsiento;
import aterrizarv2.asientos.CodigoAsiento;
import aterrizarv2.asientos.EnumClaseAsiento;
import aterrizarv2.asientos.EnumEstadoAsiento;
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
import aterrizarv2.fecha.excepcionesFecha.FechaNoValidaException;
import aterrizarv2.fecha.excepcionesFecha.FormatoFechaIncorrectoException;
import aterrizarv2.hora.Hora;
import aterrizarv2.hora.excepcionesHora.FormatoHoraIncorrectoException;
import aterrizarv2.hora.excepcionesHora.HoraInvalidaException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Assert;
import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;


public class VueloTest {
    
    public VueloTest() {
    }
    
    EnumClaseAsiento claseTurista = EnumClaseAsiento.TURISTA;
    EnumClaseAsiento clasePrimeraClase = EnumClaseAsiento.PRIMERA_CLASE;
    EnumClaseAsiento claseEjecutivo = EnumClaseAsiento.EJECUTIVO;
    EnumUbicacionAsiento ubicacionVentana = EnumUbicacionAsiento.VENTANA;
    EnumUbicacionAsiento ubicacionPasillo = EnumUbicacionAsiento.PASILLO;
    EnumUbicacionAsiento ubicacionCentro = EnumUbicacionAsiento.CENTRO;
    Vuelo vueloRioLima;
    Vuelo vueloBsAsMadrid;
    Asiento asiento2;
    Asiento asiento1;
    Aerolinea aerolinea;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() throws FormatoFechaIncorrectoException, FechaNoValidaException, FormatoHoraIncorrectoException, HoraInvalidaException, CodigoAsientoException, PrecioNegativoException, EstadoAsientoInvalidaException {
        String origenBuenosAires = "BUE";
        String destinoMadrid = "MAD";
        String origenRioJaneiro = "RIO";
        String destinoLima = "LIM";
        FechaFlexible fechaSalida1Junio2018 = new FechaFlexible("01/06/2018");
        FechaFlexible fechaLlegada2Junio2018 = new FechaFlexible("02/06/2018");
        FechaFlexible fechaSalida13Noviembre2018 = new FechaFlexible("13/11/2018");
        FechaFlexible fechaLlegada13Noviembre2018 = new FechaFlexible("13/11/2018");
        Hora horaSalida23hs = new Hora("23:05");
        Hora horaLlegada11hs = new Hora("11:30");
        Hora horaSalida12hs = new Hora("12:20");
        Hora horaLlegada21hs = new Hora("21:15");
        
        
        aerolinea = Mockito.mock(Aerolinea.class);
        
        String[][] asientosDisponibles = {{"EC0344-42","565.60","P","P","D"}, {"EC0344-66","365.60","T","E","D"}};
        
        Mockito.when(aerolinea.asientosDisponibles("BUE", "MAD", fechaSalida1Junio2018.representacionEnIso(), 
                fechaLlegada2Junio2018.representacionEnIso(), horaSalida23hs.getHoraFormatoString(), 
                horaLlegada11hs.getHoraFormatoString())).thenReturn(asientosDisponibles);

        
        vueloBsAsMadrid = new Vuelo(origenBuenosAires, destinoMadrid, fechaSalida1Junio2018, fechaLlegada2Junio2018, horaSalida23hs, horaLlegada11hs);
   
        
    }
    
    @Test
    public void agregamosVueloAerolinea() throws CodigoAsientoException, PrecioNegativoException, ClaseAsientoInvalidaException, UbicacionAsientoInvalidaException, EstadoAsientoInvalidaException{
        vueloBsAsMadrid.cargarAsientos(aerolinea);
        LinkedList<Asiento> asientosVuelo = vueloBsAsMadrid.getAsientosVuelo();

        ClaseAsiento primeraClase = new ClaseAsiento("P");
        ClaseAsiento turista = new ClaseAsiento("T");

        UbicacionAsiento pasillo = new UbicacionAsiento("P");
        UbicacionAsiento ventana = new UbicacionAsiento("E");

        
        Assert.assertEquals(asientosVuelo.get(0).getCodigo().getCodigo(),"EC0344-42");
        Assert.assertEquals(asientosVuelo.get(1).getCodigo().getCodigo(),"EC0344-66");
        
        Assert.assertEquals(asientosVuelo.get(0).getClase().getClaseAsiento(),primeraClase.getClaseAsiento());
        Assert.assertEquals(asientosVuelo.get(1).getClase().getClaseAsiento(),turista.getClaseAsiento());
        
        Assert.assertEquals(asientosVuelo.get(0).getPrecio().getPrecioAsiento(),565.60);
        Assert.assertEquals(asientosVuelo.get(1).getPrecio().getPrecioAsiento(),365.60);
        
        Assert.assertEquals(asientosVuelo.get(0).getUbicacion().getUbicacionAsiento(),pasillo.getUbicacionAsiento());
        Assert.assertEquals(asientosVuelo.get(1).getUbicacion().getUbicacionAsiento(),ventana.getUbicacionAsiento());
        
        Assert.assertEquals(asientosVuelo.get(0).getEstado().getEstadoAsiento(),EnumEstadoAsiento.DISPONIBLE);
        Assert.assertEquals(asientosVuelo.get(1).getEstado().getEstadoAsiento(),EnumEstadoAsiento.DISPONIBLE);
        
    }
    
     
}
