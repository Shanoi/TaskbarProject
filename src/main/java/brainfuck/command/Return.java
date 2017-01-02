package brainfuck.command;

import brainfuck.lecture.Fichiers;
import brainfuck.memory.ComputationalModel;

/**
 * This class represents the Return of the Functions. It allows to return a value in a program.
 *
 * @author Team Taskbar
 */
public class Return implements Command {

    @Override
    public void execute() {
        System.out.println("NUM2 LINE --- " + Fichiers.getRetAdress().peek());
        ComputationalModel.setI(Fichiers.getRetAdress().pop()-1);
        ComputationalModel.setExecFonction(false);
        ComputationalModel.returnValue();
        ComputationalModel.removeFonctionMemory();
        ComputationalModel.setLastP();

    }



}