package dat3.experiment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleSanitizerTest {

    @Test
    void simpleSanitize() {
        String result = SimpleSanitizer.simpleSanitize("Hello <h2><b>World</b></h2>!");

        assertEquals("Hello World!", result);
    }
}