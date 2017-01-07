package brainfuck.lecture;

import org.junit.*;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import static org.junit.Assert.*;
/**
 * Created by sebde on 08/01/2017.
 */
public class DelCommsTest {
    @Test
    public void deleteComTest() throws Exception{
        DelComms delComms=new DelComms();
        assertEquals(delComms.deleteCom("      INCR# ceci est un commentaire",null),"INCR");
    }

}
