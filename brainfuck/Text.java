package brainfuck;

import java.util.Arrays;
import brainfuck.lecture.*;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;

public class Text extends Interpreter {

    private static String filein = "";
    private static String fileout = "";

    /**
     * Initialize the different command
     *
     * @param path
     * @param args
     */
    public Text(String path, String[] args) {

        super(path, Arrays.asList(args));

    }

    public Text(String file, String path, List<String> args) {
        super(path, args);
        this.filein = file;
    }

    public static String getFileIn() {
        return filein;
    }

    public static String getFileOut(){
        return fileout;
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
