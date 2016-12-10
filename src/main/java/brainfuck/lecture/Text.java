/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brainfuck.lecture;

import brainfuck.command.Decrementer;
import brainfuck.command.EnumCommands;
import static brainfuck.command.EnumCommands.isCommand;
import static brainfuck.command.EnumCommands.isShortCommand;
import static brainfuck.command.EnumCommands.toCommand;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

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

                line = deleteCom(line);

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

            line = deleteCom(line);

            if (!line.equals("")) {

                separated = line.split(" ");

                if (macros.containsKey(separated[0])) {

                    ReadMacro(separated);

                } else {

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
     * This method allows to support commentaries in a program
     *
     * @param line
     * @param file
     * @return
     * @throws IOException
     */
    private String deleteCom(String line) throws IOException {
        System.out.println(" LINE ---------------- " + line);
        String str2 = "";

        char prevChar = ' ';

        for (int k = 0; k < line.length(); k++) {

            if (line.charAt(k) == '#') {

                return str2;

            }

            if (line.charAt(k) != '\t' && line.charAt(k) != ' ') {

                str2 += line.charAt(k);

            }

            if (line.charAt(k) == ' ' && (prevChar != ' ' && prevChar != '\t' && !isShortCommand(Character.toString(prevChar)))) {

                str2 += line.charAt(k);

            }

            prevChar = line.charAt(k);

        }

        System.out.println(" LINE ---------------- " + str2);

        return str2;

    }

    /**
     * This method allows to read line per line a program //to check
     *
     * @param line
     */
    private void ReadLine(String line) {

        System.out.println("READ    LINE ----- |" + line + "|-");
        if ((line.charAt(0) <= 'A') || (line.charAt(0) >= 'Z')) {

            for (int j = 0; j < line.length(); j++) {

                if (isCommand(Character.toString(line.charAt(j)))) {

                    list.add(toCommand((Character.toString(line.charAt(j)))));

                } else {

                    errorLecture(line);

                    System.exit(4);

                }

            }

        } else {

            if (isCommand(line)) {

                list.add(toCommand(line));

            } else {

                errorLecture(line);

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

    /**
     * This method allows to
     *
     * @param macro
     * @param j
     * @param separated
     */
    private void MacroOrLine(Macro macro, int j, String[] separated) {

        //System.out.println("MACRO --- " + macro.getNom());

        /*for (int k = 0; k < separated.length; k++) {
         String separated1 = separated[k];

         System.out.println("SEPARATED -------------------------------------------------------------------- " + separated1);

         }*/
        String[] separatedMacro = macro.getCommands().get(j).split(" ");

        //System.out.println(separatedMacro.length + " ------- LIGNE COMMAND  ---- " + macro.getCommands().get(j));
        int repete = 1;

        //if (macro.isParam(separatedMacro[0].split(":")[0])) {
        if (macro.isParam(macro.getCommands().get(j).split(":")[0])) {

            //System.out.println("SEPA ---------------- " + macro.getCommands().get(j).split(":")[0]);
            //Récupérer la valeur du paramètre associé
            repete = macro.getNumParam(separatedMacro[0].split(":")[0]);
            /*System.out.println("NULL ---------------------------------------------------- " + separated[repete]);

             if (macro.isParam(separated[repete])) {

             System.out.println(" ----------------------------------------------------------------------------------- PARAM");

             }*/

            repete = Integer.parseInt(separated[repete]);   //Danger d'erreur si on sort du tableau

        }

        //System.out.println("REPETE ----------------- " + repete);
        //Répéter la ligne autant de fois que le paramètre, sinon la faire qu'une seule fois
        if (repete == 1) {

            if (macros.containsKey(separatedMacro[0])) {

                if (separatedMacro.length == macros.get(separatedMacro[0]).getnbParam() + 1) {

                    //System.out.println("NOM MACRO APPELANTE ------------------------------------------------------------" + macro.getNom());
                    for (int k = 0; k < separatedMacro.length; k++) {
                        String separatedMacro1 = separatedMacro[k];
                        /*System.out.println("------------------------------------------------------------------------------------ " + separatedMacro1);
                         System.out.println("PARAM MACRO APPELANTE --------------------------------------------------------------------- " + macro.isParam(separatedMacro1));*/

                        if (macro.isParam(separatedMacro1)) {

                            //System.out.println("PARAM MACRO APPELANTE --------------------------------------------------------------------- " + macro.getNumParam(separatedMacro1));
                            //System.out.println(k + " VALUE MACRO APPELANTE --------------------------------------------------------------------- " + separated[macro.getNumParam(separatedMacro1)]);
                            separatedMacro[k] = separated[macro.getNumParam(separatedMacro1)];

                        }

                    }

                    /* for (int k = 0; k < separatedMacro.length; k++) {
                     String separatedMacro1 = separatedMacro[k];
                        
                     System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ " + separatedMacro1);
                        
                     }*/
                    ReadMacro(separatedMacro);

                } else {
                    /*System.out.println(separatedMacro.length + " ------- LIGNE COMMAND ------  ---- " + macro.getCommands().get(j));
                     System.out.println(" ---------------------------------------------------------------------------------------------------------------- ");
                     System.out.println("separatedMacro.length --- " + separatedMacro.length + "        macros.get(separatedMacro[0]).getnbParam() + 1 ---- "
                     + (macros.get(separatedMacro[0]).getnbParam() + 1));*/

                    if (Run.ifTrace()) {

                        int N = list.size();

                        FileWriter file = null;
                        try {
                            file = new FileWriter("/Users/dev/TaskbarProject/test.txt", true);
                            file.write("La Macro n'a pas le bon nombre de paramètres\n"
                                    + macro.getCommands().get(j)
                                    + "La lecture de l'instruction n°" + N + " a échouée");
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

                    System.exit(10);

                }

            } else {

                ReadLine(macro.getCommands().get(j));

            }

        } else {

            for (int k = 0; k < repete; k++) {

                //System.out.println("------------------------------------------------------------------------ " + macro.getCommands().get(j).split(": ")[1]);
                separatedMacro = macro.getCommands().get(j).split(": ")[1].split(" ");

                if (macros.containsKey(separatedMacro[0])) {

                    ReadMacro(separatedMacro);

                } else {

                    ReadLine(macro.getCommands().get(j).split(": ")[1]);

                }

            }

        }
    }

    private void errorLecture(String line) {

        if (Run.ifTrace()) {

            int N = list.size();

            FileWriter file = null;
            try {
                file = new FileWriter("/Users/dev/TaskbarProject/test.txt", true);
                file.write("La lecture de l'instruction a échouée\n"
                        + line
                        + "La lecture de l'instruction n°" + N + " a échouée");
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

    }

}
