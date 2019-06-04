package aterrizarv2.reservas;

import aterrizarv2.AterrizarV2;
import aterrizarv2.aerolinea.aerolineaOceanic.AerolineaOceanic;
import aterrizarv2.aerolinea.aerolineaOceanic.TipoPedidoInvalidaException;
import aterrizarv2.asientos.Asiento;
import aterrizarv2.asientos.ClaseAsiento;
import aterrizarv2.asientos.CodigoAsiento;
import aterrizarv2.asientos.EnumClaseAsiento;
import aterrizarv2.asientos.EnumUbicacionAsiento;
import aterrizarv2.asientos.PrecioAsiento;
import aterrizarv2.asientos.UbicacionAsiento;
import aterrizarv2.asientos.excepcionesAsiento.AsientoNoDisponibleException;
import aterrizarv2.asientos.excepcionesAsiento.AsientoReservadoException;
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
import aterrizarv2.usuarios.UsuarioNoPaga;
import aterrizarv2.usuarios.UsuarioPaga;
import aterrizarv2.vuelos.AsientoDTO;
import aterrizarv2.vuelos.Vuelo;
import java.util.LinkedList;
import junit.framework.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.mockito.Mockito;

/**
 *
 * @author santi
 */
public class ReservasOceanicTest {
    
    EnumClaseAsiento claseTurista = EnumClaseAsiento.TURISTA;
    EnumClaseAsiento clasePrimeraClase = EnumClaseAsiento.PRIMERA_CLASE;
    EnumClaseAsiento claseEjecutivo = EnumClaseAsiento.EJECUTIVO;
    EnumUbicacionAsiento ubicacionVentana = EnumUbicacionAsiento.VENTANA;
    EnumUbicacionAsiento ubicacionPasillo = EnumUbicacionAsiento.PASILLO;
    EnumUbicacionAsiento ubicacionCentro = EnumUbicacionAsiento.CENTRO;
    Vuelo vueloBsAsLima;
    Vuelo vueloRioLosAngeles;
    Asiento asiento2;
    Asiento asiento1;
    AerolineaOceanic aerolineaOc;
    UsuarioPaga userVip;
    UsuarioNoPaga userEstandar;
    
