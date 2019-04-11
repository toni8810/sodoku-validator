import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MainTest {

    /**
     * Given path to file is not provided
     * When file is read
     * Then RuntimeException will be thrown
     */

    @Test
    public void argsIsNull() {
        RuntimeException runtimeException = assertThrows(
                RuntimeException.class,
                () -> Main.main(new String[]{}),
                "RuntimeException should have been thrown when empty array is passed to main method"
        );

        assertEquals("Please pass file path as first argument", runtimeException.getMessage());
    }

    @Test
    public void firstArgIsEmpty() {
        RuntimeException runtimeException = assertThrows(
                RuntimeException.class,
                () -> Main.main(new String[]{""}),
                "RuntimeException should have been thrown when empty string is passed to main method"
        );

        assertEquals("Please pass file path as first argument", runtimeException.getMessage());
    }

    @Test
    public void firstArgIsNull() {
        RuntimeException runtimeException = assertThrows(
                RuntimeException.class,
                () -> Main.main(new String[] {null}),
                "RuntimeException should have been thrown when first arg is null"
                );

        assertEquals("Please pass file path as first argument", runtimeException.getMessage());

    }

    @Test
    public void validPuzzle() throws IOException {
        String path = System.getProperty("user.dir") + "/src/test/resources/sodokuValid";
        Main.main(new String[] {path});
    }

}
