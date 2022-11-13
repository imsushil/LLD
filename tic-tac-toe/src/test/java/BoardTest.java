import com.systemdesign.Board;
import com.systemdesign.Player;
import com.systemdesign.Symbol;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

class BoardTest {
    private Board board;

    @BeforeEach
    void setup() throws NoSuchFieldException, IllegalAccessException {
        resetSingleton();
        this.board = Board.getInstance();
    }

    void resetSingleton() throws NoSuchFieldException, IllegalAccessException {
        Field board = Board.class.getDeclaredField("board");
        board.setAccessible(true);
        board.set(null, null);
    }

    @Test
    void whenPlayerSymbolIsBlankMarkThrowsException() {
        Player sushil = new Player("Sushil", Symbol.BLANK);
        Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> this.board.makeMove(0, 0, sushil));
        Assertions.assertEquals("_ is the symbol for unoccupied box. Players cannot use it.", e.getMessage());
    }

    @Test
    void whenRowOutsideBoardMarkThrowsException() {
        Player sushil = new Player("Sushil", Symbol.BLANK);
        Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> this.board.makeMove(-1, 0, sushil));
        Assertions.assertEquals("_ is the symbol for unoccupied box. Players cannot use it.", e.getMessage());
    }

    @Test
    void whenRowOutsideBoardMarkThrowsException1() {
        Player sushil = new Player("Sushil", Symbol.O);
        Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> this.board.makeMove(4, 0, sushil));
        Assertions.assertEquals("Move out of board boundary", e.getMessage());
    }

    @Test
    void whenColOutsideBoardMarkThrowsException() {
        Player sushil = new Player("Sushil", Symbol.X);
        Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> this.board.makeMove(0, -1, sushil));
        Assertions.assertEquals("Move out of board boundary", e.getMessage());
    }

    @Test
    void whenColOutsideBoardMarkThrowsException1() {
        Player sushil = new Player("Sushil", Symbol.X);
        Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> this.board.makeMove(0, 5, sushil));
        Assertions.assertEquals("Move out of board boundary", e.getMessage());
    }

    @Test
    void whenBoxIsOccupiedMarkThrowsException() {
        Player sushil = new Player("Sushil", Symbol.X);
        this.board.makeMove(0, 0, sushil);

        Player vinod = new Player("Sushil", Symbol.O);

        Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> this.board.makeMove(0, 0, vinod));
        Assertions.assertEquals("Box is already occupied.", e.getMessage());
    }

    @Test
    void whenBoxIsUnoccupiedMakeMoveReturnsSymbol() {
        Player sushil = new Player("Sushil", Symbol.X);
        char symbol = this.board.makeMove(0, 0, sushil);
        Assertions.assertEquals(symbol, sushil.getSymbol().getValue());
    }

    @Test
    void playerHasWonRowest() {
        Player sushil = new Player("Sushil", Symbol.X);
        this.board.makeMove(0, 0, sushil);
        this.board.makeMove(0, 1, sushil);
        this.board.makeMove(0, 2, sushil);
        Assertions.assertEquals(true, sushil.isWinner());
    }

    @Test
    void playerHasWonOnColTest() {
        Player sushil = new Player("Sushil", Symbol.X);
        this.board.makeMove(0, 0, sushil);
        this.board.makeMove(1, 0, sushil);
        this.board.makeMove(2, 0, sushil);
        Assertions.assertEquals(true, sushil.isWinner());
    }

    @Test
    void playerHasWonOnDiagonalTest() {
        Player sushil = new Player("Sushil", Symbol.X);
        this.board.makeMove(0, 0, sushil);
        this.board.makeMove(1, 1, sushil);
        this.board.makeMove(2, 2, sushil);
        Assertions.assertEquals(true, sushil.isWinner());
    }

    @Test
    void playerHasWonOnReverseDiagonalTest() {
        Player sushil = new Player("Sushil", Symbol.X);
        this.board.makeMove(0, 2, sushil);
        this.board.makeMove(1, 1, sushil);
        this.board.makeMove(2, 0, sushil);
        Assertions.assertEquals(true, sushil.isWinner());
    }
}
