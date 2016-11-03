public class Wellformed extends Lecture{

    @Override
    public void execute()
    {
	BufferedReader file = new BufferedReader(new FileReader(path));
	Syntaxe line = new Syntaxe();
	while( (line = file.readLine()) != null )
	    {
      	    if(line.isShort())
		shortSyntaxe.verify(line);
	    else
       		longSyntaxe.verify(line);
	    }
	file.close();

    }


}
