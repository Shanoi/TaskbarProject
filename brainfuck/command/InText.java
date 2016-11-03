package brainfuck.command;

import brainfuck.ComputationalModel;
/**
 * Created by sebde on 03/11/2016.
 */
public class InText implements Command {

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public void execute() {

    }

    @Override
    public void printShort() {
        System.out.print(",");
    }
}
