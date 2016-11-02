    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brainfuck.command;

import brainfuck.ComputationalModel;

/**
 *
 * @author TeamTaskbar
 */
public class Incrementer implements Command {

    /**
     * Increment the value of the current cell of the memory
     *
     */
    @Override
    public void execute() {
        ComputationalModel cm = new ComputationalModel();
        if (cm.getCurrentCaseValue() < 255) {
            cm.setCurrentCaseValue((byte) (cm.getCurrentCaseValue() + 1));
            cm.setI(cm.getI() + 1);
        } else {
            System.exit(1);
        }

    }

    /**
     * Print the instruction in short syntax for the rewrite instruction
     *
     */
    @Override
    public void printShort() {
        System.out.print("+");
    }

}



