/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brainfuck.lecture;

import brainfuck.command.EnumCommands;
import static brainfuck.command.EnumCommands.isCommand;
import static brainfuck.command.EnumCommands.isShortCommand;
import static brainfuck.command.EnumCommands.toCommand;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author TeamTaskbar
 */
public class Text extends Fichiers {

    private HashMap<String, Macro> macros;

    public Text(String path) {
        super(path);
        macros = new HashMap<>();
    }

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

            while (!((line = file.readLine())).equals("---- ENDMACRO")) { // On ne gère pas les NullPointerException

                line = deleteCom(line, file);

                if (line.charAt(0) == '*') {

                    separated = line.split(" ");

                    System.out.println("NOM --- " + separated[1]);

                    //macro = new Macro(separated[1]);
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
        do {

            line = deleteCom(line, file);
            separated = line.split(" ");

            if (macros.containsKey(separated[0])) {
                //System.out.println("LIT MACRO --- " + line);
                ReadMacro(separated);

            } else {
                //System.out.println("LIT LINE --- " + line);
                ReadLine(line);

            }

        } while ((line = file.readLine()) != null);

        file.close();

    }

    @Override
    public void Encod() {

        for (int j = 0; j < list.size(); j++) {

            EnumCommands get = list.get(j);

            System.out.println(get.getShort());

        }

    }

    private String deleteCom(String line, BufferedReader file) throws IOException {

        System.out.println("LINE --- " + line);
        String str2 = new String();

        char prevChar = ' ';

        System.out.println("LINE LENGTH -- " + line.length());

        for (int j = 0; j < line.length() && line.charAt(j) != ' ' && line.charAt(j) != '\t'; j++) {

            if (line.charAt(j) == '#') {

                return deleteCom(file.readLine(), file);

            }

        }

        for (int k = 0; k < line.length(); k++) {

            //System.out.println("BOUCLE + charAt -- |" + line.charAt(k) + "|");
            if (line.charAt(k) == '#') {

                System.out.println("LINE AVEC COMM --- |" + line + "|");
                System.out.println("LINE SANS COMM --- |" + str2 + "|");
                return str2;

            }

            if (line.charAt(k) != '\t' && line.charAt(k) != ' ') {

                System.out.println(" ------------------------------------------ |" + line.charAt(k) + "|");

                str2 += line.charAt(k);

            }

            if (line.charAt(k) == ' ' && (prevChar != ' ' && prevChar != '\t' && !isShortCommand(Character.toString(prevChar)))) {

                str2 += line.charAt(k);

            }

            prevChar = line.charAt(k);

        }

        System.out.println("LINE SANS COMM2 --- |" + str2 + "|");

        return str2;

    }

    private void ReadLine(String line) {

        //System.out.println("READ    LINE ----- |" + line + "|-");
        if ((line.charAt(0) <= 'A') || (line.charAt(0) >= 'Z')) {

            for (int j = 0; j < line.length(); j++) {

                if (isCommand(Character.toString(line.charAt(j)))) {

                    //line = this.deleteCom(line);
                    list.add(toCommand((Character.toString(line.charAt(j)))));

                } else {
                    System.out.println("MARCHE PAS --- |" + line.charAt(j) + "| " + line);
                    System.exit(4);

                }

            }

        } else {

            if (isCommand(line)) {

                //line = this.deleteCom(line);
                list.add(toCommand(line));

            } else {

                System.exit(4);

            }
        }

    }

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

                /*if (numParam >= separated.length) {

                 MacroOrLine(macro, j);

                 } else {

                 for (int k = 0; k < Integer.parseInt(separated[numParam]); k++) {

                 MacroOrLine(macro, j);

                 }

                 numParam++;
                 }*/
            }

        }

    }

    private void MacroOrLine(Macro macro, int j, String[] separated) {

        String[] separatedMacro = macro.getCommands().get(j).split(" ");

        //System.out.println(separatedMacro.length + " ------- LIGNE COMMAND  ---- " + macro.getCommands().get(j));
        int repete = 1;

        String tmp = "";

        //if (macro.isParam(separatedMacro[0].split(":")[0])) {
        if (macro.isParam(macro.getCommands().get(j).split(":")[0])) {

            //System.out.println("SEPA ---------------- " + macro.getCommands().get(j).split(":")[0]);
            //Récupérer la valeur du paramètre associé
            repete = macro.getNumParam(separatedMacro[0].split(":")[0]);
            //System.out.println("NULL ---------------------------------------------------- " + repete);
            repete = Integer.parseInt(separated[repete]);   //Danger d'erreur si on sort du tableau

        }

        //System.out.println("REPETE ----------------- " + repete);
        //Répéter la ligne autant de fois que le paramètre, sinon la faire qu'une seule fois
        if (repete == 1) {

            if (macros.containsKey(separatedMacro[0])) {

                if (separatedMacro.length == macros.get(separatedMacro[0]).getnbParam() + 1) {

                    ReadMacro(separatedMacro);

                } else {
                    /*System.out.println(separatedMacro.length + " ------- LIGNE COMMAND ------  ---- " + macro.getCommands().get(j));
                     System.out.println(" ---------------------------------------------------------------------------------------------------------------- ");
                     System.out.println("separatedMacro.length --- " + separatedMacro.length + "        macros.get(separatedMacro[0]).getnbParam() + 1 ---- "
                     + (macros.get(separatedMacro[0]).getnbParam() + 1));*/

                    System.exit(10);

                }

            } else {

                ReadLine(macro.getCommands().get(j));

            }

        } else {

            for (int k = 0; k < repete; k++) {

                //System.out.println("------------------------------------------------------------------------ " + macro.getCommands().get(j).split(": ")[1]);
                tmp = macro.getCommands().get(j).split(": ")[1];

                separatedMacro = tmp.split(" ");

                //System.out.println("+++++++++++++++++++++++++++++" + separatedMacro[0]);
                if (macros.containsKey(separatedMacro[0])) {

                    ReadMacro(separatedMacro);

                } else {

                    ReadLine(macro.getCommands().get(j).split(": ")[1]);

                }

            }

        }
    }

}
