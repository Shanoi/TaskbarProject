/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brainfuck.lecture;

import brainfuck.ComputationalModel;
import brainfuck.command.EnumCommands;
import static brainfuck.command.EnumCommands.toCommand;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 *
 * @author TeamTaskbar
 */
public class ImageRun extends Lecture {

    private String filename;
    private List<EnumCommands> list;
    private static int i = 0;

    /**
     * Constructor Initialize the Command to modify the memory
     *
     * @param filename The image file to read and execute
     */
    public ImageRun(String filename) {

        super(filename);
        this.list = new ArrayList<>();

    }

    /**
     * Read the image file and execute the program in it
     */
    public void LireImage() {

    }

    @Override
    public void execute() throws IOException, FileNotFoundException {
        ComputationalModel.init();

        while (ComputationalModel.getI() < list.size()) {

            list.get(ComputationalModel.getI()).getCommand().execute();
            i = (ComputationalModel.getI() + 1);
            ComputationalModel.setI(i);
        }
    }

    @Override
    public void load() throws IOException, FileNotFoundException {

        BufferedImage img = null;

        try {

            img = ImageIO.read(new File(filename));

            if (img.getHeight() % 3 == 0 && img.getWidth() % 3 == 0) {

                for (int o = 0; o < img.getHeight(); o += 3) {

                    for (int j = 0; j < img.getWidth(); j += 3) {

                        Color pixelcolorBase = new Color(img.getRGB(j, o));

                        for (int k = o, cpt1 = 0; cpt1 < 3; k++, cpt1++) {

                            for (int l = j, cpt2 = 0; cpt2 < 3; l++, cpt2++) {

                                Color pixelcolor = new Color(img.getRGB(l, k));

                                if (!pixelcolor.equals(pixelcolorBase)) {

                                    System.exit(9);

                                }

                            }

                        }

                        String rgb = String.format("%02x", pixelcolorBase.getRed());
                        rgb += String.format("%02x", pixelcolorBase.getGreen());
                        rgb += String.format("%02x", pixelcolorBase.getBlue());

                        list.add(toCommand(rgb));

                        /* Command tempinstruction = Instructions.get(rgb);

                         if (tempinstruction != null) {

                         System.out.println("INSTR : " + tempinstruction);

                         tempinstruction.execute();

                         } else {

                         System.exit(9);

                         }*/
                    }

                }
            }

        } catch (Exception e) {
            System.out.println("ERROR" + e);
        }

    }

}
