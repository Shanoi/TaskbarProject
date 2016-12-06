package brainfuck.command;

import brainfuck.lecture.Fichiers;
import brainfuck.lecture.Run;

/**
 *
 * @author TeamTaskbar
 */
public class Decrementer implements Command {

    /**
     * Decr : Decrement the value of the current cell of the memory
     * This method allows to execute the command DECR
     */
    @Override
    public void execute() {

        Fichiers tempfile = new Fichiers("");

        Run.IncrEXEC_MOVE();
        Run.IncrDATA_WRITE();

        if (tempfile.getCm().getCurrentCaseValue() > 0) {
            tempfile.getCm().setCurrentCaseValue((byte) (tempfile.getCm().getCurrentCaseValue() - 1));
        } else {
            System.exit(1);
        }
    }

}
