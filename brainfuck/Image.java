/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brainfuck;

import brainfuck.command.AfficheMemory;
import brainfuck.command.Command;
import brainfuck.command.Decrementer;
import brainfuck.command.In;
import brainfuck.command.Incrementer;
import brainfuck.command.Left;
import brainfuck.command.Out;
import brainfuck.command.Right;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 *
 * @author TeamTaskbar
 */
public class Image {

    private String filename;

    /**
     * Constructor Initialize the Command to modify the memory
     *
     * @param filename The image file to read and execute
     */
    public Image(String filename) {
        this.filename = filename;
        //this.cm = new ComputationalModel();

        ComputationalModel.init();

       /* Instructions = new HashMap<>();

        Instructions.put("ffffff", new Incrementer());
        Instructions.put("4b0082", new Decrementer());
        Instructions.put("9400d3", new Left());
        Instructions.put("0000ff", new Right());
        Instructions.put("00ff00", new Out());
        Instructions.put("ffff00", new In());
        /*Instructions.put("ff7f00", new Jump());
         Instructions.put("ff0000", new Back());*/
       // Instructions.put("AFF", new AfficheMemory());

    }

    /**
     * Read the image file and execute the program in it
     */
    public void LireImage() {

        BufferedImage img = null;

        int compteur = 0;

        try {

            img = ImageIO.read(new File(filename));

            if (img.getHeight() % 3 == 0 && img.getWidth() % 3 == 0) {

                for (int i = 0; i < img.getHeight(); i += 3) {

                    for (int j = 0; j < img.getWidth(); j += 3) {

                        Color pixelcolorBase = new Color(img.getRGB(j, i));

                        for (int k = i, cpt1 = 0; cpt1 < 3; k++, cpt1++) {

                            for (int l = j, cpt2 = 0; cpt2 < 3; l++, cpt2++) {

                                Color pixelcolor = new Color(img.getRGB(l, k));
                                System.out.println("ICI" + pixelcolor);
                                if (!pixelcolor.equals(pixelcolorBase)) {

                                    System.exit(9);

                                }

                            }

                        }

                        String rgb = String.format("%02x", pixelcolorBase.getRed());
                        rgb += String.format("%02x", pixelcolorBase.getGreen());
                        rgb += String.format("%02x", pixelcolorBase.getBlue());

                       /* Command tempinstruction = Instructions.get(rgb);

                        if (tempinstruction != null) {

                            System.out.println("INSTR : " + tempinstruction);

                            tempinstruction.execute();

                        } else {

                            System.exit(9);

                        }*/

                        System.out.println("cpt : " + compteur);
                        compteur++;

                    }

                }
            }

        } catch (Exception e) {
            System.out.println("ERROR" + e);
        }

       // Instructions.get("AFF").execute();

        System.out.println("Compteur " + compteur);

    }

}
