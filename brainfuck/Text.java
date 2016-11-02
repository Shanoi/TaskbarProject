package brainfuck;

import brainfuck.command.AfficheMemory;
import brainfuck.command.Decrementer;
import brainfuck.command.In;
import brainfuck.command.Incrementer;
import brainfuck.command.Left;
import brainfuck.command.Out;
import brainfuck.command.Right;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Text extends Interpreter {

    

    /**
     * Initialize the different command
     *
     * @param directory The file to reead and execute
     * @throws FileNotFoundException The exception if the file is not found by
     * the program
     */
    public Text(String directory) throws FileNotFoundException {

	this.path = directory;
        ComputationalModel.init();

        Instructions = new HashMap<>();

        //initialistaion de la Hashmap
        Instructions.put("INCR", new Incrementer());
        Instructions.put("DECR", new Decrementer());
        Instructions.put("LEFT", new Left());
        Instructions.put("RIGHT", new Right());
        Instructions.put("OUT", new Out());
        Instructions.put("IN", new In());
        Instructions.put("+", new Incrementer());
        Instructions.put("-", new Decrementer());
        Instructions.put("<", new Left());
        Instructions.put(">", new Right());
        Instructions.put(".", new Out());
        Instructions.put(",", new In());
        Instructions.put("AFF", new AfficheMemory());
    }

    /**
     * This method read the content of the file and execute the different
     * Command associate This method can read long and short synthaxe and also
     * the mixed synthaxe
     *
     */
    public void launchInterpreter() throws IOException, FileNotFoundException{
	BufferedReader memoryOfFile = new BufferedReader(new FileReader(path));
        String line = new String();

        try {
            while ((line = memoryOfFile.readLine()) != null) {

                if ((line.charAt(0) >= 'A') && (line.charAt(0) <= 'Z')) {
                    (Instructions.get(line)).execute();
                } else {
                    readShort(line);
                }

            }

        } catch (IOException ex) {
            Logger.getLogger(Text.class.getName()).log(Level.SEVERE, null, ex);
        }
	memoryOfFile.close();
    }

    public void readShort(String line) {

        int i = 0;
        int n = line.length();
        while (i < n) {

            (Instructions.get(Character.toString(line.charAt(i)))).execute();
            i++;
        }
    }

    /**
     *
     * Rewrite the code with short syntax
     *
     */
    public void rewrite() throws IOException {
        String line = new String();
	BufferedReader memoryOfFile = new BufferedReader(new FileReader(path));
        while ((line = memoryOfFile.readLine()) != null) {

            if ((line.charAt(0) >= 'A') && (line.charAt(0) <= 'Z')) {
                (Instructions.get(line)).printShort();
            } else {
                System.out.print(line);
            }

        }
	memoryOfFile.close();
    }
    public void wellformed() throws IOException
    {
	BufferedReader memoryOfFile = new BufferedReader(new FileReader(path));

       	String str = memoryOfFile.readLine();

	memoryOfFile.close();
    }

}
