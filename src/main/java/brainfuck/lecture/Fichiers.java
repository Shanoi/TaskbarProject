/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brainfuck.lecture;

import brainfuck.command.EnumCommands;
import brainfuck.command.uByte;
import brainfuck.memory.ComputationalModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static brainfuck.command.EnumCommands.BACK;
import static brainfuck.command.EnumCommands.JUMP;

/**
 * This class stores the different information related to the read file
 * such as the path. It manages the memory. It also stores
 * in a list the commands of the Brainf*ck code and a table which
 * send back respectively the associated BACK and the associated JUMP.
 *
 * @author Team Taskbar
 */
public class Fichiers {


    protected static Map<Integer, Integer> jumpBack = new HashMap<>();
    protected static Map<Integer, String> fonction = new HashMap<>();
    protected static Map<String, Integer> fonction2 = new HashMap<>();
    protected static Map<String, Integer> nbArgFonction = new HashMap<>();

    protected static List<EnumCommands> list = new ArrayList<>();
    protected static Queue<String> fonctionName = new LinkedList<>();
    protected static Queue<Integer> arguments = new LinkedList<>();
    protected static Stack<Integer> retAdress = new Stack<>();


    protected int i = 0;

    protected ComputationalModel cm;

    protected String path;

    /**
     * This method allows to set the table loop.
     */
    public void setTableLoop() {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == JUMP) {
                jumpBack.put(i, jumpAssoc(i));
            }
            if (list.get(i) == BACK) {
                jumpBack.put(i, backAssoc(i));
            }
        }
    }


    /**
     * This method allows to retrieve the cipher of the associated loop instruction.
     *
     * @param i the cipher of the instruction considered.
     * @return the cipher of the associated loop instruction.
     */
    public int getTableLoopAssoc(int i) {
        return jumpBack.get(i);
    }

    public Fichiers(String path) {

        cm = new ComputationalModel();
        this.path = path;

    }

    /**
     * This method allows to read a file.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void Read() throws FileNotFoundException, IOException {
    }

    /**
     * This method allows to encode a file.
     */
    public void Encod() {

    }


    /**
     * This method allows to get the cipher of the associated Jump.
     *
     * @param i the cipher of the instruction considered.
     * @return the cipher of the associated Jump.
     */
    public int jumpAssoc(int i) {

        Stack<EnumCommands> stack = new Stack<>();
        //        int o = cm.getI();
        int o = i;

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

    /**
     * This method allows to retrieve the associated back (cipher of the instruction).
     *
     * @param i the cipher of the instruction considered.
     * @return the cipher of the associated back.
     */
    public int backAssoc(int i) {

        Stack<EnumCommands> stack = new Stack<>();
        //        int o = cm.getI();
        int o = i;

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


    //=================
    //Getter and Setter
    //=================

    /**
     * Getter of the size of the list, NbI.
     *
     * @return the size of the list.
     */
    public int getNbI() {
        return list.size();
    }

    /**
     * Getter of the instructions.
     *
     * @return the list of the different commands.
     */
    public List<EnumCommands> getInstructions() {
        return list;
    }

    /**
     * Getter of the computational model.
     *
     * @return the object ComputationalModel.
     */
    public ComputationalModel getCm() {
        return cm;
    }

    /**
     * Getter of the name of the function.
     * @return a queue of the name.
     */
    public static Queue<String> getQueueFonction() {
        return fonctionName;
    }

    /**
     * Setter of the RetAdress.
     * @param adress the adress considered.
     */
    public static void setRetAdress(int adress) {
        retAdress.push(adress);
    }

    /**
     * Getter of the line of the function.
     * @param line the line considered.
     * @return the cipher of the line considered.(function)
     */
    public static int getFonction2(String line) {
        return fonction2.get(line);
    }

    /**
     * Getter of the RetAdress.
     * @return a stack of Integer representing the adress.
     */
    public static Stack<Integer> getRetAdress() {
        return retAdress;
    }

    /**
     * Getter of the function.
     * @param a
     * @return a string representing the function.
     */
    public static String getFonction(int a) {
        return fonction.get(a);
    }

    /**
     * Getter of the different arguments.
     * @return the cipher of the different arguments.
     */
    public static int getArguments() { return  arguments.poll(); }

    /**
     * Setter of the arguments.
     * @param a
     */
    public static void setArguments(int a) {  arguments.add(a); }

    /**
     * Getter of the cipher of arguments in a function.
     * @param fonction
     * @return the cipher of arguments.
     */
    public static int getNbArgFonction(String fonction) {  return nbArgFonction.get(fonction); }

}
