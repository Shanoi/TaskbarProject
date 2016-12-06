/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brainfuck.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author TeamTaskbar
 */
public enum EnumCommands {

    INCR("INCR", "+", "#ffffff", new Incrementer()),
    DECR("DECR", "-", "#4b0082", new Decrementer()),
    IN("IN", ",", "#ffff00", new In()),
    LEFT("LEFT", ">", "#9400d3", new Left()),
    RIGHT("RIGHT", ">", "#0000ff", new Right()),
    OUT("OUT", ".", "#00ff00", new Out()),
    JUMP("JUMP", "[", "#ff7f00", new Jump()),
    BACK("BACK", "]", "#ff0000", new Back());

    private final String Long;
    private final String Short;
    private final String color;

    private final Command command;

    static final Map<ArrayList<String>, EnumCommands> STRING_TO_COMMANDS = new HashMap<>();

    private EnumCommands(String Long, String Short, String color, Command command) {
        this.Long = Long;
        this.Short = Short;
        this.color = color;

        this.command = command;

    }

    static {

        Arrays.asList(EnumCommands.values()).forEach(cw -> STRING_TO_COMMANDS.put(cw.getAllSyntaxe(), cw));

    }

    /**
     * Getter of AllSyntaxe
     * @return
     */
    //Should change the location of this method perhaps?
    private ArrayList<String> getAllSyntaxe() {

        return new ArrayList<>(Arrays.asList(getLong(), getShort(), getColor()));

    }

    /**
     * Maps natural language words to enums.
     *
     * @param word
     * @return The enum associated with word, or null if word is not a key.
     */
    public static EnumCommands toCommand(String word) {

        Set wordofCommand = STRING_TO_COMMANDS.keySet();

        Iterator<ArrayList> it = wordofCommand.iterator();
        while (it.hasNext()) {

            ArrayList temp = it.next();

            for (int i = 0; i < temp.size(); i++) {

                if (temp.get(i).equals(word)) {

                    return STRING_TO_COMMANDS.get(temp);

                }

            }

        }

        return null;

    }

    /**
     * Check whether a given String is a valid name word or not.
     *
     * @param aString The string to be checked.
     * @return true if it is valid, false if it isn't.
     */
    public static boolean isCommand(String aString) {

        Set wordofCommand = STRING_TO_COMMANDS.keySet();

        Iterator<ArrayList> it = wordofCommand.iterator();
        while (it.hasNext()) {

            ArrayList temp = it.next();

            for (int i = 0; i < temp.size(); i++) {

                if (temp.get(i).equals(aString)) {

                    return true;

                }

            }

        }

        return false;

    }

    /**
     * This method allows to know whether it's a short command or not
     * @param aString
     * @return
     */
    public static boolean isShortCommand(String aString) {

        Set wordofCommand = STRING_TO_COMMANDS.keySet();

        Iterator<ArrayList> it = wordofCommand.iterator();
        while (it.hasNext()) {

            ArrayList temp = it.next();

            if (temp.get(1).equals(aString)) {

                return true;

            }

        }

        return false;

    }

    //=================
    //Getter and Setter
    //=================

    /**
     * Getter of the Long
     * @return
     */
    public String getLong() {
        return Long;
    }

    /**
     * Getter of the Short
     * @return
     */
    public String getShort() {
        return Short;
    }

    /**
     * Getter of the Color
     * @return
     */
    public String getColor() {
        return color;
    }

    /**
     * Getter of the Command
     * @return
     */
    public Command getCommand() {

        return command;

    }

}
