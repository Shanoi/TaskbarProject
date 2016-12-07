/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brainfuck.lecture;

import brainfuck.command.EnumCommands;
import static brainfuck.command.EnumCommands.BACK;
import static brainfuck.command.EnumCommands.JUMP;
import brainfuck.memory.ComputationalModel;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Olivier
 */
public class Fichiers {

    protected static final List<EnumCommands> list = new ArrayList<>();
    protected int i = 0;

    protected ComputationalModel cm;

    protected String path;

    public Fichiers(String path) {

        cm = new ComputationalModel();
        this.path = path;

    }

    public void Read() throws FileNotFoundException, IOException {
    }

    public void Encod() {

    }

    public int getNbI() {
        return list.size();
    }

    public List<EnumCommands> getInstructions() {
        return list;
    }

    public ComputationalModel getCm() {
        return cm;
    }

    public int jumpAssoc(int i) {

        Stack<EnumCommands> stack = new Stack<>();
        int o = cm.getI();

        stack.push(list.get(o));

        while (!stack.isEmpty()) {

            o++;

            if (list.get(o) == JUMP) {

                stack.push(list.get(o));

            }

            if (list.get(o) == BACK) {

                stack.pop();

            }
        }
        return o;
    }

    public int backAssoc(int i) {

        Stack<EnumCommands> stack = new Stack<>();
        int o = cm.getI();

        stack.push(list.get(o));

        while (!stack.isEmpty()) {

            o--;

            if (list.get(o) == JUMP) {

                stack.pop();
            }

            if (list.get(o) == BACK) {

                stack.push(list.get(o));

            }
        }
        return o;
    }
    

    

}
