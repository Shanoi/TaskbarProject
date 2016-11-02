/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brainfuck;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Olivier
 */
public class ImageEncod {

    BufferedReader memoryOfFile;

    public ImageEncod(String directory) {

        try {
            this.memoryOfFile = new BufferedReader(new FileReader(directory));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImageEncod.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void Encod(){
        
        String line = "";
        
        try {
            while ((line = memoryOfFile.readLine()) != null) {
                
                if ((line.charAt(0) >= 'A') && (line.charAt(0) <= 'Z')) {
                    
                } else {
                    readShort(line);
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(ImageEncod.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void readShort(String line) {

        int i = 0;
        int n = line.length();
        while (i < n) {

            
            i++;
        }
    }

}
