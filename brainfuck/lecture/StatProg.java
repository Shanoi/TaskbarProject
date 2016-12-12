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
    public void actualiser(Observer.Observable o) {
        
    }

    @Override
    public void actualiserIncr() {
        
        EXEC_MOVE++;
        DATA_WRITE++;
        
        System.out.println("Update from Incr");
        
    }

    
    


}
