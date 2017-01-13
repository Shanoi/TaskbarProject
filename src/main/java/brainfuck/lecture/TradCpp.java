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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class represents the Translator in C++. From the Brainf*ck langage, we can generate
 * code in C++. We can generate macros but not functions.
 *
 * @author Team Taskbar
 */
public class TradCpp implements ObservableLogstxt {

    private ArrayList<EnumCommands> commands = new ArrayList<>();

    //private ArrayList<String> macros = new ArrayList<>();
    private HashMap<String, Macro> macros;

    private HashMap<Integer, ArrayList<String>> macroProg = new HashMap<>();

    private String filename;

    private PrintWriter file;

    private ArrayList observers;// Tableau d'observateurs.

    private boolean in = false;

    private boolean out = false;

    private int cptBoucle = 0;

    private int cptInstr = 0;

    //Constructor of TradCpp

    public TradCpp(String filename) {

        this.filename = filename;

        macros = new HashMap<>();

        try {
            file = new PrintWriter(new FileWriter(filename + ".cpp"));
        } catch (IOException ex) {
            Logger.getLogger(TradCpp.class.getName()).log(Level.SEVERE, null, ex);
        }

        Monitor observer = new Monitor();

        observers = new ArrayList();

        this.addObserver(observer);

    }

    /**
     * Execute the translation of a Brainf*ck program in C++
     */
    public void execute() {

        ReadMacro();

        if (in) {

            writeSupIn();

        }

        if (out) {

            writeSupOut();

        }

        fillCommands();
        if (commands.contains(IN)) {

            writeSupIn();
            in = true;

        }

        if (commands.contains(OUT)) {

            writeSupOut();
            out = true;

        }

        writeHeader();

        writeMacro();

        //fillCommands();
        writeInitVar();

        writeInstr(false, commands);
        writeEnd();

        writeMemory();
    }

    /**
     * Write the header of the program Test if include of In.h and Out.h
     *
     */
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

        Header.append("\nusing namespace std;\n\n");

