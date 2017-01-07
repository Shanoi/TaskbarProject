package brainfuck.lecture;

import brainfuck.command.EnumCommands;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.StringReader;

import static org.junit.Assert.*;

/**
 *
 * @author TeamTaskbar
 */
public class TextTest {
    
    public TextTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of Read method, of class Text.
     */
    @Test
    public void testRead() throws Exception {
        String path=new File("").getAbsolutePath();
        Text text= new Text(path+"/src/test/java/brainfuck/lecture/FichierTestUnit.txt");
        Fichiers file=new Fichiers("");
        file.list.clear();
        text.Read();
        assertEquals(file.list.size(),2);
        file.list.clear();
    }

    @Test
    public void testReadMacro() throws  Exception{
        String path=new File("").getAbsolutePath();
        Text text= new Text(path+"/src/test/java/brainfuck/lecture/FichierTestUnit3.txt");
        Fichiers file=new Fichiers("");
        text.Read();
        assertEquals(file.list.size(),4);
        for(int i=0;i<file.list.size();i++){
            assertEquals(file.list.get(i), EnumCommands.INCR);
        }
        file.list.clear();
    }

    @Test
    public  void testReadFunction() throws  Exception{
        String path=new File("").getAbsolutePath();
        Text text= new Text(path+"/src/test/java/brainfuck/lecture/FichierTestUnit4.txt");
        Fichiers file=new Fichiers("");
        text.Read();
        assertEquals(file.list.size(),10);
        file.list.clear();
    }

    
}
