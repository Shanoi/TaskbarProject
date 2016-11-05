public class Run extends Lecture{

    private List<String> list = new ArrayList<String>();

    public void load()
    {
	BufferedReader file = new BufferedReader( new FileReadder(path));
	Syntaxe line = new Syntaxe();

	while( (line = file.readline()) != null)
	    {
		if( line.isShort())
		    {
			for(int i = 0; i < line.length(); i++)
			    list.add(Character.toString(line.charAt(i)));
		    }
		else
		    list.add(line);
	    }
	file.close();
    }

    @Override
    public void execute()
    {
	BufferedReader file = new BufferedReader(new FileReadder(path));
	Syntaxe line = new Syntaxe();
	ComputationalModel.init();
        
	while(ComputationalModel.getI() < list.size())
	    {
		if(list.get(getI()).isShort())
		    shortSyntaxe.run(list.get(ComputationalModel.getI()));
		else
		    longSyntaxe.run(list.get(ComputationalModel.getI()));
		ComputationalModel.setI(getI()+1);
	    }


    }




}
