package brainfuck.command;

import brainfuck.ComputationalModel;

/**
 *
 * @author TeamTaskbar
 */
public class Incrementer implements Command {

    /**
     * Increment the value of the current cell of the memory
     *
     */
    @Override
    public void execute() {
        ComputationalModel cm = new ComputationalModel();
        if (cm.getCurrentCaseValue() < 255) {
            cm.setCurrentCaseValue((byte) (cm.getCurrentCaseValue() + 1));
        } else {
            System.exit(1);
        }

    }

}
