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
public class IncrementerIT {
    
    public IncrementerIT() {
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
        command.execute();

    }


    
}
