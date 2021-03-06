package brainfuck.command;

import brainfuck.lecture.Fichiers;
import brainfuck.lecture.Monitor;
import brainfuck.memory.ComputationalModel;
import org.junit.*;

import static org.junit.Assert.*;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;


/**
 *
 * @author TeamTaskbar
 */
public class IncrementerTest{
    
    public IncrementerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ComputationalModel cmReset=new ComputationalModel();
        Fichiers fileReset=new Fichiers("");
        Monitor monitorReset=new Monitor();
        cmReset.Reset();
        fileReset.Reset();
        monitorReset.Reset();
    }
    
    @After
    public void tearDown() {
        ComputationalModel cmReset=new ComputationalModel();
        Fichiers fileReset=new Fichiers("");
        Monitor monitorReset=new Monitor();
        cmReset.Reset();
        fileReset.Reset();
        monitorReset.Reset();
    }
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    /**
     * Test of execute method, of class Incrementer.
     */
    @Test
    public void testExecute() {
        ComputationalModel cm=new ComputationalModel();
        cm.init();
        Command command=new Incrementer();
        command.execute();
        assertEquals(cm.getCurrentCaseValue(),1);
    }

    @Test
    public void TestMaxIncr(){
        ComputationalModel cm=new ComputationalModel();
        cm.init();
        Command command=new Incrementer();
        for(int i=0;i<255;i++)command.execute();
        exit.expectSystemExit();
        command.execute();

    }


    
}
