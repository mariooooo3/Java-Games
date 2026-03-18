package MaffyBird;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {
    int boardWidth = 360;
    int boardHeight = 640;
    JButton start;
    Image backgroundImg;

    Menu()
    {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setLayout(null);
        setFocusable(true);
        backgroundImg = new ImageIcon(getClass().getResource("/MaffyBird/BasicTheme/menuPng.png")).getImage();
        start = new JButton();
        start.setOpaque(false);
        start.setContentAreaFilled(false);
        start.setBorderPainted(false);
        start.setFocusPainted(false);
        add(start);
        start.setBounds(boardWidth/2 - 65, 430, 130, 60);

    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(backgroundImg, 0, 0, boardWidth, boardHeight, null);
    }
}
