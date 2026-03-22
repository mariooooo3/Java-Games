package ChromeDino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class ChromeDino extends JPanel implements ActionListener, KeyListener {
    public int boardWidth = 750;
    public int boardHeight = 250;
    ArrayList<Track> tracks = new ArrayList<Track>();
    ArrayList<Cloud> clouds = new ArrayList<Cloud>();
    ArrayList<Volcano> volcanoes = new ArrayList<Volcano>();

    Image cloudImg;
    Image trackImg;
    Image dinoImg;
    Image dinoJumpImg;
    Image dinoRunImg;
    Image volcanoImg;

    Timer gameLoop;
    Timer gameLoopTrack;
    Timer placeClouds;
    Timer placeVolcanoes;

    Boolean gameOver = false;
    Boolean gameStarted = false;

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
    int volcanoX = boardWidth;
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
    Volcano vulcano;

    ChromeDino() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setFocusable(true);
        setLayout(null);

        volcanoImg = new ImageIcon(getClass().getResource("/ChromeDino/vulcano.gif")).getImage();
        cloudImg = new ImageIcon(getClass().getResource("/ChromeDino/cloud.png")).getImage();
        trackImg = new ImageIcon(getClass().getResource("/ChromeDino/track.png")).getImage();
        dinoImg = new ImageIcon(getClass().getResource("/ChromeDino/dino.png")).getImage();
        dinoJumpImg = new ImageIcon(getClass().getResource("/ChromeDino/dino-jump.png")).getImage();
        dinoRunImg = new ImageIcon(getClass().getResource("/ChromeDino/dino-run.gif")).getImage();

        dino = new Dino(dinoImg);
        track = new Track(trackImg);
        tracks.add(track);
        cloud = new Cloud(cloudImg);
        clouds.add(cloud);
        vulcano = new Volcano(volcanoImg);
        volcanoes.add(vulcano);

        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();

        gameLoopTrack = new Timer(3500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeTrack();
            }
        });

        placeClouds = new Timer(6000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeClouds();
            }
        });

        placeVolcanoes = new Timer(12000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeVulcanoes();
            }
        });

        addKeyListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g) {
        for(Volcano vulcano : volcanoes) {
            g.drawImage(vulcano.img, vulcano.x, vulcano.y, vulcano.width, vulcano.height, null);
        }
        for (Track track : tracks) {
            g.drawImage(track.img, track.x, track.y, track.width, track.height, null);
        }

        for (Cloud cloud : clouds) {
            g.drawImage(cloud.img, cloud.x, cloud.y, cloud.width, cloud.height, null);
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
            dino.img = dinoRunImg;
        }
        for (Track track : tracks) {
            track.x += trackVelocityX;
        }

        for (Cloud cloud : clouds) {
            cloud.x += cloudVelocityX;
        }
        for(Volcano vulcano : volcanoes) {
            vulcano.x += volcanoVelocityX;
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

    public void placeVulcanoes() {
        Volcano newVolcano = new Volcano(volcanoImg);
        volcanoes.add(newVolcano);
        if(volcanoes.size() > 10)
            volcanoes.remove(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (!gameStarted) {
                gameStarted = true;
                gameLoopTrack.start();
                placeClouds.start();
                placeVolcanoes.start();
                dino.img = dinoRunImg;
                return;
            }
            if (dino.y == dinoY) {
                dinoVelocityY = -17;
                dino.img = dinoJumpImg;
            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}

