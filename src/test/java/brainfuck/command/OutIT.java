package brainfuck.command;

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
public class OutIT {
    
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
     * Test of execute method, of class Out.
     */
    @Test
    public void testExecute() {
        ComputationalModel cm=new ComputationalModel();

        System.out.println("execute");
        Out instance = new Out();
        instance.execute();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");


    }
    
}
