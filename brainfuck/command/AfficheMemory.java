package brainfuck.command;

import brainfuck.memory.ComputationalModel;

public class AfficheMemory implements Command {

    @Override
    public void execute() {
        ComputationalModel cm = new ComputationalModel();
        cm.affichememoire();
    }

}
