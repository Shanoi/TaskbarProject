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
public class Incrementer implements Command {

    /**
     * Increment the value of the current cell of the memory This method allows
     * to execute the command INCR
     */
    @Override
    public void execute() {
        Fichiers tempfile = new Fichiers("");

        Run.IncrEXEC_MOVE();
        Run.IncrDATA_WRITE();

        if (tempfile.getCm().getCurrentCaseValue() < 255) {
            tempfile.getCm().setCurrentCaseValue((byte) (tempfile.getCm().getCurrentCaseValue() + 1));
        } else {

            if (Run.ifTrace()) {
                FileWriter file = null;
                try {
                    file = new FileWriter("/Users/dev/TaskbarProject/test.txt", true);
                    file.write("Incrémenter la cellule courante a échoué\n"
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

            System.exit(1);
        }

    }

}
