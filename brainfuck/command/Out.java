package brainfuck.command;

import brainfuck.Observer.Observable;
import brainfuck.Observer.ObservableLogs;
import brainfuck.Observer.Observateur;
import brainfuck.lecture.Fichiers;
import brainfuck.lecture.Run;
import brainfuck.lecture.Monitor;
import brainfuck.memory.Interpreter;
import static brainfuck.memory.Interpreter.FLAG_trace;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public final class Out implements Command, Observable, ObservableLogs {

    private String file;
    private static String tempString = "";

    private ArrayList observers;// Tableau d'observateurs.

    public Out() {

        Monitor observer = new Monitor();

        observers = new ArrayList();

        this.addObserver(observer);

        //this.addObserver(observer);
    }

    /**
     * This method allows to execute the command OUT
     */
    @Override
    public void execute() {

        /*Run.IncrEXEC_MOVE();
         Run.IncrDATA_READ();*/
        Fichiers tempfile = new Fichiers("");
        file = Interpreter.getFileOut();
        if (file.equals("")) {
            System.out.println((char) tempfile.getCm().getCurrentCaseValue());
        } else {
            file = Interpreter.getFileOut();
            try {
                FileWriter inputFile = new FileWriter(new File(file));
                FileReader tampon = new FileReader(new File(file));
                int temp = tampon.read();
                while (temp != -1) {
                    tempString += (char) temp;
                }
                tempString += (char) tempfile.getCm().getCurrentCaseValue();
                inputFile.write(tempString);
                inputFile.close();
                tampon.close();

            } catch (FileNotFoundException e) {

                if (FLAG_trace) {
                    notifyForLogs();
                }
                System.exit(3);

            } catch (IOException e) {

                if (FLAG_trace) {
                    notifyForLogs();
                }
                System.exit(3);
            }
        }

        notifyObservers();

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
    public void notifyObservers() {

        for (int i = 0; i < observers.size(); i++) {
            Observateur o = (Observateur) observers.get(i);
            o.updateExec_Move();// On utilise la méthode "tiré".
            o.updateData_Read();// On utilise la méthode "tiré".
        }

    }

    @Override
    public void notifyForLogs() {

        for (int i = 0; i < observers.size(); i++) {
            Observateur o = (Observateur) observers.get(i);
            o.logsDecr();// On utilise la méthode "tiré".

        }

    }

}
