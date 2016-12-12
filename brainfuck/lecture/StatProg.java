/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brainfuck.lecture;

import Observer.Observateur;
import java.util.Observable;

/**
 *
 * @author Olivier
 */
public class StatProg implements Observateur {

    private static long EXEC_TIME = 0;
    private static int EXEC_MOVE = 0;
    private static int DATA_MOVE = 0;
    private static int DATA_WRITE = 0;
    private static int DATA_READ = 0;

    public long getEXEC_TIME() {
        return EXEC_TIME;
    }

    public int getEXEC_MOVE() {
        return EXEC_MOVE;
    }

    public int getDATA_MOVE() {
        return DATA_MOVE;
    }

    public int getDATA_WRITE() {
        return DATA_WRITE;
    }

    public int getDATA_READ() {
        return DATA_READ;
    }

    @Override
    public String toString() {

        return "Nombre de déplacements du pointeur d'instruction: " + EXEC_MOVE + "\n"
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
    public void updateTime() {

        EXEC_TIME = Math.abs(EXEC_TIME - System.currentTimeMillis());

    }

}
