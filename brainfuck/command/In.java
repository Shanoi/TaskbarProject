package brainfuck.command;

import java.util.Scanner;
import brainfuck.ComputationalModel;

public class In implements Command {

    static int cnt = 0;
    static String str;
    static int str_length = 0;
    static int state;

    @Override
    public void execute() {
        ComputationalModel cm = new ComputationalModel();
        if (state == 0) {
            Scanner sc = new Scanner(System.in);
            str = sc.nextLine();
            sc.close();
            str_length = str.length();
            state = 1;
        }
        if (cnt < str_length) {
            char a = str.charAt(cnt);
            cm.setCurrentCaseValue((byte) a);
            cnt++;
        } else {
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
