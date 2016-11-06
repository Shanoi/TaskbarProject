/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brainfuck.lecture;

import static brainfuck.command.EnumCommands.toCommand;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Math.ceil;
import static java.lang.Math.sqrt;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Olivier
 */
public class ImageEncod {

    //private BufferedReader memoryOfFile;
    //private BufferedImage img;
    private String directory;
    private Run run;
    private int pixelSize = 3;

    public ImageEncod(String directory) {

        this.directory = directory;

        run = new Run(directory);

        /*try {
         BufferedReader memoryOfFile = new BufferedReader(new FileReader(directory));
         } catch (FileNotFoundException ex) {
         Logger.getLogger(ImageEncod.class.getName()).log(Level.SEVERE, null, ex);
         }*/
    }

    public void Encod() {

        try {

            String line = "";

            //Définir si le programme est WellFormed
            run.load();

            int dim = pixelSize * (int) ceil(sqrt(run.getNbI()));

        } catch (IOException ex) {
            Logger.getLogger(ImageEncod.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private BufferedImage createImg(int dim) {

        final BufferedImage res = new BufferedImage(dim, dim, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < dim; i++) {

            for (int j = 0; j < dim; j++) {

                //res.setRGB(i, j, toCommand(run.getInstructions().get(i)).getColor());

            }

        }

        return res;

    }

    private void draw(String color) {

        //Ecriture d'un pixel ou d'un groupe de 9 pixels
    }

}
