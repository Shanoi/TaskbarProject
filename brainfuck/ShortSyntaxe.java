public class ShortSyntaxe implements SyntaxeCommand
{
    @Override
    public void verify(String line)
    {
	for( int i = 0; i < line.length(); i++ )
	{
	    if(!EnumCommands.isCommand(Character.toString(line.charAt(i))))
		System.exit(4);
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
