package brainfuck.command;

import Observer.Observable;
import Observer.ObservableLogs;
import Observer.Observateur;
import brainfuck.lecture.Fichiers;
import brainfuck.lecture.Run;
import brainfuck.lecture.Monitor;
import brainfuck.memory.Interpreter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public final class In implements Command, Observable, ObservableLogs {

    private String file;
    private String str;
    private static int cnt = 0;
    private int temp = 0;
    private int text_length = 0;
    private static int state = 0;
    private static ArrayList<Integer> text_list = new ArrayList<>();

    private ArrayList observers;// Tableau d'observateurs.

    public In() {

        Monitor observer = new Monitor();

        observers = new ArrayList();

        this.addObserver(observer);
        
        //this.addObserver(observer);
    }
    
    public void In(String file) {
        this.file = file;
    }

    /**
     * This method allows to execute the command IN
     */
    @Override
    public void execute() {

        /*Run.IncrEXEC_MOVE();
        Run.IncrDATA_WRITE();*/

        file = Interpreter.getFileIn();
        if (file.equals("")) {
            Fichiers tempfile = new Fichiers("");
            Scanner sc = new Scanner(System.in);
            str = sc.nextLine();
            if (str.length() > 0) {
                tempfile.getCm().setCurrentCaseValue((byte) str.charAt(0));
            } else {
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

                System.exit(3);

            } catch (IOException e) {

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
            o.updateData_Write();// On utilise la méthode "tiré".
        }

    }

    @Override
    public void notifyForLogs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
