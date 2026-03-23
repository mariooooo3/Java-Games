package ChromeDino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class ChromeDino extends JPanel implements KeyListener {
    public int boardWidth = 750;
    public int boardHeight = 250;
    ArrayList<Track> tracks = new ArrayList<Track>();
    ArrayList<Cloud> clouds = new ArrayList<Cloud>();
    ArrayList<Volcano> volcanoes = new ArrayList<Volcano>();
    ArrayList<Block> cactusList = new ArrayList<Block>();

    Image messageImg;
    Image cloudImg;
    Image trackImg;
    Image dinoImg;
    Image dinoJumpImg;
    Image dinoRunImg;
    Image volcanoImg;
    Image dinoDuckImg;
    Image cactus1Img;
    Image cactus2Img;
    Image cactus3Img;
    Image bigCactus1Img;
    Image bigCactus2Img;
    Image bigCactus3Img;

    Timer gameLoopTrack;
    Timer placeClouds;
    Timer placeVolcanoes;
    Timer placeCactus;

    Boolean gameOver = false;
    Boolean gameStarted = false;
    Boolean duckIsPressed = false;
    Boolean message = true;

    int trackWidth = 2404;
    int trackHeight = 28;
    int trackX = 0;
    int trackY = boardHeight - trackHeight;
    int trackVelocityX = -12;

    public class Track {
        int x = trackX;
        int y = trackY;
        int width = trackWidth;
        int height = trackHeight;
        Image img;

        public Track(Image img) {
            this.img = img;
        }
    }
    int volcanoWidth = 1000;
    int volcanoHeight = 450;
    int volcanoX = 650;
    int volcanoY = -70;
    int volcanoVelocityX = -1;

    public class Volcano {
        int x = volcanoX;
        int y = volcanoY;
        int width = volcanoWidth;
        int height = volcanoHeight;
        Image img;

        public Volcano(Image img)
        {
            this.img = img;
        }
    }


    int dinoWidth = 88;
    int dinoHeight = 94;
    int dinoX = 50;
    int dinoY = boardHeight - dinoHeight;
    int dinoVelocityY = 0;
    int dinoGravity = 1;


    public class Dino {
        int x = dinoX;
        int y = dinoY;
        int width = dinoWidth;
        int height = dinoHeight;
        Image img;

        public Dino(Image img) {
            this.img = img;
        }
    }

    int cloudWidth = 101;
    int cloudHeight = 84;
    int cloudX = boardWidth;
    int cloudY = 20;
    int cloudVelocityX = -3;

    public class Cloud {
        int x = cloudX;
        int y = cloudY;
        int height = cloudHeight;
        int width = cloudWidth;
        Image img;

        public Cloud(Image img) {
            this.img = img;
        }
    }

    int blockVelocityX = -12;
    public class Block {
        int x;
        int y;
        int height;
        int width;
        Image img;

        public Block(int x, int y, int width, int height, Image img) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.img = img;
        }
    }

    Dino dino;
    Track track;
    Cloud cloud;
    Volcano volcano;

    ChromeDino() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setFocusable(true);
        setLayout(null);

        volcanoImg = new ImageIcon(getClass().getResource("/ChromeDino/volcano.gif")).getImage();
        messageImg = new ImageIcon(getClass().getResource("/ChromeDino/message.png")).getImage();
        cloudImg = new ImageIcon(getClass().getResource("/ChromeDino/cloud.png")).getImage();
        trackImg = new ImageIcon(getClass().getResource("/ChromeDino/track.png")).getImage();
        cactus1Img = new ImageIcon(getClass().getResource("/ChromeDino/cactus1.png")).getImage();
        cactus2Img = new ImageIcon(getClass().getResource("/ChromeDino/cactus2.png")).getImage();
        cactus3Img = new ImageIcon(getClass().getResource("/ChromeDino/cactus3.png")).getImage();
        bigCactus1Img = new ImageIcon(getClass().getResource("/ChromeDino/big-cactus1.png")).getImage();
        bigCactus2Img = new ImageIcon(getClass().getResource("/ChromeDino/big-cactus2.png")).getImage();
        bigCactus3Img = new ImageIcon(getClass().getResource("/ChromeDino/big-cactus3.png")).getImage();
        dinoImg = new ImageIcon(getClass().getResource("/ChromeDino/dino.png")).getImage();
        dinoJumpImg = new ImageIcon(getClass().getResource("/ChromeDino/dino-jump.png")).getImage();
        dinoRunImg = new ImageIcon(getClass().getResource("/ChromeDino/dino-run.gif")).getImage();
        dinoDuckImg = new ImageIcon(getClass().getResource("/ChromeDino/dino-duck.gif")).getImage();

        dino = new Dino(dinoImg);
        track = new Track(trackImg);
        tracks.add(track);
        cloud = new Cloud(cloudImg);
        clouds.add(cloud);
        volcano = new Volcano(volcanoImg);
        volcanoes.add(volcano);

        Thread gameThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(16);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                SwingUtilities.invokeLater(() -> {
                    move();
                    repaint();
                });
            }
        });
        gameThread.setDaemon(true);
        gameThread.start();

        gameLoopTrack = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeTrack();
                message = false;
            }
        });

        placeClouds = new Timer(4500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeClouds();
            }
        });

        placeVolcanoes = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeVolcanoes();
            }
        });

        placeCactus = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeCactus();
            }
        });

        addKeyListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g) {
        for(Volcano volcano : volcanoes) {
            g.drawImage(volcano.img, volcano.x, volcano.y, volcano.width, volcano.height, null);
        }
        if(message)
            g.drawImage(messageImg, 120, -40, 500, 333, null);

        for (Track track : tracks) {
            g.drawImage(track.img, track.x, track.y, track.width, track.height, null);
        }

        for (Cloud cloud : clouds) {
            g.drawImage(cloud.img, cloud.x, cloud.y, cloud.width, cloud.height, null);
        }

        for(Block block : cactusList)
        {
            g.drawImage(block.img, block.x, block.y, block.width, block.height, null);
        }

        g.drawImage(dino.img, dino.x, dino.y, dino.width, dino.height, null);
    }

    public void move() {
        if (gameOver || !gameStarted)
            return;

        dinoVelocityY += dinoGravity;
        dino.y += dinoVelocityY;

        if (dino.y >= dinoY) {
            dino.y = dinoY;
            dinoVelocityY = 0;
            if(!duckIsPressed)
                dino.img = dinoRunImg;
        }
        for (Track track : tracks) {
            track.x += trackVelocityX;
        }

        for (Cloud cloud : clouds) {
            cloud.x += cloudVelocityX;
        }
        for(Volcano volcano : volcanoes) {
            volcano.x += volcanoVelocityX;
        }
        for(Block block : cactusList) {
            block.x += blockVelocityX;

            if(collision(dino, block))
            {
                gameOver = true;
            }
        }

    }

    public void placeTrack() {
        Track newTrack = new Track(trackImg);
        tracks.add(newTrack);
        if (tracks.size() > 10)
            tracks.remove(0);
    }

    public void placeClouds() {
        Cloud newCloud = new Cloud(cloudImg);
        Random rand = new Random();
        int vel = rand.nextInt(10);
        newCloud.y += vel;
        clouds.add(newCloud);
        if (clouds.size() > 10)
            clouds.remove(0);
    }

    public void placeVolcanoes() {
        Volcano newVolcano = new Volcano(volcanoImg);
        volcanoes.add(newVolcano);
        if(volcanoes.size() > 10)
            volcanoes.remove(0);
    }

    public void placeCactus()
    {
        Random rand = new Random();
        int choice = rand.nextInt(7);
        switch (choice) {
            case 0: Block cactus0 = new Block(2000, 170, 34, 70, cactus1Img);
            cactusList.add(cactus0);
            break;
            case 1: Block cactus1 = new Block(2000, 170, 69, 70, cactus2Img);
            cactusList.add(cactus1);
            break;
            case 2: Block cactus2 = new Block(2000, 170, 102, 70, cactus3Img);
            cactusList.add(cactus2);
            break;
            case 3: Block cactus3 = new Block(2000, 150, 50, 90, bigCactus1Img);
            cactusList.add(cactus3);
            break;
            case 4: Block cactus4 = new Block(2000, 150, 103, 90, bigCactus2Img);
            cactusList.add(cactus4);
            break;
            case 5: Block cactus5 = new Block(2000, 150, 150, 90, bigCactus3Img);
            cactusList.add(cactus5);
            break;
            case 6: break;
        }
    }

    boolean collision(Dino a, Block b) {
        int dinoPadX = 15;
        int dinoPadY = 10;
        int cactusPadX = 8;
        int cactusPadY = 5;

        return (a.x + dinoPadX) < (b.x + b.width - cactusPadX) &&
                (a.x + a.width - dinoPadX) > (b.x + cactusPadX) &&
                (a.y + dinoPadY) < (b.y + b.height - cactusPadY) &&
                (a.y + a.height - dinoPadY) > (b.y + cactusPadY);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (!gameStarted) {
                gameStarted = true;
                gameLoopTrack.start();
                placeClouds.start();
                placeVolcanoes.start();
                placeCactus.start();
                dino.img = dinoRunImg;
                return;
            }
            if (dino.y == dinoY) {
                dinoVelocityY = -17;
                dino.img = dinoJumpImg;
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN && gameStarted) {
            duckIsPressed = true;
        }
        if(duckIsPressed)
            dino.img = dinoDuckImg;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            duckIsPressed = false;
            dino.img = dinoRunImg;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}

