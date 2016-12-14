package brainfuck.command;

import brainfuck.Observer.Observable;
import brainfuck.Observer.ObservableLogs;
import brainfuck.Observer.Observateur;
import brainfuck.lecture.Fichiers;
import brainfuck.lecture.Run;
import brainfuck.lecture.Monitor;
import static brainfuck.memory.Interpreter.FLAG_trace;
import java.util.ArrayList;

/**
 *
 * @author TeamTaskbar
 */
public final class Left implements Command, Observable, ObservableLogs {

    private ArrayList observers;// Tableau d'observateurs.

    public Left() {

        Monitor observer = new Monitor();

        observers = new ArrayList();

        this.addObserver(observer);

        //this.addObserver(observer);
    }

    /**
     * Change the current cell of the memory, taking the cell at the left of the
     * current cell This method allows to execute the command LEFT
     */
    @Override
    public void execute() {

        Fichiers tempfile = new Fichiers("");

        /*Run.IncrEXEC_MOVE();
         Run.IncrDATA_MOVE();*/
        if (tempfile.getCm().getCurrentIndice() > 0) {
            tempfile.getCm().setCurrentIndice(tempfile.getCm().getCurrentIndice() - 1);
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
