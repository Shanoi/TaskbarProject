package brainfuck;
//test
import brainfuck.command.Command;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

abstract class Interpreter {
    //bonjour
    /*
     *
     *  attributes
     *
     */
    protected ComputationalModel cm = new ComputationalModel();
    protected HashMap<String, Command> Instructions;
    protected String path = new String();
    protected List<String> args = new ArrayList();

    /*protected Interpreter( String path, List<String> args)
    {
	this.path = path;
	this.args = args;
    }*/
}
