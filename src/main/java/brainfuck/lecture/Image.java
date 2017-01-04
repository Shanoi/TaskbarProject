package brainfuck.lecture;

import brainfuck.Observer.ObservableLogsImage;
import brainfuck.Observer.Observateur;

import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static brainfuck.command.EnumCommands.isCommand;
import static brainfuck.command.EnumCommands.toCommand;
import static brainfuck.lecture.Fichiers.list;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import static brainfuck.memory.Launcher.FLAG_trace;
import static java.lang.Math.ceil;
import static java.lang.Math.sqrt;

/**
 * This class reads an .bmp image and stores the instructions in a list.
 * It also translates the image instructions into a text one.
 *
 * @author Team Taskbar
 */
public final class Image extends Fichiers implements ObservableLogsImage {

    private final int pixelSize = 3;

    private ArrayList observers;// Tableau d'observateurs.

    public Image(String path) {

        super(path);

        Monitor observer = new Monitor();

        observers = new ArrayList();

        this.addObserver(observer);

    }

    /**
     * This method allows to create an image from the program (.txt)
     *
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
     *
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

                                    if (FLAG_trace) {
                                        notifyForLogs(o + j);
                                    }
                                    System.exit(9);

                                }

                            }

                        }

                        if (isCommand("#" + Integer.toHexString(pixelcolorBase.getRGB()).substring(2)) && pixelcolorBase != Color.BLACK) {

                            list.add(toCommand("#" + Integer.toHexString(pixelcolorBase.getRGB()).substring(2)));

                        } else {

                            if (FLAG_trace) {
                                notifyForLogs(o + j);
                            }
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

    @Override
    public void addObserver(Observateur o) {

        observers.add(o);

    }

    @Override
    public void delObserver(Observateur o) {

        observers.remove(0);

    }

    @Override
    public void notifyForLogs(int i) {

        for (int j = 0; j < observers.size(); j++) {
            Observateur o = (Observateur) observers.get(j);
            o.logsImage(i);// On utilise la méthode "tiré".

        }
    }

}
