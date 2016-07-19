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
        assertThat(game.getCells(), is(new int[4][4]));
    }

    @Test
    public void startGame() throws Exception {
        game.start();
        assertThat(isTwoCellsOnStart(game.getCells()), is(true));
    }

    private boolean isTwoCellsOnStart(int[][] board) {
        int counter = 0;
        for (int[] row : board) {
            for (int cell : row) {
                if (cell > 0)
                    counter++;
            }
        }
        return counter == 2;
    }

    public boolean isEmptyCell(int cell) {
        return cell == 0;
    }
}


