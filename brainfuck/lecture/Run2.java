package brainfuck.lecture;

import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import brainfuck.Syntaxe;
import brainfuck.ComputationalModel;
import brainfuck.command.EnumCommands;
import static brainfuck.command.EnumCommands.toCommand;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Run2 extends Lecture {

    private List<EnumCommands> list = new ArrayList<>();
    private static int i = 0;

    public Run2(String path) {
        super(path);
    }

    public void load() throws IOException, FileNotFoundException {
        BufferedReader file = new BufferedReader(new FileReader(path));
        String line = new String();

        while ((line = file.readLine()) != null) {
            if (Syntaxe.isShort(line)) {
                for (int j = 0; j < line.length(); j++) {
                    list.add(toCommand(Character.toString(line.charAt(j))));
                }
            } else {
                list.add(toCommand(line));
            }
        }
        file.close();
    }

    public int getNbI() {
        return list.size();
    }

    public List<EnumCommands> getInstructions() {
        return this.list;
    }

    public static int jumpAssoc(int i) {
        return 1;
    }

    public static int backAssoc(int i) {
        return 1;
    }

    @Override
    public void execute() throws IOException, FileNotFoundException {
        ComputationalModel.init();

        while (ComputationalModel.getI() < list.size()) {

            list.get(ComputationalModel.getI()).getCommand().execute();
            i = (ComputationalModel.getI() + 1);
            ComputationalModel.setI(i);
        }

    }

}
