package brainfuck.lecture;

import brainfuck.memory.ComputationalModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Run {

    private static long EXEC_TIME = 0;
    private static int EXEC_MOVE = 0;
    private static int DATA_MOVE = 0;
    private static int DATA_WRITE = 0;
    private static int DATA_READ = 0;
    private static boolean trace = false;
    protected final String path;

    private final ComputationalModel cm;

    private Fichiers fichier;

    //protected static final List<EnumCommands> list = new ArrayList<>();
    private int i = 0;

    public Run(String path) {

        cm = new ComputationalModel();
        this.path = path;

    }

    //=================
    //Counters
    //=================

    /**
     * Counter of the execution time of the program, in milliseconds
     */
    public static void IncrEXEC_MOVE() {
        Run.EXEC_MOVE++;
    }

    /**
     * Counter of the number of time the data pointer was moved to execute this program
     */
    public static void IncrDATA_MOVE() {
        Run.DATA_MOVE++;
    }

    /**
     * Counter of the number of time the memory was accessed to change its contents
     * while executing this program
     */
    public static void IncrDATA_WRITE() {
        Run.DATA_WRITE++;
    }

    /**
     * Counter of the number of times the memory was accessed to read its contents
     */
    public static void IncrDATA_READ() {
        Run.DATA_READ++;
    }

    /**
     * This method allows to load the given file
     * @throws IOException
     * @throws FileNotFoundException
     */
    public void load() throws IOException, FileNotFoundException {

        if (path.lastIndexOf(".") > 0) {

            // On récupère l'extension du fichier
            String ext = path.substring(path.lastIndexOf("."));

            // Si le fichier n'est pas en .txt on le met en .txt
            if (".bmp".equals(ext)) {

                fichier = new Image(path);

            } else {

                fichier = new Text(path);

            }

        } else {

            fichier = new Text(path);

        }

        fichier.Read();

    }

    public void execute() throws IOException, FileNotFoundException {
        String str = ""; // the execution step number (starting at one), the location of the execution pointer after the execution of this step, the location of the data pointer at the very same time, and a snapshot of the memory.
        System.out.println("EXEC");
        long instantA = System.currentTimeMillis();

        cm.init();

        //FileWriter file = new FileWriter();

        /*if (trace) {
            FileWriter file;

            file = new FileWriter("/Users/dev/TaskbarProject/test.txt ", true);

        }*/

        while (cm.getI() < Fichiers.list.size()) {

            Fichiers.list.get(i).getCommand().execute();
            if (trace) {
                FileWriter file = new FileWriter("/Users/dev/TaskbarProject/test.txt", true);
                file.write("Execution step number: " + EXEC_MOVE + " \nPointer of the execution: " + cm.getI() + " \nLocation of the data pointer: " + cm.getCurrentIndice() + "\n Affichage de la mémoire\n" + cm.toString() + "\n");
                file.close();
            }

            i = (cm.getI() + 1);
            cm.setI(i);
        }

        /*if (trace) {
            
            file.close();
            
        }*/
        

        /*File file = new File("D:/res.txt");
         PrintWriter writer = new PrintWriter("D:/res.txt", "UTF-8");
         writer.println(str);*/
        long instantB = System.currentTimeMillis();
        EXEC_TIME = instantB - instantA;
        afficheStats();

    }

    /**
     * This method allows to display the differents stats(counters) of a program
     */
    public void afficheStats() {

        System.out.println("Nombre d'instructions: " + fichier.getNbI());
        System.out.println("Temps d'executions: " + EXEC_TIME);
        System.out.println("Nombre de déplacements du pointeur d'instruction: " + EXEC_MOVE);
        System.out.println("Nombre de déplacements dans la mémoire: " + DATA_MOVE);
        System.out.println("Nombre d'écritures dans la mémoire: " + DATA_WRITE);
        System.out.println("Nombre de lectures dans la mémoire: " + DATA_READ);

    }


    //=================
    //Getter and Setter
    //=================

    /**
     * Setter of the Trace
     */
    public void setTrace(boolean trace1) {

        trace = trace1;
    }

    /**
     * Getter of the file (fichiers)
     * @return
     */
    public Fichiers getFichier() {

        return this.fichier;
    }


    /**
     * Getter of the object computational model
     * @return
     */
    public ComputationalModel getCm() {
        return cm;
    }

}
