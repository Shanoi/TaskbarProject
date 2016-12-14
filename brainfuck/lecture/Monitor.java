/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brainfuck.lecture;

import Observer.Observateur;
import brainfuck.command.Decrementer;
import brainfuck.memory.ComputationalModel;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Olivier
 */
public class Monitor implements Observateur {

    private static int NB_INSTR = 0;
    private static long EXEC_TIME = 0;
    private static int EXEC_MOVE = 0;
    private static int DATA_MOVE = 0;
    private static int DATA_WRITE = 0;
    private static int DATA_READ = 0;
    private static PrintWriter file;

    private static String fileuh;

    private final ComputationalModel cm;

    public Monitor(String fileuh) throws IOException {
        //StatProg.file = new FileWriter(file, true);

        //File f = new File(file);
        this.fileuh = fileuh;

        file = new PrintWriter(new FileWriter(fileuh));

        //StatProg.file = new FileWriter(f, true);
        cm = new ComputationalModel();

        cm.init();

    }

    public Monitor() {

        cm = new ComputationalModel();

    }

    @Override
    public String toString() {

        return "Nombre d'instructions: " + NB_INSTR + "\n"
                + "Nombre de déplacements du pointeur d'instruction: " + EXEC_MOVE + "\n"
                + "Nombre de déplacements dans la mémoire: " + DATA_MOVE + "\n"
                + "Nombre d'écritures dans la mémoire: " + DATA_WRITE + "\n"
                + "Nombre de lectures dans la mémoire: " + DATA_READ + "\n"
                + "Temps d'exécution: " + EXEC_TIME;

    }

    @Override
    public void actualiser(Observer.Observable o) {

    }

    @Override
    public void updateExec_Move() {

        EXEC_MOVE++;

    }

    @Override
    public void updateData_Write() {

        DATA_WRITE++;

    }

    @Override
    public void updateData_Read() {

        DATA_READ++;

    }

    @Override
    public void updateData_Move() {

        DATA_MOVE++;

    }

    @Override
    public void updateNb_Instr(int i) {

        NB_INSTR = i;

    }

    @Override
    public void updateTime() {

        EXEC_TIME = Math.abs(EXEC_TIME - System.currentTimeMillis());

    }

    @Override
    public void traceLog() {

        writer("Execution step number: " + EXEC_MOVE + " \n"
                + "Pointer of the execution: " + cm.getI() + " \n"
                + "Location of the data pointer: " + cm.getCurrentIndice() + "\n"
                + "Affichage de la mémoire\n" + cm.toString() + "\n\n");
    }

    @Override
    public void logsDecr() {

        writer("Décrémenter la cellule courante a échoué\n"
                + "L'instruction n°" + EXEC_MOVE + " a échouée");

    }

    @Override
    public void logsIncr() {

        writer("Incrémenter la cellule courante a échoué\n"
                + "L'instruction n°" + EXEC_MOVE + " a échouée");

    }

    @Override
    public void logsIn() {

        writer("La lecture de l'entrée a échouée\n"
                + "L'instruction n°" + EXEC_MOVE + " a échouée");

    }

    @Override
    public void logsLeft() {

        writer("Le déplacement dans la mémoire vers la gauche a échoué\n"
                + "L'instruction n°" + EXEC_MOVE + " a échouée");

    }

    @Override
    public void logsOut() {

        writer("L'écriture dans la sortie a échouée\n"
                + "L'instruction n°" + EXEC_MOVE + " a échouée");

    }

    @Override
    public void logsRight() {

        writer("Le déplacement dans la mémoire vers la droite a échoué\n"
                + "L'instruction n°" + EXEC_MOVE + " a échouée");

    }

    private void writer(String logs) {

        try {
            file = new PrintWriter(new FileWriter(fileuh, true), true);
        } catch (IOException ex) {
            Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
        }

        file.write(logs);

        file.close();

    }

    @Override
    public void logsImage(int N) {

        writer("La couleur ne correspond pas à une couleur d'instruction\n"
                + "La lecture de l'instruction n°" + N + " a échouée\n"
                + "Voir le code d'erreur pour plus d'informations\n");

    }

    @Override
    public void logsTxt(String string, int i) {

        writer("La lecture de l'instruction a échouée\n"
                + string + "\n"
                + "La lecture de l'instruction n°" + i + " a échouée");

    }

}
