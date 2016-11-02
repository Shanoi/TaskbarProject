package brainfuck.command;

import brainfuck.ComputationalModel;

public class AfficheMemory implements Command {

    @Override
    public void execute() {
        ComputationalModel cm = new ComputationalModel();
        ComputationalModel.affichememoire();
    }

    @Override
    public void printShort() {
        System.out.print("");
    }

}
