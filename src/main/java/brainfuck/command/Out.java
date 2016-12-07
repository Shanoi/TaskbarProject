package brainfuck.command;

import brainfuck.lecture.Fichiers;
import brainfuck.lecture.Run;
import brainfuck.memory.Interpreter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Out implements Command {

    private String file;
    private static String tempString = "";

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
                System.exit(3);

            } catch (IOException e) {
                System.exit(3);
            }
        }

    }

}
