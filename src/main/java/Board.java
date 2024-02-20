import java.util.ArrayList;

public class Board {
    ArrayList<Cell> CellBoard = new ArrayList<Cell>();
    private int m_width;
    private int m_height;

    public void CreateBoard(int width, int height) {
        int totalCells = width * height;
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                CellBoard.add( new Cell() );
            }
        }

        this.m_width = width;
        this.m_height = height;
    }

    private int GetIndexFromPos(int x, int y) {
        if(x < 0 || x >= m_width || y < 0 || y >= m_height) {
            return -1;
        } 

        return y * m_width + x;
    }

    public Cell GetCell(int x, int y) {
        int index = GetIndexFromPos(x, y);
        if(index != -1) {
            return CellBoard.get(index);
        }
        else {
            return null;
        }

    }

    public Board(int width, int height) {
        CreateBoard(width, height);
    }

    public int GetWidth() {
        return m_width;
    }

    public int GetHeight() {
        return m_height;
    }
}
