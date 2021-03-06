package aterrizarv2.busquedas.ordenamientoBusqueda;

import aterrizarv2.AterrizarV2;
import aterrizarv2.aerolinea.aerolineaLanchita.AerolineaLanchita;
import aterrizarv2.aerolinea.aerolineaLanchita.AerolineaLanchitaI;
import aterrizarv2.aerolinea.aerolineaOceanic.TipoPedidoInvalidaException;
import aterrizarv2.aerolinea.exceptionesAerolinea.DatosVueloIncorrectoException;
import aterrizarv2.asientos.excepcionesAsiento.AsientoReservadoException;
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
import aterrizarv2.usuarios.DniInvalidoException;
import aterrizarv2.usuarios.UsuarioNoPaga;
import aterrizarv2.usuarios.UsuarioPaga;
import aterrizarv2.vuelos.AsientoVueloFullData;
import aterrizarv2.vuelos.Vuelo;
import java.util.LinkedList;
import java.util.List;
import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


public class OrdenPorPopularidadTest {
    Vuelo vueloRioLima;
    Vuelo vueloBsAsMadrid;
    AerolineaLanchitaI lanchitaMockeada;
    AerolineaLanchita lanchitaNoMockeada;
    UsuarioPaga userVip;
    UsuarioNoPaga userEstandar;
    FechaFlexible fechaSalida1Junio2018;
    FechaFlexible fechaSalida13Noviembre2018;
    FechaFlexible fechaLlegada2Junio2018;
    FechaFlexible fechaLlegada13Noviembre2018;
    AterrizarV2 aterrizar;
    LinkedList<Vuelo> vuelosRetorno;
    
    @Before
    public void setUp() throws DniInvalidoException, CodigoAsientoException, PrecioNegativoException, ClaseAsientoInvalidaException, UbicacionAsientoInvalidaException, EstadoAsientoInvalidaException, TipoPedidoInvalidaException, FechaNoValidaException, FormatoFechaIncorrectoException, FormatoHoraIncorrectoException, HoraInvalidaException, DatosVueloIncorrectoException {
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

        lanchitaMockeada = Mockito.mock(AerolineaLanchitaI.class);
        lanchitaNoMockeada = new AerolineaLanchita(lanchitaMockeada);
        
        
        String[][] asientosDisponiblesBueMad = {{"EC0344-42","565.60","P","P","D"}, {"EC0344-66","365.60","T","E","D"}};
        String[][] asientosDisponiblesRioLim = {{"EC0LAM-12","4555.60","P","P","D"}, {"EC0LAM-13","3665.60","T","E","D"}};
        
        
        Mockito.when(lanchitaMockeada.asientosDisponibles("BUE", "MAD", fechaSalida1Junio2018.representacionEnIso(), 
                fechaLlegada2Junio2018.representacionEnIso(), horaSalida23hs.getHoraFormatoString(), 
                horaLlegada11hs.getHoraFormatoString())).thenReturn(asientosDisponiblesBueMad);
        
        Mockito.when(lanchitaMockeada.asientosDisponibles("RIO", "LIM", fechaSalida13Noviembre2018.representacionEnIso(), 
                fechaLlegada13Noviembre2018.representacionEnIso(), horaSalida12hs.getHoraFormatoString(), 
                horaLlegada21hs.getHoraFormatoString())).thenReturn(asientosDisponiblesRioLim);

        
        vueloBsAsMadrid = new Vuelo(origenBuenosAires, destinoMadrid, fechaSalida1Junio2018, fechaLlegada2Junio2018, horaSalida23hs, horaLlegada11hs);
        vueloRioLima = new Vuelo(origenRioJaneiro, destinoLima, fechaSalida13Noviembre2018, fechaLlegada13Noviembre2018, horaSalida12hs, horaLlegada21hs);
        
        
        lanchitaNoMockeada.agregarVuelo(vueloBsAsMadrid, null);
        lanchitaNoMockeada.agregarVuelo(vueloRioLima, null);
    
        
        userVip = new UsuarioPaga("Juan", "Carlos",41565456 ,320 );
        userVip.efectuarCompra(200000);
        userEstandar = new UsuarioNoPaga("Pedro", "Benitez", 31256748);
        
        aterrizar = new AterrizarV2();
        aterrizar.agregarAerolinea(lanchitaNoMockeada);
        
        
    }
    
    @Test
    public void devuelveECO344PrimeroMasVuelosVendidos() throws CodigoAsientoException, AsientoReservadoException{
        lanchitaNoMockeada.comprarAsiento("EC0344-42", userEstandar);
        lanchitaNoMockeada.comprarAsiento("EC0344-66", userEstandar);
        lanchitaNoMockeada.comprarAsiento("EC0LAM-12", userEstandar);
        vuelosRetorno = new LinkedList();
        vuelosRetorno = lanchitaNoMockeada.getVuelos();
        OrdenPorPopularidad orden = new OrdenPorPopularidad();
        List<AsientoVueloFullData> asientosAOrdenadar = lanchitaNoMockeada.obtenerAsientosVuelosDisponibles(vuelosRetorno);
        AsientoVueloFullData asientoECO42 = asientosAOrdenadar.get(0);
        AsientoVueloFullData asientoECO66 = asientosAOrdenadar.get(1);
        AsientoVueloFullData asientoLAM12 = asientosAOrdenadar.get(2);
        AsientoVueloFullData asientoLAM13 = asientosAOrdenadar.get(3);
        orden.ordenar(asientosAOrdenadar, lanchitaNoMockeada.getAsientosVendidosVuelo());
        
        LinkedList<AsientoVueloFullData> desordenados = new LinkedList<>();
        desordenados.add(asientoLAM13);
        desordenados.add(asientoECO66);
        desordenados.add(asientoECO42);
        desordenados.add(asientoLAM12);
        
        orden.ordenar(desordenados, lanchitaNoMockeada.getAsientosVendidosVuelo());
        
        MatcherAssert.assertThat(asientosAOrdenadar,IsIterableContainingInOrder.contains(asientoECO42,asientoECO66,asientoLAM12,asientoLAM13));
        MatcherAssert.assertThat(desordenados,IsIterableContainingInOrder.contains(asientoECO66,asientoECO42,asientoLAM13,asientoLAM12));
    }
    
    
}