    @Before
    public void setUp() throws FormatoFechaIncorrectoException, FechaNoValidaException, FormatoHoraIncorrectoException, HoraInvalidaException, CodigoAsientoException, PrecioNegativoException, EstadoAsientoInvalidaException, ClaseAsientoInvalidaException, UbicacionAsientoInvalidaException, TipoPedidoInvalidaException {
        String origenBuenosAires = "BUE";
        String destinoMadrid = "MAD";
        String origenRioJaneiro = "RIO";
        String destinoLima = "LIM";
        String destinoLosAngeles = "LA";
        String fechaSalida22Diciembre2018 = (new FechaFlexible("22/12/2018")).representacionEnLatinoamericano();
        String fechaLlegada23Diciembre2018 = (new FechaFlexible("23/12/2018")).representacionEnLatinoamericano();
        String fechaSalida13Noviembre2018 = (new FechaFlexible("13/11/2018")).representacionEnLatinoamericano();
        String fechaLlegada13Noviembre2018 = (new FechaFlexible("13/11/2018")).representacionEnLatinoamericano();
        String horaSalida23hs = (new Hora("23:05")).getHoraFormatoString();
        String horaLlegada11hs = (new Hora("11:30")).getHoraFormatoString();
        String horaSalida12hs = (new Hora("12:20")).getHoraFormatoString();
        String horaLlegada21hs = (new Hora("21:15")).getHoraFormatoString();
        
        
        aerolineaOc = Mockito.mock(AerolineaOceanic.class);
        
        
        
        vueloBsAsLima = new Vuelo(origenBuenosAires, destinoLima, new FechaFlexible(fechaSalida13Noviembre2018), new FechaFlexible(fechaLlegada13Noviembre2018), new Hora(horaSalida12hs), new Hora(horaLlegada21hs));
        
        vueloRioLosAngeles = new Vuelo(origenRioJaneiro,destinoLosAngeles,new FechaFlexible(fechaSalida22Diciembre2018), new FechaFlexible(fechaLlegada23Diciembre2018), new Hora(horaSalida23hs), new Hora(horaLlegada11hs));
        
        String[][] asientosDisponiblesBueLim = {{"EC0344","13",fechaSalida13Noviembre2018,horaSalida12hs,"565.60","P","P"},{"EC0324","23",fechaSalida13Noviembre2018,horaSalida12hs,"565.60","T","C"}};
        String[][] asientosDisponiblesRioLA = {{"MLR123","67", fechaSalida22Diciembre2018, horaSalida23hs, "872.50", "P", "P"},{"NDL113", "122", fechaSalida22Diciembre2018, horaSalida23hs, "921.76", "T","C"}};
        
        LinkedList<Asiento> asientosBueLim = new LinkedList<>();
        
        String codigo = asientosDisponiblesBueLim[0][0];
        String numeroAsiento = asientosDisponiblesBueLim[0][1];
        String fechaSalida = asientosDisponiblesBueLim[0][2];
        String horaSalida = asientosDisponiblesBueLim[0][3];
        PrecioAsiento precio = new PrecioAsiento(Double.parseDouble(asientosDisponiblesBueLim[0][4]));
        ClaseAsiento clase = new ClaseAsiento(asientosDisponiblesBueLim[0][5]);
        UbicacionAsiento ubicacion = new UbicacionAsiento(asientosDisponiblesBueLim[0][6]);
        CodigoAsiento codigoVuelo = new CodigoAsiento(codigo,numeroAsiento);
        AsientoDTO asientoUno = new AsientoDTO(new FechaFormatoLatinoamericano(fechaSalida),new Hora(horaSalida),clase,codigoVuelo,null,precio,ubicacion);
        
        String codigoDos = asientosDisponiblesBueLim[1][0];
        String numeroAsientoDos = asientosDisponiblesBueLim[1][1];
        String fechaSalidaDos = asientosDisponiblesBueLim[1][2];
        String horaSalidaDos = asientosDisponiblesBueLim[1][3];
        PrecioAsiento precioDos = new PrecioAsiento(Double.parseDouble(asientosDisponiblesBueLim[1][4]));
        ClaseAsiento claseDos = new ClaseAsiento(asientosDisponiblesBueLim[1][5]);
        UbicacionAsiento ubicacionDos = new UbicacionAsiento(asientosDisponiblesBueLim[1][6]);
        CodigoAsiento codigoVueloDos = new CodigoAsiento(codigoDos,numeroAsientoDos);
        AsientoDTO asientoDos = new AsientoDTO(new FechaFormatoLatinoamericano(fechaSalidaDos),new Hora(horaSalidaDos),claseDos,codigoVueloDos,null,precioDos,ubicacionDos);
        
        asientosBueLim.add(asientoUno);
        asientosBueLim.add(asientoDos);
        
        LinkedList<Asiento> asientosRioLa = new LinkedList<>();
        
        String codigoUnoBis = asientosDisponiblesRioLA[0][0];
        String numeroAsientoUnoBis= asientosDisponiblesRioLA[0][1];
        String fechaSalidaUnoBis = asientosDisponiblesRioLA[0][2];
        String horaSalidaUnoBis = asientosDisponiblesRioLA[0][3];
        PrecioAsiento precioUnoBis = new PrecioAsiento(Double.parseDouble(asientosDisponiblesRioLA[0][4]));
        ClaseAsiento claseUnoBis = new ClaseAsiento(asientosDisponiblesRioLA[0][5]);
        UbicacionAsiento ubicacionUnoBis = new UbicacionAsiento(asientosDisponiblesRioLA[0][6]);
        CodigoAsiento codigoVueloUnoBis = new CodigoAsiento(codigoUnoBis,numeroAsientoUnoBis);
        AsientoDTO asientoUnoBis = new AsientoDTO(new FechaFormatoLatinoamericano(fechaSalidaUnoBis),new Hora(horaSalidaUnoBis),claseUnoBis,codigoVueloUnoBis,null,precioUnoBis,ubicacionUnoBis);
        
        String codigoDosBis = asientosDisponiblesRioLA[1][0];
        String numeroAsientoDosBis  = asientosDisponiblesRioLA[1][1];
        String fechaSalidaDosBis  = asientosDisponiblesRioLA[1][2];
        String horaSalidaDosBis  = asientosDisponiblesRioLA[1][3];
        PrecioAsiento precioDosBis  = new PrecioAsiento(Double.parseDouble(asientosDisponiblesRioLA[1][4]));
        ClaseAsiento claseDosBis  = new ClaseAsiento(asientosDisponiblesRioLA[1][5]);
        UbicacionAsiento ubicacionDosBis  = new UbicacionAsiento(asientosDisponiblesRioLA[1][6]);
        CodigoAsiento codigoVueloDosBis  = new CodigoAsiento(codigoDosBis ,numeroAsientoDosBis);
        AsientoDTO asientoDosBis  = new AsientoDTO(new FechaFormatoLatinoamericano(fechaSalidaDosBis ),new Hora(horaSalidaDosBis ),claseDosBis ,codigoVueloDosBis ,null,precioDosBis ,ubicacionDosBis);
        
        asientosRioLa.add(asientoUnoBis);
        asientosRioLa.add(asientoDosBis);
        
        Mockito.when(aerolineaOc.devolverAsiento(asientosDisponiblesBueLim)).thenReturn(asientosBueLim);
        
        Mockito.when(aerolineaOc.devolverAsiento(asientosDisponiblesRioLA)).thenReturn(asientosRioLa);
        
        Mockito.when(aerolineaOc.asientosDisponibles(vueloBsAsLima,"Origen")).thenReturn(asientosDisponiblesBueLim);  
        
        Mockito.when(aerolineaOc.asientosDisponibles(vueloRioLosAngeles,"OrigenYDestino")).thenReturn(asientosDisponiblesRioLA);
    }
    
