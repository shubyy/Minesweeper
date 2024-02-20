import java.util.Random;

public class Minesweeper {
    private Board board;
    private int m_numMines;
    private boolean m_allCellsRevealed;
    private boolean m_mineRevealed;
    private boolean m_playing;
    private Random r;

    public void StartGame(int x, int y) {
        //Create random mines
        SetRandomMines(x, y);
        //Save number of nearby mines for each cell
        for(int i = 0; i < board.GetHeight(); i++) {
            for(int j = 0; j < board.GetWidth(); j++) {
                int nearbyMines = GetNumberOfNearbyMines(j, i);
                Cell cell = board.GetCell(j, i);
                cell.SetNearbyMines(nearbyMines);
            }
        }

        m_playing = true;
    }

    public void ToggleFlag(int x, int y) {
        Cell flaggedCell = board.GetCell(x, y);
        flaggedCell.ToggleFlag();
    }

    private void SetRandomMines(int x, int y) {
        int width = board.GetWidth();
        int height = board.GetHeight();

        for(int i = 0; i < m_numMines; i++) {
            boolean set = false;
            do {
                int randomX = r.nextInt(0, width);
                int randomY = r.nextInt(0, height);
                if(randomX != x && randomY != y) {
                    Cell cell = board.GetCell(randomX, randomY);
                    if(!cell.IsMine()) {
                        cell.SetMine();
                        set = true;
                    }
                }
            } while(!set);
        }
    }

    private int GetNumberOfNearbyMines(int x, int y) {
        int totalMines = 0;
        for(int i = (x - 1); i <= (x + 1); i++) {
            for(int j = (y - 1); j <= (y + 1); j++) {
                Cell adjacentCell = board.GetCell(i, j);
                if (adjacentCell != null && adjacentCell.IsMine()) {
                    totalMines += 1;
                }
            }
        }
        return totalMines;
    }

    public Cell GetCell(int x, int y) {
        return board.GetCell(x, y);
    }

    private void RevealRegionOfCell(int x, int y) {
        for(int i = (x - 1); i <= (x + 1); i++) {
            for(int j = (y - 1); j <= (y + 1); j++) {
                Cell cell = board.GetCell(i, j);
                if(cell != null && !cell.IsMine() && !cell.IsRevealed()) {
                    cell.Reveal();
                    int nearby = cell.GetNumberOfNearbyMines();
                    if(nearby == 0) {
                        RevealRegionOfCell(i, j);
                    }
                }
            }
        }
    }

    public boolean MineRevealed() {
        return m_mineRevealed;
    }

    public boolean CellsRevealed() {
        return m_allCellsRevealed;
    }

    public void PlayCell(int x, int y) {
        if(!m_playing) {
            StartGame(x, y);
        }

        Cell playedCell = board.GetCell(x, y);
        if(playedCell != null && !playedCell.IsFlagged()) {
            if(playedCell.IsMine()) {
                playedCell.Reveal();
                m_mineRevealed = true;
            }
            else {
                int nearbyMines = GetNumberOfNearbyMines(x, y);
                if(nearbyMines == 0) {
                    RevealRegionOfCell(x, y);
                } else {
                    playedCell.Reveal();
                }
                CheckAllRevealed();
            }
        }
    }

    public void CheckAllRevealed() {
        for(int i = 0; i < board.GetHeight(); i++) {
            for(int j = 0; j < board.GetWidth(); j++) {
                Cell cell = board.GetCell(j, i);
                if(!cell.IsMine() && !cell.IsRevealed()) {
                    return;
                }
            }
        }

        m_allCellsRevealed = true;
    }

    public int MinesLeft() {
        int total = 0;
        for(int i = 0; i < board.GetHeight(); i++) {
            for(int j = 0; j < board.GetWidth(); j++) {
                Cell cell = board.GetCell(j, i);
                if(cell.IsMine()) {
                    total += 1;
                }
            }
        }
        return total;
    }

    public int GetBoardWidth() {
        return board.GetWidth();
    }

    public int GetBoardHeight() {
        return board.GetHeight();
    }

    public Minesweeper(int width, int height, int numMines) {
        board = new Board(width, height);
        m_numMines = numMines;
        m_playing = false;
        r = new Random();
    }
}
