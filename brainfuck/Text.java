package brainfuck;

import java.util.Arrays;
import brainfuck.lecture.*;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;

public class Text extends Interpreter {

    private String file = "";

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
        this.file = file;
    }

    public String getFile() {
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

        Lecture test2 = new Rewrite(path);
        test2.execute();
    }

}
