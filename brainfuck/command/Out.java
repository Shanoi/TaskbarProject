package brainfuck.command;

import brainfuck.ComputationalModel;
import brainfuck.Text;

import java.io.*;

public class Out implements Command {
    private String file;
    private static String tempString="";

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
