package brainfuck.memory;

import brainfuck.lecture.*;
import java.util.ArrayList;
import brainfuck.command.EnumCommands;
import brainfuck.lecture.Run;
import brainfuck.lecture.Text;
import java.io.IOException;

public class Interpreter {

    //private ComputationalModel cm = new ComputationalModel();
    private String path = new String();
    private String[] args;
    //private List<String> args;

    private static String filein = "";
    private static String fileout = "";

    /**
     * Initialize the different command
     *
     * @param path
     * @param args
     */
    public Interpreter(String path, String[] args) {

        this.path = path;
        this.args = args;

    }

    public void lauchInterpreter() throws IOException {

         //this.args = new ArrayList(Arrays.asList(args));
        Run run = new Run(path);

        run.load();

        for (int i = 0; i < args.length; i++) {

            if (args[i].equals("--trace")) {

                run.setTrace(true);

                return;
            }

        }

        for (int i = 0; i < args.length; i++) {

            if (args[i].equals("--rewrite")) {

                new Text(path).Encod();

                return;
            }

        }

        for (int i = 0; i < args.length; i++) {

            if (args[i].equals("--check")) {

                new Wellformed((ArrayList<EnumCommands>) run.getFichier().getInstructions()).execute();
            }

        }

        for (int i = 0; i < args.length; i++) {

            if (args[i].equals("--translate")) {

                new Image(path).Encod();
            }

        }

        for (int i = 0; i < args.length; i++) {

            if (args[i].equals("-o")) {

                fileout = args[i + 1];

            }

            if (args[i].equals("-i")) {

                filein = args[i + 1];

            }

        }

        run.execute();
        ComputationalModel.affichememoire();

    }

    /**
     * Getter of the filein
     * @return
     */
    public static String getFileIn() {
        return filein;
    }

    /**
     * Getter of the fileout
     * @return
     */
    public static String getFileOut() {
        return fileout;
    }

}
