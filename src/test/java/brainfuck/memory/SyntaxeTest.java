package brainfuck.memory;

import org.junit.*;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import static org.junit.Assert.*;

/**
 * Created by sebde on 07/01/2017.
 */
public class SyntaxeTest {
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
