
public class Cell {
    private boolean isMine;
    private boolean revealed;
    private boolean flagged;
    private int nearbyMines;

    public Cell() {
        isMine = false;
        nearbyMines = 0;
        flagged = false;
        revealed = false;
    }

    public void SetMine() {
        isMine = true;
    }

    public boolean IsMine() {
        return isMine;
    }

    public boolean IsFlagged() {
        return flagged;
    }

    public boolean IsRevealed() {
        return revealed;
    }

    public void ToggleFlag() {
        flagged = !flagged;
    }

    public int GetNumberOfNearbyMines() {
        return nearbyMines;
    }

    public void SetNearbyMines(int num) {
        nearbyMines = num;
    }

    public void Reveal() {
        revealed = true;
    }

}
