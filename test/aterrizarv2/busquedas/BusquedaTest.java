package aterrizarv2.busquedas;

import aterrizarv2.AterrizarV2;
import aterrizarv2.aerolinea.Aerolinea;
import aterrizarv2.asientos.Asiento;
import aterrizarv2.asientos.ClaseAsiento;
import aterrizarv2.asientos.excepcionesAsiento.ClaseAsientoInvalidaException;
import aterrizarv2.asientos.excepcionesAsiento.CodigoAsientoException;
import aterrizarv2.asientos.excepcionesAsiento.EstadoAsientoInvalidaException;
import aterrizarv2.asientos.excepcionesAsiento.PrecioNegativoException;
import aterrizarv2.asientos.excepcionesAsiento.UbicacionAsientoInvalidaException;
import aterrizarv2.busquedas.exceptionesBusqueda.ParametrosInsuficienteException;
import aterrizarv2.fecha.FechaFlexible;
import aterrizarv2.fecha.excepcionesFecha.FechaNoValidaException;
import aterrizarv2.fecha.excepcionesFecha.FormatoFechaIncorrectoException;
import aterrizarv2.filtrosBusqueda.FiltroClaseAsiento;
import aterrizarv2.filtrosBusqueda.FiltroDestino;
import aterrizarv2.filtrosBusqueda.FiltroFecha;
import aterrizarv2.filtrosBusqueda.FiltroOrigen;
import aterrizarv2.hora.Hora;
import aterrizarv2.hora.excepcionesHora.FormatoHoraIncorrectoException;
import aterrizarv2.hora.excepcionesHora.HoraInvalidaException;
import aterrizarv2.usuarios.DniInvalidoException;
import aterrizarv2.usuarios.UsuarioNoPaga;
import aterrizarv2.usuarios.UsuarioPaga;
import aterrizarv2.vuelos.AsientoVueloFullData;
import aterrizarv2.vuelos.Vuelo;
import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;


public class BusquedaTest {
    
