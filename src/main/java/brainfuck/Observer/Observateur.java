/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brainfuck.Observer;

/**
 * @author TeamTaskbar
 */
public interface Observateur {

    /**
     * This method allows to update the EXEC_MOVE counter.
     */
    public void updateExec_Move();

    /**
     * This method allows to update the DATA_WRITE counter.
     */
    public void updateData_Write();

    /**
     * This method allows to update the DATA_READ counter.
     */
    public void updateData_Read();

    /**
     * This method allwos to update the DATA_MOVE counter.
     */
    public void updateData_Move();

    /**
     * This method allows to update the number of instructions in a program.
     */
    public void updateNb_Instr(int i);

    /**
     * This method allows to update the EXEC_TIME counter (milliseconds).
     */
    public void updateTime();

    /**
     * This method allows to display the log through the trace command.
     */
    public void traceLog();

    /**
     * This method allows to indicate when there is a decrementation of a cell and where.
     */
    public void logsDecr();

    /**
     * This method allows to indicate when there is a incrementation of a cell and where.
     */
    public void logsIncr();

    /**
     * This method allows to indicate when there is a reading failure due to an IN and where it occured.
     */
    public void logsIn();

    /**
     * This method allows to indicate when the memory failed to move left and where.
     */
    public void logsLeft();

    /**
     * This method allows to indicate when there is a outputting failure due to an OUT and where it occured.
     */
    public void logsOut();

    /**
     * This method allows to indicate when the memory failed to move right and where.
     */
    public void logsRight();

    /**
     * This method allows to indicate when a color didn't match with the specific instruction and where.
     */
    public void logsImage(int N);

    /**
     * This method allows to indicate when the reading of a file failed and where.
     */
    public void logsTxt(String string, int i);

    /**
     * This method allows to indicate when a program is not well formed(parenthesis matter) and when
     * it's missing a JUMP or BACK command and where.
     */
    public void logsWellformed(int i, boolean BACK);

}
