package brainfuck.command;

import brainfuck.memory.ComputationalModel;
import brainfuck.lecture.Fichiers;

/**
 * This class represents the arguments in functions or procedures.
 *
 * @author Team Taskbar
 */
public class Arg implements Command {

    @Override
    public void execute() {
        Fichiers.setArguments(ComputationalModel.getCurrentCaseValue());
        /*Fichiers.setRetAdress(ComputationalModel.getI()+1);
        //System.out.println("NUM FONC --- " + Fichiers.getFonction(ComputationalModel.getI()+1));

        int numLine = Fichiers.getFonction2(Fichiers.getFonction(ComputationalModel.getI()+1));
        //System.out.println("NUM FONC --- " + Fichiers.getFonction(ComputationalModel.getI()+1));
        ComputationalModel.setI(numLine-1);
        System.out.println("NUM LINE --- " + numLine);
        //ici on recherche la fonction appelé dans la hashmap et stocké dans la stack
        //on stocke l'adresse de retour
        ComputationalModel.setTmpP();
        ComputationalModel.setExecFonction(true);
        ComputationalModel.setPFonction();
        (EnumCommands.toCommand("RIGHT")).getCommand().execute();*/
    }


}