    Vuelo vueloRioLima;
    Vuelo vueloBsAsMadrid;
    Aerolinea aerolinea;
    UsuarioPaga userVip;
    UsuarioNoPaga userEstandar;
    FechaFlexible fechaSalida1Junio2018;
    FechaFlexible fechaSalida13Noviembre2018;
    FechaFlexible fechaLlegada2Junio2018;
    FechaFlexible fechaLlegada13Noviembre2018;
    AterrizarV2 aterrizar;
                    
    
    @Before
    public void setUp() throws FormatoFechaIncorrectoException, FechaNoValidaException, FormatoHoraIncorrectoException, HoraInvalidaException, CodigoAsientoException, PrecioNegativoException, ClaseAsientoInvalidaException, UbicacionAsientoInvalidaException, EstadoAsientoInvalidaException, DniInvalidoException {
        String origenBuenosAires = "BUE";
        String destinoMadrid = "MAD";
        String origenRioJaneiro = "RIO";
        String destinoLima = "LIM";
        fechaSalida1Junio2018 = new FechaFlexible("01/06/2018");
        fechaLlegada2Junio2018 = new FechaFlexible("02/06/2018");
        fechaSalida13Noviembre2018 = new FechaFlexible("13/11/2018");
        fechaLlegada13Noviembre2018 = new FechaFlexible("13/11/2018");
        Hora horaSalida23hs = new Hora("23:05");
        Hora horaLlegada11hs = new Hora("11:30");
        Hora horaSalida12hs = new Hora("12:20");
        Hora horaLlegada21hs = new Hora("21:15");
        
        
        aerolinea = Mockito.mock(Aerolinea.class);
        
        String[][] asientosDisponiblesBueMad = {{"EC0344-42","565.60","P","P","D"}, {"EC0344-66","365.60","T","E","D"}};
        String[][] asientosDisponiblesRioLim = {{"EC0LAM-12","4555.60","P","P","D"}, {"EC0LAM-13","3665.60","T","E","D"}};
        
        
        Mockito.when(aerolinea.asientosDisponibles("BUE", "MAD", fechaSalida1Junio2018.representacionEnIso(), 
                fechaLlegada2Junio2018.representacionEnIso(), horaSalida23hs.getHoraFormatoString(), 
                horaLlegada11hs.getHoraFormatoString())).thenReturn(asientosDisponiblesBueMad);
        
        Mockito.when(aerolinea.asientosDisponibles("RIO", "LIM", fechaSalida13Noviembre2018.representacionEnIso(), 
                fechaLlegada13Noviembre2018.representacionEnIso(), horaSalida12hs.getHoraFormatoString(), 
                horaLlegada21hs.getHoraFormatoString())).thenReturn(asientosDisponiblesRioLim);

        
        vueloBsAsMadrid = new Vuelo(origenBuenosAires, destinoMadrid, fechaSalida1Junio2018, fechaLlegada2Junio2018, horaSalida23hs, horaLlegada11hs);
        vueloRioLima = new Vuelo(origenRioJaneiro, destinoLima, fechaSalida13Noviembre2018, fechaLlegada13Noviembre2018, horaSalida12hs, horaLlegada21hs);
        
        vueloBsAsMadrid.cargarAsientos(aerolinea);
        vueloRioLima.cargarAsientos(aerolinea);
        
        
        LinkedList<Vuelo> vuelosRetorno = new LinkedList<>();
        vuelosRetorno.add(vueloBsAsMadrid);
        vuelosRetorno.add(vueloRioLima);
        Mockito.when(aerolinea.getVuelos()).thenReturn(vuelosRetorno);
        
        userVip = new UsuarioPaga("Juan", "Carlos",41565456 ,320 );
        userVip.efectuarCompra(200000);
        userEstandar = new UsuarioNoPaga("Pedro", "Benitez", 31256748);
        
        aterrizar = new AterrizarV2();
        aterrizar.agregarAerolinea(aerolinea);
    }
    
    
    @Test
    public void todosLosFiltrosBusquedaMatchean() throws ClaseAsientoInvalidaException, ParametrosInsuficienteException{
        FiltroOrigen buenosAires = new FiltroOrigen("BUE");
        FiltroDestino madrid = new FiltroDestino("MAD");
        FiltroFecha junio2018 = new FiltroFecha(fechaSalida1Junio2018);
        FiltroClaseAsiento primClase = new FiltroClaseAsiento(new ClaseAsiento("P"));
        
        Busqueda busqueda = new Busqueda(buenosAires,madrid,junio2018,primClase);
        
        AsientoVueloFullData asiento = vueloBsAsMadrid.getDatosAsientoVuelo().get(0);
       
        
       boolean resultado = busqueda.cumpleTodosRequisitos(asiento);
        
        Assert.assertEquals(true,resultado);
        
    }
    
    @Test
    public void devuelveAsientoPrimeraClaseUsuarioPaga() throws ClaseAsientoInvalidaException, ParametrosInsuficienteException{
        FiltroOrigen buenosAires = new FiltroOrigen("BUE");
        FiltroDestino madrid = new FiltroDestino("MAD");
        FiltroFecha junio2018 = new FiltroFecha(fechaSalida1Junio2018);
        FiltroClaseAsiento primClase = new FiltroClaseAsiento(new ClaseAsiento("P"));
        
        Busqueda busqueda = new Busqueda(buenosAires,madrid,junio2018,primClase);
        
        
        List<AsientoVueloFullData> asientosCumplenParametro = aterrizar.asientosCumplenParametro(userVip, busqueda);
        
        
        
        Assert.assertEquals(asientosCumplenParametro.size(),1);
        Assert.assertEquals(asientosCumplenParametro.get(0).getAsiento().getCodigo().getCodigo(),"EC0344-42");
        
    }
    
    @Test
    public void noDevuelveAsientosCumpleFiltrosPeroElUserNoEsVip() throws ClaseAsientoInvalidaException, ParametrosInsuficienteException{
        FiltroOrigen buenosAires = new FiltroOrigen("BUE");
        FiltroDestino madrid = new FiltroDestino("MAD");
        FiltroFecha junio2018 = new FiltroFecha(fechaSalida1Junio2018);
        FiltroClaseAsiento primClase = new FiltroClaseAsiento(new ClaseAsiento("P"));
        
        Busqueda busqueda = new Busqueda(buenosAires,madrid,junio2018,primClase);
        
        
        List<AsientoVueloFullData> asientosCumplenParametro = aterrizar.asientosCumplenParametro(userEstandar, busqueda);
        
        
        
        Assert.assertEquals(asientosCumplenParametro.size(),0);
    }
    
   
}
