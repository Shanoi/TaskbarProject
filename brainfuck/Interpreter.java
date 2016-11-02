package brainfuck.v5;

import brainfuck.v5.command.Command;
import java.util.HashMap;

abstract class Interpreter {
    
    /*
     *
     *  attributes
     *
     */
    protected ComputationalModel cm = new ComputationalModel();
    protected HashMap<String, Command> Instructions;

}
