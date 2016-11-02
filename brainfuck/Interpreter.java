package brainfuck;

import brainfuck.command.Command;
import java.util.HashMap;

abstract class Interpreter {

    /*
     *
     *  attributes
     *
     */
    protected ComputationalModel cm = new ComputationalModel();
    protected HashMap<String, Command> Instructions;
    protected String path = new String();
}
