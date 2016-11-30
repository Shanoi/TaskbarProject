package brainfuck.memory;

public class Syntaxe {

    public String line;

    /**
     *Boolean checking whether the line is short or not
     * @param line
     * @return
     */
    public static boolean isShort(String line) {
        if ((line.charAt(0) >= 'A') && (line.charAt(0) <= 'Z')) {
            return false;
        }
        return true;
    }

}
