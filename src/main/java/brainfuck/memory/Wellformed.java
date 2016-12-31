package brainfuck.memory;

import brainfuck.Observer.ObservableLogsWF;
import brainfuck.Observer.Observateur;
import brainfuck.command.EnumCommands;
import brainfuck.lecture.Monitor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import static brainfuck.command.EnumCommands.BACK;
import static brainfuck.command.EnumCommands.JUMP;
import static brainfuck.memory.Launcher.FLAG_trace;

public class Wellformed implements ObservableLogsWF {

    private Stack<String> stack = new Stack<>();

    private ArrayList<EnumCommands> commands;

    private ArrayList observers;// Tableau d'observateurs.

    public Wellformed(ArrayList<EnumCommands> commands) {

        this.commands = commands;

        Monitor observer = new Monitor();

        observers = new ArrayList();

        this.addObserver(observer);

    }

    public void execute() throws IOException, FileNotFoundException {
        for (int i = 0; i < commands.size(); i++) {
            EnumCommands get = commands.get(i);

            if (get == JUMP) {
                pushStack("[");
            }
            if (get == BACK && IsemptyStack()) {

                if (FLAG_trace) {
                    notifyForLogs(i, true);
                }

                System.exit(4);
            }
            if (get == BACK) {
                popStack();
            }

        }

        if (!IsemptyStack()) {

            if (FLAG_trace) {
                notifyForLogs(stack.size(), false);
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

    @Override
    public void addObserver(Observateur o) {

        observers.add(o);

    }

    @Override
    public void delObserver(Observateur o) {

        observers.remove(0);

    }

    @Override
    public void notifyForLogs(int i, boolean BACK) {

        for (int j = 0; j < observers.size(); j++) {
            Observateur o = (Observateur) observers.get(j);
            o.logsWellformed(i, BACK);// On utilise la méthode "tiré".

        }

    }

}
