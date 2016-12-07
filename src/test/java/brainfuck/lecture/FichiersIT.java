/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brainfuck.lecture;

import brainfuck.command.EnumCommands;
import brainfuck.memory.ComputationalModel;
import java.util.List;
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
public class FichiersIT {
    
    public FichiersIT() {
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
     * Test of Read method, of class Fichiers.
     */
    @Test
    public void testRead() throws Exception {
        System.out.println("Read");
        Fichiers instance = null;
        instance.Read();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Encod method, of class Fichiers.
     */
    @Test
    public void testEncod() {
        System.out.println("Encod");
        Fichiers instance = null;
        instance.Encod();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNbI method, of class Fichiers.
     */
    @Test
    public void testGetNbI() {
        System.out.println("getNbI");
        Fichiers instance = null;
        int expResult = 0;
        int result = instance.getNbI();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInstructions method, of class Fichiers.
     */
    @Test
    public void testGetInstructions() {
        System.out.println("getInstructions");
        Fichiers instance = null;
        List<EnumCommands> expResult = null;
        List<EnumCommands> result = instance.getInstructions();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCm method, of class Fichiers.
     */
    @Test
    public void testGetCm() {
        System.out.println("getCm");
        Fichiers instance = null;
        ComputationalModel expResult = null;
        ComputationalModel result = instance.getCm();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of jumpAssoc method, of class Fichiers.
     */
    @Test
    public void testJumpAssoc() {
        System.out.println("jumpAssoc");
        int i = 0;
        Fichiers instance = null;
        int expResult = 0;
        int result = instance.jumpAssoc(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of backAssoc method, of class Fichiers.
     */
    @Test
    public void testBackAssoc() {
        System.out.println("backAssoc");
        int i = 0;
        Fichiers instance = null;
        int expResult = 0;
        int result = instance.backAssoc(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
