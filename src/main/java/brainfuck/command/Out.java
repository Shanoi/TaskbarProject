package brainfuck.command;

import brainfuck.lecture.Fichiers;
import brainfuck.lecture.Run;
import brainfuck.memory.Interpreter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Out implements Command {

    private String file;
    private static String tempString = "";

    /**
     * This method allows to execute the command OUT
     */
    @Override
    public void execute() {

        Run.IncrEXEC_MOVE();
        Run.IncrDATA_READ();

        Fichiers tempfile = new Fichiers("");
        file = Interpreter.getFileOut();
        if (file.equals("")) {
            System.out.println((char) tempfile.getCm().getCurrentCaseValue());
        } else {
            file = Interpreter.getFileOut();
            try {
                FileWriter inputFile = new FileWriter(new File(file));
                FileReader tampon = new FileReader(new File(file));
                int temp = tampon.read();
                while (temp != -1) {
                    tempString += (char) temp;
                }
                tempString += (char) tempfile.getCm().getCurrentCaseValue();
                inputFile.write(tempString);
                inputFile.close();
                tampon.close();

            } catch (FileNotFoundException e) {
                
                error(tempfile);
                
                System.exit(3);

            } catch (IOException e) {
                
                error(tempfile);
                
                System.exit(3);
            }
        }

    }
    
    private void error(Fichiers tempfile) {

        if (Run.ifTrace()) {
            FileWriter file = null;
            try {
                file = new FileWriter("/Users/dev/TaskbarProject/test.txt", true);
                file.write("L'écriture dans la sortie a échouée\n"
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

    }

}
