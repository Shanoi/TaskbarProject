package brainfuck.command;

/**
 * This class convert a signed Byte to an unsigned byte
 *
 * @author Olivier
 */
public class uByte {

    byte a;

    public uByte() {
        this.a = 0;
    }

    public uByte(byte a) {
        this.a = a;
    }

    public void set(byte n) {
        this.a = n;
    }

    public int byteToInt() {
        return ((int) a) & 0xFF;
    }

}
