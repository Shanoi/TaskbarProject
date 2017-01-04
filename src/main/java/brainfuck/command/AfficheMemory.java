package brainfuck.command;

import brainfuck.memory.ComputationalModel;

public class AfficheMemory implements Command {


    @Override
    /**
     * This method allows to display the memory.
     */
    public void execute() {
        ComputationalModel cm = new ComputationalModel();
        cm.affichememoire();
    }

}
