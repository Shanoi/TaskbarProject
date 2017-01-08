package brainfuck.command;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by sebde on 08/01/2017.
 */
public class uByteTest {
    @Test
    public void byteToInt(){
        uByte ubyte=new uByte((byte)50);
        assertEquals(ubyte.byteToInt(),50);

    }
}
