/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brainfuck.Observer;

/**
 * @author TeamTaskbar
 */
public interface ObservableLogsImage {

    // Méthode permettant d'ajouter (abonner) un observateur.
    public void addObserver(Observateur o);

    // Méthode permettant de supprimer (résilier) un observateur.
    public void delObserver(Observateur o);

    public void notifyForLogs(int i);

}
