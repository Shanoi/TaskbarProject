package brainfuck.command;

import brainfuck.Observer.Observable;
import brainfuck.Observer.Observateur;
import brainfuck.lecture.Fichiers;
import brainfuck.lecture.Monitor;

import java.util.ArrayList;

/**
 * This class represents the command Back of the Brainf*ck.
 * @author TeamTaskbar
 */
public final class Back implements Command, Observable {

    int i = 0;

    private ArrayList observers;// Tableau d'observateurs.

    //Constructor of Back

    public Back() {

        Monitor observer = new Monitor();

        observers = new ArrayList();

        this.addObserver(observer);

        //this.addObserver(observer);
    }

    /**
     * This method allows to execute the command BACK.
     */
    @Override
    public void execute() {

        Fichiers tempfile = new Fichiers("");

        if (tempfile.getCm().getCurrentCaseValue() > 0) {

            tempfile.getCm().setI(tempfile.getTableLoopAssoc(tempfile.getCm().getI()));

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
            o.updateData_Read();// On utilise la méthode "tiré".
        }

    }

}
