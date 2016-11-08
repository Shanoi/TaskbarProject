package brainfuck.command;

import brainfuck.ComputationalModel;

/**
 *
 * @author TeamTaskbar
 */
public class Decrementer implements Command {

    /**
     * Decrement the value of the current cell of the memory
     *
     */
    @Override
    public void execute() {
        ComputationalModel cm = new ComputationalModel();
        if (cm.getCurrentCaseValue() > 0) {
            cm.setCurrentCaseValue((byte) (cm.getCurrentCaseValue() - 1));
        } else {
            System.exit(1);
        }
    }


}
