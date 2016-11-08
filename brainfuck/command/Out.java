package brainfuck.command;

import brainfuck.ComputationalModel;
import brainfuck.Text;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }

    }

}
