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
public final class Incrementer implements Command, Observable {

    private ArrayList tabObservateur;// Tableau d'observateurs.

    public Incrementer() {

        StatProg observer = new StatProg();

        tabObservateur = new ArrayList();

        this.ajouterObservateur(observer);
        
        //this.addObserver(observer);
    }

    /*public void notifyObs() {

     setChanged();
     notifyObservers();

     }*/
    /**
     * Increment the value of the current cell of the memory This method allows
     * to execute the command INCR
     */
    @Override
    public void execute() {
        Fichiers tempfile = new Fichiers("");

        Run.IncrEXEC_MOVE();
        Run.IncrDATA_WRITE();

        if (tempfile.getCm().getCurrentCaseValue() < 255) {
            tempfile.getCm().setCurrentCaseValue((byte) (tempfile.getCm().getCurrentCaseValue() + 1));
        } else {
            System.exit(1);
        }
        System.out.println("coucou");
        notifierObservateurs();
        
        //notifyObs();
    }

    @Override
    public void ajouterObservateur(Observateur o) {

        tabObservateur.add(o);

    }

    @Override
    public void supprimerObservateur(Observateur o) {

        tabObservateur.remove(0);

    }

    @Override
    public void notifierObservateurs() {

        for (int i = 0; i < tabObservateur.size(); i++) {
            Observateur o = (Observateur) tabObservateur.get(i);
            o.actualiserIncr();// On utilise la méthode "tiré".
        }

    }

}
