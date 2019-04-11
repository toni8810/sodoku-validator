package service;

import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class SodokuService {

    public int[][] getPuzzleFromFile(String fileFromPath) throws IOException {
        File file = new File(fileFromPath);
        int[][] puzzle = new int[9][9];
        int row = 0;

        if (!file.exists()) {
            throw new IOException("File at path " + fileFromPath + " does not exist");
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] rowElements = line.split(",");

                if (row > 8) {
                    throw new IOException("Puzzle must contain 9 rows");
                }

                if (rowElements.length != 9) {
                    throw new IOException("Puzzle must contain 9 columns");
                }

                for (int i=0; i<rowElements.length; i++) {
                    if ((rowElements[i].isEmpty()) || (rowElements[i].contentEquals(" "))) {
                        puzzle[row][i] = 10;
                    }
                    else if (NumberUtils.isCreatable(rowElements[i])) {
                        puzzle[row][i] = Integer.parseInt(rowElements[i]);

                        if ((puzzle[row][i] > 9) || (puzzle[row][i] < 1)) {
                            throw new IOException("Puzzle must contain only numbers between 1 and 10");
                        }
                    }
                    else {
                        throw new IOException("Puzzle must contain only numbers between 1 and 10");
                    }

                }
                row++;
            }

            if (row < 9) {
                throw new IOException("Puzzle must contain 9 rows");
            }
        }

        return puzzle;
    }

    public int validate(int[][] puzzle) {
        for (int columnIndex = 0; columnIndex < 9; columnIndex++) {
            int[] row = new int[9];
            int[] square = new int[9];
            int[] column = puzzle[columnIndex].clone();

            for (int rowIndex = 0; rowIndex < 9; rowIndex ++) {
                row[rowIndex] = puzzle[rowIndex][columnIndex];
                square[rowIndex] = puzzle[(columnIndex / 3) * 3 + rowIndex / 3][columnIndex * 3 % 9 + rowIndex % 3];
            }

            if ((!isValid(row)) || (!isValid(column)) || (!isValid(square))) {
                return -1;
            }
        }
        return 0;
    }

    private boolean isValid(int[] check) {
        Arrays.sort(check);

        for (int i=0; i<check.length; i++) {
            if ((check[i] != 10) && (i + 1 < check.length)) {
                if (check[i] >= check[i +1]) {
                    return false;
                }
            }

        }

        return true;
    }
}
