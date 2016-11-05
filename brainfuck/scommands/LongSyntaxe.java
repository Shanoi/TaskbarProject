package brainfuck.scommands;

import brainfuck.command.EnumCommands;

public class LongSyntaxe implements SyntaxeCommand
{
    @Override
    public void verify(String line)
    {
	if(!EnumCommands.isCommand(line))
	    System.exit(4);
	if(Character.toString(line.charAt(i)).equals("JUMP"))
	    Run.pushStack("[");
	if(Character.toString(line.charAt(i)).equals("BACK") && !emptyStack())
	    Run.popStack();
	if(Character.toString(line.charAt(i)).equals("BACK") && !emptyStack())
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
