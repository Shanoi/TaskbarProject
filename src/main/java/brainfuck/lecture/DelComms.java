/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brainfuck.lecture;

import java.io.BufferedReader;
import java.io.IOException;

import static brainfuck.command.EnumCommands.isShortCommand;

/**
 * This class allows the deletion of commentaries, tabsand spaces in
 * a Brainf*ck program without modifying the syntax of the macros.
 * @author TeamTaskbar
 */
public class DelComms {

    /**
     * This method allows to support commentaries in a program
     *
     * @param line the line considered.
     * @param file the file considered.
     * @return the line without the com.
     * @throws IOException
     */
    public static String deleteCom(String line, BufferedReader file) throws IOException {

        
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

        return str2;

    }

}
