package brainfuck.lecture;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum EnumArg {

    PRINT("-p"),
    REWRITE("--rewrite"),
    TRANSLATE("--translate"),
    INF("-i"),
    OUTF("-o"),
    CHECK("--check");

    private final String arg;
    static final Map< String, EnumArg> comArg = new HashMap<>();

    static {
        Arrays.asList(EnumArg.values()).forEach(val -> comArg.put(val.toString(), val));
    }

    EnumArg(String arg) {
        this.arg = arg;
    }

    @Override
    public String toString() {
        return this.arg;
    }

}
