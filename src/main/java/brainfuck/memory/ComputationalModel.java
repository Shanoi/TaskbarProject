package brainfuck.memory;

import brainfuck.command.uByte;

import java.util.ArrayList;

/**
 * This class represents the computational model.  It allows to stock a 30 000 cells memory.
 * It also allows to move in the memory, to increment or decrement a cell.
 * @author Team Taskbar
 */
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
     * Allowing the program to initialize the memory with 0 as a default value of
     * each cell.
     */
    public void init() {

        _memory = new ArrayList<>();

        _memory.add(new uByte());

    }


    /**
     * Change the value of a cell.
     *
     * @param n the value of the cell pointed.
     */
    public static void setCurrentCaseValue(byte n) {

        _memory.get(p).set(n);

    }

    /**
     * Change the current cell.
     *
     * @param p The emplacement in the memory.
     */
    public void setCurrentIndice(int p) {

        while (p > _memory.size() - 1) {

            _memory.add(new uByte());

        }

        this.p = p;
    }

    /**
     * Displaying the memory in row.
     */
    public static void affichememoire() {

        _memory.stream().forEach((_memory1) -> {
            System.out.print(" | " + _memory1.byteToInt());
        });

        System.out.println();

    }

    /**
     * @return a string representation of the object.
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
     * Getter of I.
     * @return the integer i.
     */
    public static int getI() {
        return i;
    }

    /**
     * Setter of i.
     * @param d
     */
    public static void setI(int d) {
        i = d;
    }


    /**
     * Getter of the current Indice.
     *
     * @return the indice wanted.
     */
    public int getCurrentIndice() {
        return p;
    }

    /**
     * Getter of the size of the memory.
     *
     * @return the size of the memory.
     */
    public int getMemorySize() {
        return memorySize;
    }

    /**
     * Getter of the current Case Value.
     *
     * @return the value in the current case.
     */
    public static int getCurrentCaseValue() {

        return _memory.get(p).byteToInt();

    }

    /**
     * Setter of the function executable.
     * @param a
     */
    public static void setExecFonction(boolean a) {
        execFonction = a;
    }

    /**
     * This method allows to know whether it can execute the function or not.
     * @return true or false.
     */
    public static boolean getExecFonction() {
        return execFonction;
    }

    /***
     * Setter of the P function.
     */
    public static void setTmpP() {
        tmpP = p;
    }

    /**
     * This method allows to set the P function.
     */
    public static void setPFonction() {
        p = _memory.size()-1;
        tmpSize = p;
    }

    /**
     * This method allows to remove a function from the memory.
     */
    public static void removeFonctionMemory() {
        for (int j = _memory.size()-1; j > tmpSize ; j--) {
            _memory.remove(j);
        }
    }

    /**
     * This method allows to return a value from the memory.
     */
    public static void returnValue() {
        _memory.set(tmpP, _memory.get(p));
    }

    /**
     * Setter of the last p.
     */
    public static void setLastP() {
        p = tmpP;
    }

    /**
     * Getter of the last p.
     * @return the p.
     */
    public static int getLastP() {
        return tmpP;
    }

    public void Reset(){
        p=0;
        i=0;
        init();
    }
}
