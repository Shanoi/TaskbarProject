package brainfuck.command;

import Observer.Observable;
import Observer.Observateur;
import brainfuck.lecture.Fichiers;
import brainfuck.lecture.Run;
import brainfuck.lecture.Monitor;
import java.util.ArrayList;

/**
 * @author TeamTaskbar
 */
public final class Jump implements Command, Observable {

    private ArrayList observers;// Tableau d'observateurs.

    public Jump() {

        Monitor observer = new Monitor();

        observers = new ArrayList();

        this.addObserver(observer);
        
        //this.addObserver(observer);
    }
    
    /**
     * This method allows to execute the command JUMP
     */
    @Override
    public void execute() {

        Fichiers tempfile = new Fichiers("");

        /*Run.IncrEXEC_MOVE();
        Run.IncrDATA_READ();*/

        if (tempfile.getCm().getCurrentCaseValue() == 0) {

            tempfile.getCm().setI(tempfile.jumpAssoc(tempfile.getCm().getI()));

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