/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brainfuck.lecture;

import Observer.ObservableLogs;
import Observer.ObservableLogstxt;
import Observer.Observateur;
import brainfuck.command.EnumCommands;
import static brainfuck.command.EnumCommands.isCommand;
import static brainfuck.command.EnumCommands.isShortCommand;
import static brainfuck.command.EnumCommands.toCommand;
import static brainfuck.lecture.DelComms.deleteCom;
import static brainfuck.memory.Interpreter.FLAG_trace;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author TeamTaskbar
 */
public final class Text extends Fichiers implements ObservableLogstxt {

    private HashMap<String, Macro> macros;

    private ArrayList observers;// Tableau d'observateurs.

    public Text(String path) {
        super(path);
        macros = new HashMap<>();

        Monitor observer = new Monitor();

        observers = new ArrayList();

        this.addObserver(observer);

    }

    /**
     * This method allows to read a program(.txt)
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Override
    public void Read() throws FileNotFoundException, IOException {

        BufferedReader file = new BufferedReader(new FileReader(path));
        String line = new String();

        line = file.readLine();

        String[] separated;

        Macro macro = new Macro();

        //////////////////////////////////////////////////////////
        ////////////////////Lecture des macros////////////////////
        //////////////////////////////////////////////////////////
        if (line.equals("---- MACRO")) {

            while (!((line = file.readLine())).equals("---- ENDMACRO") && line != null) {

                line = deleteCom(line, file);

                if (line.charAt(0) == '*') {

                    separated = line.split(" ");

                    System.out.println("NOM --- " + separated[1]);

                    macro = new Macro(separated);

                    macros.put(separated[1], macro);

                } else {

                    macro.fillCommands(line);

                }

            }

            line = file.readLine();

        }

        //////////////////////////////////////////////////////////
        ////////////////////Lecture du programme//////////////////
        //////////////////////////////////////////////////////////
        while (line != null) {

            line = deleteCom(line, file);

            if (!line.equals("")) {

                separated = line.split(" ");

                if (macros.containsKey(separated[0])) {

                    ReadMacro(separated);

                } else {
                    System.out.println("LIT LINE --- " + line);
                    ReadLine(line);

                }

            }

            line = file.readLine();

        }

        file.close();

    }

    @Override
    public void Encod() {

        for (int j = 0; j < list.size(); j++) {

            EnumCommands get = list.get(j);

            System.out.println(get.getShort());

        }

    }

    /**
     * This method allows to read line per line a program //to check
     *
     * @param line
     */
    private void ReadLine(String line) {

        if ((line.charAt(0) <= 'A') || (line.charAt(0) >= 'Z')) {

            for (int j = 0; j < line.length(); j++) {

                if (isCommand(Character.toString(line.charAt(j)))) {

                    list.add(toCommand((Character.toString(line.charAt(j)))));

                } else {
                    System.out.println("MARCHE PAS --- |" + line.charAt(j) + "| \n" + line + "     " + j);

                    if (FLAG_trace) {
                        notifyForLogs(line, list.size());
                    }

                    System.exit(4);

                }

            }

        } else {

            if (isCommand(line)) {

                list.add(toCommand(line));

            } else {

                System.out.println("nOPE -- " + line);

                if (FLAG_trace) {
                    notifyForLogs(line, list.size());
                }

                System.exit(4);

            }
        }

    }

    /**
     * This method allows to support macro in the program
     *
     * @param separated
     */
    private void ReadMacro(String[] separated) {

        Macro macro = macros.get(separated[0]);

        if (separated.length == 2 && macro.getnbParam() == 0) {

            for (int k = 0; k < Integer.parseInt(separated[1]); k++) {

                for (int j = 0; j < macro.getCommands().size(); j++) {

                    MacroOrLine(macro, j, separated);

                }

            }

        } else {

            for (int j = 0; j < macro.getCommands().size(); j++) {

                MacroOrLine(macro, j, separated);

            }

        }

    }

    private void MacroOrLine(Macro macro, int j, String[] separated) {

        String[] separatedMacro = macro.getCommands().get(j).split(" ");

        int repete = 1;

        String tmp = "";

        //if (macro.isParam(separatedMacro[0].split(":")[0])) {
        if (macro.isParam(macro.getCommands().get(j).split(":")[0])) {

            //Récupérer la valeur du paramètre associé
            repete = macro.getNumParam(separatedMacro[0].split(":")[0]);

            repete = Integer.parseInt(separated[repete]);   //Danger d'erreur si on sort du tableau

        }

        //Répéter la ligne autant de fois que le paramètre, sinon la faire qu'une seule fois
        if (repete == 1) {

            if (macros.containsKey(separatedMacro[0])) {

                if (separatedMacro.length == macros.get(separatedMacro[0]).getnbParam() + 1) {

                    for (int k = 0; k < separatedMacro.length; k++) {
                        String separatedMacro1 = separatedMacro[k];

                        if (macro.isParam(separatedMacro1)) {

                            separatedMacro[k] = separated[macro.getNumParam(separatedMacro1)];

                        }

                    }

                    ReadMacro(separatedMacro);

                } else {

                    if (FLAG_trace) {
                        notifyForLogs(macro.getCommands().get(j), list.size());
                    }

                    System.exit(10);

                }

            } else {

                ReadLine(macro.getCommands().get(j));

            }

        } else {

            for (int k = 0; k < repete; k++) {

                tmp = macro.getCommands().get(j).split(": ")[1];

                separatedMacro = tmp.split(" ");

                if (macros.containsKey(separatedMacro[0])) {

                    ReadMacro(separatedMacro);

                } else {

                    ReadLine(macro.getCommands().get(j).split(": ")[1]);

                }

            }

        }
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
    public void notifyForLogs(String string, int i) {

        for (int j = 0; j < observers.size(); j++) {
            Observateur o = (Observateur) observers.get(j);
            o.logsTxt(string, i);// On utilise la méthode "tiré".

        }

    }

}
