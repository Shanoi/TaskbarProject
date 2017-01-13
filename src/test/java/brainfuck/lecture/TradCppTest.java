/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brainfuck.lecture;

import java.io.File;
import java.io.FilenameFilter;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Olivier
 */
public class TradCppTest {

    private String path;

    @Before
    public void setUp() {

        this.path = new File("").getAbsolutePath() + "/src/test/java/brainfuck/lecture/";

    }

    @Test
    public void testMemory() {

        new TradCpp(path + "TradCppTest00.txt").execute();

        assertTrue(new File(path + "Memory.cpp").exists());
        assertTrue(new File(path + "Memory.h").exists());

    }

    @Test
    public void FichierProgTest() {

        new TradCpp(path + "TradCppTest00.txt").execute();

        assertTrue(new File(path + "TradCppTest00.txt.cpp").exists());

    }

    @Test
    public void FichierInTest() {

        new TradCpp(path + "TradCppTest01.txt").execute();

        assertTrue(new File(path + "In.cpp").exists());
        assertTrue(new File(path + "In.h").exists());

    }

    @Test
    public void FichierOutTest() {

        new TradCpp(path + "TradCppTest02.txt").execute();

        assertTrue(new File(path + "Out.cpp").exists());
        assertTrue(new File(path + "Out.h").exists());

    }

    @After
    public void tearDown() {

        File rep = new File(path);

        FilenameFilter fileNameFilter = (File dir, String name) -> {
            if (name.lastIndexOf('.') > 0) {
                // get last index for '.' char
                int lastIndex = name.lastIndexOf('.');

                // get extension
                String str = name.substring(lastIndex);

                // match path name extension
                if (str.equals(".cpp") || str.equals(".h")) {
                    return true;
                }
            }
            return false;
        };

        File[] paths;

        paths = rep.listFiles(fileNameFilter);

        for (int i = 0; i < paths.length; i++) {
 
            paths[i].delete();

        }

    }

}
