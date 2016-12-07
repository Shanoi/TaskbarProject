package brainfuck.command;

import brainfuck.memory.ComputationalModel;
import org.junit.*;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

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
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    /**
     * Test of execute method, of class Decrementer.
     */
    @Test
    public void testExecute() {
        ComputationalModel cm = new ComputationalModel();
        cm.init();
        Command decr = new Decrementer();
        exit.expectSystemExit();
        decr.execute();

    }
}
