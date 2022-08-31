package dat3.experiment;

public class SimpleSanitizer {

    public static String simpleSanitize(String s) {
        return s.replaceAll("(<\\w+>)|(</\\w+>)", "");

    }

}
