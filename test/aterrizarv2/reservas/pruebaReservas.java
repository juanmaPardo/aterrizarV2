package aterrizarv2.reservas;

import aterrizarv2.AterrizarV2;
import aterrizarv2.aerolinea.aerolineaLanchita.AerolineaLanchita;
import aterrizarv2.aerolinea.aerolineaLanchita.AerolineaLanchitaI;
import aterrizarv2.aerolinea.aerolineaOceanic.TipoPedidoInvalidaException;
import aterrizarv2.aerolinea.exceptionesAerolinea.DatosVueloIncorrectoException;
import aterrizarv2.asientos.Asiento;
import aterrizarv2.asientos.ClaseAsiento;
import aterrizarv2.asientos.CodigoAsiento;
import aterrizarv2.asientos.EstadoAsiento;
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
import aterrizarv2.fecha.excepcionesFecha.FechaNoValidaException;
import aterrizarv2.fecha.excepcionesFecha.FormatoFechaIncorrectoException;
import aterrizarv2.hora.Hora;
import aterrizarv2.hora.excepcionesHora.FormatoHoraIncorrectoException;
import aterrizarv2.hora.excepcionesHora.HoraInvalidaException;
import aterrizarv2.usuarios.DniInvalidoException;
import aterrizarv2.usuarios.UsuarioNoPaga;
import aterrizarv2.usuarios.UsuarioPaga;
import aterrizarv2.vuelos.Vuelo;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.Before;
import org.mockito.Mockito;


public class pruebaReservas {
    Vuelo vueloRioLima;
    Vuelo vueloBsAsMadrid;
    AerolineaLanchita lanchitaNoMockeada;
    UsuarioPaga userVip;
    UsuarioNoPaga userEstandar;
    FechaFlexible fechaSalida1Junio2018;
    FechaFlexible fechaSalida13Noviembre2018;
    FechaFlexible fechaLlegada2Junio2018;
    FechaFlexible fechaLlegada13Noviembre2018;
    AerolineaLanchitaI lanchitaMockeada;
    AterrizarV2 aterrizar;
    private CodigoAsiento codigoUno;
    private Asiento asientoUno;
    private CodigoAsiento codigoDos;
    private Asiento asientoDos;
    private CodigoAsiento codigoTres;
    private Asiento asientoTres;
    private CodigoAsiento codigoCuatro;
    private Asiento asientoCuatro;
    
    
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
        
        userVip = new UsuarioPaga("Juan", "Carlos",41565456 ,320 );
        userVip.efectuarCompra(200000);
        userEstandar = new UsuarioNoPaga("Pedro", "Benitez", 31256748);
        
        aterrizar = new AterrizarV2();
        
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
        
        codigoUno = new CodigoAsiento(asientosDisponiblesBueMad[0][0]);
        PrecioAsiento precio = new PrecioAsiento(Double.parseDouble(asientosDisponiblesBueMad[0][1]));
        ClaseAsiento clase = new ClaseAsiento(asientosDisponiblesBueMad[0][2]);
        UbicacionAsiento ubicacion = new UbicacionAsiento(asientosDisponiblesBueMad[0][3]);
        EstadoAsiento estado = new EstadoAsiento(asientosDisponiblesBueMad[0][4]);
        asientoUno = new Asiento(clase, codigoUno, estado, precio, ubicacion);
        
        codigoDos = new CodigoAsiento(asientosDisponiblesBueMad[1][0]);
        PrecioAsiento precioDos = new PrecioAsiento(Double.parseDouble(asientosDisponiblesBueMad[1][1]));
        ClaseAsiento claseDos = new ClaseAsiento(asientosDisponiblesBueMad[1][2]);
        UbicacionAsiento ubicacionDos = new UbicacionAsiento(asientosDisponiblesBueMad[1][3]);
        EstadoAsiento estadoDos = new EstadoAsiento(asientosDisponiblesBueMad[1][4]);
        asientoDos = new Asiento(claseDos, codigoDos, estadoDos, precioDos, ubicacionDos);
        
