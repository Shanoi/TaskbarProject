package brainfuck.memory;

import brainfuck.command.uByte;
import java.util.ArrayList;

public class ComputationalModel {

    private final int memorySize = 30000;

    private static int p = 0;
    private static int i = 0;

    private static ArrayList<uByte> _memory;

    public ComputationalModel() {

    }

    /**
     * Allow the program to initialize the memory with 0 as a default value of
     * each cell
     */
    public void init() {

        _memory = new ArrayList<>();

        _memory.add(new uByte());

    }


    /**
     * Change the value of a cell
     *
     * @param n the value of the cell pointed
     */
    public void setCurrentCaseValue(byte n) {

        _memory.get(p).set(n);

    }

    /**
     * Change the current cell
     *
     * @param p The emplacement in the memory
     */
    public void setCurrentIndice(int p) {

        while (p > _memory.size() - 1) {

            _memory.add(new uByte());

        }

        this.p = p;
    }

    /**
     * Display the memory in row
     */
    public static void affichememoire() {

        _memory.stream().forEach((_memory1) -> {
            System.out.print(" | " + _memory1.byteToInt());
        });

        System.out.println();

    }

    /**
     *
     * @return a string representation of the object
     */
    @Override
    public String toString(){
        
        StringBuilder memory = new StringBuilder();
        
        _memory.stream().forEach((_memory1) -> {
            
            memory.append(" | ").append(_memory1.byteToInt());
            
        });
        
        
        return memory.toString();
        
    }


    //=================
    //Getter and Setter
    //=================


    /**
     * Getter of I
     * @return
     */
    public int getI() {
        return i;
    }

    /**
     * Setter of I
     * @param d
     */
    public void setI(int d) {
        i = d;
    }


    /**
     * Getter of the current Indice
     * @return
     */
    public int getCurrentIndice() {
        return p;
    }

    /**
     * Getter of the size of the memory
     * @return
     */
    public int getMemorySize() {
        return memorySize;
    }

    /**
     * Getter of the current Case Value
     * @return
     */
    public int getCurrentCaseValue() {

        return _memory.get(p).byteToInt();

    }

}
