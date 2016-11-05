package brainfuck.command;

import brainfuck.ComputationalModel;
import static brainfuck.ComputationalModel.getI;
import brainfuck.lecture.Lecture;
import brainfuck.lecture.Run;

/**
 * @author TeamTaskbar
 */
public class Jump implements Command {


    @Override
    public void execute() {
        ComputationalModel cm = new ComputationalModel();
        if(cm.getCurrentCaseValue() == 0){
            setI(Run.JumpAssoc(getI()));
        }

    }

    @Override
    public void printShort() {
        System.out.print("[");
    }

    @Override
    public String getColor() {
        return null;
    }
}

