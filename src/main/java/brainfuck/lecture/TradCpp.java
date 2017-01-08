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

        //writeMacro();
        writeInstr();

        writeEnd();

        writeMemory();
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

        Header.append("#include \"Memory.h\"\n");

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

        writer("\tMemory *mem = new Memory();\n\n");

        int cpt = 0;

        int nbInstrCons = 0;

        EnumCommands prevInstr = commands.get(0);

        int size = commands.size();

        for (int i = 0; i < size; i++) {

            System.out.println("COMMAND -- " + commands.get(i) + " -- " + size + " --- " + i);
            if (commands.get(i).equals(prevInstr) && i != size - 1) {

                cpt++;
                nbInstrCons++;

            } else {
                System.out.println("ESLES");
                if (commands.get(i).equals(OUT)) {

                    writeInstr(cpt, prevInstr);

                    writeOut();

                    cpt = 1;

                } else if (commands.get(i).equals(IN)) {

                    writeInstr(cpt, prevInstr);

                    writeIn();

                    cpt = 1;

                } else if (commands.get(i).equals(JUMP)) {

                    writeInstr(cpt, prevInstr);

                    writeFunc();

                } else if (commands.get(i).equals(BACK)) {
                    
                    writeInstr(cpt, prevInstr);
                    writer("\t\n}\n\n");

                } else if (i == size - 1 && !commands.get(i).equals(prevInstr)) {
                    System.out.println("SIZE ----------------------- ");
                    writeInstr(cpt, prevInstr);

                    cpt = 1;
                    nbInstrCons = 1;

                    // if (!commands.get(i).equals(prevInstr)) {
                    writeInstr(cpt, commands.get(i));

                    //  }
                } else if (i == size - 1) {

                    writeInstr(cpt + 1, prevInstr);

                } else {

                    writeInstr(cpt, prevInstr);

                    cpt = 1;
                    nbInstrCons = 1;

                }

            }

            prevInstr = commands.get(i);

        }

        /*if (i == size - 1) {
            
         }*/
        writer("\n");

        if (in) {

            writer("\tdelete(In);\n");

        }

        if (out) {

            writer("\tdelete(fOut);\n");

        }

        //writeInstr(pos, cpt, prevInstr);
        writer("\tdelete(mem)\n"
                + "\n\treturn 0;"
                + "\n\n}\n\n");

    }

    private void writeInstr(int cpt, EnumCommands prevInstr) {

        switch (prevInstr) {

            case INCR:

                writer("\tmem->add(" + cpt + ");\n");
                break;

            case DECR:

                writer("\tmem->sub(" + cpt + ");\n");
                break;

            case RIGHT:

                writer("\tmem->decaleRight(" + cpt + ");\n");

                break;

            case LEFT:

                writer("\tmem->decaleLeft(" + cpt + ");\n");

                break;

        }

    }

    private void writeOut() {

        //writer("\n\tcout << (char) m[" + pos + "] << endl;\n\n");
        //writer("\n\tfOut->Output(m[position]);\n");
        writer("\n\tfOut->Output(mem->getCurrentValue());\n");

    }

    private void writeIn() {

        /*writer("\n\tcin >> c;\n\n"
         + "m[" + pos + "]\n");*/
        //writer("\n\tfm[position] = In->Input();\n");
        writer("\n\tmem->setCurrentValue(In->Input());\n");

    }

    private void writeFunc() {

        writer("\n\twhile(mem->getCurrentValue() > 0){\n"
                + "\n");

    }

