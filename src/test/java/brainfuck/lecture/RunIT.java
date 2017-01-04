package brainfuck.lecture;

import brainfuck.memory.ComputationalModel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 *
 * @author TeamTaskbar
 */
public class RunIT {
    
    public RunIT() {
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
    public void testRead() throws Exception{
        String path=new File("").getAbsolutePath();
        System.out.println(path);
        Run run=new Run(path+"/src/test/java/brainfuck/lecture/test.txt");
        run.load();
        assertEquals(run.getFichier().list.size(),1);
    }
}
