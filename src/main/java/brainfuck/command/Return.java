package brainfuck.command;

import brainfuck.lecture.Fichiers;
import brainfuck.memory.ComputationalModel;

/**
 * This class represents the RETURN command of the Brainf*ck langage.
 * It allows to return a value in a function.
 *
 * @author Team Taskbar
 */
public class Return implements Command {

    @Override
    /**
     * This method allows to do the return action.
     */
    public void execute() {
        System.out.println("NUM2 LINE --- " + Fichiers.getRetAdress().peek());
        ComputationalModel.setI(Fichiers.getRetAdress().pop()-1);
        ComputationalModel.setExecFonction(false);
        ComputationalModel.returnValue();
        ComputationalModel.removeFonctionMemory();
        ComputationalModel.setLastP();

    }



}