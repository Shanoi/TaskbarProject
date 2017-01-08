/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brainfuck.lecture;

import brainfuck.Observer.Observateur;
import brainfuck.memory.ComputationalModel;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class represents the Monitor. It allows to use the Observer Pattern and keep an eye on different classes
 * and update their information when it's needed. It also allows to use the command log which display in a file, after
 * reading a Brainf*ck program, what kind of mistake occured and where it happened.
 *
 * @author Team Taskbar
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
    /**
     * @return a string representation of the object.
     */
    public String toString() {

        return "Nombre d'instructions: " + NB_INSTR + "\n"
                + "Nombre de déplacements du pointeur d'instruction: " + EXEC_MOVE + "\n"
                + "Nombre de déplacements dans la mémoire: " + DATA_MOVE + "\n"
                + "Nombre d'écritures dans la mémoire: " + DATA_WRITE + "\n"
                + "Nombre de lectures dans la mémoire: " + DATA_READ + "\n"
                + "Temps d'exécution: " + EXEC_TIME;

    }

    @Override
    /**
     * This method allows to update the EXEC_MOVE counter.
     */
    public void updateExec_Move() {

        EXEC_MOVE++;

    }

    @Override
    /**
     * This method allows to update the DATA_WRITE counter.
     */
    public void updateData_Write() {

        DATA_WRITE++;

    }

    @Override
    /**
     * This method allows to update the DATA_READ counter.
     */
    public void updateData_Read() {

        DATA_READ++;

    }

    @Override
    /**
     * This method allwos to update the DATA_MOVE counter.
     */
    public void updateData_Move() {

        DATA_MOVE++;

    }

    @Override
    /**
     * This method allows to update the number of instructions in a program.
     */
    public void updateNb_Instr(int i) {

        NB_INSTR = i;

    }

    @Override
    /**
     * This method allows to update the EXEC_TIME counter (milliseconds).
     */
    public void updateTime() {

        EXEC_TIME = Math.abs(EXEC_TIME - System.currentTimeMillis());

    }

    @Override
    /**
     * This method allows to display the log through the trace command.
     */
    public void traceLog() {

        writer("Execution step number: " + EXEC_MOVE + " \n"
                + "Pointer of the execution: " + cm.getI() + " \n"
                + "Location of the data pointer: " + cm.getCurrentIndice() + "\n"
                + "Affichage de la mémoire\n" + cm.toString() + "\n\n");
    }

    @Override
    /**
     * This method allows to indicate when there is a decrementation of a cell and where.
     */
    public void logsDecr() {

        writer("Décrémenter la cellule courante a échoué\n"
                + "L'instruction n°" + EXEC_MOVE + " a échouée");

    }

    @Override
    /**
     * This method allows to indicate when there is a incrementation of a cell and where.
     */
    public void logsIncr() {

        writer("Incrémenter la cellule courante a échoué\n"
                + "L'instruction n°" + EXEC_MOVE + " a échouée");

    }

    @Override
    /**
     * This method allows to indicate when there is a reading failure due to an IN and where it occured.
     */
    public void logsIn() {

        writer("La lecture de l'entrée a échouée\n"
                + "L'instruction n°" + EXEC_MOVE + " a échouée");

    }

    @Override
    /**
     * This method allows to indicate when the memory failed to move left and where.
     */
    public void logsLeft() {

        writer("Le déplacement dans la mémoire vers la gauche a échoué\n"
                + "L'instruction n°" + EXEC_MOVE + " a échouée");

    }

    @Override
    /**
     * This method allows to indicate when there is a outputting failure due to an OUT and where it occured.
     */
    public void logsOut() {

        writer("L'écriture dans la sortie a échouée\n"
                + "L'instruction n°" + EXEC_MOVE + " a échouée");

    }

    @Override
    /**
     * This method allows to indicate when the memory failed to move right and where.
     */
    public void logsRight() {

        writer("Le déplacement dans la mémoire vers la droite a échoué\n"
                + "L'instruction n°" + EXEC_MOVE + " a échouée");

    }

    @Override
    /**
     * This method allows to indicate when a color didn't match with the specific instruction and where.
     */
    public void logsImage(int N) {

        writer("La couleur ne correspond pas à une couleur d'instruction\n"
                + "La lecture de l'instruction n°" + N + " a échouée\n"
                + "Voir le code d'erreur pour plus d'informations\n");

    }

    @Override
    /**
     * This method allows to indicate when the reading of a file failed and where.
     */
    public void logsTxt(String string, int i) {

        writer("La lecture de l'instruction a échouée\n"
                + string + "\n"
                + "La lecture de l'instruction n°" + i + " a échouée");

    }

    @Override
    /**
     * This method allows to indicate when a program is not well formed(parenthesis matter) and when
     * it's missing a JUMP or BACK command and where.
     */
    public void logsWellformed(int i, boolean BACK) {

        if (BACK) {

            writer("Le programme est mal parenthésé\n"
                    + "Il manque un JUMP associé au BACK n°" + i);

        } else {

            writer("Le programme est mal parenthésé\n"
                    + "Il manque " + i + " BACK au programme ");

        }

    }

    /**
     * This method allows to write the log on a file.
     * @param logs
     */
    private void writer(String logs) {

        try {
            file = new PrintWriter(new FileWriter(fileuh, true), true);
        } catch (IOException ex) {
            Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
        }

        file.write(logs);

        file.close();

    }
    public void Reset(){
        NB_INSTR=0;
        EXEC_MOVE=0;
        EXEC_TIME=0;
        DATA_MOVE=0;
        DATA_READ=0;
        DATA_WRITE=0;
    }

}
