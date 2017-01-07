package brainfuck.memory;

import brainfuck.command.EnumCommands;
import brainfuck.memory.ComputationalModel;
import org.junit.*;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by sebde on 07/01/2017.
 */
public class WellformedTest {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void executeTest() throws Exception{
        Wellformed well=new Wellformed(new ArrayList<EnumCommands>(Arrays.asList(EnumCommands.BACK,EnumCommands.JUMP)));
        exit.expectSystemExit();
        well.execute();
    }

}
