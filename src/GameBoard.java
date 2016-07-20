import com.sun.org.apache.xml.internal.utils.WrongParserException;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Artem Klots on 7/19/16.
 */
public class GameBoard implements Movable, ValueGenerator {
    private static final int BOARD_SIZE = 4;
    private List<Cell> cells;
    private Direction direction;

    public GameBoard() {
        cells = new ArrayList<>();
        generateEmptyBoard();
        generateStartBoard();
    }

    private void generateStartBoard() {
        // TODO: 7/20/16 Починить!!
//        generateRandomValue();
//        generateRandomValue();
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


    @Override
    public void swipeRight() {
        direction = Direction.Right;
        moveToRightSide();
        for (int y = 0; y < BOARD_SIZE; y++) {
            plusToRightIfPossible(y);
        }
        moveToRightSide();
    }

    private void moveToRightSide() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                for (int x = 0; x < BOARD_SIZE - 1; x++) {

                    if (isEmptyCellInHorizontalDirection(x, y, direction)) {
                        moveCellInHorizontalDirection(y, x, direction);
                    }

                }
            }
        }
    }

    private void plusToRightIfPossible(int y) {
        for (int x = BOARD_SIZE - 1; x > 0; x--) {
            joinCellInHorizontalDirection(x, y, direction);
        }
    }

    @Override
    public void swipeLeft() {
        direction = Direction.Left;
        moveToLeftSide();
        for (int i = 0; i < BOARD_SIZE; i++) {
            plusToLeftIfPossible(i);
        }
        moveToLeftSide();
    }

    private void plusToLeftIfPossible(int y) {
        for (int x = 0; x < 3; x++) {
            joinCellInHorizontalDirection(x, y, direction);
        }
    }

    // TODO: 7/20/16 По возможности исправить вложености. (создать список с непустыми елементами и сдвинуть вправо)
    private void moveToLeftSide() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                for (int x = BOARD_SIZE - 1; x > 0; x--) {
                    if (isEmptyCellInHorizontalDirection(x, y, direction))
                        moveCellInHorizontalDirection(y, x, direction);
                }
            }
        }
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

    public List<Cell> selectEmptyCells(List<Cell> cells) {
        List<Cell> emptyCells = new ArrayList<>();
        for (Cell cell : cells) {
            if (!cell.isEmpty())
                emptyCells.add(cell);
        }
        return emptyCells;
    }

    @Override
    public void generateRandomValue() {
        Random randomGenerator = new Random();

        List<Cell> emptyCells = this.selectEmptyCells(cells);
        Cell randomEmptyCell = emptyCells.get(randomGenerator.nextInt(emptyCells.size() - 1));
        if (randomGenerator.nextBoolean()) {
            randomEmptyCell.setValue(2);
        } else {
            randomEmptyCell.setValue(4);
        }
    }

    public int[][] getCellsToArray() {
        int[][] result = new int[BOARD_SIZE][BOARD_SIZE];
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                result[y][x] = getCellByPosition(x, y).getValue();
            }
        }
        return result;
    }

    private boolean isEmptyCellInHorizontalDirection(int x, int y, Direction direction) {
        int directionIndex = getDirectionIndex(direction);
        return getCellByPosition(x + directionIndex, y).isEmpty();
    }

    private void moveCellInHorizontalDirection(int y, int x, Direction direction) {
        int directionIndex = getDirectionIndex(direction);
        getCellByPosition(x + directionIndex, y).setValue(getCellByPosition(x, y).getValue());
        getCellByPosition(x, y).setValue(0);
    }

    private int getDirectionIndex(Direction direction) {
        switch (direction) {
            case Right:
                return 1;
            case Left:
                return -1;
        }
        throw new WrongParserException("Wrong comand here");
    }

    private void joinCellInHorizontalDirection(int x, int y, Direction direction) {
        int directionIndex = 0;
        // TODO: 7/20/16 Запихнуть команду в мапу, тем самым убрав Switch
        switch (direction) {
            case Right:
                directionIndex = -1;
                break;
            case Left:
                directionIndex = 1;
                break;
        }
        if (getCellByPosition(x, y).equals(getCellByPosition(x + directionIndex, y))) {
            getCellByPosition(x, y).joinNextCells(getCellByPosition(x + directionIndex, y));
        }
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

    List<Cell> getCells() {
        return cells;
    }
}

