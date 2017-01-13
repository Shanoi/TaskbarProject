package brainfuck.command;

import brainfuck.memory.ComputationalModel;

/**
 * This class was created to display the memory.
 *
 * @author TeamTaskbar
 */
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
