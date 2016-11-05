package brainfuck.scommands;

public interface SyntaxeCommand
{
    public abstract void run(String line);
    public abstract void verify(String line);
    public abstract void translate(String line);

}
