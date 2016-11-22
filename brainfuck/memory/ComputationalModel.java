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

    public int getMemorySize() {
        return memorySize;
    }

    public int getCurrentCaseValue() {

        return _memory.get(p).byteToInt();

    }

    /**
     * Change the value of a cell
     *
     * @param n the value of the cell pointed
     */
    public void setCurrentCaseValue(byte n) {

        _memory.get(p).set(n);

    }

    public int getCurrentIndice() {
        return p;
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

    public int getI() {
        return i;
    }

    public void setI(int d) {
        i = d;
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

}
