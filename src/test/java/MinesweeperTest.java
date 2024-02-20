import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MinesweeperTest {
    @Test
    public void testGameMines() {
        Minesweeper game = new Minesweeper(20, 20, 10);
        game.StartGame(-1, -1);
        Assertions.assertEquals(game.MinesLeft(), 10);
    }

    @Test
    public void testGameDimensions() {
        Minesweeper game = new Minesweeper(20, 20, 10);
        Assertions.assertEquals(game.GetBoardWidth(), 20);
        Assertions.assertEquals(game.GetBoardHeight(), 20);
        Assertions.assertNotNull(game.GetCell(0,0));
        Assertions.assertNotNull(game.GetCell(19,19));
        Assertions.assertNull(game.GetCell(21,5));
        Assertions.assertNull(game.GetCell(1,-5));
    }

    @Test
    public void testFlagCell() {
        Minesweeper game = new Minesweeper(20, 20, 10);
        game.StartGame(-1, -1);
        Cell flaggedCell = game.GetCell(5, 5);
        Assertions.assertFalse(flaggedCell.IsFlagged());
        game.ToggleFlag(5, 5);
        Assertions.assertTrue(flaggedCell.IsFlagged());
    }

    @Test
    public void testMineRevealed() {
        Minesweeper game = new Minesweeper(20, 20, 10);
        game.StartGame(-1, -1);
        Cell mineCell = game.GetCell(5, 5);
        mineCell.SetMine();
        game.PlayCell(5, 5);
        Assertions.assertTrue( game.MineRevealed() );
    }

    @Test
    public void testAllCellsRevealed() {
        int width = 10;
        int height = 10;
        Minesweeper game = new Minesweeper(width, height, 25);
        game.StartGame(-1, -1);

        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                Cell current = game.GetCell(i, j);
                if(!current.IsMine() && !current.IsRevealed()) {
                    Assertions.assertFalse(game.CellsRevealed());
                    game.PlayCell(i, j);
                }
            }
        }

        Assertions.assertTrue(game.CellsRevealed());
    }

    @Test
    public void testNearbyMines() {
        Minesweeper game = new Minesweeper(5, 5, 0);
        Cell mineCell = game.GetCell(2, 2);
        mineCell.SetMine();
        game.StartGame(-1, -1);

        Cell nextCell = game.GetCell(1, 2);
        Assertions.assertEquals(nextCell.GetNumberOfNearbyMines(), 1);
    }
}
