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

    /**
     * Constructor of Text
     * @param path
     */
    public Text(String path) {
        super(path);
        macros = new HashMap<>();
    }

    /**
     * Allows to read a file line per line
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

            while (!((line = file.readLine())).equals("---- ENDMACRO")) {

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

            //line = deleteCom(line);
            
            separated = line.split(" ");

            if (macros.containsKey(separated[0])) {

                ReadMacro(separated);

            } else {

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

    private String deleteCom(String line) {

        String str2 = new String();
        
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '#') {

                return str2;
            }
            if (!(line.charAt(i) == '\t') || !(line.charAt(i) == ' ')) {
                str2 += Character.toString(line.charAt(i));
            }
        }
        return str2;

    }

    private void ReadLine(String line) {

        if ((line.charAt(0) <= 'A') || (line.charAt(0) >= 'Z')) {

            for (int j = 0; j < line.length(); j++) {

                if (isCommand(Character.toString(line.charAt(j)))) {

                    line = this.deleteCom(line);
                    list.add(toCommand((Character.toString(line.charAt(j)))));

                } else {

                    System.exit(4);

                }

            }

        } else {

            if (isCommand(line)) {

                line = this.deleteCom(line);
                list.add(toCommand(line));

            } else {

                System.exit(4);

            }
        }

    }

    private void ReadMacro(String[] separated) {

        int numParam = 1;

        Macro macro = macros.get(separated[0]);

        if (separated.length == 2) {

            for (int k = 0; k < Integer.parseInt(separated[1]); k++) {

                for (int j = 0; j < macro.getCommands().size(); j++) {

                    MacroOrLine(macro, j);

                }

            }

        } else {

            for (int j = 0; j < macro.getCommands().size(); j++) {

                if (numParam >= separated.length) {

                    MacroOrLine(macro, j);

                } else {

                    for (int k = 0; k < Integer.parseInt(separated[numParam]); k++) {

                        MacroOrLine(macro, j);

                    }

                    numParam++;
                }

            }

        }

    }

    /**
     *
     * @param macro
     * @param j
     */
    private void MacroOrLine(Macro macro, int j) {

        String[] separatedMacro = macro.getCommands().get(j).split(" ");

        if (macros.containsKey(separatedMacro[0])) {

            ReadMacro(separatedMacro);

        } else {

            ReadLine(macro.getCommands().get(j));

        }

    }
}
