package brainfuck.lecture;

import static brainfuck.command.EnumCommands.toCommand;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Math.ceil;
import static java.lang.Math.sqrt;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author TeamTaskbar
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

            //Définir si le programme est WellFormed
            run.load();

            int dim = pixelSize * (int) ceil(sqrt(run.getNbI()));

            saveImg(createImg(dim), directory + ".bmp");

        } catch (IOException ex) {
            Logger.getLogger(ImageEncod.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private BufferedImage createImg(int dim) {

        final BufferedImage res = new BufferedImage(dim, dim, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < dim; i += 3) {

            for (int j = 0; j < dim; j += 3) {

                for (int k = i, cpt1 = 0; cpt1 < pixelSize; k++, cpt1++) {

                    for (int l = j, cpt2 = 0; cpt2 < pixelSize; l++, cpt2++) {

                        if (i+j > run.getNbI()){
                        
                        res.setRGB(k, l, Color.decode(toCommand(run.getInstructions().get(i+j)).getColor()).getRGB());
                        
                        }else{
                            
                            res.setRGB(k, l, Color.decode("#000000").getRGB());
                            
                        }

                    }
                }

            }

        }

        return res;

    }

    private void saveImg(final BufferedImage img, String path) {

        try {
            RenderedImage rendImg = img;
            ImageIO.write(rendImg, "bmp", new File(path));
        } catch (IOException ex) {
            Logger.getLogger(ImageEncod.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
