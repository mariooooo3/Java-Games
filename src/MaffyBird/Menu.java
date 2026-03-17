package MaffyBird;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {
    int boardWidth = 360;
    int boardHeight = 640;
    JButton start;
    Image backgreoundImg;

    Menu()
    {
        setVisible(true);
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setFocusable(true);
        backgreoundImg = new ImageIcon(getClass().getResource("/MaffyBird/BasicTheme/flappybirdbg.png")).getImage();
        start = new JButton("Start");
        start.setBounds(boardWidth/2, boardHeight/2, 10, 10);

    }
}
