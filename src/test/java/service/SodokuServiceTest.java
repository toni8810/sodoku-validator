package service;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SodokuServiceTest {

    private final SodokuService sodokuService = new SodokuService();

    private final String RESOURCES_PATH = System.getProperty("user.dir") + "/src/test/resources/";

    /**
     * Given file at path does not exist
     * When file is read
     * Then IOException will be thrown
     */

    @Test
    public void fileDoesNotExist() {
        IOException ioException = assertThrows(
                IOException.class,
                () -> sodokuService.getPuzzleFromFile("/bogus/path"),
                "IOException should have been thrown when file path is invalid"
        );

        assertEquals("File at path /bogus/path does not exist", ioException.getMessage());
    }

    /**
     * Given file contains more than 9 columns
     * When file is read
     * Then IOException will be thrown
     */

    @Test
    public void moreThan9Columns() {
        String path = RESOURCES_PATH + "sodoku10Columns";
        IOException ioException = assertThrows(
                IOException.class,
                () -> sodokuService.getPuzzleFromFile(path),
                "IOException should have been thrown as puzzle have 10 columns"
                );

        assertEquals("Puzzle must contain 9 columns", ioException.getMessage());
    }

    /**
     * Given file contains fewer than 9 columns
     * When file is read
     * Then IOException will be thrown
     */

    @Test
    public void fewerThan9Columns() {
        String path = RESOURCES_PATH + "sodoku8Columns";
        IOException ioException = assertThrows(
                IOException.class,
                () -> sodokuService.getPuzzleFromFile(path),
                "IOException should have been thrown as puzzle have 8 columns"
        );

        assertEquals("Puzzle must contain 9 columns", ioException.getMessage());
    }

    /**
     * Given file contains more than 9 rows
     * When file is read
     * Then IOException will be thrown
     */
    @Test
    public void moreThan9Rows() {
        String path = RESOURCES_PATH + "soduku10Rows";
        IOException ioException = assertThrows(
                IOException.class,
                () -> sodokuService.getPuzzleFromFile(path),
                "IOException should have been thrown as puzzle have 10 rows"
        );

        assertEquals("Puzzle must contain 9 rows", ioException.getMessage());
    }

    /**
     * Given file contains fewer than 9 rows
     * When file is read
     * Then IOException will be thrown
     */
    @Test
    public void fewerThan9Rows() {
        String path = RESOURCES_PATH + "soduku8Rows";
        IOException ioException = assertThrows(
                IOException.class,
                () -> sodokuService.getPuzzleFromFile(path),
                "IOException should have been thrown as puzzle have 8 rows"
        );

        assertEquals("Puzzle must contain 9 rows", ioException.getMessage());
    }

    /**
     * Given file contains a letter
     * When file is read
     * Then IOException will be thrown
     */

    @Test
    public void containsInvalidCharacter() {
        String path = RESOURCES_PATH + "sodukuWithInvalidChar";
        IOException ioException = assertThrows(
                IOException.class,
                () -> sodokuService.getPuzzleFromFile(path),
                "IOException should have been thrown as puzzle has invalid character in it"
        );

        assertEquals("Puzzle must contain only numbers between 1 and 10", ioException.getMessage());

    }

    /**
     * Given file contains a number over 9
     * When file is read
     * Then IOException will be thrown
     */

    @Test
    public void containsLargeNumber() {
        String path = RESOURCES_PATH + "sodukuWithLargeNum";
        IOException ioException = assertThrows(
                IOException.class,
                () -> sodokuService.getPuzzleFromFile(path),
                "IOException should have been thrown as puzzle has a too large number in it"
        );
        assertEquals("Puzzle must contain only numbers between 1 and 10", ioException.getMessage());
    }


    /**
     * Given file contains a valid sodoku puzzle
     * When file is read
     * Then 0 will be returned
     */

    @Test
    public void validPuzzle() throws IOException {
        String path = RESOURCES_PATH + "sodokuValid";

        assertEquals(0, sodokuService.validate(sodokuService.getPuzzleFromFile(path)));
    }

    /**
     * Given file contains a valid unfilled sodoku puzzle
     * When file is read
     * Then 0 will be returned
     */

    @Test
    public void validUnfilledPuzzle() throws IOException {
        String path = RESOURCES_PATH + "sodokuValidUnfilled";

        assertEquals(0, sodokuService.validate(sodokuService.getPuzzleFromFile(path)));
    }

    /**
     * Given file contains an invalid sodoku puzzle
     * When file is read
     * Then -1 will be returned
     */

    @Test
    public void invalidPuzzle() throws IOException {
        String path = RESOURCES_PATH + "sodokuInvalid";

        assertEquals(-1, sodokuService.validate(sodokuService.getPuzzleFromFile(path)));
    }

    /**
     * Given file contains an invalid unfilled sodoku puzzle
     * When file is read
     * Then -1 will be returned
     */

    @Test
    public void invalidUnfilledPuzzle() throws IOException {
        String path = RESOURCES_PATH + "sodokuInvalidUnfilled";

        assertEquals(-1, sodokuService.validate(sodokuService.getPuzzleFromFile(path)));
    }
}
