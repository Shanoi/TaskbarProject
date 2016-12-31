package brainfuck.memory;

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
public class LauncherIT {
    
    public LauncherIT() {
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
        Launcher instance = null;
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
        String result = Launcher.getFileIn();
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
        String result = Launcher.getFileOut();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
