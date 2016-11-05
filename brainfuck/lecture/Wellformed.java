package brainfuck.lecture;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import brainfuck.Syntaxe;

public class Wellformed extends Lecture{

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
    public void load()
    {

    }

}
