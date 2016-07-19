import java.util.Arrays;
import java.util.Random;

/**
 * Created by Artem Klots on 7/19/16.
 */
public class Game {
    private static final int BOARD_SIZE = 4;
    private int[][] cells;

    public Game() {
        cells = new int[BOARD_SIZE][BOARD_SIZE];
    }

    public int[][] getCells() {
        return cells;
    }

    public void start() {
        generateEmptyBoard();
        generateStartBoard();
    }

    private void generateEmptyBoard() {
        for (int[] row : cells) {
            for (int cell : row) {
                cell = 0;
            }
        }
    }

    private void generateStartBoard() {
        for (int i = 0; i < 2; i++) {
            generateRandomCell();
        }
    }

    private void generateRandomCell() {
        if (isEmptyCellsOnBoard()) {
            boolean isSuccessful = false;

            while (!isSuccessful) {
                Random randomGenerator = new Random();
                int x = randomGenerator.nextInt(BOARD_SIZE);
                int y = randomGenerator.nextInt(BOARD_SIZE);
                if (isEmptyCell(cells[x][y])) {
                    if (randomGenerator.nextBoolean()) {
                        cells[x][y] = 2;
                    } else {
                        cells[x][y] = 4;
                    }
                    isSuccessful = true;
                }
            }

        }
        //// TODO: 7/19/16 else end of game

    }

    private boolean isEmptyCellsOnBoard() {
        for (int[] row : cells) {
            for (int cell : row) {
                if (isEmptyCell(cell))
                    return true;
            }
        }
        return false;
    }

    private boolean isEmptyCell(int cell) {
        return cell == 0;
    }
}
