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
public final class Back implements Command, Observable {

    int i = 0;

     private ArrayList observers;// Tableau d'observateurs.

    public Back() {

        StatProg observer = new StatProg();

        observers = new ArrayList();

        this.addObserver(observer);
        
        //this.addObserver(observer);
    }

    /**
     * This method allows to execute the command BACK
     */
    @Override
    public void execute() {

        Fichiers tempfile = new Fichiers("");

        /*Run.IncrEXEC_MOVE();
        Run.IncrDATA_READ();*/

        if (tempfile.getCm().getCurrentCaseValue() > 0) {

            tempfile.getCm().setI(tempfile.backAssoc(tempfile.getCm().getI()));

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
            o.updateData_Read();// On utilise la méthode "tiré".
        }

    }

}
