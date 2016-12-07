package brainfuck.lecture;

import brainfuck.memory.ComputationalModel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author TeamTaskbar
 */
public class RunIT {
    
    public RunIT() {
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
     * Test of getCm method, of class Run.
     */
    @Test
    public void testGetCm() {
        System.out.println("getCm");
        Run instance = null;
        ComputationalModel expResult = null;
        ComputationalModel result = instance.getCm();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of IncrEXEC_MOVE method, of class Run.
     */
    @Test
    public void testIncrEXEC_MOVE() {
        System.out.println("IncrEXEC_MOVE");
        Run.IncrEXEC_MOVE();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of IncrDATA_MOVE method, of class Run.
     */
    @Test
    public void testIncrDATA_MOVE() {
        System.out.println("IncrDATA_MOVE");
        Run.IncrDATA_MOVE();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of IncrDATA_WRITE method, of class Run.
     */
    @Test
    public void testIncrDATA_WRITE() {
        System.out.println("IncrDATA_WRITE");
        Run.IncrDATA_WRITE();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of IncrDATA_READ method, of class Run.
     */
    @Test
    public void testIncrDATA_READ() {
        System.out.println("IncrDATA_READ");
        Run.IncrDATA_READ();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of load method, of class Run.
     */
    @Test
    public void testLoad() throws Exception {
        System.out.println("load");
        Run instance = null;
        instance.load();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of execute method, of class Run.
     */
    @Test
    public void testExecute() throws Exception {
        System.out.println("execute");
        Run instance = null;
        instance.execute();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of afficheStats method, of class Run.
     */
    @Test
    public void testAfficheStats() {
        System.out.println("afficheStats");
        Run instance = null;
        instance.afficheStats();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTrace method, of class Run.
     */
    @Test
    public void testSetTrace() {
        System.out.println("setTrace");
        boolean trace1 = false;
        Run instance = null;
        instance.setTrace(trace1);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFichier method, of class Run.
     */
    @Test
    public void testGetFichier() {
        System.out.println("getFichier");
        Run instance = null;
        Fichiers expResult = null;
        Fichiers result = instance.getFichier();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
