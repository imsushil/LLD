import com.systemdesign.Player;
import com.systemdesign.Symbol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setup() {
        this.player = new Player("Sushil", Symbol.X);
    }

    @Test
    void isWinnerTest() {
        this.player.setWinner(true);
        assertTrue(this.player.isWinner());
    }

    @Test
    void getSymbolTest() {
        assertEquals(Symbol.X, player.getSymbol());
    }
}