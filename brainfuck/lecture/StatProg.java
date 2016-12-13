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
public class StatProg implements Observateur {

    private static int NB_INSTR = 0;
    private static long EXEC_TIME = 0;
    private static int EXEC_MOVE = 0;
    private static int DATA_MOVE = 0;
    private static int DATA_WRITE = 0;
    private static int DATA_READ = 0;
    private static PrintWriter file;
    
    private String fileuh;

    private final ComputationalModel cm;

    public StatProg(String fileuh) throws IOException {
        //StatProg.file = new FileWriter(file, true);

        //File f = new File(file);

        this.fileuh = fileuh;
        
        file = new PrintWriter(new FileWriter(fileuh));

        //StatProg.file = new FileWriter(f, true);
        cm = new ComputationalModel();

        cm.init();

    }

    public StatProg() {

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

        try {
            file = new PrintWriter(new FileWriter(fileuh, true), true);
        } catch (IOException ex) {
            Logger.getLogger(StatProg.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        file.write("Execution step number: " + EXEC_MOVE + " \n"
                + "Pointer of the execution: " + cm.getI() + " \n"
                + "Location of the data pointer: " + cm.getCurrentIndice() + "\n"
                + "Affichage de la mémoire\n" + cm.toString() + "\n");
        
        file.close();

    }

    @Override
    public void logsDecr() {

        file.write("Décrémenter la cellule courante a échoué\n"
                + "L'instruction n°" + EXEC_MOVE + " a échouée");

    }

}
