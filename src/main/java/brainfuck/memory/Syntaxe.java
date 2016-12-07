package brainfuck.memory;

public class Syntaxe {

    public String line;

    /**
     * This method allows to know whether it's a short syntaxe or not.
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
