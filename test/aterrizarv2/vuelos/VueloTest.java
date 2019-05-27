
package aterrizarv2.vuelos;

import aterrizarv2.aerolinea.Aerolinea;
import aterrizarv2.aerolinea.exceptionesAerolinea.DatosVueloIncorrectoException;
import aterrizarv2.asientos.Asiento;
import aterrizarv2.asientos.ClaseAsiento;
import aterrizarv2.asientos.CodigoAsiento;
import aterrizarv2.asientos.EnumClaseAsiento;
import aterrizarv2.asientos.EnumUbicacionAsiento;
import aterrizarv2.asientos.EstadoAsiento;
import aterrizarv2.asientos.PrecioAsiento;
import aterrizarv2.asientos.UbicacionAsiento;
import aterrizarv2.asientos.excepcionesAsiento.CodigoAsientoException;
import aterrizarv2.asientos.excepcionesAsiento.EstadoAsientoInvalidaException;
import aterrizarv2.asientos.excepcionesAsiento.PrecioNegativoException;
import aterrizarv2.fecha.FechaFlexible;
import aterrizarv2.fecha.excepcionesFecha.FechaNoValidaException;
import aterrizarv2.fecha.excepcionesFecha.FormatoFechaIncorrectoException;
import aterrizarv2.hora.Hora;
import aterrizarv2.hora.excepcionesHora.FormatoHoraIncorrectoException;
import aterrizarv2.hora.excepcionesHora.HoraInvalidaException;
import java.util.LinkedList;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


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
        Hora horaSalida23hs = new Hora("23");
        Hora horaLlegada11hs = new Hora("11");
        Hora horaSalida12hs = new Hora("12");
        Hora horaLlegada21hs = new Hora("21");
        CodigoAsiento codigoAsiento1 = new CodigoAsiento("EC0344-42");
        CodigoAsiento codigoAsiento2 = new CodigoAsiento("BEA745-67");
        PrecioAsiento precio1000Pesos = new PrecioAsiento(1000);
        PrecioAsiento precio2000Pesos = new PrecioAsiento(2000);
        PrecioAsiento precio1500Pesos = new PrecioAsiento(1500);
        EstadoAsiento estadoDisponible = new EstadoAsiento("D");
        EstadoAsiento estadoReservado = new EstadoAsiento("R");
        asiento1 = new Asiento(new ClaseAsiento(clasePrimeraClase),codigoAsiento1,estadoDisponible,precio1500Pesos,new UbicacionAsiento(ubicacionCentro));
        asiento2 = new Asiento(new ClaseAsiento(claseEjecutivo), codigoAsiento2,estadoReservado,precio2000Pesos,new UbicacionAsiento(ubicacionVentana));
        vueloBsAsMadrid = new Vuelo(origenBuenosAires,destinoMadrid,fechaSalida1Junio2018,fechaLlegada2Junio2018,horaSalida23hs,horaLlegada11hs);
        vueloRioLima = new Vuelo(origenRioJaneiro,destinoLima,fechaSalida13Noviembre2018,fechaLlegada13Noviembre2018,horaSalida12hs,horaLlegada21hs);
    }

    @After
    public void tearDown() throws Exception {
    }
    
    
    @Test
    public void testGetDatosAsientoVuelo() throws DatosVueloIncorrectoException {
        vueloBsAsMadrid.agregarAsiento(asiento1);
        vueloBsAsMadrid.agregarAsiento(asiento2);
        LinkedList<Asiento> resultadoObtenidoAsientos = vueloBsAsMadrid.getAsientosVuelo();
        LinkedList<Asiento> resultadoEsperadoAsientos = new LinkedList<>();
        resultadoEsperadoAsientos.add(asiento1);
        resultadoEsperadoAsientos.add(asiento2);
        assertEquals(resultadoEsperadoAsientos,resultadoObtenidoAsientos);
        
    }
    
    @Test
    public void testGetOrigen(){
        String origenObtenido = vueloBsAsMadrid.getOrigen();
        String origenEsperado = "BUE";
        Assert.assertEquals(origenEsperado, origenObtenido);
    }
    
    @Test
    public void testGetDestino(){
        String destinoObtenido = vueloBsAsMadrid.getDestino();
        String destinoEsperado = "MAD";
        Assert.assertEquals(destinoEsperado, destinoObtenido);
    }
    
    @Test
    public void testFechaSalida(){
        String fechaSalidaObtenida = vueloBsAsMadrid.getFechaSalida();
        String fechaSalidaEsperada = "01/06/2018";
        Assert.assertEquals(fechaSalidaEsperada,fechaSalidaObtenida);
    }

    /**
     * Test of cargarAsientos method, of class Vuelo.
     */
    @Test
    public void testCargarAsientos() throws Exception {
        System.out.println("cargarAsientos");
        Aerolinea aerolinea = null;
        Vuelo instance = null;
        instance.cargarAsientos(aerolinea);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of agregarAsiento method, of class Vuelo.
     */
    @Test
    public void testAgregarAsiento() {
        System.out.println("agregarAsiento");
        Asiento asientoVuelo = null;
        Vuelo instance = null;
        instance.agregarAsiento(asientoVuelo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAsientosVuelo method, of class Vuelo.
     */
    @Test
    public void testGetAsientosVuelo() {
        System.out.println("getAsientosVuelo");
        Vuelo instance = null;
        LinkedList<Asiento> expResult = null;
        LinkedList<Asiento> result = instance.getAsientosVuelo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFechaSalida method, of class Vuelo.
     */
    @Test
    public void testGetFechaSalida() {
        System.out.println("getFechaSalida");
        Vuelo instance = null;
        String expResult = "";
        String result = instance.getFechaSalida();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFechaLlegada method, of class Vuelo.
     */
    @Test
    public void testGetFechaLlegada() {
        System.out.println("getFechaLlegada");
        Vuelo instance = null;
        String expResult = "";
        String result = instance.getFechaLlegada();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHoraLLegada method, of class Vuelo.
     */
    @Test
    public void testGetHoraLLegada() {
        System.out.println("getHoraLLegada");
        Vuelo instance = null;
        String expResult = "";
        String result = instance.getHoraLLegada();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
     
}
