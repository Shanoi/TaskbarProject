package brainfuck.command;

import Observer.Observable;
import Observer.Observateur;
import brainfuck.lecture.Fichiers;
import brainfuck.lecture.Run;
import brainfuck.lecture.StatProg;
import java.util.ArrayList;

/**
 *
 * @author TeamTaskbar
 */
public final class Decrementer implements Command, Observable {

    private ArrayList observers;// Tableau d'observateurs.

    public Decrementer() {

        StatProg observer = new StatProg();

        observers = new ArrayList();

        this.addObserver(observer);

    }

    /**
     * Decr : Decrement the value of the current cell of the memory This method
     * allows to execute the command DECR
     */
    @Override
    public void execute() {

        Fichiers tempfile = new Fichiers("");

        Run.IncrEXEC_MOVE();
        Run.IncrDATA_WRITE();

        if (tempfile.getCm().getCurrentCaseValue() > 0) {
            tempfile.getCm().setCurrentCaseValue((byte) (tempfile.getCm().getCurrentCaseValue() - 1));
        } else {
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

}
