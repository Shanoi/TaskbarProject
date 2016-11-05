package brainfuck.lecture;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import brainfuck.Syntaxe;
import java.util.Stack;

public class Wellformed extends Lecture{

    static private Stack<String> stack = new Stack<String>();
    
    public Wellformed(String path)
    {
	super(path);
    }
    
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
    static public void popStack()
    {
	stack.pop();
    }
    static public void pushStack(String line)
    {
	stack.push(line);
    }
    static public boolean emptyStack()
    {
	return stack.empty();
    }
    public void load()
    {

    }

}
