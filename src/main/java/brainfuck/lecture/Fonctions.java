/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brainfuck.lecture;

import static brainfuck.command.EnumCommands.RET;
import java.util.ArrayList;

/**
 *
 * @author Olivier
 */
public class Fonctions {

    private final String nom;
    private ArrayList<String> commands;
    private int params;

    public Fonctions() {

        this.nom = "";
        this.commands = new ArrayList<>();

    }

    public Fonctions(String nom) {

        this.nom = nom;
        this.commands = new ArrayList<>();

    }

    public Fonctions(String[] separated) {

        this.nom = separated[1];
        this.commands = new ArrayList<>();
        this.params = Integer.parseInt(separated[1]);

    }

    //=================
    //Getter and Setter
    //=================
    /**
     * Getter of the name.
     *
     * @return the string of the name.
     */
    public String getNom() {
        return nom;
    }

    public int getParams() {
        return params;
    }

    /**
     * Getter of the commands.
     *
     * @return an arraylist of string containing the commands.
     */
    public ArrayList<String> getCommands() {
        return commands;
    }

    /**
     * This method allows to fill the commands / Should change its location.
     *
     * @param command
     */
    public void fillCommands(String command) {

        commands.add(command);

    }
    
    public boolean isProc(){
        
        return commands.get(commands.size() - 1).equals(RET.getLong());
        
    }

}
