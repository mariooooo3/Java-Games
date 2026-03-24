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
    JButton restart;

    Image messageImg;Image cloudImg;Image birdImg;Image birdDeadImg;
    Image trackImg;Image dinoImg;Image dinoJumpImg;Image dinoRunImg;
    Image volcanoImg;Image dinoDuckImg;Image dinoDeadImg;Image cactus1Img;
    Image cactus2Img;Image cactus3Img;Image bigCactus1Img;Image bigCactus2Img;
    Image bigCactus3Img;Image gameOverImg;Image restartImg;Image gasStationImg;

    Timer gameLoopTrack;
    Timer placeClouds;
    Timer placeVolcanoes;
    Timer placeCactus;

    Boolean gameOver = false;
    Boolean gameStarted = false;
    Boolean duckIsPressed = false;
    Boolean message = true;
    Boolean gasStation = false;

    int trackWidth = 2404;
    int trackHeight = 28;
    int trackX = 0;
    int trackY = boardHeight - trackHeight;
    int trackVelocityX = -9;

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

        public Volcano(Image img) {
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

    int blockVelocityX = -9;
    int gasStationX = 100;

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
        birdImg = new ImageIcon(getClass().getResource("/ChromeDino/bird.gif")).getImage();
        birdDeadImg = new ImageIcon(getClass().getResource("/ChromeDino/bird1.png")).getImage();
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
        dinoDeadImg = new ImageIcon(getClass().getResource("/ChromeDino/dino-dead.png")).getImage();
        dinoDuckImg = new ImageIcon(getClass().getResource("/ChromeDino/dino-duck.gif")).getImage();
        gameOverImg = new ImageIcon(getClass().getResource("/ChromeDino/game-over.png")).getImage();
        restartImg = new ImageIcon(getClass().getResource("/ChromeDino/reset.png")).getImage();
        gasStationImg = new ImageIcon(getClass().getResource("/ChromeDino/gasStation.png")).getImage();

        dino = new Dino(dinoImg);
        track = new Track(trackImg);
        tracks.add(track);
        cloud = new Cloud(cloudImg);
        clouds.add(cloud);
        volcano = new Volcano(volcanoImg);
        volcanoes.add(volcano);

        restart = new JButton();
        restart.setBounds(boardWidth/2-30,boardHeight/2+10,60,50);
        restart.setVisible(false);
        add(restart);
        restart.setOpaque(false);
        restart.setContentAreaFilled(false);
        restart.setBorderPainted(false);
        restart.setFocusPainted(false);
        restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

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

        gameLoopTrack = new Timer(1000, new ActionListener() {
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
                trackVelocityX--;
                blockVelocityX--;
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
        for (Volcano volcano : volcanoes) {
            g.drawImage(volcano.img, volcano.x, volcano.y, volcano.width, volcano.height, null);
        }
        if(gasStation) {
            g.drawImage(gasStationImg, gasStationX, 10, 600, 267, null);
        }
        if (message)
            g.drawImage(messageImg, 120, -40, 500, 333, null);

        for (Track track : tracks) {
            g.drawImage(track.img, track.x, track.y, track.width, track.height, null);
        }

        for (Cloud cloud : clouds) {
            g.drawImage(cloud.img, cloud.x, cloud.y, cloud.width, cloud.height, null);
        }

        for (Block block : cactusList) {
            g.drawImage(block.img, block.x, block.y, block.width, block.height, null);
        }

        g.drawImage(dino.img, dino.x, dino.y, dino.width, dino.height, null);
        if (gameOver) {
            g.drawImage(gameOverImg, boardWidth / 4, boardHeight / 4, 386, 40, null);
            g.drawImage(restartImg, boardWidth / 2 - 40, boardHeight / 2, 76, 68, null);
            restart.setVisible(true);
        }
    }

    public void move() {
        if (gameOver || !gameStarted)
            return;

        int groundY = duckIsPressed ? dinoY + (dinoHeight - 60) : dinoY;

        gasStationX += -2;
        dinoVelocityY += dinoGravity;
        dino.y += dinoVelocityY;

        if (dino.y >= groundY) {
            dino.y = groundY;
            dinoVelocityY = 0;
            if (!duckIsPressed) {
                dino.img = dinoRunImg;
                dino.width = dinoWidth;
                dino.height = dinoHeight;
            } else {
                dino.img = dinoDuckImg;
                dino.width = 118;
                dino.height = 60;
            }
        }

        for (Track track : tracks) {
            track.x += trackVelocityX;
        }

        for (Cloud cloud : clouds) {
            cloud.x += cloudVelocityX;
        }
        for (Volcano volcano : volcanoes) {
            volcano.x += volcanoVelocityX;
        }
        for (Block block : cactusList) {
            block.x += blockVelocityX;

            if (collision(dino, block)) {
                gameOver = true;
                gameStarted = false;
                dino.img = dinoDeadImg;
                for (Block b : cactusList) {
                    if (b.img == birdImg)
                        b.img = birdDeadImg;
                }

                gameLoopTrack.stop();
                placeClouds.stop();
                placeVolcanoes.stop();
                placeCactus.stop();
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
        if (volcanoes.size() > 10)
            volcanoes.remove(0);
    }

    public void placeCactus() {
        Random rand = new Random();
        int choice = rand.nextInt(10);
        switch (choice) {
            case 0:
                Block cactus0 = new Block(2000, 170, 34, 70, cactus1Img);
                cactusList.add(cactus0);
                break;
            case 1:
                Block cactus1 = new Block(2000, 170, 69, 70, cactus2Img);
                cactusList.add(cactus1);
                break;
            case 2:
                Block cactus2 = new Block(2000, 170, 102, 70, cactus3Img);
                cactusList.add(cactus2);
                break;
            case 3:
                Block cactus3 = new Block(2000, 150, 50, 90, bigCactus1Img);
                cactusList.add(cactus3);
                break;
            case 4:
                Block cactus4 = new Block(2000, 150, 103, 90, bigCactus2Img);
                cactusList.add(cactus4);
                break;
            case 5:
                Block cactus5 = new Block(2000, 150, 150, 90, bigCactus3Img);
                cactusList.add(cactus5);
                break;
            case 6:
                Block bird1 = new Block(2000, boardHeight / 4 + 10, 97, 68, birdImg);
                cactusList.add(bird1);
                break;
            case 7:
                Block bird2 = new Block(2000, 150, 97, 68, birdImg);
                cactusList.add(bird2);
                break;
            case 8:
                Block bird3 = new Block(2000, boardHeight / 4 + 50, 97, 68, birdImg);
                cactusList.add(bird3);
                break;
            case 9:
                break;
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

    public void reset()
    {
        trackVelocityX = -9;
        blockVelocityX = -9;
        dino.img = dinoRunImg;
        dino.y = dinoY;
        gameLoopTrack.start();
        placeCactus.start();
        cactusList.clear();
        placeVolcanoes.start();
        volcanoes.clear();
        volcano = new Volcano(volcanoImg);
        volcanoes.add(volcano);
        placeClouds.start();
        clouds.clear();
        restart.setVisible(false);
        gameOver = false;
        gameStarted = true;
        gasStation = true;
        gasStationX = 100;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gameOver)
            return;

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
                dinoVelocityY = -18;
                dino.img = dinoJumpImg;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN && gameStarted) {
            duckIsPressed = true;
        }
        if (duckIsPressed && dino.y == dinoY) {
            dino.img = dinoDuckImg;
            dino.height = 60;
            dino.width = 118;
            dino.y = dinoY + (dinoHeight - 60);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (gameOver)
            return;

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            duckIsPressed = false;
            dino.img = dinoRunImg;
            dino.width = 88;
            dino.height = 94;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}

