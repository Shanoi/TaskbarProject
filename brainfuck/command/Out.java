package brainfuck.v5.command;

import brainfuck.v5.ComputationalModel;

public class Out implements Command {

    @Override
    public void execute() {
        ComputationalModel cm = new ComputationalModel();
        System.out.println((char) cm.getCurrentCase());
    }

    /**
     * Print the instruction in short syntax for the rewrite instruction
     * 
     */
    @Override
    public void printShort() {
        System.out.print(".");
    }

}
