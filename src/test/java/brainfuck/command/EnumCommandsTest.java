package brainfuck.command;

import org.junit.Test;

import static brainfuck.command.EnumCommands.*;
import static org.junit.Assert.*;

/**
 * Created by sebde on 13/01/2017.
 */
public class EnumCommandsTest {
    @Test
    public void isCommandTest(){
        assertTrue(isCommand("INCR"));
    }
    @Test
    public void isCommandTest2(){
        assertTrue(isCommand("JUMP"));
    }
    @Test
    public void isCommandTest3(){
        assertFalse(isCommand("AZERTY"));
    }
    @Test
    public void toCommandTest(){
        assertEquals(toCommand("INCR"),INCR);
    }
    @Test
    public void toCommandTest2(){
        assertEquals(toCommand("-"),DECR);
    }
    @Test
    public void toCommandTest3(){
        assertEquals(toCommand("AZERTY"),null);
    }
    @Test
    public void isShortCommandTest(){
        assertTrue(isShortCommand(","));
    }
    @Test
    public void isShortCommandTest2(){
        assertFalse(isShortCommand("INCR"));
    }

}
