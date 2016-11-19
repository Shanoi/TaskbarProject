package brainfuck.command;

import brainfuck.lecture.Fichiers;
import brainfuck.lecture.Run;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author TeamTaskbar
 */
public class InText implements Command {

    private String file;
    private static int cnt = 0;
    private int temp = 0;

    public void InText(String file) {
        this.file = file;
    }

    @Override
    public void execute() {

        Run.IncrEXEC_MOVE();
        Run.IncrDATA_WRITE();

        Fichiers tempfile = new Fichiers("");
        File inputFile = new File(file);
        FileReader in = null;

        try {
            in = new FileReader(inputFile);

            for (int i = 0; i <= cnt; i++) {
                temp = in.read();
            }
            if (temp != -1) {
                tempfile.getCm().setCurrentCaseValue((byte) (char) temp);
                cnt++;
            } else {
                System.exit(3);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
