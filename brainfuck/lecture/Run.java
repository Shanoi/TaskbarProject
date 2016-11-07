package brainfuck.lecture;

import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import brainfuck.Syntaxe;
import brainfuck.ComputationalModel;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Stack;

public class Run extends Lecture{

    private List<String> list = new ArrayList<String>();
    private static int i = 0;

    public Run(String path)
    {
	super(path);
    }
    public void load() throws IOException, FileNotFoundException
    {
	BufferedReader file = new BufferedReader( new FileReader(path));
	String line = new String();

	while( (line = file.readLine()) != null)
	    {
		if( Syntaxe.isShort(line))
		    {
			for(int j = 0; j < line.length(); j++)
			    list.add(Character.toString(line.charAt(j)));
		    }
		else
		    list.add(line);
	    }
	file.close();
    }

    public int getNbI()
    {
	return list.size();
    }

    public List<String> getInstructions()
    {
	return this.list;
    }

    public static int jumpAssoc(int i)
    {
	Stack<String> stack = new Stack<String>();
	int k = ComputationalModel.getI();
	stack.push(list.get(k));
	while( !stack.isEmpty() ){
	    k++;
	    if( list.get(k).equals("["))
		stack.push("[");
	    if( list.get(k).equals("]"))
		stack.pop();
	}
	return k;
    }
    public static int backAssoc(int i)
    {
	Stack<String> stack = new Stack<String>();
	int k = ComputationalModel.getI();
	stack.push(list.get(k));
	while( !stack.isEmpty() ){
	    k--;
	    if( list.get(k).equals("["))
		stack.pop();
	    if( list.get(k).equals("]"))
		stack.push("]");
	}
	return k;
    }
    @Override
    public void execute() throws IOException, FileNotFoundException
    {
	ComputationalModel.init();
        
	while(ComputationalModel.getI() < list.size())
	    {
		if(Syntaxe.isShort(list.get(ComputationalModel.getI())))
		    shortSyntaxe.run(list.get(ComputationalModel.getI()));
		else
		    longSyntaxe.run(list.get(ComputationalModel.getI()));
		i = (ComputationalModel.getI()+1);
		ComputationalModel.setI(i);
	    }


    }




}
