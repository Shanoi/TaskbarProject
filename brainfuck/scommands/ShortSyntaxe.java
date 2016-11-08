package brainfuck.scommands;

import brainfuck.command.EnumCommands;
import brainfuck.lecture.Wellformed;

public class ShortSyntaxe implements SyntaxeCommand
{
    @Override
    public void verify(String line)
    {
	for( int i = 0; i < line.length(); i++ )
	{
	    if(!EnumCommands.isCommand(Character.toString(line.charAt(i))))
		System.exit(4);
	    if(Character.toString(line.charAt(i)).equals("["))
		Wellformed.pushStack("[");
        if(Character.toString(line.charAt(i)).equals("]") && Wellformed.emptyStack())
            System.exit(4);
        if(Character.toString(line.charAt(i)).equals("]") && !Wellformed.emptyStack())
		Wellformed.popStack();
	    
	    
	}
    }

    @Override
    public void translate(String line)
    {
	System.out.print(line);
    }

    @Override
    public void run(String line)
    {
	EnumCommands.toCommand(line).getCommand().execute();
    }

}
