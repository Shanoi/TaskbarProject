package brainfuck.command;

import brainfuck.lecture.Fichiers;
import brainfuck.lecture.Run;

/**
 * @author TeamTaskbar
 */
public class Jump implements Command {

    /**
     * This method allows to execute the command JUMP
     */
    @Override
    public void execute() {

        Fichiers tempfile = new Fichiers("");

        Run.IncrEXEC_MOVE();
        Run.IncrDATA_READ();

        if (tempfile.getCm().getCurrentCaseValue() == 0) {

            tempfile.getCm().setI(tempfile.jumpAssoc(tempfile.getCm().getI()));

        }

    }

}
