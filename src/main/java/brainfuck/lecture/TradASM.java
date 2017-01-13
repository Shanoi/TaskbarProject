package brainfuck.lecture;

import brainfuck.command.EnumCommands;
import brainfuck.memory.ComputationalModel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the translator ASM. From the Brainf*ck langage we can generate an ASM code.
 * It can generate functions and macros.
 *
 * @author Team Taskbar
 */
public class TradASM {
    
    private BufferedWriter writer;
    private List<EnumCommands> list = new ArrayList<>();
    
    //Constructor of TradASM

    public TradASM(String path) throws IOException {
        list = Fichiers.getInstructions();
        writer = new BufferedWriter(new FileWriter(path));
        
        writer.write("section .data\n\t_memory: TIMES 30000 db 0\n\t_i: dw 0x00\n\nsection .text\n");//pour nasm
        
    }

    /**
     *This method allows to run the translation.
     * @throws IOException
     */
    public void runTrad() throws IOException {
        boolean in_fonc = false;
        int temp = 0;
        int i = ComputationalModel.getI();
        int nbRight = 0;
        while(i<list.size()){
            if(!in_fonc) {
                switch (list.get(i)) {
                    case CALL:
                        writer.write("global _fonc" + (Fichiers.getFonction2(Fichiers.getFonction(i + 1)) ) + "\n"+ "_fonc" + (Fichiers.getFonction2(Fichiers.getFonction(i + 1)) ) + ":\n" + "\tpush ebp\n" +
                                     "\tmov ebp, esp\n");
                        in_fonc = true;
                        temp = i;
                        for (int h = 0; h <Fichiers.getNbArgFonction(Fichiers.getFonction(i+1)) ; h++) {
                            writer.write("\tinc dword [_i]\n");
                            writer.write("\tmov eax, [esp + " + ((4*h) + 8) + "]\n"); //récupérer les args
                            writer.write("\tmov dword [_memory + edx*0x08], eax\n");
                        }
                        i = Fichiers.getFonction2(Fichiers.getFonction(i + 1)) - 1;
                        
                        break;
                }
            }
            else {
                switch (list.get(i)) {
                    case INCR:
                        writer.write("\tmov dword edx,[_i]\n" +
                                     "\tinc dword [_memory + edx*0x08]\n");
                        break;
                    case DECR:
                        writer.write("\tmov dword edx,[_i]\n" +
                                     "\tdec dword [_memory + edx*0x08]\n");
                        break;
                    case LEFT:
                        writer.write("\tdec dword [_i]\n");
                        nbRight--;
                        break;
                    case RIGHT:
                        writer.write("\tinc dword [_i]\n");
                        nbRight++;
                        break;
                    case IN:
                        writer.write("\tLEA eax, [_memory + edx*0x08]\n" +
                                     "\tmov eax, 3\n" +
                                     "\tpush dword 1\n" +
                                     "\tpush dword eax\n" +
                                     "\tpush dword 0\n" +
                                     "\tpush eax\n" +
                                     "\tint 0x80\n" +
                                     "\tadd esp, 16\n");
                        break;
                    case OUT:
                        writer.write("\tmov dword edx,[_i]\n" + "\tLEA eax, [_memory + edx*0x08]\n" +
                                     "\tpush    dword 0x01\n" +
                                     "\tpush    dword eax\n" +
                                     "\tpush    dword 1\n" +
                                     "\tmov     eax, 4\n" +
                                     "\tsub     esp, 4\n" +
                                     "\tint     0x80\n" +
                                     "\tadd     esp, 16\n");
                        break;
                    case JUMP:
                        writer.write("_JUMP" + Fichiers.getTableLoopAssoc(i) + ":\n" + "\tmov		ecx, dword [_memory + edx*0x08]\n" +
                                     "\tjcxz  _BACK" + Fichiers.getTableLoopAssoc(i) + "\n");
                        break;
                    case BACK:
                        writer.write("\tjmp _JUMP" + i + "\n" + "_BACK" + i + ":\n");
                        break;
                    case CALL:
                        writer.write("\tcall _fonc" + (Fichiers.getFonction2(Fichiers.getFonction(i+1)) ) + "\n");
                        break;
                    case ARG:
                        writer.write("\tLEA eax, [_memory + edx*0x08]\n" +
                                     "\tpush    dword eax\n");
                        break;
                    case RETURN:
                        //TODO bug si on est dans la partie ARG
                        for (int j = 0; j < Fichiers.getNbArgFonction(Fichiers.getFonction(temp+1)) + nbRight; j++) {
                            writer.write("\tmov dword edx,[_i]\n" +
                                         "\tmov dword [_memory + edx*0x08],0x0\n");
                            writer.write("\tdec dword [_i]\n");
                            
                        }
                        writer.write("\tleave\n" +
                                     "\tret\n");
                        in_fonc = false;
                        i = temp;
                        break;
                    case RET:
                        //TODO bug si on est dans la partie ARG
                        for (int j = 0; j < Fichiers.getNbArgFonction(Fichiers.getFonction(temp+1)) + nbRight ; j++) {
                            writer.write("\tmov dword edx,[_i]\n" +
                                         "\tmov dword [_memory + edx*0x08],0x0\n");
                            writer.write("\tdec dword [_i]\n");
                            
                        }
                        writer.write("\tleave\n" +
                                     "\tret\n");
                        in_fonc = false;
                        i = temp;
                        break;
                }
            }
            i++;
        }
        writer.write("global _main\n" +
                     "\t_main:\n");
        
        for (int j = ComputationalModel.getI(); j < list.size(); j++) {
            switch (list.get(j)) {
                case INCR:
                    writer.write("\tmov dword edx,[_i]\n" +
                                 "\tinc dword [_memory + edx*0x08]\n");
                    break;
                case DECR:
                    writer.write("\tmov dword edx,[_i]\n" +
                                 "\tdec dword [_memory + edx*0x08]\n");
                    break;
                case LEFT:
                    writer.write("\tdec dword [_i]\n");
                    break;
                case RIGHT:
                    writer.write("\tinc dword [_i]\n");
                    break;
                case IN:
                    writer.write("\tLEA eax, [_memory + edx*0x08]\n" +
                                 "\tmov eax, 3\n" +
                                 "\tpush dword 1\n" +
                                 "\tpush dword eax\n" +
                                 "\tpush dword 0\n" +
                                 "\tpush eax\n" +
                                 "\tint 0x80\n" +
                                 "\tadd esp, 16\n");
                    break;
                case OUT:
                    writer.write("\tmov dword edx,[_i]\n" + "\tLEA eax, [_memory + edx*0x08]\n" +
                                 "\tpush    dword 0x01\n" +
                                 "\tpush    dword eax\n" +
                                 "\tpush    dword 1\n" +
                                 "\tmov     eax, 4\n" +
                                 "\tsub     esp, 4\n" +
                                 "\tint     0x80\n" +
                                 "\tadd     esp, 16\n");
                    break;
                case JUMP:
                    writer.write("_JUMP" + Fichiers.getTableLoopAssoc(j) + ":\n" + "\tmov		ecx, dword [_memory + edx*0x08]\n" +
                                 "\tjcxz  _BACK" + Fichiers.getTableLoopAssoc(j) + "\n");
                    break;
                case BACK:
                    writer.write("jmp _JUMP" + j + "\n" + "_BACK" + j + ":\n");
                    break;
                case CALL:
                    writer.write("\tcall _fonc" + (Fichiers.getFonction2(Fichiers.getFonction(j+1))) + "\n");
                    break;
                case ARG:
                    writer.write("\tLEA eax, [_memory + edx*0x08]\n" +
                                 "\tpush    dword eax\n");
                    
                    break;
            }
        }
        writer.write("\tmov eax,1\n" +
                     "\tint 80H");
        writer.close();
    }
    
    
}
