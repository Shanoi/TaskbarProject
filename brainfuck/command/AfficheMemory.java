package brainfuck.v5.command;

import brainfuck.v5.ComputationalModel;

public class AfficheMemory implements Command {

    @Override
    public void execute() {
        ComputationalModel cm = new ComputationalModel();
        cm.affichememoire();
    }

    @Override
    public void printShort() {
        System.out.print("");
    }

}
