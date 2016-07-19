import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by employee on 7/19/16.
 */
public class BoardTest {
    @Test
    public void constructorTest() throws Exception {
        Board board = new Board();
        assertThat(board.getBoard(), is(new int[4][4]));
    }
}

