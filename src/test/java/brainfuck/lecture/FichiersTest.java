package brainfuck.lecture;

import brainfuck.command.EnumCommands;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by sebde on 08/01/2017.
 */
public class FichiersTest {
    @Test
    public void setTableLoopTest(){
        Fichiers file=new Fichiers("");
        file.list.add(EnumCommands.JUMP);
        file.list.add(EnumCommands.BACK);
        file.setTableLoop();
        System.out.println(file.jumpBack.get(0).toString());
        assertEquals(file.jumpBack.get(0),new Integer(1));
        assertEquals(file.jumpBack.get(1),new Integer(0));
        file.list.clear();
        file.jumpBack.clear();
    }
    @Test
    public void jumpAssocTest(){
        Fichiers file= new Fichiers("");
        file.list.add(EnumCommands.JUMP);
        file.list.add(EnumCommands.BACK);
        assertEquals(file.jumpAssoc(0),1);
        assertEquals(file.backAssoc(1),0);
    }
}
