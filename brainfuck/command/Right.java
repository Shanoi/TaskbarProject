package brainfuck.command;

import Observer.Observable;
import Observer.ObservableLogs;
import Observer.Observateur;
import brainfuck.lecture.Fichiers;
import brainfuck.lecture.Run;
import brainfuck.lecture.Monitor;
import static brainfuck.memory.Interpreter.FLAG_trace;
import java.util.ArrayList;

/**
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
     * the current cell This method allows to execute the command RIGHT
     */
    @Override
    public void execute() {

        /*Run.IncrEXEC_MOVE();
         Run.IncrDATA_MOVE();*/
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
            o.updateData_Move();// On utilise la méthode "tiré".
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
