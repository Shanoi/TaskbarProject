/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brainfuck.lecture;

import brainfuck.command.EnumCommands;
import static brainfuck.command.EnumCommands.isCommand;
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

            while (!((line = file.readLine())).equals("---- ENDMACRO")) {

                System.out.println("LINE --- " + line);

                if (line.charAt(0) == '*') {

                    separated = line.split(" ");

                    macro = new Macro(separated[1]);

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

            separated = line.split(" ");

            if (macros.containsKey(separated[0])) {

                ReadMacro(separated);

            } else {

                System.out.println("PAS MACRO --- " + line);

                ReadLine(line);

            }

        } while ((line = file.readLine()) != null);

        file.close();
        System.out.println("MA LISTE --- " + list);
        for (int j = 0; j < list.size(); j++) {

            System.out.println("LIST ------ " + list.get(j));

        }

    }

    @Override
    public void Encod() {

        for (int j = 0; j < list.size(); j++) {

            EnumCommands get = list.get(j);

            System.out.println(get.getShort());

        }

    }

    private String deleteCom(String line) {
        String str2 = new String();
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '#') {
                return str2;
            }
            if (!(line.charAt(i) == '\t')) {
                str2 += Character.toString(line.charAt(i));
            }
        }
        return str2;

    }

    private void ReadLine(String line) {

        if ((line.charAt(0) <= 'A') || (line.charAt(0) >= 'Z')) {
            System.out.println("S COURTE --- ");
            for (int j = 0; j < line.length(); j++) {

                if (isCommand(Character.toString(line.charAt(j)))) {
                    line = this.deleteCom(line);
                    System.out.println("LINE S COURTE ---- " + (Character.toString(line.charAt(j))));
                    list.add(toCommand((Character.toString(line.charAt(j)))));

                } else {

                    System.out.println("TEST");
                    System.exit(4);

                }

            }

        } else if ((line.charAt(0) == '#') || (line.charAt(0) == '\t')) {

        } else {

            if (isCommand(line)) {
                line = this.deleteCom(line);
                list.add(toCommand(line));

            } else {
                System.out.println("TEST2");
                System.exit(4);

            }

        }

    }

    private void ReadMacro(String[] separated) {

        //System.out.println("MACRO ----- " + line);
        int numParam = 1;

        Macro macro = macros.get(separated[0]);

        if (separated.length == 2) {

            for (int k = 0; k < Integer.parseInt(separated[1]); k++) {

                for (int j = 0; j < macro.getCommands().size(); j++) {

                    ReadLine(macro.getCommands().get(j));

                }

            }

        } else {

            for (int j = 0; j < macro.getCommands().size(); j++) {

                if (numParam >= separated.length) {

                    ReadLine(macro.getCommands().get(j));

                } else {

                    System.out.println("PARMAS --- " + numParam);
                    for (int k = 0; k < Integer.parseInt(separated[numParam]); k++) {

                        ReadLine(macro.getCommands().get(j));

                    }

                    numParam++;
                }

            }

        }

    }
}
