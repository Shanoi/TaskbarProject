package brainfuck.command;

import brainfuck.ComputationalModel;
import brainfuck.command.Command;

/**
 * @author TeamTaskbar
 */
public class Jump implements Command {


    @Override
    public void execute() {
        ComputationalModel cm = new ComputationalModel();
        if(cm.getCurrentCaseValue() = 0){
            setI(Lecture.JumpAssoc(getI));
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

