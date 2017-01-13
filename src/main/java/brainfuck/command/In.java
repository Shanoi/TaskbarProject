package brainfuck.command;

import brainfuck.Observer.Observable;
import brainfuck.Observer.ObservableLogs;
import brainfuck.Observer.Observateur;
import brainfuck.lecture.Fichiers;
import brainfuck.lecture.Monitor;
import brainfuck.memory.Launcher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static brainfuck.memory.Launcher.FLAG_trace;

/**
 * This class represents the command IN of the Brainf*ck langage.
 *
 * @author TeamTaskbar
 */
public final class In implements Command, Observable, ObservableLogs {

    private String file;
    private String str;
    private static int cnt = 0;
    private int temp = 0;
    private int text_length = 0;
    private static int state = 0;
    private static ArrayList<Integer> text_list = new ArrayList<>();

    private ArrayList observers;// Tableau d'observateurs.

    //Constructor of In

    public In() {

        Monitor observer = new Monitor();

        observers = new ArrayList();

        this.addObserver(observer);

    }

    public void In(String file) {
        this.file = file;
    }

    /**
     * This method allows to execute the command IN.
     */
    @Override
    public void execute() {

        /*Run.IncrEXEC_MOVE();
         Run.IncrDATA_WRITE();*/
        file = Launcher.getFileIn();
        if (file.equals("")) {
            Fichiers tempfile = new Fichiers("");
            Scanner sc = new Scanner(System.in);
            str = sc.nextLine();
            if (str.length() > 0) {
                tempfile.getCm().setCurrentCaseValue((byte) str.charAt(0));
            } else {

                if (FLAG_trace) {
                    notifyForLogs();
                }
                System.exit(3);
            }
        } else {
            Fichiers tempfile = new Fichiers("");
            File inputFile = new File(file);
            FileReader in = null;
            try {
                in = new FileReader(inputFile);

                if (state == 0) {
                    temp = in.read();
                    while (temp != -1) {
                        text_list.add(temp);
                        temp = in.read();
                    }
                    text_length = text_list.size();
                    state = 1;
                }

                if (cnt < text_length) {
                    tempfile.getCm().setCurrentCaseValue((byte) (char) text_list.get(cnt).intValue());
                    cnt++;
                } else {
                    System.exit(3);
                }

            } catch (FileNotFoundException e) {

                notifyForLogs();
                System.exit(3);

            } catch (IOException e) {

                notifyForLogs();
                System.exit(3);

            }
        }

        notifyObservers();

    }

    @Override
    /**
     * This method allows to add an observer.
     */
    public void addObserver(Observateur o) {

        observers.add(o);

    }

    @Override
    /**
     * This method allows to delete an observer previously added.
     */
    public void delObserver(Observateur o) {

        observers.remove(0);

    }

    @Override
    /**
     * This method allows the observers to update their information.
     */
    public void notifyObservers() {

        for (int i = 0; i < observers.size(); i++) {
            Observateur o = (Observateur) observers.get(i);
            o.updateExec_Move();// On utilise la méthode "tiré".
            o.updateData_Write();// On utilise la méthode "tiré".
        }

    }

    @Override
    /**
     * This method allows to update the observers for the log command.
     */
    public void notifyForLogs() {

        for (int i = 0; i < observers.size(); i++) {
            Observateur o = (Observateur) observers.get(i);
            o.logsDecr();// On utilise la méthode "tiré".

        }

    }

}
