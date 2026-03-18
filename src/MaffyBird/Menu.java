package MaffyBird;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {
    int boardWidth = 360;
    int boardHeight = 640;
    JButton start;
    Image backgroundImg;
    Image highScoreImg;
    double highScore;

    Menu() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setLayout(null);
        setFocusable(true);
        highScoreImg = new ImageIcon(getClass().getResource("/MaffyBird/BasicTheme/trophy.png")).getImage();
        backgroundImg = new ImageIcon(getClass().getResource("/MaffyBird/BasicTheme/menuPng.png")).getImage();
        start = new JButton();
        start.setOpaque(false);
        start.setContentAreaFilled(false);
        start.setBorderPainted(false);
        start.setFocusPainted(false);
        add(start);
        start.setBounds(boardWidth / 2 - 65, 430, 130, 60);
        this.highScore = MaffyBird.loadHighScore();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImg, 0, 0, boardWidth, boardHeight, null);
        g.drawImage(highScoreImg, 70, 180, 64, 64, null);
        g.setFont(new Font("Arial", Font.BOLD, 25));
        g.setColor(Color.WHITE);
        g.drawString(":" + (int) highScore + " points", 120, 240);
    }

    public void updateHighScore(double score) {
        this.highScore = score;
        repaint();
    }
}
