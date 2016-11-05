package brainfuck.lecture;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import brainfuck.Syntaxe;
import java.util.Stack;

public class Wellformed extends Lecture{

    static private Stack<String> stack = new Stack<>();
    
    public Wellformed(String path)
    {
	super(path);
    }
    
    @Override
    public void execute() throws IOException, FileNotFoundException
    {
	BufferedReader file = new BufferedReader(new FileReader(path));
	String line = new String();
	while( (line = file.readLine()) != null )
	    {
      	    if(Syntaxe.isShort(line))
		shortSyntaxe.verify(line);
	    else
       		longSyntaxe.verify(line);
	    }
	file.close();
    }
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    static public popStack()
=======
    public void popStack()
>>>>>>> eb50e9a5813f9d8b1c15647038e478fb6688c825
=======
    public popStack()
>>>>>>> parent of 2109bc4... -fix
=======
    public popStack()
>>>>>>> parent of 2109bc4... -fix
=======
    public popStack()
>>>>>>> parent of 2109bc4... -fix
    {
	stack.pop();
    }
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    static public pushStack(String line)
=======
    public void pushStack(String line)
>>>>>>> eb50e9a5813f9d8b1c15647038e478fb6688c825
=======
    public pushStack(String line)
>>>>>>> parent of 2109bc4... -fix
=======
    public pushStack(String line)
>>>>>>> parent of 2109bc4... -fix
=======
    public pushStack(String line)
>>>>>>> parent of 2109bc4... -fix
    {
	stack.push(line);
    }
    public void load()
    {

    }

}
