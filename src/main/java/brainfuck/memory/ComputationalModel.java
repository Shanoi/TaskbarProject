package brainfuck.memory;

import brainfuck.command.uByte;

import java.util.ArrayList;

public class ComputationalModel {

    private final int memorySize = 30000;

    private static int p = 0;
    private static int i = 0;
    private static boolean execFonction = false;
    private static int tmpP = 0;
    private static int tmpSize = 0;
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
    public static void setCurrentCaseValue(byte n) {

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
     * @return a string representation of the object
     */
    @Override
    public String toString() {

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
     *
     * @return
     */
    public static int getI() {
        return i;
    }

    /**
     * Setter of I
     *
     * @param d
     */
    public static void setI(int d) {
        i = d;
    }


    /**
     * Getter of the current Indice
     *
     * @return
     */
    public int getCurrentIndice() {
        return p;
    }

    /**
     * Getter of the size of the memory
     *
     * @return
     */
    public int getMemorySize() {
        return memorySize;
    }

    /**
     * Getter of the current Case Value
     *
     * @return
     */
    public static int getCurrentCaseValue() {

        return _memory.get(p).byteToInt();

    }

    public static void setExecFonction(boolean a) {
        execFonction = a;
    }

    public static boolean getExecFonction() {
        return execFonction;
    }

    public static void setTmpP() {
        tmpP = p;
    }

    public static void setPFonction() {
        p = _memory.size()-1;
        tmpSize = p;
    }

    public static void removeFonctionMemory() {
        for (int j = _memory.size()-1; j > tmpSize ; j--) {
            _memory.remove(j);
        }
    }

    public static void returnValue() {
        _memory.set(tmpP, _memory.get(p));
    }


    public static void setLastP() {
        p = tmpP;
    }

    public static int getLastP() {
        return tmpP;
    }

}
