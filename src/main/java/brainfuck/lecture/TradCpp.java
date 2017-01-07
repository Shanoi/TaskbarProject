/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brainfuck.lecture;

import brainfuck.Observer.ObservableLogstxt;
import brainfuck.Observer.Observateur;
import brainfuck.command.EnumCommands;
import static brainfuck.command.EnumCommands.BACK;
import static brainfuck.command.EnumCommands.IN;
import static brainfuck.command.EnumCommands.INCR;
import static brainfuck.command.EnumCommands.JUMP;
import static brainfuck.command.EnumCommands.LEFT;
import static brainfuck.command.EnumCommands.OUT;
import static brainfuck.command.EnumCommands.RIGHT;
import static brainfuck.command.EnumCommands.isCommand;
import static brainfuck.command.EnumCommands.toCommand;
import static brainfuck.lecture.DelComms.deleteCom;
import static brainfuck.memory.Launcher.FLAG_trace;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//////////////////////////////
///////Attention à la lecture, il faut la faire dans ce fichiers sinon on ne prend pas en compte les macros
//////////////////////////////
/**
 *
 * @author Olivier
 */
public class TradCpp implements ObservableLogstxt {

    private ArrayList<EnumCommands> commands = new ArrayList<>();

    private String filename;

    private PrintWriter file;

    private ArrayList observers;// Tableau d'observateurs.

    private boolean in = false;

    private boolean out = false;

    private int cptboucle = 0;

    public TradCpp(/*ArrayList<EnumCommands> commands,*/String filename) {

        //this.commands = commands;
        this.filename = filename;

        try {
            file = new PrintWriter(new FileWriter(filename + ".cpp"));
        } catch (IOException ex) {
            Logger.getLogger(TradCpp.class.getName()).log(Level.SEVERE, null, ex);
        }

        Monitor observer = new Monitor();

        observers = new ArrayList();

        this.addObserver(observer);

        fillCommands();

    }

    public void execute() {

        if (commands.contains(IN)) {

            writeSupIn();
            in = true;

        }

        if (commands.contains(OUT)) {

            writeSupOut();
            out = true;

        }

        writeHeader();

        writeInstr();

        writeEnd();

    }

    private void writeHeader() {

        StringBuilder Header = new StringBuilder();
        Header.append("#include <string>\n"
                + "#include <fstream>\n"
                + "#include <iostream>\n"
                + "#include <cstring>\n"
                + "#include <stdlib.h>\n\n");

        if (in) {

            Header.append("#include \"In.h\"\n");

        }

        if (out) {

            Header.append("#include \"Out.h\"\n");

        }

        Header.append("\nusing namespace std;\n\n"
                + "void checkSegFault(int i);\n\n");

        writer(Header.toString());

    }

    private void writeEnd() {

        writer("void checkSegFault(int i, int k){\n\n"
                + "\tif (i + k > 30000 || i + k < 0){\n\n"
                + "\t\texit(1);\n\n"
                + "\t}\n"
                + "}\n");

    }

    private void writeInstr() {

        writer("int main(int n, char *params[]){\n\n"
                + "\tunsigned char m[30000];\n\n"
                + "\tint position = 0;\n\n");

        if (in) {

            writer("In *fIn = new In(argv);\n");

        }

        if (out) {

            writer("Out *fOut = new Out(argv);\n");

        }

        writer("\n");

        int cpt = 0;

        int nbInstrCons = 0;

        EnumCommands prevInstr = commands.get(0);

        int size = commands.size();

        for (int i = 0; i < size; i++) {

            //System.out.println("COMMAND -- " + commands.get(i) + " -- " + size + " --- " + i);
            if (commands.get(i).equals(prevInstr) && i != size) {

                cpt++;
                nbInstrCons++;

            } else {

                if (commands.get(i).equals(OUT)) {

                    writeInstr(cpt, prevInstr);

                    writeOut();

                    cpt = 1;

                } else if (commands.get(i).equals(IN)) {

                    writeInstr(cpt, prevInstr);

                    writeIn();

                    cpt = 1;

                } else if (commands.get(i).equals(JUMP)) {

                    writeFunc();

                } else if (commands.get(i).equals(BACK)) {

                    writer("\t\n}\n\n");

                } else {

                    writeInstr(cpt, prevInstr);

                    cpt = 1;
                    nbInstrCons = 1;

                }

            }

            prevInstr = commands.get(i);

        }

        if (in) {

            writer("delete(In);\n");

        }

        if (out) {

            writer("delete(fOut);\n");

        }

        //writeInstr(pos, cpt, prevInstr);
        writer("\n\treturn 0;"
                + "\n\n}\n\n");

    }

