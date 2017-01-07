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
public class ComputationalModelTest {
    
    public ComputationalModelTest() {
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

    @Test
    public void initTest(){
        ComputationalModel cm=new ComputationalModel();
        cm.init();
        assertEquals(cm.getCurrentCaseValue(),0);
    }

    @Test
    public void setCurrentCaseValueTest(){
        ComputationalModel cm=new ComputationalModel();
        cm.init();
        cm.setCurrentCaseValue((byte)100);
        assertEquals(cm.getCurrentCaseValue(),100);
    }
    @Test
    public void setCurrentIndiceTest(){
        ComputationalModel cm=new ComputationalModel();
        cm.init();
        cm.setCurrentIndice(4);
        assertEquals(cm.getCurrentIndice(),4);
    }

}
