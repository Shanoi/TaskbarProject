/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brainfuck.memory;

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
public class InterpreterIT {
    
    public InterpreterIT() {
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
     * Test of lauchInterpreter method, of class Interpreter.
     */
    @Test
    public void testLauchInterpreter() throws Exception {
        System.out.println("lauchInterpreter");
        Interpreter instance = null;
        instance.lauchInterpreter();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFileIn method, of class Interpreter.
     */
    @Test
    public void testGetFileIn() {
        System.out.println("getFileIn");
        String expResult = "";
        String result = Interpreter.getFileIn();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFileOut method, of class Interpreter.
     */
    @Test
    public void testGetFileOut() {
        System.out.println("getFileOut");
        String expResult = "";
        String result = Interpreter.getFileOut();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
