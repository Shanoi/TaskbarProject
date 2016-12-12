package brainfuck.lecture;

import Observer.Observable;
import Observer.Observateur;
import brainfuck.command.Command;
import brainfuck.command.Incrementer;
import brainfuck.memory.ComputationalModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public final class Run implements Observable {

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

    private ArrayList observers;// Tableau d'observateurs.

    public Run() {

        path = "";
        cm = new ComputationalModel();

    }

    /**
     * Allows to run the path
     *
     * @param path
     */
    public Run(String path) {

        cm = new ComputationalModel();
        this.path = path;

        StatProg observer = new StatProg();

        observers = new ArrayList();

        this.addObserver(observer);

    }

    //=================
    //Counters
    //=================
    /**
     * Counter of the EXEC MOVE
     */
    public static void IncrEXEC_MOVE() {
        Run.EXEC_MOVE++;
    }

    /**
     * Counter of the DATA MOVE
     */
    public static void IncrDATA_MOVE() {
        Run.DATA_MOVE++;
    }

    /**
     * Counter of the DATA WRITE
     */
    public static void IncrDATA_WRITE() {
        Run.DATA_WRITE++;
    }

    /**
     * Counter of the DATA READ
     */
    public static void IncrDATA_READ() {
        Run.DATA_READ++;
    }

    /**
     * Allows to read a fichier whether it's a .txt or .bmp(converted to .txt)
     *
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

    /**
     * Allows to calculate the different stats related to the executed program
     *
     * @throws IOException
     * @throws FileNotFoundException
     */
    public void execute() throws IOException, FileNotFoundException {
        String str = ""; // the execution step number (starting at one), the location of the execution pointer after the execution of this step, the location of the data pointer at the very same time, and a snapshot of the memory.
        System.out.println("EXEC");
        long instantA = System.currentTimeMillis();
        notifyObservers();
        cm.init();

        FileWriter file;

        if (true) {

            file = new FileWriter("D:/monFichier.txt", true);

        }

        while (cm.getI() < Fichiers.list.size()) {

            Fichiers.list.get(i).getCommand().execute();
            if (true) {
                file.write("Execution step number: " + EXEC_MOVE + " \n"
                        + "Pointer of the execution: " + cm.getI() + " \n"
                        + "Location of the data pointer: " + cm.getCurrentIndice() + "\n"
                        + "Affichage de la mémoire\n" + cm.toString() + "\n");
            }

            i = (cm.getI() + 1);
            cm.setI(i);
        }

        System.out.println("STR --- " + str);

        file.close();

        /*File file = new File("D:/res.txt");
         PrintWriter writer = new PrintWriter("D:/res.txt", "UTF-8");
         writer.println(str);*/
        long instantB = System.currentTimeMillis();
        notifyObservers();
        EXEC_TIME = instantB - instantA;

        System.out.println(new StatProg());

        afficheStats();

    }

    /**
     * Allows to display the differents stats related to the executed program
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
     * Setter of the trace
     *
     * @param trace1
     */
    public void setTrace(boolean trace1) {

        trace = trace1;
    }

    /**
     * Getter of fichier
     *
     * @return
     */
    public Fichiers getFichier() {

        return this.fichier;
    }

    /**
     * Getter of the cm
     *
     * @return
     */
    public ComputationalModel getCm() {
        return cm;
    }

    /*@Override
     public void update(Observable o, Object o1) {
        
     if (o instanceof Incrementer){
            
     System.out.println("Updated");
            
     }
        
        
        
     }*/
    @Override
    public void addObserver(Observateur o) {

        observers.add(o);

    }

    @Override
    public void delObserver(Observateur o) {

        observers.remove(0);

    }

    @Override
    public void notifyObservers() {

        for (int i = 0; i < observers.size(); i++) {
            Observateur o = (Observateur) observers.get(i);
            o.updateTime();// On utilise la méthode "tiré".

        }

    }
}
