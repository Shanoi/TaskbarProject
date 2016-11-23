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

        while ((line = file.readLine()) != null) {
            
            if ((line.charAt(0) <= 'A') || (line.charAt(0) >= 'Z')) {

                for (int j = 0; j < line.length(); j++) {

                    if (isCommand(Character.toString(line.charAt(j)))) {
                        line = this.deleteCom(line);
                        list.add(toCommand((Character.toString(line.charAt(j)))));

                    } else {
                        System.exit(4);

                    }

                }

            }
            else if((line.charAt(0) == '#') || (line.charAt(0) == '\t')){
            
            }
            else{
                
                if (isCommand(line)) {
                    line = this.deleteCom(line);
                    list.add(toCommand(line));

                } else {

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
    
    public String deleteCom(String line)
    {
        String str2 = new String();
        for( int i = 0; i < line.length(); i++)
        {
            if ( line.charAt(i) == '#' )
                return str2;
            if( !(line.charAt(i) == '\t') )
            {
                str2 += Character.toString(line.charAt(i));
            }
        }
        return str2;
        
        
    }
}
