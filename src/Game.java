/**
 * Created by Artem Klots on 7/19/16.
 */
public class Game {
    private static final int BOARD_SIZE = 4;
    private int[][] board;

    public Game() {
        board = new int[BOARD_SIZE][BOARD_SIZE];
    }

    public int[][] getBoard() {
        return board;
    }

    public void start() {
        generateEmptyBoard();
    }

    private void generateEmptyBoard() {
        for (int[] row : board) {
            for (int cell : row) {
                cell = 0;
            }
        }
    }


}
