package brainfuck.command;

import brainfuck.lecture.Fichiers;
import brainfuck.memory.ComputationalModel;

/**
 * This class represents the command RET of the Brainf*ck langage.
 * It allows to stop a function without returning anything.(used for procedures)
 *
 *@author TeamTaskbar
 */
public class Ret implements Command {

    @Override
    public void execute() {

        ComputationalModel.setI(Fichiers.getRetAdress().pop()-1);
        ComputationalModel.setExecFonction(false);
        ComputationalModel.removeFonctionMemory();
        ComputationalModel.setLastP();

    }



}