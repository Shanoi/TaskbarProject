package brainfuck.command;

import brainfuck.memory.ComputationalModel;
import brainfuck.lecture.Fichiers;

/**
 * This class represents the command CALL. It allows to call the different
 * functions in a Brainf*ck program.
 *
 * @author TeamTaskbar
 */
public class Call implements Command {

    @Override
    /**
     * This method allows to execute the command Call.
     */
    public void execute() {

        Fichiers.setRetAdress(ComputationalModel.getI() + 1);
        //System.out.println("NUM FONC --- " + Fichiers.getFonction(ComputationalModel.getI()+1));
        String fct = Fichiers.getFonction(ComputationalModel.getI() + 1);
        int numLine = Fichiers.getFonction2(fct);
        //System.out.println("NUM FONC --- " + Fichiers.getFonction(ComputationalModel.getI()+1));
        ComputationalModel.setI(numLine - 1);

        //ici on recherche la fonction appelé dans la hashmap et stocké dans la stack
        //on stocke l'adresse de retour
        ComputationalModel.setTmpP();
        ComputationalModel.setExecFonction(true);
        ComputationalModel.setPFonction();
        (EnumCommands.toCommand("RIGHT")).getCommand().execute();
        int nbArg = Fichiers.getNbArgFonction(fct);
        while (nbArg > 0) {
            ComputationalModel.setCurrentCaseValue((byte) Fichiers.getArguments());
            (EnumCommands.toCommand("RIGHT")).getCommand().execute();
            nbArg--;
        }
    }

}
