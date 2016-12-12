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
public interface Observable {

    // Méthode permettant d'ajouter (abonner) un observateur.
    public void addObserver(Observateur o);

    // Méthode permettant de supprimer (résilier) un observateur.

    public void delObserver(Observateur o);

    // Méthode qui permet d'avertir tous les observateurs lors d'un changement d'état.

    public void notifyObservers();
}
