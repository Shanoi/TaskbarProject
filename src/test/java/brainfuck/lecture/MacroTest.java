package brainfuck.lecture;

import brainfuck.memory.ComputationalModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by sebde on 08/01/2017.
 */
public class MacroTest {
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
    public void isParamTest(){
        Macro macro=new Macro("* macro A B C".split(" "));
        assertEquals(macro.isParam("B"),true);
    }
    @Test
    public void getnbParamTest(){
        Macro macro=new Macro("* macro A B C".split(" "));
        assertEquals(macro.getnbParam(),3);
    }
    @Test
    public void getNumParamTest(){
        Macro macro=new Macro("* macro A B C".split(" "));
        assertEquals(macro.getNumParam("A"),1);
    }

}
