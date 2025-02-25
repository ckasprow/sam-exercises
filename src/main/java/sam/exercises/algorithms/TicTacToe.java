package sam.exercises.algorithms;



public class TicTacToe {

    private static final int MAX_LENGTH = 3;
    private int moveCount = 0;
    private int playerNo = 1;
    private int[][] board;

    public TicTacToe(int width, int height) {
        board = new int[width][height];
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe(3, 3);
        game.play(1, 1);
        game.play(0, 0);
        game.play(2, 1);
        game.play(0, 1);
        game.play(2, 0);
        game.play(0, 2);
    }

    public String play(int x, int y) {
        if (moveCount >= board.length * board[0].length) {
            System.out.println("Game over");
            return "Game over";
        } else if (!isValid(x, y)) {
            throw new IllegalArgumentException("Position is out of bounds");
        } else if (board[x][y] != 0) {
            throw new IllegalArgumentException("Position is already occupied");
        }
        board[x][y] = playerNo;
        if (checkWinner(x, y)) {
            System.out.println("Player " + playerNo + " wins!");
            return "Player " + playerNo + " wins!";
        }
        String result = "Move #" + moveCount + ": Player " + playerNo + " occupies position ("+x+","+y+")";
        moveCount++;
        playerNo = playerNo % 2 + 1;
        return result;
    }

    public boolean isValid(int x, int y) {
        return x >= 0 && x < board.length && y >= 0 && y < board[0].length;
    }

    public boolean checkWinner(int x, int y) {
        int hCount = 0;
        int vCount = 0;
        int tlCount = 0;
        int blCount = 0;
        for (int i = -(MAX_LENGTH-1); i < MAX_LENGTH; i++) {
            // Horizontal
            hCount = (isValid(x+i, y) && board[x+i][y] == playerNo) ? hCount+1 : 0;

            // Vertical
            vCount = (isValid(x, y+i) && board[x][y+i] == playerNo) ? vCount+1 : 0;

            // Diagonal TL -> BR
            tlCount = (isValid(x+i,y+i) && board[x+i][y+i] == playerNo) ? tlCount+1 : 0;

            // Diagonal BL -> TR
            blCount = (isValid(x+i, y-i) && board[x+i][y-i] == playerNo) ? blCount+1 : 0;

            if (hCount >= MAX_LENGTH || vCount >= MAX_LENGTH || tlCount >= MAX_LENGTH || blCount >= MAX_LENGTH) {
                return true;
            }
        }
        return false;
    }

}
