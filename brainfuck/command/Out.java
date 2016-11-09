package brainfuck.command;

import brainfuck.ComputationalModel;
import brainfuck.Text;

import java.io.*;

/**
 * This class is used to print the character associated to the ASCII value in the current memory case
 * it an take a file to store different characters of different OUT
 */
public class Out implements Command {
    private String file;
    private static String tempString="";

    /**
     * execute the Out function
     */
    @Override
    public void execute() {
        ComputationalModel cm = new ComputationalModel();
        if(file.equals("")){
            System.out.println((char) cm.getCurrentCaseValue());
        }
        else{
            file= Text.getFileOut();
            try {
                FileWriter inputFile = new FileWriter(new File(file));
                FileReader tampon=new FileReader(new File(file));
                int temp=tampon.read();
                while(temp!=-1){
                    tempString+=(char)temp;
                }
                tempString+=(char)cm.getCurrentCaseValue();
                inputFile.write(tempString);
                inputFile.close();
                tampon.close();

            } catch(FileNotFoundException e){
                System.exit(3);

            } catch(IOException e){
                System.exit(3);
            }
        }

    }

}
