package brainfuck;

public class Syntaxe
{
    public String line;




    public static boolean isShort(String line)
    {
	if((line.charAt(0) >= 'A') && (line.charAt(0) <= 'Z'))
	    return false;
	return true;
    }

}
