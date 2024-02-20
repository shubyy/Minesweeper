import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardTest {
    @Test
    public void testBoardCreateBoard() {
        Board board = new Board(100, 200);
        Assertions.assertEquals(board.GetWidth(), 100);
        Assertions.assertEquals(board.GetHeight(), 200);

        Assertions.assertNotNull( board.GetCell(0, 0) );
        Assertions.assertNotNull( board.GetCell(99, 199) );

        Assertions.assertNull( board.GetCell(-2, -2) );
        Assertions.assertNull( board.GetCell(103, 205) );
    }
}
