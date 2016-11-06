package brainfuck.command;

import brainfuck.ComputationalModel;

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
            setI(Lecture.BackAssoc(getI));
        }


    }


}






