/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aterrizarv2.busquedas;

import aterrizarv2.vuelos.AsientoVueloFullData;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juan
 */
public class BusquedaTest {
    
    public BusquedaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of asientosCumplenRequisitoBusqueda method, of class Busqueda.
     */
    @Test
    public void testAsientosCumplenRequisitoBusqueda() {
        System.out.println("asientosCumplenRequisitoBusqueda");
        List<AsientoVueloFullData> disponibles = null;
        Busqueda instance = null;
        List<AsientoVueloFullData> expResult = null;
        List<AsientoVueloFullData> result = instance.asientosCumplenRequisitoBusqueda(disponibles);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
