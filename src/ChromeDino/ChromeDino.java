package ChromeDino;

import javax.swing.*;
import java.awt.*;

public class ChromeDino extends JPanel {
    public int boardWidth = 750;
    public int boardHeight = 250;

    Image trackImg;
    Image dinoImg;


    int trackWidth = 2404;
    int trackHeight = 28;
    int trackX = 0;
    int trackY = boardHeight - trackHeight;

    int dinoWidth = 88;
    int dinoHeight = 94;
    int dinoX = 50;
    int dinoY = boardHeight - dinoHeight;

    public class Dino
    {
        int x = dinoX;
        int y = dinoY;
        int width = dinoWidth;
        int height = dinoHeight;
        Image img;

        public Dino(Image img)
        {
            this.img = img;
        }
    }

    public class Block
    {
        int x;
        int y;
        int height;
        int width;
        Image img;

        public Block(int x, int y, int width, int height, Image img)
        {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.img = img;
        }
    }

    ChromeDino()
    {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setFocusable(true);
        setLayout(null);

        trackImg = new ImageIcon(getClass().getResource("/ChromeDino/track.png")).getImage();
        dinoImg = new ImageIcon(getClass().getResource("/ChromeDino/dino.png")).getImage();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g)
    {
        g.drawImage(trackImg, trackX, trackY, trackWidth, trackHeight, null);
        g.drawImage(dinoImg, dinoX, dinoY, dinoWidth, dinoWidth, null);
    }
}

