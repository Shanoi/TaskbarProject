package brainfuck.memory;

import brainfuck.command.EnumCommands;
import brainfuck.lecture.*;
import brainfuck.lecture.Run;
import brainfuck.lecture.Text;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This class manages the input file and the input commands. It gives the path
 * of the file for the IN/OUT command. It launches the Run. It also executes the
 * considered commands.
 *
 * @author Team Taskbar
 */
public class Launcher {

    public static boolean FLAG_trace = false;

    //private ComputationalModel cm = new ComputationalModel();
    private String path = new String();
    private String[] args;
    //private List<String> args;

    private static String filein = "";
    private static String fileout = "";

    /**
     * Initialize the different command.
     *
     * @param path the path of the file.
     * @param args
     */
    public Launcher(String path, String[] args) {

        this.path = path;
        this.args = args;

    }

    /**
     * This method allows to launch the interpreter.
     *
     * @throws IOException
     */
    public void lauchInterpreter() throws IOException {

        //this.args = new ArrayList(Arrays.asList(args));
        Run run = new Run(path);

        for (int i = 0; i < args.length; i++) {
            
            if (args[i].equals("-p")) {
                
                File f = new File(args[i + 1]);
                
                if(f.exists()){
                    
                    path = args[i + 1];
                    
                    break;
                    
                }else{
                    
                    System.exit(1);
                    
                    }
                
                
                }
            
            }
        }
    
        for (int i = 0; i < args.length; i++) {

            if (args[i].equals("--trace")) {

                FLAG_trace = true;
                break;
            }

        }

        run.load();

        for (int i = 0; i < args.length; i++) {

            if (args[i].equals("--rewrite")) {

                new Text(path).Encod();

                break;
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
        
        for (int i = 0; i < args.length; i++) {
            
            if (args[i].equals("--TCpp")) {

                new TradCpp(path).execute();
            }

            
        }

        run.execute();
        ComputationalModel.affichememoire();

    }

    //=================
    //Getter and Setter
    //=================
    /**
     * Getter of the input File.
     *
     * @return the input file.
     */
    public static String getFileIn() {
        return filein;
    }

    /**
     * Getter of the output File.
     *
     * @return the output file
     */
    public static String getFileOut() {
        return fileout;
    }

}
