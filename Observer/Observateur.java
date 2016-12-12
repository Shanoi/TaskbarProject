/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Observer;

/**
 *
 * @author Olivier
 */
public interface Observateur {

    // Méthode appelée automatiquement lorsque l'état (position ou précision) du GPS change.
    public void actualiser(Observable o);

    public void actualiserIncr();

}
