import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class MinesweeperUI implements ActionListener {
    private Minesweeper game;
    private JFrame minesweeperFrame;
    private JPanel buttonPanel;
    public MinesweeperUI(int width, int height, int numMines) {
        game = new Minesweeper(width, height, numMines);

        minesweeperFrame = new JFrame("Minesweeper");
        minesweeperFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttonPanel = new JPanel();
        JPanel containerPanel = new JPanel();
        GridLayout glayout = new GridLayout(height, width);
        GridLayout textLayout = new GridLayout(1, 4);
        glayout.setHgap(2);
        glayout.setVgap(2);
        buttonPanel.setLayout(glayout);
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                JCellButton cellButton = new JCellButton(j, i);
                cellButton.setBorder(null);
                cellButton.setBackground(Color.GRAY);
                cellButton.addActionListener(this);
                cellButton.setForeground(Color.BLACK);
                cellButton.setFont(new Font("Arial", Font.PLAIN, 30));
                buttonPanel.add(cellButton);
            }
        }

        buttonPanel.setPreferredSize(new Dimension(30 * width, 30 * height));
        containerPanel.add(buttonPanel);
        UpdateBoard();

        minesweeperFrame.getContentPane().add(containerPanel);
        minesweeperFrame.pack();
        minesweeperFrame.setVisible(true);
    }

    public void UpdateBoard() {
        int height = game.GetBoardHeight();
        int width = game.GetBoardWidth();

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                Cell gameCell = game.GetCell(j, i);
                int index = GetIndexFromPos(j, i);
                JCellButton cellButton = (JCellButton) buttonPanel.getComponent( index );

                cellButton.setText( "" );
                if(gameCell.IsRevealed()) {
                    if(gameCell.IsMine()) {
                        cellButton.setBackground(Color.RED);
                        cellButton.setText( "X" );
                    }
                    else {
                        cellButton.setBackground(Color.WHITE);
                        int numMines = gameCell.GetNumberOfNearbyMines();
                        cellButton.setText( Integer.toString(numMines));
                        switch(numMines) {
                            case 0:
                                cellButton.setForeground(Color.BLACK);
                                break;
                            case 1:
                                cellButton.setForeground(Color.BLUE);
                                break;
                            case 2:
                                cellButton.setForeground(Color.GREEN);
                                break;
                            default:
                                cellButton.setForeground(Color.RED);
                                break;
                        }
                    }
                } else if(gameCell.IsFlagged()) {
                    cellButton.setText( "F" );
                }

            }
        }

        if(game.MineRevealed()) {
            EndGame();
            minesweeperFrame.dispatchEvent(new WindowEvent(minesweeperFrame, WindowEvent.WINDOW_CLOSING));
        } else if(game.CellsRevealed()) {
            WinGame();
            minesweeperFrame.dispatchEvent(new WindowEvent(minesweeperFrame, WindowEvent.WINDOW_CLOSING));
        }

    }

    public void EndGame() {
        JOptionPane.showMessageDialog(null, "You stepped on a mine!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }

    public void WinGame() {
        JOptionPane.showMessageDialog(null, "You revealed all the mines!", "You Won!", JOptionPane.INFORMATION_MESSAGE);
    }

    private int GetIndexFromPos(int x, int y) {
        int width = game.GetBoardWidth();
        if(x < 0 || x >= width || y < 0 || y >= game.GetBoardHeight()) {
            return -1;
        } 
        return y * width + x;
    }

    public void actionPerformed(ActionEvent e) {
        JCellButton button = (JCellButton) e.getSource();
        int modifier = e.getModifiers();
        if((modifier & ActionEvent.SHIFT_MASK) > 0) {
            game.ToggleFlag(button.x, button.y);
        } else {
                game.PlayCell(button.x, button.y);
        }
        UpdateBoard();
    }
    
}
