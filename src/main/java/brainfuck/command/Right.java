package brainfuck.command;

import brainfuck.lecture.Fichiers;
import brainfuck.lecture.Run;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TeamTaskbar
 */
public class Right implements Command {

    /**
     * Change the current cell of the memory, taking the cell at the right of
     * the current cell
     * This method allows to execute the command RIGHT
     */
    @Override
    public void execute() {

        Run.IncrEXEC_MOVE();
        Run.IncrDATA_MOVE();

        Fichiers tempfile = new Fichiers("");

        if (tempfile.getCm().getCurrentIndice() < tempfile.getCm().getMemorySize()) {

            tempfile.getCm().setCurrentIndice(tempfile.getCm().getCurrentIndice() + 1);

        } else {

            if (Run.ifTrace()) {
                FileWriter file = null;
                try {
                    file = new FileWriter("/Users/dev/TaskbarProject/test.txt", true);
                    file.write("Le déplacement dans la mémoire vers la droite a échoué\n"
                            + "L'instruction n°" + tempfile.getCm().getI() + " a échouée");
                    file.close();
                } catch (IOException ex) {
                    Logger.getLogger(Decrementer.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        file.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Decrementer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
            System.exit(2);

        }
    }

}
