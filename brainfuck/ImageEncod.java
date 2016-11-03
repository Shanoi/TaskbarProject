/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brainfuck;

import brainfuck.command.AfficheMemory;
import brainfuck.command.Decrementer;
import brainfuck.command.In;
import brainfuck.command.Incrementer;
import brainfuck.command.Left;
import brainfuck.command.Out;
import brainfuck.command.Right;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Olivier
 */
public class ImageEncod extends Interpreter {

    //private BufferedReader memoryOfFile;
    //private BufferedImage img;
    private String directory;

    public ImageEncod(String directory) {

        this.directory = directory;

        try {
            BufferedReader memoryOfFile = new BufferedReader(new FileReader(directory));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImageEncod.class.getName()).log(Level.SEVERE, null, ex);
        }

        Instructions = new HashMap<>();

        //initialistaion de la Hashmap
        Instructions.put("INCR", new Incrementer());
        Instructions.put("DECR", new Decrementer());
        Instructions.put("LEFT", new Left());
        Instructions.put("RIGHT", new Right());
        Instructions.put("OUT", new Out());
        Instructions.put("IN", new In());
        Instructions.put("+", new Incrementer());
        Instructions.put("-", new Decrementer());
        Instructions.put("<", new Left());
        Instructions.put(">", new Right());
        Instructions.put(".", new Out());
        Instructions.put(",", new In());
        Instructions.put("AFF", new AfficheMemory());

    }

    public void Encod() {

        String line = "";

        try {
            BufferedReader memoryOfFile = new BufferedReader(new FileReader(directory));

            while ((line = memoryOfFile.readLine()) != null) {

                if ((line.charAt(0) >= 'A') && (line.charAt(0) <= 'Z')) {
                    (Instructions.get(line)).execute();
                } else {
                    readShort(line);
                }

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImageEncod.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ImageEncod.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void readShort(String line) {

        int i = 0;
        int n = line.length();
        while (i < n) {

            (Instructions.get(Character.toString(line.charAt(i)))).getColor();

            i++;
        }
    }

    private void draw(String color) {
        
        //Ecriture d'un pixel ou d'un groupe de 9 pixels
        
    }

    private int defTaille() {

        //TODO comptage du nombre d'instruction et définition de la résoltion de l'image
        return 0;

    }

}
