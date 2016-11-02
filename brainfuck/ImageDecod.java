/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brainfuck.v5;

import brainfuck.v5.command.AfficheMemory;
import brainfuck.v5.command.Command;
import brainfuck.v5.command.Decrementer;
import brainfuck.v5.command.In;
import brainfuck.v5.command.Incrementer;
import brainfuck.v5.command.Left;
import brainfuck.v5.command.Out;
import brainfuck.v5.command.Right;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 *
 * @author Olivier
 */
public class ImageDecod extends Interpreter {

    private String filename;

    public ImageDecod(String filename) {
        this.filename = filename;
        this.cm = new ComputationalModel();

        ComputationalModel.init();

        Instructions = new HashMap<>();

        Instructions.put("ffffff", new Incrementer());
        Instructions.put("4b0082", new Decrementer());
        Instructions.put("9400d3", new Left());
        Instructions.put("0000ff", new Right());
        Instructions.put("00ff00", new Out());
        Instructions.put("ffff00", new In());
        /*Instructions.put("ff7f00", new Jump());
         Instructions.put("ff0000", new Back());*/
        Instructions.put("AFF", new AfficheMemory());

    }
    /**
     * 
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

                                    /*System.out.println("P : " + pixelcolor);

                                     String rgb = Integer.toHexString(pixelcolor.getRed());
                                     rgb += Integer.toHexString(pixelcolor.getGreen());
                                     rgb += Integer.toHexString(pixelcolor.getBlue());*/
                                }/*else{
                                    
                                 System.out.println("FALSE");
                                    
                                 }*/

                            }

                        }

                        /*String rgb = Integer.toHexString(pixelcolorBase.getRed());
                         rgb += Integer.toHexString(pixelcolorBase.getGreen());
                         rgb += Integer.toHexString(pixelcolorBase.getBlue());*/
             
                        
                        String rgb = String.format("%02x", pixelcolorBase.getRed());
                        rgb += String.format("%02x", pixelcolorBase.getGreen());
                        rgb += String.format("%02x", pixelcolorBase.getBlue());

                        Command tempinstruction = Instructions.get(rgb);

                        if (tempinstruction != null) {

                            System.out.println("INSTR : " + tempinstruction);
                            
                            tempinstruction.execute();

                        } else {

                            System.exit(9);

                        }

                        //Instructions.get(rgb).execute();
                        System.out.println("cpt : " + compteur);
                        //Rajouter 2 for pour tester si le carré est d'une seule couleur
                        compteur++;

                        //Color pixelcolor = new Color(img.getRGB(j, i));
                        //System.out.println("RGB : " + img.getRGB(j, i));
                    /*System.out.println("R " + pixelcolor.getRed());
                         System.out.println("G " + pixelcolor.getGreen());
                         System.out.println("B " + pixelcolor.getBlue());*/
                        /* */
                        //System.out.println("RGB " + rgb);
                        /*if (rgb.equals("ffffff")) {

                         System.out.println("INCR");

                         }*/
                    }

                }
            }

            //System.out.println(img);
        } catch (Exception e) {
            System.out.println("ERROR" + e);
        }

        Instructions.get("AFF").execute();

        System.out.println("Compteur " + compteur);

    }

}
