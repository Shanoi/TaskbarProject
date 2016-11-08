package brainfuck.scommands;

import brainfuck.command.EnumCommands;
import brainfuck.lecture.Wellformed;

public class LongSyntaxe implements SyntaxeCommand
{
    @Override
    public void verify(String line)
    {
	if(!EnumCommands.isCommand(line))
	    System.exit(4);
	if(line.equals("JUMP"))
	    Wellformed.pushStack("[");
	if(line.equals("BACK") && !Wellformed.emptyStack())
	    Wellformed.popStack();
	if(line.equals("BACK") && !Wellformed.emptyStack())
	    System.exit(4);
    }

    @Override
    public void translate(String line)
    {
	System.out.print(EnumCommands.toCommand(line).getShort());
    }

    @Override
    public void run(String line)
    {
	EnumCommands.toCommand(line).getCommand().execute();
    }

}
