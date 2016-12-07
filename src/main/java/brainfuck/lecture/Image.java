package brainfuck.lecture;

import static brainfuck.command.EnumCommands.isCommand;
import static brainfuck.command.EnumCommands.toCommand;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
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
public class Image extends Fichiers {

    private final int pixelSize = 3;

    public Image(String path) {

        super(path);

    }

    /**
     * This method allows to create an image from the program (.txt)
     * @param dim
     * @return
     */
    private BufferedImage createImg(int dim) {

        final BufferedImage res = new BufferedImage(dim, dim, BufferedImage.TYPE_INT_RGB);

        int numInstr = 0;

        for (int i = 0; i < dim; i += pixelSize) {

            for (int j = 0; j < dim; j += pixelSize) {

                for (int k = i, cpt1 = 0; cpt1 < pixelSize; k++, cpt1++) {

                    for (int l = j, cpt2 = 0; cpt2 < pixelSize; l++, cpt2++) {

                        if (numInstr < getNbI()) {

                            res.setRGB(l, k, Color.decode(getInstructions().get(numInstr).getColor()).getRGB());

                        } else {

                            res.setRGB(l, k, Color.decode("#000000").getRGB());

                        }

                    }
                }

                numInstr++;

            }

        }

        return res;

    }

    /**
     * This method allows to save the image
     * @param img
     * @param path
     * @throws IOException
     */
    private void saveImg(final BufferedImage img, String path) throws IOException {

        RenderedImage rendImg = img;
        ImageIO.write(rendImg, "bmp", new File(path));

    }

    /**
     * This method allows to read a program(.bmp)
     */
    @Override
    public void Read() {

        try {
            BufferedImage img = null;

            img = ImageIO.read(new File(path));

            if (img.getHeight() % pixelSize == 0 && img.getWidth() % pixelSize == 0) {

                for (int o = 0; o < img.getHeight(); o += pixelSize) {

                    for (int j = 0; j < img.getWidth(); j += pixelSize) {

                        Color pixelcolorBase = new Color(img.getRGB(j, o));

                        for (int k = o, cpt1 = 0; cpt1 < pixelSize; k++, cpt1++) {

                            for (int l = j, cpt2 = 0; cpt2 < pixelSize; l++, cpt2++) {

                                Color pixelcolor = new Color(img.getRGB(l, k));

                                if (!pixelcolor.equals(pixelcolorBase)) {

                                    System.exit(9);

                                }

                            }

                        }

                        if (isCommand("#"+Integer.toHexString(pixelcolorBase.getRGB()).substring(2)) && pixelcolorBase != Color.BLACK) {

                            list.add(toCommand("#"+Integer.toHexString(pixelcolorBase.getRGB()).substring(2)));

                        } else {
                            
                            System.exit(4);

                        }

                    }

                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * This method allows to encod an image from a program(.txt)
     */
    @Override
    public void Encod() {

        int dim = pixelSize * (int) ceil(sqrt(getNbI()));

        try {
            saveImg(createImg(dim), path + ".bmp");
        } catch (IOException ex) {
            Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

}
