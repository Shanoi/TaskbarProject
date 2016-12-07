package brainfuck.memory;

import brainfuck.command.EnumCommands;
import static brainfuck.command.EnumCommands.BACK;
import static brainfuck.command.EnumCommands.JUMP;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Stack;

public class Wellformed {

    private Stack<String> stack = new Stack<>();

    private ArrayList<EnumCommands> commands;

    public Wellformed(ArrayList<EnumCommands> commands) {

        this.commands = commands;

    }

    public void execute() throws IOException, FileNotFoundException {
        for (int i = 0; i < commands.size(); i++) {
            EnumCommands get = commands.get(i);

            if (get == JUMP) {
                pushStack("[");
            }
            if (get == BACK && IsemptyStack()) {

                System.exit(4);
            }
            if (get == BACK) {
                popStack();
            }

        }

        if (!IsemptyStack()) {

            System.exit(4);

        }

    }

    private void popStack() {
        stack.pop();
    }

    private void pushStack(String line) {
        stack.push(line);
    }

    private boolean IsemptyStack() {
        return stack.empty();
    }

}
