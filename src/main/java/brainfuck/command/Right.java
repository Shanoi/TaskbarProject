package brainfuck.command;

import brainfuck.Observer.Observable;
import brainfuck.Observer.ObservableLogs;
import brainfuck.Observer.Observateur;
import brainfuck.lecture.Fichiers;
import brainfuck.lecture.Monitor;
import java.util.ArrayList;

import static brainfuck.memory.Launcher.FLAG_trace;

/**
 * This class represents the command RIGHT of the Brainf*ck langage.
 *
 * @author TeamTaskbar
 */
public final class Right implements Command, Observable, ObservableLogs {

    private ArrayList observers;// Tableau d'observateurs.

    public Right() {

        Monitor observer = new Monitor();

        observers = new ArrayList();

        this.addObserver(observer);

        //this.addObserver(observer);
    }

    /**
     * Change the current cell of the memory, taking the cell at the right of
     * the current cell This method allows to execute the command RIGHT.
     */
    @Override
    public void execute() {

        Fichiers tempfile = new Fichiers("");

        if (tempfile.getCm().getCurrentIndice() < tempfile.getCm().getMemorySize()) {

            tempfile.getCm().setCurrentIndice(tempfile.getCm().getCurrentIndice() + 1);

        } else {

            if (FLAG_trace) {
                notifyForLogs();
            }
            System.exit(2);

        }

        notifyObservers();

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
            o.updateExec_Move();// On utilise la méthode "tiré".
            o.updateData_Move();// On utilise la méthode "tiré".
        }

    }

    @Override
    /**
     * This method allows to update the observers for the log command.
     */
    public void notifyForLogs() {

        for (int i = 0; i < observers.size(); i++) {
            Observateur o = (Observateur) observers.get(i);
            o.logsDecr();// On utilise la méthode "tiré".

        }

    }

}
