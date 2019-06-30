package AterrizarDummy;

import aterrizarv2.aerolinea.RequisitoCargaAsientos;
import aterrizarv2.aerolinea.aerolineaOceanic.AerolineaOceanic;
import aterrizarv2.aerolinea.aerolineaOceanic.AerolineaOceanicI;
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
import aterrizarv2.usuarios.UsuarioNoPaga;
import aterrizarv2.usuarios.UsuarioPaga;
import aterrizarv2.vuelos.Vuelo;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mockito.Mockito;

public class OceanicDummyBuilder {
    
    public static AerolineaOceanic getOceanicDummyBuilder(Usuario usuario) {
        AerolineaOceanic arOceanic = setearOceanicDummy(usuario);
        return arOceanic;
    }
    
    public static AerolineaOceanic setearOceanicDummy(Usuario us){
        try {
            Vuelo vueloRioLosAngeles;
            Vuelo vueloBsAsMadrid;
            AerolineaOceanic oceanic;
            AerolineaOceanicI oceanicMock;
            UsuarioPaga userVip;
            UsuarioNoPaga userEstandar;
            FechaFlexible fechaSalida1Junio2018;
            FechaFlexible fechaSalida13Noviembre2018;
            FechaFlexible fechaLlegada2Junio2018;
            FechaFlexible fechaLlegada13Noviembre2018;
            
            oceanicMock = Mockito.mock(AerolineaOceanicI.class);
            oceanic = new AerolineaOceanic(oceanicMock);
            
            
            /* CIUDADES */
            
            String origenBuenosAires = "BUE";
            String destinoMadrid = "MAD";
            String origenRioJaneiro = "RIO";
            String destinoLosAngeles = "LA";
            
            String bueOceanic = oceanic.convertirFormatoCiudad(origenBuenosAires);
            String madOceanic = oceanic.convertirFormatoCiudad(destinoMadrid);
            String rioOceanic = oceanic.convertirFormatoCiudad(origenRioJaneiro);
            String losAngelesOceanic = oceanic.convertirFormatoCiudad(destinoLosAngeles);
            
            /* ----------------------------------------------------------------------------------------------*/
            
            
            
            
            /* FECHAS */
            
            
            fechaSalida1Junio2018 = new FechaFlexible("01/06/2018");
            fechaLlegada2Junio2018 = new FechaFlexible("02/06/2018");
            fechaSalida13Noviembre2018 = new FechaFlexible("13/11/2018");
            fechaLlegada13Noviembre2018 = new FechaFlexible("13/11/2018");
            
            
            String unoJunio = fechaSalida1Junio2018.representacionEnLatinoamericano();
            String dosJunio = fechaLlegada2Junio2018.representacionEnLatinoamericano();
            String treceNoviembre = fechaSalida13Noviembre2018.representacionEnLatinoamericano();
            
            
            /* ----------------------------------------------------------------------------------------------*/
            
            
            
            
            /* HORA */
            
            Hora horaSalida23hs = new Hora("23:00");
            Hora horaLlegada11hs = new Hora("11:00");
            Hora horaSalida12hs = new Hora("12:00");
            Hora horaLlegada00hs= new Hora("00:00");
            
            
            String lasOnce = "11:00";
            String lasVeintiTres = "23:00";
            String lasDoce = "12:00";
            String lasCeroCero = "00:00";
            
            /* ----------------------------------------------------------------------------------------------*/
            
            
            
            /* COMUNICACION CON OCEANICI MOCKEADA */
            
            String[][] asientosBueLim = {{"LAM344","13",unoJunio,lasDoce,"565.60","P","P"},{"LAM344","23",unoJunio,lasDoce,"565.60","T","C"}};
            String[][] asientosRioLA = {{"MLR123","67", treceNoviembre, lasVeintiTres, "872.50", "P", "P"},{"MLR123", "122", treceNoviembre, lasVeintiTres, "921.76", "T","C"}};
            
            
            Mockito.when(oceanicMock.asientosDisponiblesParaOrigen(bueOceanic, unoJunio)).thenReturn(asientosBueLim);
            Mockito.when(oceanicMock.asientosDisponiblesParaOrigenYDestino(bueOceanic, unoJunio,madOceanic)).thenReturn(asientosBueLim);
            
            Mockito.when(oceanicMock.asientosDisponiblesParaOrigen(rioOceanic, treceNoviembre)).thenReturn(asientosRioLA);
            Mockito.when(oceanicMock.asientosDisponiblesParaOrigenYDestino(rioOceanic, treceNoviembre, losAngelesOceanic)).thenReturn(asientosRioLA);
            
            /* ----------------------------------------------------------------------------------------------*/
            
            
            
            
            /* CREACION Y CARGA DE LOS VUELOS */
            
            vueloBsAsMadrid = new Vuelo(bueOceanic, madOceanic, fechaSalida1Junio2018, fechaLlegada2Junio2018, horaSalida12hs, horaLlegada11hs);
            vueloRioLosAngeles = new Vuelo(rioOceanic, losAngelesOceanic, fechaSalida13Noviembre2018, fechaLlegada13Noviembre2018, horaSalida23hs, horaLlegada00hs);
            
            RequisitoCargaAsientos requisitoOrigen = new RequisitoCargaAsientos("Origen");
            
            
            oceanic.agregarVuelo(vueloBsAsMadrid, requisitoOrigen);
            oceanic.agregarVuelo(vueloRioLosAngeles, requisitoOrigen);
            /* ----------------------------------------------------------------------------------------------*/
            
            oceanic.comprarAsiento("LAM344-13", us);
            return oceanic;
        
        } catch (FormatoFechaIncorrectoException | FechaNoValidaException | FormatoHoraIncorrectoException | HoraInvalidaException | TipoPedidoInvalidaException | DatosVueloIncorrectoException | CodigoAsientoException | AsientoReservadoException ex) {
            Logger.getLogger(OceanicDummyBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } 
    
}
