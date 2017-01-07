package brainfuck.command;

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
public class AfficheMemoryIT {
    
    public AfficheMemoryIT() {
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
     * Test of execute method, of class AfficheMemory.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        AfficheMemory instance = new AfficheMemory();
        instance.execute();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
