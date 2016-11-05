package brainfuck.lecture;

import brainfuck.Syntaxe;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Rewrite extends Lecture{

    /*
     *
     *attribute
     *
     */
    public Rewrite(String path)
    {
	super(path);
    }

    public void execute() throws IOException, FileNotFoundException
    {
	BufferedReader file = new BufferedReader(new FileReader(path));
	String line = new String();
	while( (line = file.readLine()) != null)
	{
	    if (Syntaxe.isShort(line))
		shortSyntaxe.translate(line);
	    else
		longSyntaxe.translate(line);
	}
		
	file.close();
    }

    public void load()
    {

    }

}
