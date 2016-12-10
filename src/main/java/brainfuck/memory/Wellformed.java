package brainfuck.memory;

import brainfuck.command.Decrementer;
import brainfuck.command.EnumCommands;
import static brainfuck.command.EnumCommands.BACK;
import static brainfuck.command.EnumCommands.JUMP;
import brainfuck.lecture.Run;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

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

                if (Run.ifTrace()) {
                    FileWriter file = null;
                    try {
                        file = new FileWriter("/Users/dev/TaskbarProject/test.txt", true);
                        file.write("Le programme est mal parenthésé\n"
                                + "Il manque un JUMP associé au BACK n°" + i);
                        file.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Decrementer.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        try {
                            file.close();
                        } catch (IOException ex) {
                            Logger.getLogger(Decrementer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

                System.exit(4);
            }
            if (get == BACK) {
                popStack();
            }

        }

        if (!IsemptyStack()) {

             if (Run.ifTrace()) {
                    FileWriter file = null;
                    try {
                        file = new FileWriter("/Users/dev/TaskbarProject/test.txt", true);
                        file.write("Le programme est mal parenthésé\n"
                                + "Il manque " + stack.size() + " BACK au programme ");
                        file.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Decrementer.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        try {
                            file.close();
                        } catch (IOException ex) {
                            Logger.getLogger(Decrementer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            
            System.exit(4);

        }

    }

    /**
     * This method allows to pop the stack
     */
    private void popStack() {
        stack.pop();
    }

    /**
     * This method allows to push the stack
     *
     * @param line
     */
    private void pushStack(String line) {
        stack.push(line);
    }

    /**
     * This method allows to know whether the stack is empty or not
     *
     * @return
     */
    private boolean IsemptyStack() {
        return stack.empty();
    }

}
