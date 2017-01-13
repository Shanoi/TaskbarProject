package brainfuck.command;

import brainfuck.lecture.Fichiers;
import brainfuck.lecture.Monitor;
import brainfuck.lecture.Run;
import brainfuck.memory.ComputationalModel;
import brainfuck.memory.Launcher;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

/**
 *
 * @author TeamTaskbar
 */
public class InTest {
    
    public InTest() {
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
        Launcher.setFileIn("");
    }
    
    @After
    public void tearDown() {
        ComputationalModel cmReset=new ComputationalModel();
        Fichiers fileReset=new Fichiers("");
        Monitor monitorReset=new Monitor();
        cmReset.Reset();
        fileReset.Reset();
        monitorReset.Reset();
        Launcher.setFileIn("");
    }


    /**
     * Test of execute method, of class In.
     */



    @Test
    public void testExecuteWithoutFile() {
        ComputationalModel cm=new ComputationalModel();
        cm.init();
        Command command=new In();
        String input="a";
        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        command.execute();
        assertEquals(cm.getCurrentCaseValue(),97);

    }
    @Test
    public void testExecuteWithFile() throws IOException {
        String path=new File("").getAbsolutePath();
        ComputationalModel cm= new ComputationalModel();
        //String string[]={"-i",path+"/src/test/java/brainfuck/lecture/FichierTestIn.txt"};
        //Launcher launcher= new Launcher(path+"/src/test/java/brainfuck/lecture/FichierTestUnit.txt",string);
        Launcher.setFileIn(path+"/src/test/java/brainfuck/lecture/FichierTestIn.txt");
        cm.init();
        Command command=new In();
        command.execute();
        assertEquals(cm.getCurrentCaseValue(),98);
    }


    
}
