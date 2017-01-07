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
public class SyntaxeIT {
    
    public SyntaxeIT() {
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
     * Test of isShort method, of class Syntaxe.
     */
    @Test
    public void testIsShort() {
        System.out.println("isShort");
        String line = "";
        boolean expResult = false;
        boolean result = Syntaxe.isShort(line);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
