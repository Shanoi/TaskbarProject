package brainfuck;

import brainfuck.command.AfficheMemory;
import brainfuck.command.Decrementer;
import brainfuck.command.In;
import brainfuck.command.Incrementer;
import brainfuck.command.Left;
import brainfuck.command.Out;
import brainfuck.command.Right;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import brainfuck.lecture.*;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Text extends Interpreter {


    /**
     * Initialize the different command
     *
     * @param directory The file to reead and execute
     * @throws FileNotFoundException The exception if the file is not found by
     * the program
     */

    public Text(String path, String [] args) {
	
	super(path, Arrays.asList(args));
	
    }

    /**
     * This method read the content of the file and execute the different
     * Command associate This method can read long and short synthaxe and also
     * the mixed synthaxe
     *
     */
    public void launchInterpreter() throws IOException, FileNotFoundException {


	Lecture test2 = new Verify(path);
	test2.execute();
    }

    

    
}
