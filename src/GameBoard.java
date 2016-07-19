import java.util.Arrays;
import java.util.Random;

/**
 * Created by Artem Klots on 7/19/16.
 */
public class GameBoard implements Movable {
    private static final int BOARD_SIZE = 4;
    private int[][] cells;

    public GameBoard() {
        cells = new int[BOARD_SIZE][BOARD_SIZE];
        generateStartBoard();
    }

    public GameBoard(int[][] board) {
        cells = board;
    }

    int[][] getCells() {
        return cells;
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

    public boolean isEmptyCellsOnBoard() {
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

    @Override
    public void swipeRight() {
        moveToRightSide();
        for (int i = 0; i <= 3; i++) {
            plusToRightIfPossible(i);
        }
        moveToRightSide();
    }

    private void moveToRightSide() {
        for (int i = 0; i < 4; i++) {
            for (int y = 0; y < 4; y++) {
                for (int x = 0; x < 3; x++) {

                    if (isEmptyNextCell(getRow(y), x)) {
                        moveCellToRight(y, x);
                    }

                }
            }
        }
    }

    private void plusToRightIfPossible(int row) {
        for (int i = 3; i > 0; i--) {
            if (cells[row][i] == cells[row][i - 1]) {
                cells[row][i] += cells[row][i - 1];
                cells[row][i - 1] = 0;
            }
//            System.out.println(Arrays.deepToString(cells));
        }
//        System.out.println();
    }

    private boolean isEmptyNextCell(int[] row, int x) {
        return row[x + 1] == 0;
    }

    private void moveCellToRight(int y, int x) {
        cells[y][x + 1] = cells[y][x];
        cells[y][x] = 0;
    }


    private int[] getRow(int rowNumber) {
        int[] row = new int[BOARD_SIZE];
        System.arraycopy(cells[rowNumber], 0, row, 0, BOARD_SIZE);
        return row;
    }

    @Override
    public void swipeLeft() {
        moveToLeftSide();
        for (int i = 0; i <= 3; i++) {
            plusToLeftIfPossible(i);
        }
        moveToLeftSide();
    }

    private void plusToLeftIfPossible(int row) {
        for (int i = 0; i < 3; i++) {
            if (cells[row][i] == cells[row][i + 1]) {
                cells[row][i] += cells[row][i + 1];
                cells[row][i + 1] = 0;
            }
//            System.out.println(Arrays.deepToString(cells));
        }
//        System.out.println();
    }

    private void moveToLeftSide() {
        for (int i = 0; i < 4; i++) {
            for (int y = 0; y < 4; y++) {
                for (int x = 3; x > 0; x--) {

                    if (isEmptyPreviousCell(getRow(y), x)) {
                        moveCellToLeft(y, x);
                    }

                }
//                System.out.print(Arrays.toString(getRow(y)));
            }
//            System.out.println();
        }
    }

    private void moveCellToLeft(int y, int x) {
        cells[y][x - 1] = cells[y][x];
        cells[y][x] = 0;
    }

    private boolean isEmptyPreviousCell(int[] row, int x) {
        return row[x - 1] == 0;
    }


    @Override
    public void swipeUp() {

    }

    @Override
    public void swipeDown() {

    }


}
