package AterrizarDummy;

import aterrizarv2.AterrizarV2;
import aterrizarv2.aerolinea.aerolineaLanchita.AerolineaLanchita;
import aterrizarv2.aerolinea.aerolineaLanchita.AerolineaLanchitaI;
import aterrizarv2.aerolinea.aerolineaOceanic.TipoPedidoInvalidaException;
import aterrizarv2.aerolinea.exceptionesAerolinea.DatosVueloIncorrectoException;
import aterrizarv2.asientos.excepcionesAsiento.AsientoReservadoException;
import aterrizarv2.asientos.excepcionesAsiento.CodigoAsientoException;
import aterrizarv2.fecha.FechaFlexible;
import aterrizarv2.fecha.excepcionesFecha.FechaNoValidaException;
import aterrizarv2.fecha.excepcionesFecha.FormatoFechaIncorrectoException;
import aterrizarv2.hora.Hora;
import aterrizarv2.hora.excepcionesHora.FormatoHoraIncorrectoException;
import aterrizarv2.hora.excepcionesHora.HoraInvalidaException;
import aterrizarv2.usuarios.Usuario;
import aterrizarv2.vuelos.Vuelo;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mockito.Mockito;


public class LanchitaDummyBuilder {
   private AerolineaLanchita aerLanchita;

    public static AerolineaLanchita getLanchitaDummyBuilder(Usuario us) {
        AerolineaLanchita aerLanchita = setearLanchitaDummy(us);
        return aerLanchita;
    }
   
    public static AerolineaLanchita setearLanchitaDummy(Usuario us){
       try {
           Vuelo vueloRioLima;
           Vuelo vueloBsAsMadrid;
           AerolineaLanchita lanchitaNoMockeada;
           AerolineaLanchitaI lanchitaMockeada;
           FechaFlexible fechaSalida1Junio2018;
           FechaFlexible fechaSalida13Noviembre2018;
           FechaFlexible fechaLlegada2Junio2018;
           FechaFlexible fechaLlegada13Noviembre2018;
           AterrizarV2 aterrizar;
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
           
           
           String[][] asientosDisponiblesBueMad = {{"EC0344-42","565.60","P","P","D"}, {"EC0344-66","365.60","T","E","D"},{"EC0344-67","3265.60","T","E","D"},{"EC0344-44","565.60","P","P","D"}};
           String[][] asientosDisponiblesRioLim = {{"EC0LAM-12","4555.60","P","P","D"}, {"EC0LAM-13","3665.60","T","E","D"},
               {"EC0LAM-14","4555.60","P","P","D"},{"EC0LAM-15","4555.60","P","P","D"},{"EC0LAM-16","4555.60","P","P","D"},{"EC0LAM-17","4555.60","P","P","D"}};
           
           
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
           
           lanchitaNoMockeada.reservarAsiento("EC0LAM-12", us);
           lanchitaNoMockeada.comprarAsiento("EC0344-42", us);
           lanchitaNoMockeada.comprarAsiento("EC0344-66", us);
           
           return lanchitaNoMockeada;
       } catch (FormatoFechaIncorrectoException | FechaNoValidaException | FormatoHoraIncorrectoException | HoraInvalidaException | DatosVueloIncorrectoException | TipoPedidoInvalidaException | CodigoAsientoException | AsientoReservadoException ex) {
           Logger.getLogger(LanchitaDummyBuilder.class.getName()).log(Level.SEVERE, null, ex);
       }
       return null;
    }
   
}
