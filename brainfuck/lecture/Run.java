package brainfuck.lecture;

import brainfuck.memory.ComputationalModel;
import java.io.FileNotFoundException;
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

    public ComputationalModel getCm() {
        return cm;
    }

    public static void IncrEXEC_MOVE() {
        Run.EXEC_MOVE++;
    }

    public static void IncrDATA_MOVE() {
        Run.DATA_MOVE++;
    }

    public static void IncrDATA_WRITE() {
        Run.DATA_WRITE++;
    }

    public static void IncrDATA_READ() {
        Run.DATA_READ++;
    }

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
        String str; // the execution step number (starting at one), the location of the execution pointer after the execution of this step, the location of the data pointer at the very same time, and a snapshot of the memory.



        String str = new String();

        long instantA = System.currentTimeMillis();
        
        cm.init();

        while (cm.getI() < Fichiers.list.size()) {

            Fichiers.list.get(i).getCommand().execute();
            if (trace)
                str += ("Execution step number: " + EXEC_MOVE  +" \nPointer of the execution: "  + cm.getI()  +" \nLocation of the data pointer: " + cm.getCurrentIndice() + " Affichage de la mémoire\n"+cm.toString() +"\n" );

            i = (cm.getI() + 1);
            cm.setI(i);
        }
        
        
        PrintWriter writer = new PrintWriter("results.txt", "UTF-8");
        writer.println(str);
        
        long instantB = System.currentTimeMillis();
        EXEC_TIME = instantB - instantA;
        afficheStats();

    }

    public void afficheStats() {

        System.out.println("Nombre d'instructions: " + fichier.getNbI());
        System.out.println("Temps d'executions: " + EXEC_TIME);
        System.out.println("Nombre de déplacements du pointeur d'instruction: " + EXEC_MOVE);
        System.out.println("Nombre de déplacements dans la mémoire: " + DATA_MOVE);
        System.out.println("Nombre d'écritures dans la mémoire: " + DATA_WRITE);
        System.out.println("Nombre de lectures dans la mémoire: " + DATA_READ);

    }

    public void setTrace(boolean trace1)
    {
        
        trace = trace1;
    }
    public Fichiers getFichier() {

        return this.fichier;
    }

}
