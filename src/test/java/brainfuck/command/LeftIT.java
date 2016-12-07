package brainfuck.command;

import brainfuck.memory.ComputationalModel;
import org.junit.*;

import static org.junit.Assert.*;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

/**
 *
 * @author TeamTaskbar
 */
public class LeftIT {
    
    public LeftIT() {
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
     * Test of execute method, of class Left.
     */
    @Test
    public void testExecute() {
        ComputationalModel cm=new ComputationalModel();
        cm.init();
        Command command=new Left();
        exit.expectSystemExit();
        command.execute();
    }
    
}
