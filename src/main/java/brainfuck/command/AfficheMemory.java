package brainfuck.command;

import brainfuck.memory.ComputationalModel;

public class AfficheMemory implements Command {

    /**
     * This method allows to display the memory.
     */
    @Override
    public void execute() {
        ComputationalModel cm = new ComputationalModel();
        cm.affichememoire();
    }

}
