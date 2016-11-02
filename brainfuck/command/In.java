package brainfuck.v5.command;

import java.util.Scanner;
import brainfuck.v5.ComputationalModel;

public class In implements Command {

    @Override
    public void execute() {
        ComputationalModel cm = new ComputationalModel();
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        sc.close();
        if (str.length() == 0 || str.length() > 1) {
            System.exit(3);
        } else {
            char a = str.charAt(0);
            cm.setCurrentCase((byte) a);
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
