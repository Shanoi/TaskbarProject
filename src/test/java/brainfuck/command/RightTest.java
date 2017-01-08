package brainfuck.command;

import brainfuck.lecture.Monitor;
import brainfuck.memory.ComputationalModel;
import org.junit.*;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import static org.junit.Assert.*;

/**
 *
 * @author TeamTaskbar
 */
public class RightTest {
    
    public RightTest() {
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
     * Test of execute method, of class Right.
     */
    @Test
    public void testExecute() {
        ComputationalModel cm=new ComputationalModel();
        cm.init();
        Command command=new Right();
        for(int i=0;i<30000;i++)command.execute();
        exit.expectSystemExit();
        command.execute();

    }
    @Test
    public void Reset(){
        ComputationalModel cm=new ComputationalModel();
        cm.Reset();
        Monitor monitor=new Monitor();
        monitor.Reset();
    }
    
}