        writer(Header.toString());

    }

    /**
     * Write the initialisation of the different variables of the C++ program
     */
    private void writeInitVar() {

        StringBuilder Header = new StringBuilder();
        Header.append("\nint main(int n, char *params[]){\n\n");

        if (in) {

            Header.append("\tIn *fIn = new In(argv);\n");

        }

        if (out) {

            Header.append("\tOut *fOut = new Out(argv);\n");

        }

        Header.append("\tMemory *mem = new Memory();\n\n");

        writer(Header.toString());

    }

    /**
     * Write the end of the program
     */
    private void writeEnd() {

        writer("\n");

        if (in) {

            writer("\tdelete In;\n");

        }

        if (out) {

            writer("\tdelete fOut;\n");

        }

        writer("\tdelete mem;\n"
                + "\n\treturn 0;"
                + "\n\n}\n\n");

    }

    /**
     * Write the instruction of the list of commands
     *
     * @param macro To know if we write the commands of a macro
     * @param commandstoWrite The commands to write
     */
    private void writeInstr(boolean macro, ArrayList<EnumCommands> commandstoWrite) {

        int cpt = 0;

        EnumCommands prevInstr = commandstoWrite.get(0);

        int size = commandstoWrite.size();

        for (int i = 0; i < size; i++) {

            if (macroProg.containsKey(i) && !macro) {

                if (i != 0) {

                    writeInstr(i, cpt, prevInstr, macro, size, commandstoWrite);

                }

                for (int j = 0; j < macroProg.get(i).size(); j++) {

                    writer("\t" + macroProg.get(i).get(j) + "; \n");

                }

                cpt = 1;

            } else if (commandstoWrite.get(i).equals(prevInstr) && i != size - 1) {

                cpt++;

            } else {

                writeInstr(i, cpt, prevInstr, macro, size, commandstoWrite);

                cpt = 1;

            }

            prevInstr = commandstoWrite.get(i);

        }

        if (macroProg.containsKey(size)) {

            writer("\t" + macroProg.get(size) + "; \n");

        }

    }

    /**
     * Write the command at i of the list of command
     *
     * @param i the number of the command
     * @param cpt the number of consecutive same instruction
     * @param prevInstr the previous instructions
     * @param macro To know if we write the commands of a macro
     * @param size the size of the list of commands
     * @param commandstoWrite The list of commands
     */
    private void writeInstr(int i, int cpt, EnumCommands prevInstr, boolean macro, int size, ArrayList<EnumCommands> commandstoWrite) {

        if (commandstoWrite.get(i).equals(OUT)) {

            writeInstr(cpt, prevInstr, macro);

            writeOut();

            cpt = 1;

        } else if (commandstoWrite.get(i).equals(IN)) {

            writeInstr(cpt, prevInstr, macro);

            writeIn();

            cpt = 1;

        } else if (commandstoWrite.get(i).equals(JUMP)) {

            writeInstr(cpt, prevInstr, macro);

            writeWhile();

            cpt = 1;

        } else if (commandstoWrite.get(i).equals(BACK)) {

            writeInstr(cpt, prevInstr, macro);

            writer("\t\n}");

            if (macro) {

                writer("\\");

            }

            writer("\n\n");

            cpt = 1;

        } else if (i == size - 1 && !commandstoWrite.get(i).equals(prevInstr)) {

            writeInstr(cpt, prevInstr, macro);

            cpt = 1;

            writeInstr(cpt, commandstoWrite.get(i), macro);

        } else if (i == size - 1) {

            writeInstr(cpt + 1, prevInstr, macro);

        } else {

            writeInstr(cpt, prevInstr, macro);

            cpt = 1;

        }

    }

    /**
     * Write the rigth instruction in the file
     *
     * @param cpt The number of instruction
     * @param prevInstr The previous instruction
     * @param macro To know if we write the commands of a macro
     */
    private void writeInstr(int cpt, EnumCommands prevInstr, boolean macro) {

        StringBuilder Instr = new StringBuilder();

        switch (prevInstr) {

            case INCR:

                Instr.append("\tmem->add(").append(cpt).append(");");
                break;

            case DECR:

                Instr.append("\tmem->sub(").append(cpt).append(");");
                break;

            case RIGHT:

                Instr.append("\tmem->decaleRight(").append(cpt).append(");");

                break;

            case LEFT:

                Instr.append("\tmem->decaleLeft(").append(cpt).append(");");

                break;

        }

        if (macro) {
            Instr.append("\\");
        }

        Instr.append("\n");

        writer(Instr.toString());

    }

    /**
     * Write the line for the OUT instruction
     */
    private void writeOut() {

        writer("\n\tfOut->Output(mem->getCurrentValue());\n");

    }

    /**
     * Write the instruction for the IN instruction
     */
    private void writeIn() {

        writer("\n\tmem->setCurrentValue(In->Input());\n");

    }

    /**
     * Write for the JUMP instruction
     */
    private void writeWhile() {

        writer("\n\twhile(mem->getCurrentValue() > 0){\n"
                + "\n");

    }

    /**
     * Read the macro at the beginning of the program in brainf*ck
     */
    private void ReadMacro() {

        String[] separated;

        BufferedReader progFile;

        Macro macro = new Macro();

        try {

            progFile = new BufferedReader(new FileReader(filename));

            String line = progFile.readLine();

            while (line != null) {

                if (line.equals("---- MACRO")) {

                    while (!((line = progFile.readLine())).equals("---- ENDMACRO") && line != null) {

                        line = deleteCom(line, null);

                        if (!line.equals("")) {

                            if (line.charAt(0) == '*') {

                                separated = line.split(" ");

                                macro = new Macro(separated);

                                macros.put(separated[1], macro);

                            } else {

                                if (line.equals(IN.getLong()) || line.contains(IN.getShort())) {

                                    in = true;

                                }

                                if (line.equals(OUT.getLong()) || line.contains(OUT.getShort())) {

                                    out = true;

                                }

                                macro.fillCommands(line);

                            }
                        }

                    }
                }

                line = progFile.readLine();

            }

        } catch (IOException ex) {
            Logger.getLogger(TradCpp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Write the macro at the beginning of the C++ program
     */
    private void writeMacro() {

        ArrayList<EnumCommands> commandsMacro = new ArrayList<>();

        String macroKey;

        Macro mac;

        String line;

        for (Map.Entry<String, Macro> macrosS : macros.entrySet()) {

            macroKey = macrosS.getKey();
            mac = macrosS.getValue();

            writer("#define " + macroKey);

            if (mac.getnbParam() != 0) {

                writer("(" + mac.getParams().get(0));

                for (int i = 1; i < mac.getnbParam(); i++) {

                    writer(", " + mac.getParams().get(i));

                }

                writer(")");

            } else {

                writer("(A)\\\n"
                        + "for (int i" + cptBoucle + " = 0; i" + cptBoucle + " < (A); i++){\\\n");

                cptBoucle++;

            }

            writer("\\\n");

            for (int i = 0; i < mac.getCommands().size(); i++) {

                line = mac.getCommands().get(i);

                if (line.split(": ").length > 1) {

                    String cpt = line.split(": ")[0];

                    line = line.split(": ")[1];

                    if (mac.isParam(cpt)) {

                        if (!commandsMacro.isEmpty()) {

                            writeInstr(true, commandsMacro);

                            commandsMacro.clear();

                        }

                        writer("for (int i" + cptBoucle + " = 0; i" + cptBoucle + " < (" + cpt + "); i++){\\\n\\\n");

                        cptBoucle++;

                        if (macros.containsKey(line.split(" ")[0])) {

                            writer("\t" + line.split(" ")[0]);

                            if (line.split(" ").length > 1) {

                                writer("(" + line.split(" ")[1]);

                                for (int j = 2; j < line.split(" ").length; j++) {

                                    if (mac.isParam(line.split(" ")[1])) {

                                        writer(", " + line.split(" ")[j]);

                                    } else {

                                        System.exit(10);

                                    }

                                }

                                writer(")");

                            } else {

                                writer("(1)");

                            }

                            writer("\\\n");

                        } else {

                            ReadLine(line, commandsMacro);

                            writeInstr(true, commandsMacro);

                            commandsMacro.clear();

                        }

                        /*writeInstr(true);

                         commands.clear();*/
                        writer("\\\n}\\\n\n");

                    } else {

                        System.exit(10);

                    }

                } else {

                    if (macros.containsKey(line.split(" ")[0])) {
////////////////////
                        //ReadMacro(line.split(" "));

                        writer("\t" + line.split(" ")[0] + "(1)\\\n");

                    } else {

                        ReadLine(line, commandsMacro);

                    }

                }

            }

            if (!commandsMacro.isEmpty()) {

                writeInstr(true, commandsMacro);

                commandsMacro.clear();

            }

            if (mac.getnbParam() == 0) {

                writer("\\\n}\\\n");

            }

        }
    }

    /**
     * Write the files to manage the IN instruction
     */
    private void writeSupIn() {

        String[] separated;

        separated = filename.split("/");

        String fileInName = filename.replace(separated[separated.length - 1], "In");

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

    /**
     * Write the files to manage the OUT instruction
     */
    private void writeSupOut() {

        String[] separated;

        separated = filename.split("/");

        String fileOutName = filename.replace(separated[separated.length - 1], "Out");

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

    /**
     * Write the files to manage the Memory
     */
    private void writeMemory() {

        String[] separated;

        separated = filename.split("/");

        String fileMemory = filename.replace(separated[separated.length - 1], "Memory");

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

    /**
     * Write the line in the main file
     *
     * @param instr The instruction to write
     */
    private void writer(String instr) {

        try {
            file = new PrintWriter(new FileWriter(filename + ".cpp", true), true);

        } catch (IOException ex) {
            Logger.getLogger(TradCpp.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        file.write(instr);

        file.close();

    }

    /**
     * Fill the list of commands by reading the program
     */
    private void fillCommands() {

        cptInstr = 0;

        BufferedReader progFile;

        String[] separated;

        ArrayList<String> instrMacro;

        StringBuilder paramsMacro;

        try {

            progFile = new BufferedReader(new FileReader(filename));

            String line = new String();

            while (line != null) {

                if (line.equals("---- MACRO")) {

                    while (!((line = progFile.readLine())).equals("---- ENDMACRO") && line != null) {

                    }

                    line = progFile.readLine();

                }

                line = deleteCom(line, null);

                if (!line.equals("")) {

                    instrMacro = new ArrayList<>();

                    separated = line.split(" ");

                    if (macros.containsKey(separated[0])) {

                        if (macros.get(separated[0]).getnbParam() == 0) {

                            if (!macroProg.containsKey(cptInstr)) {

                                if (separated.length == 1) {

                                    instrMacro.add(line + "(1)");

                                    macroProg.put(cptInstr, instrMacro);

                                } else if (separated.length == 2) {

                                    instrMacro.add(separated[0] + "(" + separated[1] + ")");

                                    macroProg.put(cptInstr, instrMacro);

                                } else {

                                    System.exit(10);

                                }

                            } else {

                                if (separated.length == 1) {

                                    macroProg.get(cptInstr).add(line + "(1)");

                                } else if (separated.length == 2) {

                                    macroProg.get(cptInstr).add(separated[0] + "(" + separated[1] + ")");

                                } else {

                                    System.exit(10);

                                }

                            }

                        } else {

                            paramsMacro = new StringBuilder();

                            paramsMacro.append(separated[0])
                                    .append("(")
                                    .append(separated[1]);

                            for (int i = 2; i < separated.length; i++) {
                                paramsMacro.append(", ").append(separated[i]);

                            }

                            paramsMacro.append(")");

                            if (!macroProg.containsKey(cptInstr)) {

                                instrMacro.add(paramsMacro.toString());

                                macroProg.put(cptInstr, instrMacro);

                            } else {

                                macroProg.get(cptInstr).add(paramsMacro.toString());

                            }

                        }

                    } else {

                        ReadLine(line, commands);

                    }

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
    private void ReadLine(String line, ArrayList<EnumCommands> commandsToFill) {

        if ((line.charAt(0) < 'A') || (line.charAt(0) > 'Z')) { //modif le 25/12 >= et <= avant

            for (int j = 0; j < line.length(); j++) {

                if (isCommand(Character.toString(line.charAt(j)))) {

                    commandsToFill.add(toCommand((Character.toString(line.charAt(j)))));

                } else {

                    if (FLAG_trace) {
                        notifyForLogs(line, commandsToFill.size());
                    }

                    System.exit(4);

                }

                cptInstr++;

            }

        } else {

            if (isCommand(line)) {

                commandsToFill.add(toCommand(line));

                cptInstr++;

            } else {

                if (FLAG_trace) {
                    notifyForLogs(line, commandsToFill.size());
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
