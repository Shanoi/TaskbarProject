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
public class ComputationalModelIT {
    
    public ComputationalModelIT() {
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
     * Test of init method, of class ComputationalModel.
     */
    @Test
    public void testInit() {
        System.out.println("init");
        ComputationalModel instance = new ComputationalModel();
        instance.init();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMemorySize method, of class ComputationalModel.
     */
    @Test
    public void testGetMemorySize() {
        System.out.println("getMemorySize");
        ComputationalModel instance = new ComputationalModel();
        int expResult = 0;
        int result = instance.getMemorySize();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentCaseValue method, of class ComputationalModel.
     */
    @Test
    public void testGetCurrentCaseValue() {
        System.out.println("getCurrentCaseValue");
        ComputationalModel instance = new ComputationalModel();
        int expResult = 0;
        int result = instance.getCurrentCaseValue();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCurrentCaseValue method, of class ComputationalModel.
     */
    @Test
    public void testSetCurrentCaseValue() {
        System.out.println("setCurrentCaseValue");
        byte n = 0;
        ComputationalModel instance = new ComputationalModel();
        instance.setCurrentCaseValue(n);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentIndice method, of class ComputationalModel.
     */
    @Test
    public void testGetCurrentIndice() {
        System.out.println("getCurrentIndice");
        ComputationalModel instance = new ComputationalModel();
        int expResult = 0;
        int result = instance.getCurrentIndice();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCurrentIndice method, of class ComputationalModel.
     */
    @Test
    public void testSetCurrentIndice() {
        System.out.println("setCurrentIndice");
        int p = 0;
        ComputationalModel instance = new ComputationalModel();
        instance.setCurrentIndice(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getI method, of class ComputationalModel.
     */
    @Test
    public void testGetI() {
        System.out.println("getI");
        ComputationalModel instance = new ComputationalModel();
        int expResult = 0;
        int result = instance.getI();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setI method, of class ComputationalModel.
     */
    @Test
    public void testSetI() {
        System.out.println("setI");
        int d = 0;
        ComputationalModel instance = new ComputationalModel();
        instance.setI(d);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of affichememoire method, of class ComputationalModel.
     */
    @Test
    public void testAffichememoire() {
        System.out.println("affichememoire");
        ComputationalModel.affichememoire();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class ComputationalModel.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ComputationalModel instance = new ComputationalModel();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
