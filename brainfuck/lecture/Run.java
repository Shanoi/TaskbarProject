package brainfuck.lecture;

import Observer.Observable;
import Observer.ObservableLogs;
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

public final class Run implements Observable, ObservableLogs {

    private static boolean trace = false;

    private boolean time = true;

    protected final String path;

    private final ComputationalModel cm;

    private Fichiers fichier;

    //protected static final List<EnumCommands> list = new ArrayList<>();
    private int i = 0;

    private ArrayList observers;// Tableau d'observateurs.

    public Run() {

        path = "";
        cm = new ComputationalModel(); //Passer le cm dans StatProg

    }

    /**
     * Allows to run the path
     *
     * @param path
     */
    public Run(String path) throws IOException {

        cm = new ComputationalModel();
        this.path = path;

        StatProg observer = new StatProg(path + ".log");

        observers = new ArrayList();

        this.addObserver(observer);

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
        String str = "";    //the execution step number (starting at one), 
        //the location of the execution pointer after the execution of this step, 
        //the location of the data pointer at the very same time, and a snapshot of the memory.

        System.out.println("EXEC");

        notifyObservers();

        cm.init();

        /*FileWriter file;

        if (true) {

            file = new FileWriter("D:/monFichier.txt", true);

        }*/

        while (cm.getI() < Fichiers.list.size()) {

            Fichiers.list.get(i).getCommand().execute();
            /*if (true) {
             file.write("Execution step number: " + EXEC_MOVE + " \n"
             + "Pointer of the execution: " + cm.getI() + " \n"
             + "Location of the data pointer: " + cm.getCurrentIndice() + "\n"
             + "Affichage de la mémoire\n" + cm.toString() + "\n");
             }*/

            notifyForLogs();

            i = (cm.getI() + 1);
            cm.setI(i);
        }

        //file.close();

        /*File file = new File("D:/res.txt");
         PrintWriter writer = new PrintWriter("D:/res.txt", "UTF-8");
         writer.println(str);*/
        notifyObservers();
        //afficheStats();
        System.out.println(new StatProg());

    }

    /**
     * Allows to display the differents stats related to the executed program
     */
    /*public void afficheStats() {

     System.out.println("Nombre d'instructions file : " + fichier.getNbI());

     }*/
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
            o.updateNb_Instr(Fichiers.list.size());// On utilise la méthode "tiré".

        }

    }

    @Override
    public void notifyForLogs() {

        for (int i = 0; i < observers.size(); i++) {
            Observateur o = (Observateur) observers.get(i);
            o.traceLog();
        }

    }
}
