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

/**
 *
 * @author TeamTaskbar
 */
public class Text extends Fichiers {

    public Text(String path) {
        super(path);
    }

    @Override
    public void Read() throws FileNotFoundException, IOException {

        BufferedReader file = new BufferedReader(new FileReader(path));
        String line = new String();

        
        //////////////////////////////////////////////////////////
        ////////////////////Lecture des macros////////////////////
        //////////////////////////////////////////////////////////
        
        String temp = "";//line = file.readLine()
        
        while(!(temp).equals("---- ENDMACRO")){
            
            temp = file.readLine();
            
            System.out.println("LINE --- " + temp);
            
        }
        
        
        //System.out.println("LINE 23 --- " + file.readLine());
        
        //////////////////////////////////////////////////////////
        ////////////////////Lecture du programme//////////////////
        //////////////////////////////////////////////////////////
        while ((line = file.readLine()) != null) {

            if ((line.charAt(0) <= 'A') || (line.charAt(0) >= 'Z')) {

                for (int j = 0; j < line.length(); j++) {

                    if (isCommand(Character.toString(line.charAt(j)))) {

                        list.add(toCommand((Character.toString(line.charAt(j)))));

                    } else {
                        
                        System.out.println("TEST");
                        System.exit(4);

                    }

                }

            } else {

                if (isCommand(line)) {

                    list.add(toCommand(line));

                } else {
                    System.out.println("TEST2");
                    System.exit(4);

                }

            }
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
}
