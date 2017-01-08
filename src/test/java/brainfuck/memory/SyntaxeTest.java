package brainfuck.memory;

import brainfuck.lecture.Fichiers;
import brainfuck.lecture.Monitor;
import org.junit.*;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import static org.junit.Assert.*;

/**
 * Created by sebde on 07/01/2017.
 */
public class SyntaxeTest {
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
    @Test
    public void isShortTestTrue(){
        Syntaxe syntaxe=new Syntaxe();
        assertEquals(syntaxe.isShort("++->"),true);
    }
    @Test
    public void isShortTestFalse(){
        Syntaxe syntaxe=new Syntaxe();
        assertEquals(syntaxe.isShort("DECR"),false);
    }
}
