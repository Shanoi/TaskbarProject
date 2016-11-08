package brainfuck.command;

import brainfuck.ComputationalModel;

/**
 *
 * @author TeamTaskbar
 */
public class Left implements Command {

    /**
     * Change the current cell of the memory, taking the cell at the left of the
     * current cell
     *
     */
    @Override
    public void execute() {
        ComputationalModel cm = new ComputationalModel();
        if (cm.getCurrentIndice() > 0) {
            cm.setCurrentIndice(cm.getCurrentIndice() - 1);
        } else {
            System.exit(2);
        }
    }

}
