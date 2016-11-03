public class Syntaxe extends String
{
    public boolean isShort(String str)
    {
	if((line.charAt(0) >= 'A') && (line.charAt(0) <= 'Z'))
	    return false;
	return true;
    }

}
