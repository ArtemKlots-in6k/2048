import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Artem Klots on 7/19/16.
 */
public class GameBoard implements Movable {
    private static final int BOARD_SIZE = 4;
    //    private int[][] cells;
    private List<Cell> cells;

    public GameBoard() {
        cells = new ArrayList<>();
        generateEmptyBoard();
        generateStartBoard();
    }

    private void generateEmptyBoard() {
        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                cells.add(new Cell(new Point(x, y), 0));
            }
        }
    }

    public GameBoard(int[][] board) {
        cells = new ArrayList<>();
        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                cells.add(new Cell(new Point(x, y), board[y][x]));
            }
        }
    }

    List<Cell> getCells() {
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
                if (getCellByPosition(x, y).isEmpty()) {
                    if (randomGenerator.nextBoolean()) {
                        getCellByPosition(x, y).setValue(2);
                    } else {
                        getCellByPosition(x, y).setValue(4);
                    }
                    isSuccessful = true;
                }
            }

        }
        //// TODO: 7/19/16 else end of game
    }

    private Cell getCellByPosition(int x, int y) {
        for (Cell cell : cells) {
            if (cell.getPosition().equals(new Point(x, y)))
                return cell;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    private boolean isEmptyCellsOnBoard() {
        for (Cell cell : cells) {
            if (!cell.isEmpty())
                return false;
        }
        return true;
    }

    @Override
    public void swipeRight() {
        moveToRightSide();
        for (int y = 0; y <= BOARD_SIZE - 1; y++) {
            plusToRightIfPossible(y);
        }
        moveToRightSide();
    }

    private void moveToRightSide() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                for (int x = 0; x < BOARD_SIZE - 1; x++) {

                    if (isEmptyNextCell(x, y)) {
                        moveCellToRight(y, x);
                    }

                }
            }
        }
    }

    private void plusToRightIfPossible(int y) {
        for (int x = BOARD_SIZE - 1; x > 0; x--) {
            if (getCellByPosition(x, y).equals(getCellByPosition(x - 1, y))) {
                getCellByPosition(x, y).joinNextCells(getCellByPosition(x - 1, y));
            }
        }
    }

    private boolean isEmptyNextCell(int x, int y) {
        return getCellByPosition(x + 1, y).isEmpty();
    }

    private void moveCellToRight(int y, int x) {
        getCellByPosition(x + 1, y).setValue(getCellByPosition(x, y).getValue());
        getCellByPosition(x, y).setValue(0);
    }


//    private int[] getRow(int rowNumber) {
//        int[] row = new int[BOARD_SIZE];
//        System.arraycopy(cells[rowNumber], 0, row, 0, BOARD_SIZE);
//        return row;
//    }

    @Override
    public void swipeLeft() {
        moveToLeftSide();
        for (int i = 0; i <= 3; i++) {
            plusToLeftIfPossible(i);
        }
        moveToLeftSide();
    }

    private void plusToLeftIfPossible(int y) {
        for (int x = 0; x < 3; x++) {
            if (getCellByPosition(x, y).equals(getCellByPosition(x + 1, y))) {
                getCellByPosition(x, y).joinNextCells(getCellByPosition(x + 1, y));
            }
        }
    }

    private void moveToLeftSide() {
        for (int i = 0; i < 4; i++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                for (int x = 3; x > 0; x--) {

                    if (isEmptyPreviousCell(x, y)) {
                        moveCellToLeft(y, x);
                    }

                }
            }
        }
    }

    private void moveCellToLeft(int y, int x) {
        getCellByPosition(x - 1, y).setValue(getCellByPosition(x, y).getValue());
        getCellByPosition(x, y).setValue(0);
    }

    private boolean isEmptyPreviousCell(int x, int y) {
        return getCellByPosition(x - 1, y).isEmpty();
    }


    @Override
    public void swipeUp() {
//        for (int i = 0; i < 4; i++) {
//            for (int x = 0; x < 4; x++) {
//                for (int y = 0; y < 3; y++) {
//
//                    if (isEmptyCellBellow(getColumn(x), y)) {
//                        moveCellToRight(y, x);
//                    }
//
//                }
//            }
//        }
    }

//    private boolean isEmptyCellBellow()

    @Override
    public void swipeDown() {

    }

    public int[][] getCellsToArray() {
        int[][] result = new int[BOARD_SIZE][BOARD_SIZE];
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                result[y][x] = getCellByPosition(x, y).getValue();
//                System.out.println(getCellByPosition(x, y).getValue());
            }
        }
        return result;
    }
}