    private void writeInstr(int cpt, EnumCommands prevInstr) {

        switch (prevInstr) {

            case INCR:

                writer("\tm[position]+= " + cpt + ";\n");
                break;

            case DECR:

                writer("\tm[position]-= " + cpt + ";\n");
                break;

            case RIGHT:

                writer("\n\tcheckSegFault(position, " + cpt + ");\n\n"
                        + "\tposition += " + cpt + ";\n");

                break;

            case LEFT:

                writer("\n\tcheckSegFault(position, " + -cpt + ");\n\n"
                        + "\tposition -= " + cpt + ";\n");

                break;

        }

    }

    private void writeOut() {

        //writer("\n\tcout << (char) m[" + pos + "] << endl;\n\n");
        writer("\n\tfOut->Output(m[position]);\n");

    }

    private void writeIn() {

        /*writer("\n\tcin >> c;\n\n"
         + "m[" + pos + "]\n");*/
        writer("\n\tfm[position] = In->Input();\n");

    }

    private void writeFunc() {

        writer("\n\twhile(m[position] > 0){\n"
                + "\n");

    }

    private void writeSupIn() {

        String[] separated;

        separated = filename.split("/");

        String fileInName = filename.replace(separated[separated.length - 1], "In");

        System.out.println(" ---- " + fileInName);

        PrintWriter fileIn;

        try {
            fileIn = new PrintWriter(new FileWriter(fileInName + ".h"));

            fileIn.write("#pragma once\n"
                    + "\n"
                    + "#include <string>\n"
                    + "#include <fstream>\n"
                    + "\n"
                    + "class In\n"
                    + "{\n"
                    + "private:\n"
                    + "\n"
                    + "	std::string fileIn;\n"
                    + "\n"
                    + "	std::ifstream fichierIn;\n"
                    + "\n"
                    + "	std::string argsFileIn(char *params[]);\n"
                    + "\n"
                    + "public:\n"
                    + "\n"
                    + "	In(char *params[]);\n"
                    + "	~In();\n"
                    + "\n"
                    + "	char Input();\n"
                    + "\n"
                    + "};\n"
                    + "");

            fileIn.close();

            fileIn = new PrintWriter(new FileWriter(fileInName + ".cpp"));

            fileIn.write("#include \"In.h\"\n"
                    + "\n"
                    + "#include <string>\n"
                    + "#include <fstream>\n"
                    + "#include <iostream>\n"
                    + "#include <cstring>\n"
                    + "#include <stdlib.h>\n"
                    + "\n"
                    + "using namespace std;\n"
                    + "\n"
                    + "In::In(char *params[]) : fichierIn(argsFileIn(params).c_str(), ios::in)\n"
                    + "{\n"
                    + "\n"
                    + "	if (fileIn.compare(\"\") != 0){\n"
                    + "\n"
                    + "		if (!fichierIn){\n"
                    + "\n"
                    + "			cerr << \"Erreur à l'ouverture du fichier en lecture\" << endl;\n"
                    + "\n"
                    + "		}\n"
                    + "\n"
                    + "	}\n"
                    + "\n"
                    + "}\n"
                    + "\n"
                    + "\n"
                    + "In::~In()\n"
                    + "{\n"
                    + "\n"
                    + "	fichierIn.close();\n"
                    + "\n"
                    + "}\n"
                    + "\n"
                    + "char In::Input()\n"
                    + "{\n"
                    + "\n"
                    + "	char c;\n"
                    + "\n"
                    + "	if (fichierIn){\n"
                    + "\n"
                    + "		fichierIn.get(c);\n"
                    + "\n"
                    + "	}\n"
                    + "	else{\n"
                    + "\n"
                    + "		cout << \"Entrer un caractère\" << endl;\n"
                    + "\n"
                    + "		cin >> c;\n"
                    + "\n"
                    + "	}\n"
                    + "\n"
                    + "	return c;\n"
                    + "\n"
                    + "}\n"
                    + "\n"
                    + "string In::argsFileIn(char *params[]){\n"
                    + "\n"
                    + "	string fileIn = \"\";\n"
                    + "\n"
                    + "	for (int i = 0; params[i] != NULL; i++)\n"
                    + "	{\n"
                    + "			\n"
                    + "		if (strcmp(params[i], \"-i\") == 0 && params[i + 1] != NULL){\n"
                    + "\n"
                    + "			fileIn = params[i + 1];\n"
                    + "\n"
                    + "		}\n"
                    + "\n"
                    + "	}\n"
                    + "\n"
                    + "	return fileIn;\n"
                    + "\n"
                    + "}\n"
                    + "");

            fileIn.close();

        } catch (IOException ex) {
            Logger.getLogger(TradCpp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void writeSupOut() {

        String[] separated;

        separated = filename.split("/");

        String fileInName = filename.replace(separated[separated.length - 1], "Out");

        System.out.println(" ---- " + fileInName);

        PrintWriter fileIn;

        try {
            fileIn = new PrintWriter(new FileWriter(fileInName + ".h"));

            fileIn.write("#pragma once\n"
                    + "\n"
                    + "#include <string>\n"
                    + "#include <fstream>\n"
                    + "\n"
                    + "class Out\n"
                    + "{\n"
                    + "private:\n"
                    + "\n"
                    + "	std::string fileOut;\n"
                    + "\n"
                    + "	std::ofstream fichierOut;\n"
                    + "\n"
                    + "	std::string argsFileOut(char *params[]);\n"
                    + "\n"
                    + "public:\n"
                    + "	\n"
                    + "	Out(char *params[]);\n"
                    + "	~Out();\n"
                    + "\n"
                    + "	void Output(char c);\n"
                    + "\n"
                    + "};\n"
                    + "");

            fileIn.close();

            fileIn = new PrintWriter(new FileWriter(fileInName + ".cpp"));

            fileIn.write("#include \"Out.h\"\n"
                    + "\n"
                    + "#include <string>\n"
                    + "#include <fstream>\n"
                    + "#include <iostream>\n"
                    + "#include <cstring>\n"
                    + "#include <stdlib.h>\n"
                    + "\n"
                    + "using namespace std;\n"
                    + "\n"
                    + "Out::Out(char *params[]) : fichierOut(argsFileOut(params).c_str(), ios::out | ios::trunc)\n"
                    + "{\n"
                    + "\n"
                    + "	if (fileOut.compare(\"\") != 0){\n"
                    + "\n"
                    + "		if (!fichierOut){\n"
                    + "\n"
                    + "			cerr << \"Erreur à l'ouverture du fichier en écriture\" << endl;\n"
                    + "\n"
                    + "		}\n"
                    + "\n"
                    + "	}\n"
                    + "\n"
                    + "}\n"
                    + "\n"
                    + "\n"
                    + "Out::~Out()\n"
                    + "{\n"
                    + "\n"
                    + "	fichierOut.close();\n"
                    + "\n"
                    + "}\n"
                    + "\n"
                    + "void Out::Output(char c)\n"
                    + "{\n"
                    + "\n"
                    + "	if (fichierOut){\n"
                    + "		\n"
                    + " 		fichierOut.put(c);\n"
                    + "\n"
                    + "	}\n"
                    + "	else{\n"
                    + "\n"
                    + "		cout << c << endl;\n"
                    + "\n"
                    + "	}\n"
                    + "\n"
                    + "}\n"
                    + "\n"
                    + "string Out::argsFileOut(char *params[]){\n"
                    + "\n"
                    + "	string fileOut = \"\";\n"
                    + "\n"
                    + "	for (int i = 0; params[i] != NULL; i++)\n"
                    + "	{\n"
                    + "\n"
                    + "		if (strcmp(params[i], \"-o\") == 0 && params[i + 1] != NULL){\n"
                    + "\n"
                    + "			fileOut = params[i + 1];\n"
                    + "\n"
                    + "		}\n"
                    + "\n"
                    + "	}\n"
                    + "\n"
                    + "	return fileOut;\n"
                    + "\n"
                    + "}");

            fileIn.close();

        } catch (IOException ex) {
            Logger.getLogger(TradCpp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void writer(String instr) {

        try {
            file = new PrintWriter(new FileWriter(filename + ".cpp", true), true);

        } catch (IOException ex) {
            Logger.getLogger(Monitor.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        file.write(instr);

        file.close();

    }

    private void fillCommands() {

        BufferedReader file;
        try {
            file = new BufferedReader(new FileReader(filename));

            String line = new String();

            while (line != null) {

                line = deleteCom(line, null);

                if (!line.equals("")) {

                    System.out.println("LIT LINE --- " + line);
                    ReadLine(line);

                }

                line = file.readLine();

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(TradCpp.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TradCpp.class
                    .getName()).log(Level.SEVERE, null, ex);
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

                    commands.add(toCommand((Character.toString(line.charAt(j)))));

                } else {
                    System.out.println("MARCHE PAS --- |" + line.charAt(j) + "| \n" + line + "     " + j);

                    if (FLAG_trace) {
                        notifyForLogs(line, commands.size());
                    }

                    System.exit(4);

                }

            }

        } else {

            if (isCommand(line)) {

                commands.add(toCommand(line));

            } else {

                System.out.println("nOPE -- " + line);

                if (FLAG_trace) {
                    notifyForLogs(line, commands.size());
                }

                System.exit(4);

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
