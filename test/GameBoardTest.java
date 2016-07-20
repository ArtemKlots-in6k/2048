import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Artem Klots on 7/19/16.
 */
public class GameBoardTest {
    private GameBoard gameBoard;
    private GameBoard fixedGameBoard = new GameBoard(new int[][]{
            {0, 2, 0, 0},
            {0, 2, 2, 0},
            {2, 2, 2, 0},
            {2, 2, 2, 2},
    });

    @Before
    public void setUp() throws Exception {
        gameBoard = new GameBoard();
    }

    // TODO: 7/20/16 переделать генератор через интерфесы
//    @Ignore
//    @Test
//    public void isGeneratedTwoCellsOnStart() throws Exception {
//        assertThat(isTwoCellsOnStart(gameBoard.getCells()), is(true));
//    }

    private boolean isTwoCellsOnStart(int[][] board) {
        int counter = 0;
        for (int[] row : board) {
            for (int cell : row) {
                if (!isEmptyCell(cell))
                    counter++;
            }
        }
        return counter == 2;
    }

    private boolean isEmptyCell(int cell) {
        return cell == 0;
    }

    @Test
    public void moveRight() throws Exception {
        gameBoard = fixedGameBoard;
        gameBoard.swipeRight();
        assertThat(gameBoard.getCellsToArray(), is(new int[][]{
                {0, 0, 0, 2},
                {0, 0, 0, 4},
                {0, 0, 2, 4},
                {0, 0, 4, 4}
        }));
    }

    @Test
    public void moveLeft() throws Exception {
        gameBoard = fixedGameBoard;
        gameBoard.swipeLeft();
        assertThat(gameBoard.getCellsToArray(), is(new int[][]{
                {2, 0, 0, 0},
                {4, 0, 0, 0},
                {4, 2, 0, 0},
                {4, 4, 0, 0}
        }));
    }

    @Test
    public void moveUp() throws Exception {
        gameBoard = fixedGameBoard;
        gameBoard.swipeUp();
        assertThat(gameBoard.getCellsToArray(), is(new int[][]{
                {4, 4, 4, 2},
                {0, 4, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        }));
    }

    @Test
    public void moveDown() throws Exception {
        gameBoard = fixedGameBoard;
        gameBoard.swipeLeft();
        assertThat(gameBoard.getCellsToArray(), is(new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 4, 2, 0},
                {4, 4, 4, 2}
        }));
    }
}


