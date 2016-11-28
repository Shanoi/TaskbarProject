/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brainfuck.lecture;

import brainfuck.command.EnumCommands;
import static brainfuck.command.EnumCommands.isCommand;
import static brainfuck.command.EnumCommands.toCommand;
import java.util.ArrayList;

/**
 *
 * @author Olivier
 */
public class Macro {

    private final String nom;
    private ArrayList<String> commands;

    public Macro() {

        this.nom = "";
        this.commands = new ArrayList<>();

    }

    public Macro(String nom) {

        this.nom = nom;
        this.commands = new ArrayList<>();
        
    }

    public String getNom() {
        return nom;
    }

    public ArrayList<String> getCommands() {
        return commands;
    }

    public void fillCommands(String command) {

        commands.add(command);

    }

}
