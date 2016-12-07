/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brainfuck.lecture;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Olivier
 */
public class MacroIT {
    
    public MacroIT() {
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
     * Test of getNom method, of class Macro.
     */
    @Test
    public void testGetNom() {
        System.out.println("getNom");
        Macro instance = new Macro();
        String expResult = "";
        String result = instance.getNom();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isParam method, of class Macro.
     */
    @Test
    public void testIsParam() {
        System.out.println("isParam");
        String param = "";
        Macro instance = new Macro();
        boolean expResult = false;
        boolean result = instance.isParam(param);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getnbParam method, of class Macro.
     */
    @Test
    public void testGetnbParam() {
        System.out.println("getnbParam");
        Macro instance = new Macro();
        int expResult = 0;
        int result = instance.getnbParam();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumParam method, of class Macro.
     */
    @Test
    public void testGetNumParam() {
        System.out.println("getNumParam");
        String param = "";
        Macro instance = new Macro();
        int expResult = 0;
        int result = instance.getNumParam(param);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCommands method, of class Macro.
     */
    @Test
    public void testGetCommands() {
        System.out.println("getCommands");
        Macro instance = new Macro();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.getCommands();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fillCommands method, of class Macro.
     */
    @Test
    public void testFillCommands() {
        System.out.println("fillCommands");
        String command = "";
        Macro instance = new Macro();
        instance.fillCommands(command);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
