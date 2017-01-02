package brainfuck.lecture;
//test
//test2

import java.util.ArrayList;

/**
 * This class represents the macro. It allows to create Macro in a Brainf*ck langage
 * at the beginning of a program. The instructions are put in a list and, when the macro is called,
 * it will textually replace it by the instructions when the program is executed.
 *
 * @author Team Taskbar
 */
public class Macro {

    private final String nom;
    private ArrayList<String> commands;
    private ArrayList<String> params;

    public Macro() {

        this.nom = "";
        this.commands = new ArrayList<>();

    }

    public Macro(String nom) {

        this.nom = nom;
        this.commands = new ArrayList<>();

    }

    Macro(String[] separated) {

        this.nom = separated[1];
        this.commands = new ArrayList<>();
        this.params = new ArrayList<>();

        for (int i = 2; i < separated.length; i++) {

            params.add(separated[i]);

        }

    }


    //=================
    //Getter and Setter
    //=================

    /**
     * Getter of the name.
     * @return the string of the name.
     */
    public String getNom() {
        return nom;
    }

    /**
     * This method allows to know whether it's a parameter or not.
     * @param param
     * @return true or false.
     */
    public boolean isParam(String param) {

        return params.contains(param);

    }

    /**
     * Getter of the quantity of parameters.
     * @return the number of the parameters.
     */
    public int getnbParam() {

        return params.size();

    }

    /**
     * Getter of the number of the parameters.
     * @param param
     * @return the number of parameters.
     */
    public int getNumParam(String param) {

        for (int i = 0; i < params.size(); i++) {

            if (param.equals(params.get(i))) {

                return i + 1;

            }

        }

        return 1;

    }

    /**
     * Getter of the commands.
     * @return an arraylist of string containing the commands.
     */
    public ArrayList<String> getCommands() {
        return commands;
    }

    /**
     * This method allows to fill the commands / Should change its location.
     * @param command
     */
    public void fillCommands(String command) {

        commands.add(command);

    }

}
