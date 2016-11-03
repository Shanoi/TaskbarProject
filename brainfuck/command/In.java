package brainfuck.command;

import java.util.Scanner;
import brainfuck.ComputationalModel;

public class In implements Command {

    private final String color = "ffff00";

    private static int cnt = 0;
    private static String str;
    private static int str_length = 0;
    private static int state;

    @Override
    public String getColor() {
        return color;
    }
    
    @Override
    public void execute() {
        ComputationalModel cm = new ComputationalModel();
        Scanner sc = new Scanner(System.in);
        str = sc.nextLine();
        sc.close();
        if(str.length()<0) {
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
