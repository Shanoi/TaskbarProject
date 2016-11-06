/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brainfuck;

import brainfuck.lecture.Lecture;
import brainfuck.lecture.Wellformed;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Olivier
 */
public class Image extends Interpreter {

    private static String file = "";

    /**
     * Initialize the different command
     *
     * @param path
     * @param args
     */
    public Image(String path, String[] args) {

        super(path, Arrays.asList(args));

    }

    public Image(String file, String path, List<String> args) {
        super(path, args);
        this.file = file;
    }

    public static String getFile() {
        return file;
    }

    /**
     * This method read the content of the file and execute the different
     * Command associate This method can read long and short synthaxe and also
     * the mixed syntaxe
     *
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     */
    public void launchInterpreter() throws IOException, FileNotFoundException {

        Lecture test2 = new Wellformed(path);
        test2.execute();

    }
}
