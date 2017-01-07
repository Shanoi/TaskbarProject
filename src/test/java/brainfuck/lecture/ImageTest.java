package brainfuck.lecture;

import org.junit.*;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by sebde on 08/01/2017.
 */
public class ImageTest {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void ReadTestErrorPixel(){
        String path=new File("").getAbsolutePath();
        Image image=new Image(path+"/src/test/java/brainfuck/lecture/FichierImageTest2.bmp");
        exit.expectSystemExit();
        image.Read();
    }
    @Test
    public void EncodeTest(){
        String path=new File("").getAbsolutePath();
        Image image=new Image(path+"/src/test/java/brainfuck/lecture/FichierTestUnit.txt");
        image.Encod();
    }
}