        @Test
    public void reservarAsiento_reservaExitosaDeUsuarioAUnAsiento() throws CodigoAsientoException{
        String codigoAsiento = codigoUno.getCodigo();
        userVip.reservarAsiento(codigoAsiento, aerolineaLanchita);
        Assert.assertTrue("No reservo el asiento", userVip.asientoReservadoPorMi(asientoUno));
        Assert.assertTrue("Estado asiento no es reservado",asientoUno.getEstado().estaReservado());
    }
    
    @Test
    public void reservarAsiento_usuarioReservaVariosAsientosExitosamente() throws CodigoAsientoException{
        String codigoAsiento = codigoUno.getCodigo();
        String codigoAsiento2 = codigoDos.getCodigo();
        userVip.reservarAsiento(codigoAsiento, aerolineaLanchita);
        userVip.reservarAsiento(codigoAsiento2, aerolineaLanchita);
        Assert.assertTrue("No reservo el asiento", userVip.asientoReservadoPorMi(asientoUno)); 
        Assert.assertTrue("No reservo el asiento", userVip.asientoReservadoPorMi(asientoDos)); 
        Assert.assertTrue("Estado asiento no es reservado",asientoUno.getEstado().estaReservado());
        Assert.assertTrue("Estado asiento no es reservado",asientoDos.getEstado().estaReservado());
    }
    
    @Test(expected = AsientoNoDisponibleException.class)
    public void reservarAsiento_UsuarioNoPuedeReservarAsientoReservadoPorOtro() throws CodigoAsientoException{
        String codigoAsiento = codigoUno.getCodigo();
        userVip.reservarAsiento(codigoAsiento, aerolineaLanchita);
        userEstandar.reservarAsiento(codigoAsiento, aerolineaLanchita);
    }
    
