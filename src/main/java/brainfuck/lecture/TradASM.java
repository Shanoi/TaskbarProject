package brainfuck.lecture;

import brainfuck.command.EnumCommands;
import brainfuck.memory.ComputationalModel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Florian on 09/01/2017.
 */
public class TradASM {

    private BufferedWriter writer;
    private List<EnumCommands> list = new ArrayList<>();


    public TradASM(String path) throws IOException {
        list = Fichiers.getInstructions();
        writer = new BufferedWriter(new FileWriter(path));

        writer.write("section .data\n\t_memory: TIMES 30000 db 0\n\t_i: dw 0x00\n\nsection .text\n");//pour nasm

    }


    public void runTrad() throws IOException {
        boolean in_fonc = false;
        int temp = 0;
        int i = ComputationalModel.getI();
        int nbRight = 0;
        while(i<list.size()){
            if(!in_fonc) {
                switch (list.get(i)) {
                    case CALL:
                        writer.write("global _fonc" + (Fichiers.getFonction2(Fichiers.getFonction(i + 1)) ) + "\n"+ "_fonc" + (Fichiers.getFonction2(Fichiers.getFonction(i + 1)) ) + ":\n" + "push ebp\n" +
                                "mov ebp, esp\n");
                        in_fonc = true;
                        temp = i;
                        for (int h = 0; h <Fichiers.getNbArgFonction(Fichiers.getFonction(i+1)) ; h++) {
                            writer.write("\t inc dword [_i]\n");
                            writer.write("\t mov eax, [esp + " + ((4*h) + 8) + "]\n"); //récupérer les args
                            writer.write("\t mov dword [_memory + edx*0x08], eax\n");
                        }
                        i = Fichiers.getFonction2(Fichiers.getFonction(i + 1)) - 1;

                        break;
                }
            }
            else {
                switch (list.get(i)) {
                    case INCR:
                        writer.write("\t mov dword edx,[_i]\n" +
                                "\t inc dword [_memory + edx*0x08]\n");
                        break;
                    case DECR:
                        writer.write("\t mov dword edx,[_i]\n" +
                                "\t dec dword [_memory + edx*0x08]\n");
                        break;
                    case LEFT:
                        writer.write("\t dec dword [_i]\n");
                        nbRight--;
                        break;
                    case RIGHT:
                        writer.write("\t inc dword [_i]\n");
                        nbRight++;
                        break;
                    case IN:
                        //  A FAIRE
                        break;
                    case OUT:
                        writer.write("\tmov dword edx,[_i]\n" + "LEA eax, [_memory + edx*0x08]\n" +
                                "\tpush    dword 0x01\n" +
                                "    push    dword eax\n" +
                                "    push    dword 1\n" +
                                "    mov     eax, 4\n" +
                                "    sub     esp, 4\n" +
                                "    int     0x80\n" +
                                "\tadd     esp, 16\n");
                        break;
                    case JUMP:
                        writer.write("_JUMP" + Fichiers.getTableLoopAssoc(i) + ":\n" + "mov		ecx, dword [_memory + edx*0x08]\n" +
                                "jcxz  _BACK" + Fichiers.getTableLoopAssoc(i) + "\n");
                        break;
                    case BACK:
                        writer.write("jmp _JUMP" + i + "\n" + "_BACK" + i + ":\n");
                        break;
                    case CALL:
                        writer.write("call _fonc" + (Fichiers.getFonction2(Fichiers.getFonction(i+1)) ) + "\n");
                        break;
                    case ARG:
                        writer.write("LEA eax, [_memory + edx*0x08]\n" +
                                "          push    dword eax\n");
                        break;
                    case RETURN:
                        //TODO bug si on est dans la partie ARG
                        for (int j = 0; j < Fichiers.getNbArgFonction(Fichiers.getFonction(temp+1)) + nbRight; j++) {
                            writer.write("\t mov dword edx,[_i]\n" +
                                    "\t mov dword [_memory + edx*0x08],0x0\n");
                            writer.write("\t dec dword [_i]\n");

                        }
                        writer.write("leave\n" +
                                "ret\n");
                        in_fonc = false;
                        i = temp;
                        break;
                    case RET:
                        //TODO bug si on est dans la partie ARG
                        for (int j = 0; j < Fichiers.getNbArgFonction(Fichiers.getFonction(temp+1)) + nbRight ; j++) {
                            writer.write("\t mov dword edx,[_i]\n" +
                                    "\t mov dword [_memory + edx*0x08],0x0\n");
                            writer.write("\t dec dword [_i]\n");

                        }
                        writer.write("leave\n" +
                                "ret\n");
                        in_fonc = false;
                        i = temp;
                        break;
                }
            }
            i++;
        }
        writer.write("global _main\n" +
                "        _main:\n");

        for (int j = ComputationalModel.getI(); j < list.size(); j++) {
            switch (list.get(j)) {
                case INCR:
                    writer.write("\t mov dword edx,[_i]\n" +
                            "\t inc dword [_memory + edx*0x08]\n");
                    break;
                case DECR:
                    writer.write("\t mov dword edx,[_i]\n" +
                            "\t dec dword [_memory + edx*0x08]\n");
                    break;
                case LEFT:
                    writer.write("\t dec dword [_i]\n");
                    break;
                case RIGHT:
                    writer.write("\t inc dword [_i]\n");
                    break;
                case IN:
                    //  A FAIRE
                    break;
                case OUT:
                    writer.write("\tmov dword edx,[_i]\n" + "LEA eax, [_memory + edx*0x08]\n" +
                            "\tpush    dword 0x01\n" +
                            "    push    dword eax\n" +
                            "    push    dword 1\n" +
                            "    mov     eax, 4\n" +
                            "    sub     esp, 4\n" +
                            "    int     0x80\n" +
                            "\tadd     esp, 16\n");
                    break;
                case JUMP:
                    writer.write("_JUMP" + Fichiers.getTableLoopAssoc(j) + ":\n" + "mov		ecx, dword [_memory + edx*0x08]\n" +
                            "jcxz  _BACK" + Fichiers.getTableLoopAssoc(j) + "\n");
                    break;
                case BACK:
                    writer.write("jmp _JUMP" + j + "\n" + "_BACK" + j + ":\n");
                    break;
                case CALL:
                    writer.write("call _fonc" + (Fichiers.getFonction2(Fichiers.getFonction(j+1))) + "\n");
                    break;
                case ARG:
                    writer.write("LEA eax, [_memory + edx*0x08]\n" +
                            "          push    dword eax\n");

                    break;
            }
        }
        writer.write("\tmov eax,1\n" +
                "\tint 80H");
        writer.close();
    }


}
