package brainfuck.lecture;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import brainfuck.Syntaxe;
import static brainfuck.command.EnumCommands.toCommand;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Stack;
import javax.imageio.ImageIO;

public class Wellformed extends Lecture {

    static private Stack<String> stack = new Stack<String>();

    public Wellformed(String path) {
        super(path);
    }

    public void execute() throws IOException, FileNotFoundException {

        if (path.lastIndexOf(".") > 0) {

            // On récupère l'extension du fichier
            String ext = path.substring(path.lastIndexOf("."));

            // Si le fichier n'est pas en .txt on le met en .txt
            if (!".bmp".equals(ext)) {

                wellFormedBMP();

            } else {

                wellFormedBF();

            }

        } else {

            wellFormedBF();

        }

    }

    //Faire une interface... ou un truc qui marche mieux xD
    
    static public void popStack() {
        stack.pop();
    }

    static public void pushStack(String line) {
        stack.push(line);
    }

    static public boolean emptyStack() {
        return stack.empty();
    }

    public void load() {

    }

    private void wellFormedBF() throws FileNotFoundException, IOException {

        BufferedReader file = new BufferedReader(new FileReader(path));
        String line = new String();

        while ((line = file.readLine()) != null) {

            if (Syntaxe.isShort(line)) {

                shortSyntaxe.verify(line);

            } else {

                longSyntaxe.verify(line);

            }
        }

        file.close();
    }

    private void wellFormedBMP() {

        BufferedImage img = null;

        try {

            img = ImageIO.read(new File(path));

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

                        longSyntaxe.verify(toCommand(Integer.toString(pixelcolorBase.getRGB())).getLong());

                    }

                }
            }

        } catch (Exception e) {
            System.out.println("ERROR" + e);
        }

    }

}
