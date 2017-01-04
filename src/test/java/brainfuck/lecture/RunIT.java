package brainfuck.lecture;

import brainfuck.command.EnumCommands;
import brainfuck.memory.ComputationalModel;
import org.junit.*;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

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
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();


    @Test
    public void testRead() throws Exception{
        String path=new File("").getAbsolutePath();
        Run run=new Run(path+"/src/test/java/brainfuck/lecture/FichierTestUnit.txt");
        run.load();
        assertEquals(run.getFichier().list.size(),1);
    }
    @Test
    public void testReadError() throws Exception{
        String path=new File("").getAbsolutePath();
        Run run=new Run(path+"/src/test/java/brainfuck/lecture/FichierTestUnit2.txt");
        exit.expectSystemExit();
        run.load();
    }
    @Test
    public void testReadMacro() throws Exception{
        String path=new File("").getAbsolutePath();
        Run run=new Run(path+"/src/test/java/brainfuck/lecture/FichierTestUnit3.txt");
        for(int i=0;i<run.getFichier().list.size();i++){
            assertEquals(run.getFichier().list.get(i), EnumCommands.INCR);
        }
    }
    @Test
    public  void testReadFonction() throws Exception{

    }
}
