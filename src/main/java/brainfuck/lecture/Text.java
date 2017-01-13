/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brainfuck.lecture;

import brainfuck.Observer.ObservableLogstxt;
import brainfuck.Observer.Observateur;
import brainfuck.command.EnumCommands;

import java.util.StringTokenizer;

import static brainfuck.command.EnumCommands.isCommand;
import static brainfuck.command.EnumCommands.toCommand;
import static brainfuck.lecture.DelComms.deleteCom;
import static brainfuck.lecture.Fichiers.list;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import static brainfuck.memory.Launcher.FLAG_trace;
import static brainfuck.command.EnumCommands.CALL;

/**
 * This class reads a Brainf*ck text program (.txt). (same actions as the Image
 * class but with the textual instructions) It reads the instructions and stores
 * them. It manages the reading of the macros and the creation of them.
 *
 * @author TeamTaskbar
 */
public final class Text extends Fichiers implements ObservableLogstxt {

    private HashMap<String, Macro> macros;

    private ArrayList observers;// Tableau d'observateurs.

    //Constructor of Text

    public Text(String path) {
        super(path);
        macros = new HashMap<>();

        Monitor observer = new Monitor();

        observers = new ArrayList();

        this.addObserver(observer);

    }

    /**
     * This method allows to read a program(.txt) or other.
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

                if (!line.equals("")) {

                    if (line.charAt(0) == '*') {

                        separated = line.split(" ");

                        macro = new Macro(separated);

                        macros.put(separated[1], macro);

                    } else {

                        macro.fillCommands(line);

                    }

                }

            }

            line = file.readLine();

        }

        if (line.equals("---- FONCTION")) {

            while (!((line = file.readLine())).equals("---- ENDFONCTION") && line != null) {

                line = deleteCom(line, file);

                if (!line.equals("")) {

                    if (line.charAt(0) == '*') {
                        String str = new String("");
                        int i = 0;
                        for (i = 0; i < line.length() && line.charAt(i) != ' '; i++) {
                            if (line.charAt(i) != '*') {
                                str += line.charAt(i);
                            }
                        }
                        if (i < line.length() && line.charAt(i) == ' ') {
                            StringTokenizer str2 = new StringTokenizer(line);
                            str2.nextToken();
                            if (str2.hasMoreTokens()) {
                                nbArgFonction.put(str, Integer.parseInt(str2.nextToken()));
                            }

                        } else {
                            nbArgFonction.put(str, 0);
                        }

                        fonction2.put(str, list.size());

                    } else {

                        ReadLine(line);

                    }
                }

            }

            line = file.readLine();

            cm.setI(list.size());

        }

        //////////////////////////////////////////////////////////
        ////////////////////Lecture du programme//////////////////
        //////////////////////////////////////////////////////////
        while (line != null) {

            line = deleteCom(line, file);

            if (!line.equals("")) {

                separated = line.split(" ");

                if (macros.containsKey(separated[0])) {

                    ReadMacro(separated, null, null);

                } else {

                    ReadLine(line);

                }
            }

            line = file.readLine();

        }

        file.close();

    }

    @Override
    /**
     * This method allows to encod a text file.
     */
    public void Encod() {

        for (int j = 0; j < list.size(); j++) {

            EnumCommands get = list.get(j);

            System.out.println(get.getShort());

        }

    }

    /**
     * This method allows to read line per line a program.
     *
     * @param line the line considered.
     */
    private void ReadLine(String line) {

        if ((line.charAt(0) < 'A') || (line.charAt(0) > 'Z')) { //modif le 25/12 >= et <= avant

            for (int j = 0; j < line.length(); j++) {

                if (isCommand(Character.toString(line.charAt(j)))) {

                    list.add(toCommand((Character.toString(line.charAt(j)))));

                } else {

                    if (FLAG_trace) {
                        notifyForLogs(line, list.size());
                    }

                    System.exit(4);

                }

            }

        } else if (fonction2.containsKey(line)) {
            list.add(CALL); //a ajouter dans enum command

            fonction.put(list.size(), line);
            //mettre une liste à l aplace d'une stack pourl 'ordre

        } else {

            if (isCommand(line)) {

                list.add(toCommand(line));

            } else {

                if (FLAG_trace) {
                    notifyForLogs(line, list.size());
                }

                System.exit(4);

            }
        }

    }

    /**
     * This method allows to support macro in the program.
     *
     * @param separated
     */
    private void ReadMacro(String[] separated, Macro appelante, String[] separatedApp) {

        Macro macro = macros.get(separated[0]);

        int cptBoucle = 0;

        if (separated.length == 2 && macro.getnbParam() == 0) {

            if (appelante != null && appelante.isParam(separated[1])) {

                appelante.getNumParam(separated[1]);

                cptBoucle = Integer.parseInt(separatedApp[appelante.getNumParam(separated[1])]);

            } else {

                cptBoucle = Integer.parseInt(separated[1]);

            }
            for (int k = 0; k < cptBoucle; k++) {

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

    /**
     * This method allows to know whether it's a Macro line or not.
     * @param macro the concerned macro.
     * @param j
     * @param separated
     */
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

                    ReadMacro(separatedMacro, macro, separated);

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

                    ReadMacro(separatedMacro, macro, separated);

                } else {

                    ReadLine(macro.getCommands().get(j).split(": ")[1]);

                }

            }

        }
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
     * This method allows to update te observers for the log command.
     */
    public void notifyForLogs(String string, int i) {

        for (int j = 0; j < observers.size(); j++) {
            Observateur o = (Observateur) observers.get(j);
            o.logsTxt(string, i);// On utilise la méthode "tiré".

        }

    }

}
