public class LongSyntaxe implements SyntaxeCommand
{
    @Override
    public void verify(String line)
    {
	if(!EnumCommands.isCommand(line))
	    System.exit(4);

    }

    @Override
    public void translate(String line)
    {
	EnumCommands.toCommand(line).getShort();
    }

    @Override
    public void run(String line)
    {
	EnumCommands.toCommand(line).getCommand().execute();
    }

}