        codigoTres = new CodigoAsiento(asientosDisponiblesRioLim[0][0]);
        PrecioAsiento precioTres = new PrecioAsiento(Double.parseDouble(asientosDisponiblesRioLim[0][1]));
        ClaseAsiento claseTres = new ClaseAsiento(asientosDisponiblesRioLim[0][2]);
        UbicacionAsiento ubicacionTres = new UbicacionAsiento(asientosDisponiblesRioLim[0][3]);
        EstadoAsiento estadoTres = new EstadoAsiento(asientosDisponiblesRioLim[0][4]);
        asientoTres = new Asiento(claseTres, codigoTres, estadoTres, precioTres, ubicacionTres);
        
        codigoCuatro = new CodigoAsiento(asientosDisponiblesRioLim[1][0]);
        PrecioAsiento precioCuatro = new PrecioAsiento(Double.parseDouble(asientosDisponiblesRioLim[0][1]));
        ClaseAsiento claseCuatro = new ClaseAsiento(asientosDisponiblesRioLim[1][2]);
        UbicacionAsiento ubicacionCuatro = new UbicacionAsiento(asientosDisponiblesRioLim[1][3]);
        EstadoAsiento estadoCuatro = new EstadoAsiento(asientosDisponiblesRioLim[1][4]);
        asientoCuatro = new Asiento(claseCuatro, codigoCuatro, estadoCuatro, precioCuatro, ubicacionCuatro);
         
