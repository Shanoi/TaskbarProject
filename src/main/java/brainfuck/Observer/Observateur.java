/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brainfuck.Observer;

/**
 *
 * @author Olivier
 */
public interface Observateur {

    public void updateExec_Move();

    public void updateData_Write();

    public void updateData_Read();

    public void updateData_Move();

    public void updateNb_Instr(int i);

    public void updateTime();

    public void traceLog();
    
    public void logsDecr();
    
    public void logsIncr();

    public void logsIn();
    
    public void logsLeft();

    public void logsOut();
    
    public void logsRight();

    public void logsImage(int N);

    public void logsTxt(String string, int i);
    
    public void logsWellformed(int i, boolean BACK);

}
