public class Rewrite extends Lecture{

    /*
     *
     *attribute
     *
     */


    @Overrride
    public void execute()
    {
	BufferedReader file = new BufferedReader(new FileReader(path));
	Syntaxe line = new Syntaxe();
	while( (line = file.readLine()) != null)
	{
	    if (line.isShort())
		shortSyntaxe.translate(line);
	    else
		longSyntaxe.translate(line);
	}
		
	file.close();
    }

    

}
