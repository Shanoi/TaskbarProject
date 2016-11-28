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
    //private final int params[];
    //private ArrayList<EnumCommands> commands;
    private ArrayList<String> commands;
    private int numParam;

    public Macro() {

       // int tableauEntier[] = {0};
        this.nom = "";
        //this.params = tableauEntier;
        this.commands = new ArrayList<>();
        this.numParam = 0;

    }

    public Macro(String nom) {

        /*int p[] = new int[params.length - 2];

         for (int j = 2, i = 0; j < params.length; j++, i++) {
         System.out.println("PARAM -- " + params[j]);
         p[i] = Integer.parseInt(params[j]);

         }*/
        this.nom = nom;
        //this.params = p;
        this.commands = new ArrayList<>();
        this.numParam = 0;
    }

    public String getNom() {
        return nom;
    }

    public ArrayList<String> getCommands() {
        return commands;
    }

    /*public void fillMacro(String command) {
     System.out.println("FILL --- " + command + " --- " + params.length);

     if (numParam >= params.length) {

     fillCommands(command);

     } else {

     for (int i = 0; i < params[numParam]; i++) {

     fillCommands(command);

     }

     numParam++;
     }

     }*/
    public void fillCommands(String command) {

        commands.add(command);
        
        /*if ((command.charAt(0) <= 'A') || (command.charAt(0) >= 'Z')) {

            for (int j = 0; j < command.length(); j++) {

                if (isCommand(Character.toString(command.charAt(j)))) {
                    command = this.deleteCom(command);
                    commands.add((toCommand((Character.toString(command.charAt(j))))));

                } else {

                    System.out.println("TEST");
                    System.exit(4);

                }

            }

        } else if ((command.charAt(0) == '#') || (command.charAt(0) == '\t')) {

        } else {

            if (isCommand(command)) {
                command = this.deleteCom(command);
                commands.add(toCommand(command));

            } else {
                System.out.println("TEST2");
                System.exit(4);

            }

        }*/

    }

    /*public void repeatMacro(){
        
     int taileini = commands.size();
        
     for (int i = 0; i < params[3]; i++) {
            
     for (int j = 0; j < taileini; j++) {
                
     commands.add(commands.get(i));
                
     }
            
     }
        
     }*/
    public String deleteCom(String line) {
        String str2 = new String();
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '#') {
                return str2;
            }
            if (!(line.charAt(i) == '\t')) {
                str2 += Character.toString(line.charAt(i));
            }
        }
        return str2;

    }

}
