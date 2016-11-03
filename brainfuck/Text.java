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
import java.util.Stack;

public class Text extends Interpreter {

    private Stack<Character> stack = new Stack<Character>();

    /**
     * Initialize the different command
     *
     * @param directory The file to reead and execute
     * @throws FileNotFoundException The exception if the file is not found by
     * the program
     */
    public Text(String path) throws FileNotFoundException {

	this.path = path;
	
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

	Instructions.put("[", new Out());
		Instructions.put("]", new Out());
			Instructions.put("JUMP", new Out());
				Instructions.put("BACK", new Out());
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
    public void verifyShort(String line){

        int i = 0;
        int n = line.length();
        while (i < n) {

	    if (Instructions.containsKey(Character.toString(line.charAt(i))) == false)
		System.exit(4);
	    if (line.charAt(i) == '[')
		stack.push((Character) '[');
	    if (line.charAt(i) == ']')
	    {
		if( stack.empty())
		    System.exit(4);
		else
		    stack.pop();
	    }
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

	String line = new String();
        while ((line = memoryOfFile.readLine()) != null) {

            if ((line.charAt(0) >= 'A') && (line.charAt(0) <= 'Z')) {
		        if(Instructions.containsKey(line) == false)
		            System.exit(4);
		        if(line.equals("JUMP"))
		            stack.push((Character) '[');
		        if(line.equals("BACK"))
		        {
			        if(stack.empty())
			            System.exit(4);
			        else
			        stack.pop();
		        }
		   
	            } else{
                    this.verifyShort(line);
            }
	    
	    
        }
	memoryOfFile.close();

    
    if (!stack.empty())
	System.exit(4);
    }
}
