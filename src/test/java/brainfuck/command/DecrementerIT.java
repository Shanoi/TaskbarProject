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
public class DecrementerIT {

    public DecrementerIT() {
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
     * Test of execute method, of class Decrementer.
     */
    @Test
    public void testExecute() {
        ComputationalModel cm = new ComputationalModel();
        cm.init();
        Command incr = new Incrementer();
        Command decr = new Decrementer();
        incr.execute();
        decr.execute();
        assertEquals(cm.getCurrentCaseValue(), 0);

    }
}
