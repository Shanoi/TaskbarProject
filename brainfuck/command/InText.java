package brainfuck.command;
import brainfuck.ComputationalModel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

/**
 * Created by sebde on 03/11/2016.
 */
public class InText implements Command {
    private final String color = "ffff00";
    private String file;
    private static int cnt=0;
    private int temp=0;

    public void InText(String file){
        this.file=file;
    }
    @Override
    public void execute() throws IOException {
        ComputationalModel cm = new ComputationalModel();
        File inputFile= new File(file);
        FileReader in = new FileReader(inputFile);
        for(int i=0;i<=cnt;i++){
            temp=in.read();
        }
        if(temp!=-1) {
            cm.setCurrentCaseValue((byte) (char) temp);
            cnt++;
        }
        else{
            System.exit(3);
        }
    }

    @Override
    public String getColor() {
        return color;
    }


    @Override
    public void printShort() {
        System.out.print(",");
    }
}