//    private void writeMacro(){
//        
//        String[] separated;
//        System.out.println("MACRO READ " + line);
//
//
//            if (line.equals("---- MACRO")) {
//
//                while (!((line = progFile.readLine())).equals("---- ENDMACRO") && line != null) {
//
//                    line = deleteCom(line, progFile);
//
//                    if (!line.equals("")) {
//
//                        if (line.charAt(0) == '*') {
//
//                            separated = line.split(" ");
//
//                            System.out.println("NOM --- " + separated[1]);
//
//                            /*macro = new Macro(separated);
//                        
//                             macros.put(separated[1], macro);*/
//                            writer("#define " + separated[1]);
//
//                            for (int i = 2; i < separated.length; i++) {
//                                String separated1 = separated[i];
//                                System.out.println(" ----- S " + separated1);
//                            }
//
//                        } else {
//
//                            //macro.fillCommands(line);
//                        }
//
//                    }
//
//                }
//
//                line = progFile.readLine();
//
//            }
//
//        System.out.println("LIN ---- " + line);
//        
//    }
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

        String fileOutName = filename.replace(separated[separated.length - 1], "Out");

        System.out.println(" ---- " + fileOutName);

        PrintWriter fileIn;

        try {
            fileIn = new PrintWriter(new FileWriter(fileOutName + ".h"));

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

            fileIn = new PrintWriter(new FileWriter(fileOutName + ".cpp"));

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

    private void writeMemory() {

        String[] separated;

        separated = filename.split("/");

        String fileMemory = filename.replace(separated[separated.length - 1], "Memory");

        System.out.println(" ---- " + fileMemory);

        PrintWriter fileIn;

        try {
            fileIn = new PrintWriter(new FileWriter(fileMemory + ".h"));

            fileIn.write("#pragma once\n"
                    + "\n"
                    + "#include <iostream>\n"
                    + "\n"
                    + "const int nbCase = 30000;\n"
                    + "\n"
                    + "class Memory\n"
                    + "{\n"
                    + "\n"
                    + "private:\n"
                    + "\n"
                    + "	int currentCase;\n"
                    + "	\n"
                    + "	unsigned char m[nbCase];\n"
                    + "\n"
                    + "public:\n"
                    + "\n"
                    + "	Memory();\n"
                    + "	~Memory();\n"
                    + "\n"
                    + "	void decaleLeft(int k);\n"
                    + "	void decaleRight(int k);\n"
                    + "	void add(int n);\n"
                    + "	void sub(int n);\n"
                    + "	char getCurrentValue();\n"
                    + "	void setCurrentValue(int n);\n"
                    + "\n"
                    + "	friend std::ostream& operator<<(std::ostream& flux, Memory const& p);\n"
                    + "\n"
                    + "};\n"
                    + "");

            fileIn.close();

            fileIn = new PrintWriter(new FileWriter(fileMemory + ".cpp"));

            fileIn.write("#include \"Memory.h\"\n"
                    + "\n"
                    + "#include <string>\n"
                    + "#include <fstream>\n"
                    + "#include <iostream>\n"
                    + "#include <cstring>\n"
                    + "#include <stdlib.h>\n"
                    + "\n"
                    + "using namespace std;\n"
                    + "\n"
                    + "Memory::Memory() :currentCase(0)\n"
                    + "{\n"
                    + "\n"
                    + "	for (int i = 0; i < nbCase; i++)\n"
                    + "	{\n"
                    + "\n"
                    + "		m[i] = 0;\n"
                    + "\n"
                    + "	}\n"
                    + "\n"
                    + "}\n"
                    + "\n"
                    + "\n"
                    + "Memory::~Memory()\n"
                    + "{\n"
                    + "}\n"
                    + "\n"
                    + "void Memory::decaleLeft(int k){\n"
                    + "\n"
                    + "	if (currentCase - k < 0){\n"
                    + "\n"
                    + "		exit(2);\n"
                    + "\n"
                    + "	}\n"
                    + "	else{\n"
                    + "\n"
                    + "		currentCase -= k;\n"
                    + "\n"
                    + "	}\n"
                    + "\n"
                    + "}\n"
                    + "\n"
                    + "void Memory::decaleRight(int k){\n"
                    + "\n"
                    + "	if (currentCase + k > 30000){\n"
                    + "\n"
                    + "		exit(2);\n"
                    + "\n"
                    + "	}\n"
                    + "	else{\n"
                    + "\n"
                    + "		currentCase += k;\n"
                    + "\n"
                    + "	}\n"
                    + "\n"
                    + "}\n"
                    + "\n"
                    + "void Memory::add(int n){\n"
                    + "\n"
                    + "	if ((m[currentCase] + n) > 255){\n"
                    + "\n"
                    + "		exit(1);\n"
                    + "\n"
                    + "	}\n"
                    + "	else{\n"
                    + "\n"
                    + "		m[currentCase] += n;\n"
                    + "\n"
                    + "	}\n"
                    + "\n"
                    + "}\n"
                    + "\n"
                    + "void Memory::sub(int n){\n"
                    + "\n"
                    + "	if ((m[currentCase] - n) < 0){\n"
                    + "\n"
                    + "		exit(1);\n"
                    + "\n"
                    + "	}\n"
                    + "	else{\n"
                    + "\n"
                    + "		m[currentCase] -= n;\n"
                    + "\n"
                    + "	}\n"
                    + "\n"
                    + "}\n"
                    + "\n"
                    + "char Memory::getCurrentValue(){\n"
                    + "\n"
                    + "	return m[currentCase];\n"
                    + "\n"
                    + "}\n"
                    + "\n"
                    + "void Memory::setCurrentValue(int n){\n"
                    + "\n"
                    + "	m[currentCase] = n;\n"
                    + "\n"
                    + "}\n"
                    + "\n"
                    + "ostream& operator<<(ostream& flux, Memory const& p)\n"
                    + "{\n"
                    + "\n"
                    + "	int cpt = 0;\n"
                    + "\n"
                    + "	for (int i = 0; i < nbCase; i++)\n"
                    + "	{\n"
                    + "\n"
                    + "		if ((int)p.m[i] != 0){\n"
                    + "\n"
                    + "			if ((int)p.m[i - 1] == 0){\n"
                    + "\n"
                    + "				flux << \" | \" << cpt << \" cases à 0\";\n"
                    + "\n"
                    + "			}\n"
                    + "\n"
                    + "			flux << \" | \" << (int)p.m[i];\n"
                    + "\n"
                    + "			cpt = 0;\n"
                    + "\n"
                    + "		}\n"
                    + "		else{\n"
                    + "\n"
                    + "			cpt++;\n"
                    + "\n"
                    + "		}\n"
                    + "\n"
                    + "		\n"
                    + "	}\n"
                    + "\n"
                    + "	return flux;\n"
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

        BufferedReader progFile;

        try {

            progFile = new BufferedReader(new FileReader(filename));

            String line = new String();

            while (line != null) {

                //ReadMacro(line, progFile);
                System.out.println("A MACR --- " + line);

                if (line.equals("---- MACRO")) {

                    while (!((line = progFile.readLine())).equals("---- ENDMACRO") && line != null) {

                    }

                    line = progFile.readLine();

                }

                line = deleteCom(line, null);

                if (!line.equals("")) {

                    System.out.println("LIT LINE --- " + line);

                    ReadLine(line);

                }

                line = progFile.readLine();

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

    private void ReadMacro(String line, BufferedReader progFile) throws IOException {

        String[] separated;
        System.out.println("MACRO READ " + line);

        if (line.equals("---- MACRO")) {

            while (!((line = progFile.readLine())).equals("---- ENDMACRO") && line != null) {

                line = deleteCom(line, progFile);

                if (!line.equals("")) {

                    if (line.charAt(0) == '*') {

                        separated = line.split(" ");

                        System.out.println("NOM --- " + separated[1]);

                        /*macro = new Macro(separated);
                        
                         macros.put(separated[1], macro);*/
                        writer("#define " + separated[1]);

                        for (int i = 2; i < separated.length; i++) {
                            String separated1 = separated[i];
                            System.out.println(" ----- S " + separated1);
                        }

                    } else {

                        //macro.fillCommands(line);
                    }

                }

            }

            line = progFile.readLine();

        }

        System.out.println("LIN ---- " + line);
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
