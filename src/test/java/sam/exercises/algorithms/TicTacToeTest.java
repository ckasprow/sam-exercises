package sam.exercises.algorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicTacToeTest {

    @Test
    public void positionOutOfBoundsTest() {
        TicTacToe game = new TicTacToe(3, 3);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            game.play(-1, -1);
        });
    }

    @Test
    public void positionAlreadyOccupiedTest() {
        TicTacToe game = new TicTacToe(3, 3);
        game.play(1, 1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            game.play(1, 1);
        });
    }

    /**
     * |x|o|x|
     * |x|o|x|
     * |o|x|o|
     */
    @Test
    public void gameOverTest() {
        TicTacToe game = new TicTacToe(3, 3);
        game.play(0, 0); // x
        game.play(1, 0); // o
        game.play(2, 0); // x

        game.play(1, 1); // o
        game.play(0, 1); // x
        game.play(0, 2); // o

        game.play(2, 1); // x
        game.play(2, 2); // o
        game.play(1, 2); // x
        assertEquals("Game over", game.play(0, 0));
    }

    @Test
    public void simpleThreeByThreeBoard() {
        TicTacToe game = new TicTacToe(3, 3);
        game.play(1, 1);
        game.play(0, 0);
        game.play(2, 1);
        game.play(0, 1);
        game.play(2, 0);
        assertEquals("Player 2 wins!", game.play(0, 2));
    }

}
