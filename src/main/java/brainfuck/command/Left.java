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
public class Left implements Command {

    /**
     * Change the current cell of the memory, taking the cell at the left of the
     * current cell This method allows to execute the command LEFT
     */
    @Override
    public void execute() {

        Fichiers tempfile = new Fichiers("");

        Run.IncrEXEC_MOVE();
        Run.IncrDATA_MOVE();

        if (tempfile.getCm().getCurrentIndice() > 0) {
            tempfile.getCm().setCurrentIndice(tempfile.getCm().getCurrentIndice() - 1);
        } else {

            if (Run.ifTrace()) {
                FileWriter file = null;
                try {
                    file = new FileWriter("/Users/dev/TaskbarProject/test.txt", true);
                    file.write("Le déplacement dans la mémoire vers la gauche a échoué\n"
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
