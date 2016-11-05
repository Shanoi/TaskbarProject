package brainfuck.lecture;

import brainfuck.scommands.ShortSyntaxe;
import brainfuck.scommands.LongSyntaxe;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class Lecture
{

    public abstract void execute() throws IOException, FileNotFoundException;
    public abstract void load() throws IOException, FileNotFoundException;
    ShortSyntaxe shortSyntaxe = new ShortSyntaxe();
    LongSyntaxe longSyntaxe = new LongSyntaxe();
    String path;

    public Lecture(String path)
    {
	this.path = path;
    }

}
