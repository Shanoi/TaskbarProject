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

//////////////////////////////
///////Attention à la lecture, il faut la faire dans ce fichiers sinon on ne prend pas en compte les macros
//////////////////////////////
/**
 *
 * @author Olivier
 */
public class TradCpp implements ObservableLogstxt {

    private ArrayList<EnumCommands> commands = new ArrayList<>();

    //private ArrayList<String> macros = new ArrayList<>();
    private HashMap<String, Macro> macros;

    private String filename;

    private PrintWriter file;

    private ArrayList observers;// Tableau d'observateurs.

    private boolean in = false;

    private boolean out = false;

    private int cptBoucle = 0;

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

        ReadMacro();

        writeMacro();

        fillCommands();

        writeInitVar();

        System.out.println("PROGRAMME CORE -- " + commands);
        
        writeInstr(false);

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

    private void writeInitVar() {

        StringBuilder Header = new StringBuilder();
        Header.append("\nint main(int n, char *params[]){\n\n"
                + "\tunsigned char m[30000];\n\n"
                + "\tint position = 0;\n\n");

        if (in) {

            Header.append("In *fIn = new In(argv);\n");

        }

        if (out) {

            Header.append("Out *fOut = new Out(argv);\n");

        }

        Header.append("\tMemory *mem = new Memory();\n\n");

        writer(Header.toString());

    }

    private void writeEnd() {

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

        writer("void checkSegFault(int i, int k){\n\n"
                + "\tif (i + k > 30000 || i + k < 0){\n\n"
                + "\t\texit(1);\n\n"
                + "\t}\n"
                + "}\n");

    }

    private void writeInstr(boolean macro) {

        /*writer("int main(int n, char *params[]){\n\n"
         + "\tunsigned char m[30000];\n\n"
         + "\tint position = 0;\n\n");

         if (in) {

         writer("In *fIn = new In(argv);\n");

         }

         if (out) {

         writer("Out *fOut = new Out(argv);\n");

         }

         writer("\tMemory *mem = new Memory();\n\n");*/
        int cpt = 0;

        int nbInstrCons = 0;

        EnumCommands prevInstr = commands.get(0);

        int size = commands.size();

        for (int i = 0; i < size; i++) {

            //System.out.println("COMMAND -- " + commands.get(i) + " -- " + size + " --- " + i);
            if (commands.get(i).equals(prevInstr) && i != size - 1) {

                cpt++;
                nbInstrCons++;

            } else {

                if (commands.get(i).equals(OUT)) {

                    writeInstr(cpt, prevInstr, macro);

                    writeOut();

                    cpt = 1;

                } else if (commands.get(i).equals(IN)) {

                    writeInstr(cpt, prevInstr, macro);

                    writeIn();

                    cpt = 1;

                } else if (commands.get(i).equals(JUMP)) {

                    writeInstr(cpt, prevInstr, macro);

                    writeWhile();

                } else if (commands.get(i).equals(BACK)) {

                    writeInstr(cpt, prevInstr, macro);

                    writer("\t\n}");

                    if (macro) {

                        writer("\\");

                    }

                    writer("\n\n");

                } else if (i == size - 1 && !commands.get(i).equals(prevInstr)) {

                    writeInstr(cpt, prevInstr, macro);

                    cpt = 1;
                    nbInstrCons = 1;

                    // if (!commands.get(i).equals(prevInstr)) {
                    writeInstr(cpt, commands.get(i), macro);

                    //  }
                } else if (i == size - 1) {

                    writeInstr(cpt + 1, prevInstr, macro);

                } else {

                    writeInstr(cpt, prevInstr, macro);

                    cpt = 1;
                    nbInstrCons = 1;

                }

            }

            prevInstr = commands.get(i);

        }

    }

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

    private void writeWhile() {

        writer("\n\twhile(mem->getCurrentValue() > 0){\n"
                + "\n");

    }

