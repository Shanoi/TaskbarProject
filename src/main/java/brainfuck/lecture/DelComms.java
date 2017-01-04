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
 * This class allows the delete of a commentary.
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

        //System.out.println("LINE --- " + line);
        String str2 = "";

        char prevChar = ' ';

        //System.out.println("LINE LENGTH -- " + line.length());
        /*for (int j = 0; j < line.length() && line.charAt(j) != ' ' && line.charAt(j) != '\t'; j++) {

         if (line.charAt(j) == '#') {

         //return deleteCom(file.readLine(), file);
         return "";

         }

         }*/
        for (int k = 0; k < line.length(); k++) {

            //System.out.println("BOUCLE + charAt -- |" + line.charAt(k) + "|");
            if (line.charAt(k) == '#') {

                //System.out.println("LINE AVEC COMM --- |" + line + "|");
                //System.out.println("LINE SANS COMM --- |" + str2 + "|");
                return str2;

            }

            if (line.charAt(k) != '\t' && line.charAt(k) != ' ') {

                //System.out.println(" ------------------------------------------ |" + line.charAt(k) + "|");
                str2 += line.charAt(k);

            }

            if (line.charAt(k) == ' ' && (prevChar != ' ' && prevChar != '\t' && !isShortCommand(Character.toString(prevChar)))) {

                str2 += line.charAt(k);

            }

            prevChar = line.charAt(k);

        }

        //System.out.println("LINE SANS COMM2 --- |" + str2 + "|");
        return str2;

    }

}
