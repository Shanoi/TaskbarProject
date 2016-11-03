package brainfuck.command;

import java.util.Scanner;
import brainfuck.ComputationalModel;

public class In implements Command {

    private final String color = "ffff00";

    private  String str;


    @Override
    public String getColor() {
        return color;
    }
    
    @Override
    public void execute() {
        ComputationalModel cm = new ComputationalModel();
        Scanner sc= new Scanner(System.in);
        str=sc.nextLine();
        if(str.length()>0) {
            cm.setCurrentCaseValue((byte) str.charAt(0));
        }
        else{
            System.exit(3);
        }

    }

    /**
     * Print the instruction in short syntax for the rewrite instruction
     *
     */
    @Override
    public void printShort() {
        System.out.print(",");
    }

}