    private void ReadMacro() {

        String[] separated;

        BufferedReader progFile;

        Macro macro = new Macro();

        try {

            progFile = new BufferedReader(new FileReader(filename));

            String line = progFile.readLine();

            if (line.equals("---- MACRO")) {

                while (!((line = progFile.readLine())).equals("---- ENDMACRO") && line != null) {

                    line = deleteCom(line, null);

                    if (!line.equals("")) {

                        if (line.charAt(0) == '*') {

                            separated = line.split(" ");

                            System.out.println("NOM --- " + separated[1]);

                            macro = new Macro(separated);

                            macros.put(separated[1], macro);

                        } else {

                            macro.fillCommands(line);

                        }
                    }

                }
            }

        } catch (IOException ex) {
            Logger.getLogger(TradCpp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void writeMacro() {

        String macroKey;

        Macro mac;

        String line;

        for (Map.Entry<String, Macro> macrosS : macros.entrySet()) {

            macroKey = macrosS.getKey();
            mac = macrosS.getValue();

            /*System.out.println("MACRO --------------------------------------- " + macroKey);
             System.out.println("INTR -- " + mac.getCommands());*/
            System.out.println("//////////////////////////////////////////////////////////////////////////////////////");
            System.out.println("------------------------ECRITURE DE MACRO------------------------" + macroKey);
            System.out.println("//////////////////////////////////////////////////////////////////////////////////////");

            writer("#define " + macroKey);

            if (mac.getnbParam() != 0) {

                writer("(" + mac.getParams().get(0));

                for (int i = 1; i < mac.getnbParam(); i++) {

                    writer(", " + mac.getParams().get(i));

                }

                writer(")");

            } else {

                System.out.println("//////////////////////////////////////////////////////////////////////////////////////");
                System.out.println("------------------------SANS PARAMS------------------------" + macroKey);
                System.out.println("//////////////////////////////////////////////////////////////////////////////////////");

                writer("(A)\\\n"
                        + "for (int i" + cptBoucle + " = 0; i" + cptBoucle + " < A; i++){\\\n");

                cptBoucle++;

            }

            writer("\\\n");

            for (int i = 0; i < mac.getCommands().size(); i++) {
                //System.out.println("INSTR " + mac.getCommands());
                line = mac.getCommands().get(i);

                if (line.split(": ").length > 1) {

                    String cpt = line.split(": ")[0];

                    line = line.split(": ")[1];

                    if (mac.isParam(cpt)) {

                        if (!commands.isEmpty()) {

                            writeInstr(true);

                            commands.clear();

                        }

                        writer("for (int i" + cptBoucle + " = 0; i" + cptBoucle + " <" + cpt + "; i++){\\\n\\\n");

                        cptBoucle++;

                        if (macros.containsKey(line.split(" ")[0])) {

                            System.out.println("//////////////////////////////////////////////////////////////////////////////////////");
                            System.out.println("------------------------LECTURE DE MACRO DANS UNE MACRO------------------------" + macroKey);
                            System.out.println("//////////////////////////////////////////////////////////////////////////////////////");

                            //ReadMacro(line.split(" "));
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
                                
                            }else{
                                
                                writer("(1)");
                                
                            }

                            writer("\\\n");

                        } else {

                            ReadLine(line);

                            writeInstr(true);

                            commands.clear();

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

                        System.out.println("////////////////////////// ELSE");

                        writer("\t" + line.split(" ")[0] + "(1)\\\n");

                    } else {
                        System.out.println("LIT LINE --+++- " + line);
                        ReadLine(line);

                    }

                }

            }

            if (!commands.isEmpty()) {
                System.out.println("FINAL -- " + commands);
                writeInstr(true);

                commands.clear();

            }

            if (mac.getnbParam() == 0) {

                writer("\\\n}\\\n");

            }

        }
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
            Logger.getLogger(TradCpp.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        file.write(instr);

        file.close();

    }

    private void fillCommands() {

        BufferedReader progFile;

        String[] separated;

        try {

            progFile = new BufferedReader(new FileReader(filename));

            String line = new String();

            while (line != null) {

                //ReadMacro(line, progFile);
                if (line.equals("---- MACRO")) {

                    while (!((line = progFile.readLine())).equals("---- ENDMACRO") && line != null) {

                    }

                    line = progFile.readLine();

                }

                line = deleteCom(line, null);

                if (!line.equals("")) {

                    separated = line.split(" ");

                    if (macros.containsKey(separated[0])) {
                        System.out.println("LIT Macro --- " + line);
                        //ReadMacro(separated);

                    } else {
                        System.out.println("LIT LINE --- " + line);
                        ReadLine(line);

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

    /**
     * This method allows to support macro in the program.
     *
     * @param separated
     */
    private void ReadMacro(String[] separated) {

        Macro macro = macros.get(separated[0]);

        if (separated.length == 2 && macro.getnbParam() == 0) {

            writer("for (int i" + cptBoucle + " = 0; i" + cptBoucle + " < " + separated[1] + "; i++){\n"
                    + "\n");

            cptBoucle++;

            for (int j = 0; j < macro.getCommands().size(); j++) {

                MacroOrLine(macro, j, separated);

            }
            writer("\n"
                    + "		}\n");

            /*for (int k = 0; k < Integer.parseInt(separated[1]); k++) {

             for (int j = 0; j < macro.getCommands().size(); j++) {

             MacroOrLine(macro, j, separated);

             }

             }*/
        } else {

            System.out.println("LOL");
            writer("LOL");
            /*for (int j = 0; j < macro.getCommands().size(); j++) {

             MacroOrLine(macro, j, separated);

             }*/

        }

    }

    private void MacroOrLine(Macro macro, int j, String[] separated) {

        String[] separatedMacro = macro.getCommands().get(j).split(" ");

        int repete = 1;

        String tmp = "";

        Macro mac;

        //Répéter la ligne autant de fois que le paramètre, sinon la faire qu'une seule fois
        if (repete == 1) {

            if (macros.containsKey(separatedMacro[0])) {

                if (separatedMacro.length == macros.get(separatedMacro[0]).getnbParam() + 1) {

                    mac = macros.get(separatedMacro[0]);

                    writer(mac.getNom());

                    if (mac.getnbParam() != 0) {

                        writer("(" + separatedMacro[1]);

                        for (int i = 2; i < mac.getnbParam(); i++) {

                            writer(", " + separatedMacro[0]);

                        }

                        writer(")");

                    }

                    writer("");

                } else {

                    System.exit(10);

                }

            } else {

                ReadLine(macro.getCommands().get(j));

            }

        } /*else {

         for (int k = 0; k < repete; k++) {

         tmp = macro.getCommands().get(j).split(": ")[1];

         separatedMacro = tmp.split(" ");

         if (macros.containsKey(separatedMacro[0])) {

         ReadMacro(separatedMacro);

         } else {

         ReadLine(macro.getCommands().get(j).split(": ")[1]);

         }

         }

         }*/

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
