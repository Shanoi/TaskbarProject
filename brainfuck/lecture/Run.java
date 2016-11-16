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

    private static List<String> list = new ArrayList<String>();
    private static int i = 0;
    private static long EXEC_TIME = 0;
	public static int EXEC_MOVE = 0;
	public static int DATA_MOVE=0;
	public static int DATA_WRITE=0;
	public static int DATA_READ=0;
    
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
    public long getExecTime()
    {
	return EXEC_TIME;
    }
    public int nbInstructions()
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
	int o = ComputationalModel.getI();
	stack.push(list.get(o));
	while( !stack.isEmpty() ){
	    o++;
	    if( list.get(o).equals("["))
		stack.push("[");
	    if( list.get(o).equals("]"))
		stack.pop();
	}
	return o;
    }
    public static int backAssoc(int i)
    {
	Stack<String> stack = new Stack<String>();
	int o = ComputationalModel.getI();
	stack.push(list.get(o));
	while( !stack.isEmpty() ){
	    o--;
	    if( list.get(o).equals("["))
		stack.pop();
	    if( list.get(o).equals("]"))
		stack.push("]");
	}
	return o;
    }
    @Override
    public void execute() throws IOException, FileNotFoundException
    {
	long instantA = System.currentTimeMillis(); //
	    
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

	long instantB = System.currentTimeMillis();
	EXEC_TIME = instantB - instantA;
		afficheStats();
	
    }

    public void afficheStats(){
		System.out.println("Nombre d'instructions: "+nbInstructions());
		System.out.println("Temps d'executions: "+EXEC_TIME);
		System.out.println("Nombre de déplacements du pointeur d'instruction: "+EXEC_MOVE);
		System.out.println("Nombre de déplacements dans la mémoire: "+DATA_MOVE);
		System.out.println("Nombre d'écritures dans la mémoire: "+DATA_WRITE);
		System.out.println("Nombre de lectures dans la mémoire: "+DATA_READ);
	}




}
