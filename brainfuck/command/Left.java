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
public final class Left implements Command, Observable {

    private ArrayList observers;// Tableau d'observateurs.

    public Left() {

        StatProg observer = new StatProg();

        observers = new ArrayList();

        this.addObserver(observer);
        
        //this.addObserver(observer);
    }
    
    /**
     * Change the current cell of the memory, taking the cell at the left of the
     * current cell
     * This method allows to execute the command LEFT
     */
    @Override
    public void execute() {

        Fichiers tempfile = new Fichiers("");

        /*Run.IncrEXEC_MOVE();
        Run.IncrDATA_MOVE();*/

        if (tempfile.getCm().getCurrentIndice() > 0) {
            tempfile.getCm().setCurrentIndice(tempfile.getCm().getCurrentIndice() - 1);
        } else {
            System.out.println("LEFT");
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
    
}
