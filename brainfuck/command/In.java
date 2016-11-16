package brainfuck.command;

import brainfuck.ComputationalModel;
import brainfuck.Text;
import brainfuck.lecture.Run;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is used to put a ASCII character's values to the current memory case pointed.
 * it can take an input file with different character or ask a character.
 */
public class In implements Command {

    private String file;
    private String str;
    private static int cnt = 0;
    private int temp = 0;
    private int text_length = 0;
    private static int state = 0;
    private static ArrayList<Integer> text_list = new ArrayList<Integer>();

    /**
     * parameter file contain the input file adress.
     * if not, the program will be executed asking a new character for each IN in the program.
     * @param file
     */
    public void In(String file) {
        this.file = file;
    }

    /**
     * method execute the IN function
     */
    @Override
    public void execute() {
        Run.EXEC_MOVE++;
        file = Text.getFileIn();
        if (file.equals("")) {
            ComputationalModel cm = new ComputationalModel();
            Scanner sc = new Scanner(System.in);
            str = sc.nextLine();
            if (str.length() > 0) {
                cm.setCurrentCaseValue((byte) str.charAt(0));
            } else {
                System.exit(3);
            }
        } else {
            ComputationalModel cm = new ComputationalModel();
            File inputFile = new File(file);
            FileReader in = null;
            try {
                in = new FileReader(inputFile);

                if (state == 0) {
                    temp = in.read();
                    while (temp != -1) {
                        text_list.add(temp);
                        temp = in.read();
                    }
                    text_length = text_list.size();
                    state = 1;
                }

                if (cnt < text_length - 1) {
                    cm.setCurrentCaseValue((byte) (char) text_list.get(cnt).intValue());
                    cnt++;
                } else {
                    System.exit(3);
                }

            } catch (FileNotFoundException e) {
                System.exit(3);

            } catch (IOException e) {
                System.exit(3);
                
            }
        }

    }
}
