/*test*/
import brainfuck.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, FileNotFoundException {

        Text T;
        T = new Text(args[args.length - 1]);
        T.launchInterpreter();
        if (isPresent(args, "-p")) {
            ComputationalModel.affichememoire();
        }
        if (isPresent(args, "--rewrite")) {
            T = new Text(args[args.length - 1]);
            T.rewrite();

        }

    }

    public static boolean isPresent(String[] args, String str) {
        int n = args.length;
        for (int i = 0; i < n; i++) {
            if (args[i].equals(str)) {
                return true;
            }

        }
        return false;

    }
}
