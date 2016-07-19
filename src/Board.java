/**
 * Created by Artem Klots on 7/19/16.
 */
public class Board {
    public static final int BOARD_SIZE = 4;
    int[][] board;

    public Board() {
        board = new int[BOARD_SIZE][BOARD_SIZE];
    }

    public int[][] getBoard(){
        return board;
    }


}
