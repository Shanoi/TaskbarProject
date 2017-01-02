package brainfuck.lecture;

import brainfuck.Observer.Observable;
import brainfuck.Observer.ObservableLogs;
import brainfuck.Observer.Observateur;
import brainfuck.memory.ComputationalModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static brainfuck.memory.Launcher.FLAG_trace;

/**
 * This class represents the running process. It allows to run a program (textual or an image).
 *
 * @author Team Taskbar
 */
public final class Run implements Observable, ObservableLogs {

    protected final String path;

    private final ComputationalModel cm;

    private Fichiers fichier;

    private int i = 0;

    private ArrayList observers;// Tableau d'observateurs.

    public Run() {

        path = "";
        cm = new ComputationalModel(); //Passer le cm dans Monitor

    }

    /**
     * Allowing to run the path.
     *
     * @param path
     */
    public Run(String path) throws IOException {

        cm = new ComputationalModel();
        this.path = path;

        Monitor observer = new Monitor(path + ".log");

        observers = new ArrayList();

        this.addObserver(observer);

    }

    /**
     * This method allows to load the given file.
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
        fichier.setTableLoop();

    }

    /**
     * This method allows to execute the program.
     *
     * @throws IOException
     * @throws FileNotFoundException
     */
    public void execute() throws IOException, FileNotFoundException {
        String str = ""; // the execution step number (starting at one), the location of the execution pointer after the execution of this step, the location of the data pointer at the very same time, and a snapshot of the memory.
        System.out.println("EXEC");
        notifyObservers();

        cm.init();
        i = cm.getI();
        while (cm.getI() < Fichiers.list.size()) {
            System.out.println("I:" +cm.getI() + Fichiers.list.get(0).getLong());
            Fichiers.list.get(i).getCommand().execute();

            //Fichiers.list.get(i).getLong();

            if (FLAG_trace) {

                notifyForLogs();

            }

            i = (cm.getI() + 1);
            cm.setI(i);
        }

        notifyObservers();

        System.out.println(new Monitor());

    }

    //=================
    //Getter and Setter
    //=================

    /**
     * Getter of the file (fichiers).
     * @return a file.
     */
    public Fichiers getFichier() {

        return this.fichier;
    }

    /**
     * Getter of the object computational model.
     * @return a ComputationalModel object.
     */
    public ComputationalModel getCm() {
        return cm;
    }

    @Override
    /**
     * This method allows to add an observer.
     */
    public void addObserver(Observateur o) {

        observers.add(o);

    }

    @Override
    /**
     * This method allows to delete an observer previously added.
     */
    public void delObserver(Observateur o) {

        observers.remove(0);

    }

    @Override
    /**
     * This method allows the observers to update their information.
     */
    public void notifyObservers() {

        for (int i = 0; i < observers.size(); i++) {
            Observateur o = (Observateur) observers.get(i);
            o.updateTime();// On utilise la méthode "tiré".
            o.updateNb_Instr(Fichiers.list.size());// On utilise la méthode "tiré".

        }

    }

    @Override
    /**
     * This method allows to update the observers for the log command.
     */
    public void notifyForLogs() {

        for (int i = 0; i < observers.size(); i++) {
            Observateur o = (Observateur) observers.get(i);
            o.traceLog();
        }

    }

}
