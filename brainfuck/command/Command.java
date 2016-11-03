/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brainfuck.command;

import java.io.IOException;

/**
 * Interface of the command pattern Define method for the diffrent object of the
 * command pattern
 *
 * @author TeamTaskbar
 */
public interface Command {

    void execute() throws IOException;

    void printShort();

    String getColor();
    
}