        aterrizar.agregarAerolinea(lanchitaNoMockeada);
         
    }
    
    @Test
    public void reservarAsiento_reservaExitosaDeUsuarioAUnAsiento() throws CodigoAsientoException{
        String codigoAsiento = codigoUno.getCodigo();
        userVip.reservarAsiento(codigoAsiento, lanchitaNoMockeada);
        Assert.assertTrue("No reservo el asiento", userVip.asientoReservadoPorMi(asientoUno));
        Assert.assertTrue("Estado asiento no es reservado",asientoUno.getEstado().estaReservado());
    }
    
    @Test
    public void reservarAsiento_usuarioReservaVariosAsientosExitosamente() throws CodigoAsientoException{
        String codigoAsiento = codigoUno.getCodigo();
        String codigoAsiento2 = codigoDos.getCodigo();
        userVip.reservarAsiento(codigoAsiento, lanchitaNoMockeada);
        userVip.reservarAsiento(codigoAsiento2, lanchitaNoMockeada);
        Assert.assertTrue("No reservo el asiento", userVip.asientoReservadoPorMi(asientoUno)); 
        Assert.assertTrue("No reservo el asiento", userVip.asientoReservadoPorMi(asientoDos)); 
        Assert.assertTrue("Estado asiento no es reservado",asientoUno.getEstado().estaReservado());
        Assert.assertTrue("Estado asiento no es reservado",asientoDos.getEstado().estaReservado());
    }
    
    @Test(expected = AsientoNoDisponibleException.class)
    public void reservarAsiento_UsuarioNoPuedeReservarAsientoReservadoPorOtro() throws CodigoAsientoException{
        String codigoAsiento = codigoUno.getCodigo();
        userVip.reservarAsiento(codigoAsiento, lanchitaNoMockeada);
        userEstandar.reservarAsiento(codigoAsiento, lanchitaNoMockeada);
    }
    
    @Test(expected = AsientoReservadoException.class)
    public void reservarAsiento_UsuarioNoPuedeComprarAsientoReservadoPorOtro() throws CodigoAsientoException, AsientoReservadoException{
        String codigoAsiento = codigoUno.getCodigo();
        userVip.reservarAsiento(codigoAsiento, lanchitaNoMockeada);
        userEstandar.comprarAsiento(codigoAsiento, lanchitaNoMockeada);
    }
    
    @Test(expected = AsientoNoDisponibleException.class)
    public void reservarAsiento_UsuarioNoPuedeReservarAsientoCompradoPorOtro() throws CodigoAsientoException, AsientoReservadoException{
        String codigoAsiento = codigoUno.getCodigo();
        userVip.comprarAsiento(codigoAsiento, lanchitaNoMockeada);
        userEstandar.reservarAsiento(codigoAsiento, lanchitaNoMockeada);
    }
    
    @Test
    public void expiroReserva_UsuarioReservaPeroSuReservaExpiraYOtroUsuarioReservaSuAsiento() throws CodigoAsientoException{
        String codigoAsiento = codigoUno.getCodigo();
        userVip.reservarAsiento(codigoAsiento, lanchitaNoMockeada);
        lanchitaNoMockeada.expiroReserva(asientoUno, userVip);
        userEstandar.reservarAsiento(codigoAsiento, lanchitaNoMockeada);
        Assert.assertTrue("Esta en las reservas el asiento", !userVip.asientoReservadoPorMi(asientoUno)); 
        Assert.assertTrue("No reservo el asiento", userEstandar.asientoReservadoPorMi(asientoUno));
        Assert.assertTrue("Estado asiento no es reservado",asientoUno.getEstado().estaReservado());
    }
    
    @Test
    public void expiroReserva_UsuarioPierdeLaReservaYElAsientoEstaDisponible() throws CodigoAsientoException{
        String codigoAsiento = codigoUno.getCodigo();
        userVip.reservarAsiento(codigoAsiento, lanchitaNoMockeada);
        lanchitaNoMockeada.expiroReserva(asientoUno, userVip);
        Assert.assertTrue("Esta en las reservas el asiento", !userVip.asientoReservadoPorMi(asientoUno));
        Assert.assertTrue("Estado asiento es reservado",asientoUno.getEstado().estaDisponible());
    }
    
    @Test
    public void sobrereservarAsiento_UsuarioSobrereservaAsientoReservado() throws CodigoAsientoException, AsientoReservadoException{
        String codigoAsiento = codigoUno.getCodigo();
        userVip.reservarAsiento(codigoAsiento, lanchitaNoMockeada);
        aterrizar.sobrereservarAsiento(codigoAsiento, userEstandar);
        Assert.assertTrue("No reservo el asiento", userVip.asientoReservadoPorMi(asientoUno));
        Assert.assertEquals("No sobrereservo el asiento", userEstandar, AterrizarV2.usuarioSobrereserva(codigoAsiento));
        Assert.assertTrue("Estado asiento no es sobrereservado",asientoUno.getEstado().estaSobrereservado());
    }
    
    @Test
    public void sobrereservarAsiento_UsuarioPierdeReservaYPasaAlUsuarioQueSobrereservo() throws CodigoAsientoException, AsientoReservadoException{
        String codigoAsiento = codigoUno.getCodigo();
        userVip.reservarAsiento(codigoAsiento, lanchitaNoMockeada);
        aterrizar.sobrereservarAsiento(codigoAsiento, userEstandar);
        lanchitaNoMockeada.expiroReserva(asientoUno, userVip);
        Assert.assertTrue("Esta en las reservas el asiento", !userVip.asientoReservadoPorMi(asientoUno));
        Assert.assertTrue("No reservo el asiento", userEstandar.asientoReservadoPorMi(asientoUno));
        Assert.assertTrue("Estado asiento no es reservado",asientoUno.getEstado().estaReservado());
    }
    
    @Test
    public void comprar_UsuarioCompraAsientoYElUsuarioQueSobrereservoLoPierde() throws CodigoAsientoException, AsientoReservadoException{
        String codigoAsiento = codigoUno.getCodigo();
        userVip.reservarAsiento(codigoAsiento, lanchitaNoMockeada);
        aterrizar.sobrereservarAsiento(codigoAsiento, userEstandar);
        userVip.comprarAsiento(codigoAsiento, lanchitaNoMockeada);
        Assert.assertTrue("Esta reservado el asiento", !userVip.asientoReservadoPorMi(asientoUno));
        Assert.assertNotSame("Esta en sobrereservado por el usuario", userEstandar, AterrizarV2.usuarioSobrereserva(codigoAsiento));
        Assert.assertTrue("Estado asiento no es reservado",asientoUno.getEstado().asientoVendido());
    }
    
}
