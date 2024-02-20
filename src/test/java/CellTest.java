import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class CellTest {
    @Test
    public void testCellReveal() {
        Cell testCell = new Cell();
        Assertions.assertFalse(testCell.IsRevealed());
        testCell.Reveal();
        Assertions.assertTrue(testCell.IsRevealed());
    }

    @Test
    public void testCellMine() {
        Cell testCell = new Cell();
        Assertions.assertFalse(testCell.IsMine());
        testCell.SetMine();
        Assertions.assertTrue(testCell.IsMine());
    }
}
