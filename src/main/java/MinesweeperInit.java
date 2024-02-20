import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class MinesweeperInit implements ActionListener {
    private JFrame paramFrame;
    private JPanel panel;

    private JTextField widthField;
    private JTextField heightField;
    private JTextField minesField;

    public MinesweeperInit() {
        paramFrame = new JFrame("Choose params");
        panel = new JPanel();
        widthField = new JTextField(10);
        heightField = new JTextField(10);
        minesField = new JTextField(5);
        JLabel label = new JLabel("Width");
        JLabel label2 = new JLabel("Height");
        JLabel label3 = new JLabel("Num Mines");

        FlowLayout layout = new FlowLayout();
        panel.setLayout(layout);

        JButton playButton = new JButton("Play!");
        playButton.addActionListener(this);

        panel.add(label);
        panel.add(widthField);
        panel.add(label2);
        panel.add(heightField);
        panel.add(label3);
        panel.add(minesField);
        panel.add(playButton);
        paramFrame.add(panel);

        paramFrame.getContentPane().add(panel);
        paramFrame.pack();
        paramFrame.setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        String strwidth = widthField.getText();
        String strheight = heightField.getText();
        String strnumMines = minesField.getText();
        int width, height, numMines;

        try {
            width = Integer.parseInt(strwidth);
            height = Integer.parseInt(strheight);
            numMines = Integer.parseInt(strnumMines);

            if(numMines >= (width * height) ) {
                JOptionPane.showMessageDialog(null, "Too many mines!", "Error", JOptionPane.INFORMATION_MESSAGE);
            } else {
                new MinesweeperUI(width, height, numMines);
            }
            
        } catch (NumberFormatException exp) {
            JOptionPane.showMessageDialog(null, "Invalid Value", "Error", JOptionPane.INFORMATION_MESSAGE);
         }
    }
    
}

