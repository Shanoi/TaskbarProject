package brainfuck.command;

import brainfuck.ComputationalModel;
import brainfuck.lecture.Run;

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
        Run.EXEC_MOVE++;
        Run.DATA_WRITE++;
        ComputationalModel cm = new ComputationalModel();
        if (cm.getCurrentCaseValue() > 0) {
            cm.setCurrentCaseValue((byte) (cm.getCurrentCaseValue() - 1));
        } else {
            System.exit(1);
        }
    }


}
