package brainfuck.command;

import brainfuck.lecture.Fichiers;
import brainfuck.lecture.Monitor;
import brainfuck.memory.ComputationalModel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

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


    /**
     * Test of execute method, of class In.
     */
    @Test
    public void testExecute() {
        ComputationalModel cm=new ComputationalModel();
        cm.init();
        Command command=new In();
        InputStream in=new ByteArrayInputStream("a".getBytes());
        System.setIn(in);
        command.execute();
        assertEquals(cm.getCurrentCaseValue(),97);

    }

    
}
