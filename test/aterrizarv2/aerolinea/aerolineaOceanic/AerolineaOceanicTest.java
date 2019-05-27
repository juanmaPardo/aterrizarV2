
package aterrizarv2.aerolinea.aerolineaOceanic;

import aterrizarv2.vuelos.AsientoDTO;
import java.util.LinkedList;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AerolineaOceanicTest {
    
    public AerolineaOceanicTest() {
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
