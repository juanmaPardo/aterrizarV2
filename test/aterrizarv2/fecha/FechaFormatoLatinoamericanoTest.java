package aterrizarv2.fecha;

import aterrizarv2.fecha.excepcionesFecha.FechaNoValidaException;
import aterrizarv2.fecha.excepcionesFecha.FormatoFechaIncorrectoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juanm
 */
public class FechaFormatoLatinoamericanoTest {
    
    public FechaFormatoLatinoamericanoTest() {
    }
    
    @Test
    public void seCreaExitosamenteUnaFechaFormatoLatinoamericanoYFuncionanGetters(){
        try {
            FechaFormatoLatinoamericano ffl = new FechaFormatoLatinoamericano("12/01/2012");
            Assert.assertEquals(12, ffl.getDia());
            Assert.assertEquals(1, ffl.getMes());
            Assert.assertEquals(2012, ffl.getAnio());
        } catch (FormatoFechaIncorrectoException | FechaNoValidaException ex) {
            Assert.assertEquals(false, true);
        }     
    }
    
    @Test(expected = FormatoFechaIncorrectoException.class)
    public void lanzaExceptionYaQueElSeparadorEsIncorrecto() throws FormatoFechaIncorrectoException, FechaNoValidaException{
        FechaFormatoLatinoamericano ffl = new FechaFormatoLatinoamericano("12-01-2012");
    }
    
    @Test(expected = FormatoFechaIncorrectoException.class)
    public void seIntentaCrearUnaFechaLatamConFormatoIncorrentoPorLongitudFechaExcesivaYLanzaExcepcion() throws FormatoFechaIncorrectoException, FechaNoValidaException{
        FechaFormatoLatinoamericano ffl = new FechaFormatoLatinoamericano("12/01/2012232");
    }
    
    @Test(expected = FormatoFechaIncorrectoException.class)
    public void seIntentaCrearUnaFechaLatamConFormatoIncorrentoPorqueEsIsoYLanzaExcepcion() throws FormatoFechaIncorrectoException, FechaNoValidaException{
        FechaFormatoLatinoamericano ffl = new FechaFormatoLatinoamericano("2012/12/13");
    }
    
    @Test(expected = FormatoFechaIncorrectoException.class)
    public void seIntentaCrearUnaFechaLatamConFormatoIncorrentoPorContenidoNoNumericoYLanzaExcepcion() throws FormatoFechaIncorrectoException, FechaNoValidaException{
        FechaFormatoLatinoamericano ffl = new FechaFormatoLatinoamericano("12/01/2j12");
    }
    
    @Test(expected = FechaNoValidaException.class)
    public void seIntentaCrearUnaFechaLatamNoValidaYLanzaExcepcion() throws FormatoFechaIncorrectoException, FechaNoValidaException{
        FechaFormatoLatinoamericano ffl = new FechaFormatoLatinoamericano("52/01/2012");
    }
}

