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
public class Left implements Command {

    private final String color = "9400d3";

    public String getColor() {
        return color;
    }

    /**
     * Change the current cell of the memory, taking the cell at the left of the
     * current cell
     *
     */
    @Override
    public void execute() {
        ComputationalModel cm = new ComputationalModel();
        if (cm.getCurrentIndice() > 0) {
            cm.setCurrentIndice(cm.getCurrentIndice() - 1);
            cm.setI(cm.getI() + 1);
        } else {
            System.exit(2);
        }
    }

    /**
     * Print the instruction in short syntax for the rewrite instruction
     *
     */
    @Override
    public void printShort() {
        System.out.print("<");
    }

}
