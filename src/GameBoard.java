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

//    private void nextStep(){
//        if (isEmptyCellsOnBoard()){
//
//        }
//    }

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
    public void flickRight() {
        System.out.println(Arrays.deepToString(cells));
        for (int i = 0; i < 3; i++) {
            moveCellToRight();
        }
    }

    private void moveCellToRight() {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 3; x++) {
                if (isNextRightCellEmpty(getRow(y), x)) {
                    transportCellsToRight(y, x);
//                    System.out.println(Arrays.deepToString(cells));
                } else {
                    plusIfPossible(y, x);
                }
            }
        }
//        System.out.println();
    }

    private void plusIfPossible(int y, int x) {
        if (cells[y][x] == cells[y][x + 1]) {
            transportCellsToRight(y, x);
        }
    }

    private void transportCellsToRight(int y, int x) {
        cells[y][x + 1] += cells[y][x];
        cells[y][x] = 0;
    }

    private boolean isNextRightCellEmpty(int[] row, int x) {
        return isEmptyCell(row[x + 1]);
    }

    private int[] getRow(int rowNumber) {
        int[] row = new int[BOARD_SIZE];
        System.arraycopy(cells[rowNumber], 0, row, 0, BOARD_SIZE);
        return row;
    }

    @Override
    public void flickLeft() {

    }

    @Override
    public void flickUp() {

    }

    @Override
    public void flickDown() {

    }


}
