package brainfuck.command;

import brainfuck.ComputationalModel;

public class Out implements Command {

    @Override
    public void execute() {
        ComputationalModel cm = new ComputationalModel();
        System.out.println((char) cm.getCurrentCaseValue());
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
