import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by employee on 7/19/16.
 */
public class GameBoardTest {
    private GameBoard gameBoard;
    private GameBoard fixedGameBoard = new GameBoard(new int[][]{{0, 2, 0, 0}, {0, 2, 2, 0}, {2, 2, 2, 0}, {2, 2, 2, 2},});

    @Before
    public void setUp() throws Exception {
        gameBoard = new GameBoard();
    }

    @Test
    public void isGeneratedTwooCellsOnStart() throws Exception {
        assertThat(isTwoCellsOnStart(gameBoard.getCells()), is(true));
    }

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

    public boolean isEmptyCell(int cell) {
        return cell == 0;
    }

    @Test
    public void moveRight() throws Exception {
        gameBoard = fixedGameBoard;
        gameBoard.swipeRight();
        assertThat(gameBoard.getCells(), is(new int[][]{{0, 0, 0, 2}, {0, 0, 0, 4}, {0, 0, 2, 4}, {0, 0, 4, 4}}));
    }

    @Ignore
    @Test
    public void moveLeft() throws Exception {
        gameBoard = fixedGameBoard;
        gameBoard.swipeLeft();
        assertThat(gameBoard.getCells(), is(new int[][]{{2, 0, 0, 0}, {4, 0, 0, 0}, {2, 4, 0, 0}, {8, 0, 0, 0}}));
    }
}


