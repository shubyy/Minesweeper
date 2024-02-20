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

    @Test
    public void testCellFlag() {
        Cell testCell = new Cell();
        testCell.ToggleFlag();
        Assertions.assertTrue(testCell.IsFlagged());
        testCell.ToggleFlag();
        Assertions.assertFalse(testCell.IsFlagged());
    }

    @Test
    public void testCellNearbyMines() {
        Cell testCell = new Cell();
        Assertions.assertEquals(testCell.GetNumberOfNearbyMines(), 0);
        testCell.SetNearbyMines(5);
        Assertions.assertEquals(testCell.GetNumberOfNearbyMines(), 5);
    }
}
