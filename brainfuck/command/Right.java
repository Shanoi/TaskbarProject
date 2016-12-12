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
public final class Right implements Command, Observable {

    private ArrayList observers;// Tableau d'observateurs.

    public Right() {

        StatProg observer = new StatProg();

        observers = new ArrayList();

        this.addObserver(observer);
        
        //this.addObserver(observer);
    }
    
    /**
     * Change the current cell of the memory, taking the cell at the right of
     * the current cell
     * This method allows to execute the command RIGHT
     */
    @Override
    public void execute() {

        Run.IncrEXEC_MOVE();
        Run.IncrDATA_MOVE();

        Fichiers tempfile = new Fichiers("");

        if (tempfile.getCm().getCurrentIndice() < tempfile.getCm().getMemorySize()) {

            tempfile.getCm().setCurrentIndice(tempfile.getCm().getCurrentIndice() + 1);

        } else {
            System.out.println("RIGHT");
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
