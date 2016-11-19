package brainfuck.command;

import brainfuck.lecture.Fichiers;
import brainfuck.lecture.Run;

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
        Fichiers tempfile = new Fichiers("");

        Run.IncrEXEC_MOVE();
        Run.IncrDATA_WRITE();

        if (tempfile.getCm().getCurrentCaseValue() < 255) {
            tempfile.getCm().setCurrentCaseValue((byte) (tempfile.getCm().getCurrentCaseValue() + 1));
        } else {
            System.exit(1);
        }

    }

}
