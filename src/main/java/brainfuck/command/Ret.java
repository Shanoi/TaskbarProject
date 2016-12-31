package brainfuck.command;

import brainfuck.lecture.Fichiers;
import brainfuck.memory.ComputationalModel;


public class Ret implements Command {

    @Override
    public void execute() {
        System.out.println("NUM2 LINE --- " + Fichiers.getRetAdress().peek());
        ComputationalModel.setI(Fichiers.getRetAdress().pop()-1);
        ComputationalModel.setExecFonction(false);
        ComputationalModel.removeFonctionMemory();
        ComputationalModel.setLastP();

    }



}