    @Test(expected = AsientoReservadoException.class)
    public void reservarAsiento_UsuarioNoPuedeComprarAsientoReservadoPorOtro() throws CodigoAsientoException, AsientoReservadoException{
        String codigoAsiento = codigoUno.getCodigo();
        userVip.reservarAsiento(codigoAsiento, aerolineaLanchita);
        userEstandar.comprarAsiento(codigoAsiento, aerolineaLanchita);
    }
    
    @Test(expected = AsientoNoDisponibleException.class)
    public void reservarAsiento_UsuarioNoPuedeReservarAsientoCompradoPorOtro() throws CodigoAsientoException, AsientoReservadoException{
        String codigoAsiento = codigoUno.getCodigo();
        userVip.comprarAsiento(codigoAsiento, aerolineaLanchita);
        userEstandar.reservarAsiento(codigoAsiento, aerolineaLanchita);
    }
    
    @Test
    public void expiroReserva_UsuarioReservaPeroSuReservaExpiraYOtroUsuarioReservaSuAsiento() throws CodigoAsientoException{
        String codigoAsiento = codigoUno.getCodigo();
        userVip.reservarAsiento(codigoAsiento, aerolineaLanchita);
        aerolineaLanchita.expiroReserva(asientoUno, userVip);
        userEstandar.reservarAsiento(codigoAsiento, aerolineaLanchita);
        Assert.assertTrue("Esta en las reservas el asiento", !userVip.asientoReservadoPorMi(asientoUno)); 
        Assert.assertTrue("No reservo el asiento", userEstandar.asientoReservadoPorMi(asientoUno));
        Assert.assertTrue("Estado asiento no es reservado",asientoUno.getEstado().estaReservado());
    }
   
    @Test
    public void expiroReserva_UsuarioPierdeLaReservaYElAsientoEstaDisponible() throws CodigoAsientoException{
        String codigoAsiento = codigoUno.getCodigo();
        userVip.reservarAsiento(codigoAsiento, aerolineaLanchita);
        aerolineaLanchita.expiroReserva(asientoUno, userVip);
        Assert.assertTrue("Esta en las reservas el asiento", !userVip.asientoReservadoPorMi(asientoUno));
        Assert.assertTrue("Estado asiento es reservado",asientoUno.getEstado().estaDisponible());
    }
    
    @Test
    public void sobrereservarAsiento_UsuarioSobrereservaAsientoReservado() throws CodigoAsientoException, AsientoReservadoException{
        String codigoAsiento = codigoUno.getCodigo();
        userVip.reservarAsiento(codigoAsiento, aerolineaLanchita);
        aterrizar.sobrereservarAsiento(codigoAsiento, userEstandar);
        Assert.assertTrue("No reservo el asiento", userVip.asientoReservadoPorMi(asientoUno));
        Assert.assertEquals("No sobrereservo el asiento", userEstandar, AterrizarV2.usuarioSobrereserva(codigoAsiento));
        Assert.assertTrue("Estado asiento no es sobrereservado",asientoUno.getEstado().estaSobrereservado());
    }
    
    @Test
    public void sobrereservarAsiento_UsuarioPierdeReservaYPasaAlUsuarioQueSobrereservo() throws CodigoAsientoException, AsientoReservadoException{
        String codigoAsiento = codigoUno.getCodigo();
        userVip.reservarAsiento(codigoAsiento, aerolineaLanchita);
        aterrizar.sobrereservarAsiento(codigoAsiento, userEstandar);
        aerolineaLanchita.expiroReserva(asientoUno, userVip);
        Assert.assertTrue("Esta en las reservas el asiento", !userVip.asientoReservadoPorMi(asientoUno));
        Assert.assertTrue("No reservo el asiento", userEstandar.asientoReservadoPorMi(asientoUno));
        Assert.assertTrue("Estado asiento no es reservado",asientoUno.getEstado().estaReservado());
    }
}
