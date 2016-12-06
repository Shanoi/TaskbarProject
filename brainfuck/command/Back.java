package brainfuck.command;

import brainfuck.lecture.Fichiers;
import brainfuck.lecture.Run;

/**
 *
 * @author TeamTaskbar
 */
public class Back implements Command {

    int i = 0;


    /**
     * This method allows to execute the command BACK
     */
    @Override
    public void execute() {

        Fichiers tempfile = new Fichiers("");

        Run.IncrEXEC_MOVE();
        Run.IncrDATA_READ();

        if (tempfile.getCm().getCurrentCaseValue() > 0) {

            tempfile.getCm().setI(tempfile.backAssoc(tempfile.getCm().getI()));

        }

    }

}
