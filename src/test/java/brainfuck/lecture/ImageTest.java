package brainfuck.lecture;

import brainfuck.memory.ComputationalModel;
import org.junit.*;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by sebde on 08/01/2017.
 */
public class ImageTest {
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

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void ReadTestErrorPixel(){
        String path=new File("").getAbsolutePath();
        Image image=new Image(path+"/src/test/java/brainfuck/lecture/FichierImageTest2.bmp");
        exit.expectSystemExit();
        image.Read();
    }
}