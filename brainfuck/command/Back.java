package brainfuck.command;

import brainfuck.ComputationalModel;
import static brainfuck.ComputationalModel.getI;
import static brainfuck.ComputationalModel.setI;
import brainfuck.lecture.Run;

/**
 *
 * @author TeamTaskbar
 */
public class Back implements Command {
    int i=0;

    @Override
    public void execute() {
        ComputationalModel cm = new ComputationalModel();
        if(cm.getCurrentCaseValue() > 0) {
            setI(Run.backAssoc(getI()));
        }


    }


}






