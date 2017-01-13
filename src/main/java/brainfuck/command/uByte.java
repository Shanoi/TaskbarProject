package brainfuck.command;

/**
 * This class convert a signed Byte to an unsigned byte.
 *
 * @author Team Taskbar
 */
public class uByte {

    byte a;

    // Constructor of uByte

    public uByte() {
        this.a = 0;
    }

    public uByte(byte a) {
        this.a = a;
    }

    /**
     * Setter of n
     *
     * @param n
     */
    public void set(byte n) {
        this.a = n;
    }

    /**
     * This method allows to cast a byte to an int
     *
     * @return the integer.
     */
    public int byteToInt() {
        return ((int) a) & 0xFF;
    }

}
