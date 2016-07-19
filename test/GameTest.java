import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by employee on 7/19/16.
 */
public class GameTest {
    private Game game;

    @Before
    public void setUp() throws Exception {
        game = new Game();
    }

    @Test
    public void constructorTest() throws Exception {
        assertThat(game.getBoard(), is(new int[4][4]));
    }

    @Test
    public void startGame() throws Exception {
        game.start();
        assertThat(isEmptyBoard(game.getBoard()), is(true));
    }

    public boolean isEmptyBoard(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                if (!isEmptyCell(cell))
                    return false;
            }
        }
        return true;
    }

    public boolean isEmptyCell(int cell) {
        return cell == 0;
    }
}

