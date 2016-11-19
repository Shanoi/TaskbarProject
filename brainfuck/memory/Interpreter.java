package brainfuck.memory;

import brainfuck.lecture.Run;
import brainfuck.lecture.Text;
import java.io.IOException;

public class Interpreter {

    //private ComputationalModel cm = new ComputationalModel();
    private String path = new String();
    //private List<String> args;

    private static String filein = "";
    private static String fileout = "";

    /**
     * Initialize the different command
     *
     * @param path
     * @param args
     * @throws java.io.IOException
     */
    public Interpreter(String path, String[] args) throws IOException {

        this.path = path;
        //this.args = new ArrayList(Arrays.asList(args));

        Run run = new Run(path);

        run.load();

        for (int i = 0; i < args.length; i++) {

            if (args[i].equals("--rewrite")) {

                new Text(path).Encod();

                return;
            }

        }

        for (int i = 0; i < args.length; i++) {

            if (args[i].equals("--check")) {

               // new Wellformed((ArrayList<EnumCommands>) run.getInstructions());
            }

        }

        for (int i = 0; i < args.length; i++) {

            if (args[i].equals("--translate")) {

                //run.EncodImage();
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

    }

    public static String getFileIn() {
        return filein;
    }

    public static String getFileOut() {
        return fileout;
    }

}
