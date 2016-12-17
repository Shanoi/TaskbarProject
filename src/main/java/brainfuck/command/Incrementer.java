package brainfuck.command;

import brainfuck.Observer.Observable;
import brainfuck.Observer.ObservableLogs;
import brainfuck.Observer.Observateur;
import brainfuck.lecture.Fichiers;
import brainfuck.lecture.Monitor;
import static brainfuck.memory.Launcher.FLAG_trace;
import java.util.ArrayList;

/**
 *
 * @author TeamTaskbar
 */
public final class Incrementer implements Command, Observable, ObservableLogs {

    private ArrayList observers;// Tableau d'observateurs.

    public Incrementer() {

        Monitor observer = new Monitor();

        observers = new ArrayList();

        this.addObserver(observer);

    }

    /**
     * Increment the value of the current cell of the memory This method allows
     * to execute the command INCR
     */
    @Override
    public void execute() {
        Fichiers tempfile = new Fichiers("");

        /* Run.IncrEXEC_MOVE();
         Run.IncrDATA_WRITE();*/
        if (tempfile.getCm().getCurrentCaseValue() < 255) {
            tempfile.getCm().setCurrentCaseValue((byte) (tempfile.getCm().getCurrentCaseValue() + 1));
        } else {
            if (FLAG_trace) {
                notifyForLogs();
            }
            System.exit(1);
        }

        notifyObservers();

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
            o.updateExec_Move();// On utilise la méthode "tiré".
            o.updateData_Write();// On utilise la méthode "tiré".
        }

    }

    @Override
    public void notifyForLogs() {

        for (int i = 0; i < observers.size(); i++) {
            Observateur o = (Observateur) observers.get(i);
            o.logsDecr();// On utilise la méthode "tiré".

        }

    }

}
