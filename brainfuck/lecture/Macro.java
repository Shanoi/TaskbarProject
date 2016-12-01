/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brainfuck.lecture;

import java.util.ArrayList;

/**
 *
 * @author Olivier
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

    public String getNom() {
        return nom;
    }
    
    public boolean isParam(String param){
        
        return params.contains(param);
        
    }
    
    public int getnbParam(){
        
        return params.size();
        
    }
    
    public int getNumParam(String param){
        
        for (int i = 0; i < params.size(); i++) {
            
            if(param.equals(params.get(i))){
                
                return i + 1;
                
            }
            
        }
        
        return 1;
        
    }

    public ArrayList<String> getCommands() {
        return commands;
    }

    public void fillCommands(String command) {

        commands.add(command);

    }

}
