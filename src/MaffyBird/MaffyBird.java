package MaffyBird;

import java.awt.*;
import java.awt.event.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class MaffyBird extends JPanel implements KeyListener {
    int boardWidth = 360;
    int boardHeight = 640;

    Image backgroundImg;
    Image topPipeImg;
    Image bottomPipeImg;
    Image gameOverImg;
    Image up, mid, down;
    Image messageImg;
    Image meteorImg;
    Image meteorsTimeImg;
    Image backImg;

    int birdX = boardWidth / 8;
    int birdY = boardHeight / 2;
    int birdWidth = 34;
    int birdHeight = 24;

    public class Bird {
        int x = birdX;
        int y = birdY;
        int width = birdWidth;
        int height = birdHeight;
        Image img;

        public Bird(Image img) {
            this.img = img;
        }
    }

    int pipeX = boardWidth;
    int pipeY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    public class Pipe {
        int x = pipeX;
        int y = pipeY;
        int width = pipeWidth;
        int height = pipeHeight;
        Image img;
        Boolean passed = false;

        public Pipe(Image img) {
            this.img = img;
        }
    }

    int meteorX = boardWidth;
    int meteorY = 0;
    int meteorWidth = 46;
    int meteorHeight = 46;

    public class Meteor {
        int x = meteorX;
        int y = meteorY;
        int width = meteorWidth;
        int height = meteorHeight;
        Image img;
        int vy;

        public Meteor(Image img, int vy) {
            this.img = img;
            this.vy = vy;
        }
    }

    Bird bird;
    int velocityY = 0;
    int velocityX = -4;
    int gravity = 1;
    int velocityMX = -4;

    ArrayList<Pipe> pipes;
    ArrayList<Meteor> meteors;

    Timer placePipesTimer;
    Timer placeMeteorsTimer;
    Boolean gameOver = false;
    Boolean gameStarted = false;
    double score = 0;
    double highScore = loadHighScore();
    int animFrame = 0;
    int flashTimer = 0;
    boolean showFlash = false;
    JButton back;

    Clip pointSound;
    Clip hitSound;
    Clip dieSound;

    public Clip loadSound(String path) {
        try {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(getClass().getResource(path));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            return clip;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    MaffyBird() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setFocusable(true);
        addKeyListener(this);
        setLayout(null);

        backgroundImg = new ImageIcon(getClass().getResource("/MaffyBird/BasicTheme/flappybirdbg.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("/MaffyBird/BasicTheme/toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("/MaffyBird/BasicTheme/bottompipe.png")).getImage();
        gameOverImg = new ImageIcon(getClass().getResource("/MaffyBird/BasicTheme/gameover.png")).getImage();
        up = new ImageIcon(getClass().getResource("/MaffyBird/BasicTheme/bluebird-upflap.png")).getImage();
        mid = new ImageIcon(getClass().getResource("/MaffyBird/BasicTheme/bluebird-midflap.png")).getImage();
        down = new ImageIcon(getClass().getResource("/MaffyBird/BasicTheme/bluebird-downflap.png")).getImage();
        messageImg = new ImageIcon(getClass().getResource("/MaffyBird/BasicTheme/message.png")).getImage();
        meteorImg = new ImageIcon(getClass().getResource("/MaffyBird/BasicTheme/meteor.png")).getImage();
        meteorsTimeImg = new ImageIcon(getClass().getResource("/MaffyBird/BasicTheme/meteorsTime.png")).getImage();
        backImg = new ImageIcon(getClass().getResource("/MaffyBird/BasicTheme/menu.png")).getImage();

        bird = new Bird(mid);
        pipes = new ArrayList<Pipe>();
        meteors = new ArrayList<Meteor>();
        back = new JButton();
        back.setBounds(20, 50, 32, 32);
        back.setOpaque(false);
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setFocusPainted(false);
        add(back);

        placePipesTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });

        placeMeteorsTimer = new Timer(3000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                placeMeteors();
            }
        });

        pointSound = loadSound("/MaffyBird/Sounds/point.wav");
        hitSound = loadSound("/MaffyBird/Sounds/hit.wav");
        dieSound = loadSound("/MaffyBird/Sounds/die.wav");

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

    }

    public void placePipes() {
        int randomPipeY = (int) (pipeY - pipeHeight / 4 - Math.random() * (pipeHeight / 2));
        int openingSpace = boardHeight / 4;

        Pipe topPipe = new Pipe(topPipeImg);
        topPipe.y = randomPipeY;
        pipes.add(topPipe);

        Pipe bottomPipe = new Pipe(bottomPipeImg);
        bottomPipe.y = topPipe.y + pipeHeight + openingSpace;
        pipes.add(bottomPipe);
    }

    public void placeMeteors() {
        int randomY = (int) (Math.random() * boardHeight * 0.6);
        int randomVY = (int) (1 + Math.random() * 3);
        Meteor meteor = new Meteor(meteorImg, randomVY);
        meteor.y = randomY;
        meteors.add(meteor);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, boardWidth, boardHeight, null);
        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null);
        }
        for (int i = 0; i < meteors.size(); i++) {
            Meteor meteor = meteors.get(i);
            g.drawImage(meteor.img, meteor.x, meteor.y, meteor.width, meteor.height, null);
        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 32));

        if (!gameOver && gameStarted) {
            g.drawString("Score: " + String.valueOf((int) score), 10, 35);
            g.drawString("Best: " + (int) highScore, 220, 35);
        }

        if (!gameStarted) {
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Press SPACE to start", 80, boardHeight / 2 + 93);
            g.drawImage(messageImg, 75, 100, null);
            g.drawImage(backImg, 20, 50, 32, 32, null);
        }
        if (gameOver) {
            g.drawString("Game Over: " + String.valueOf((int) score), 10, 35);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString("Press R to restart", 100, boardHeight / 2 + 93);
            g.drawImage(gameOverImg, 80, boardHeight / 2 - 20, null);
            g.drawImage(backImg, 20, 50, 32, 32, null);
        }
        if (showFlash) {
            g.drawImage(meteorsTimeImg, -25, 100, null);
        }
    }

    public void move() {
        if (gameOver || !gameStarted) return;

        velocityY += gravity;
        bird.y = bird.y + velocityY;
        bird.y = Math.max(bird.y, 0);

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.x += velocityX;

            if (!pipe.passed && bird.x > pipe.x + pipe.width) {
                pipe.passed = true;
                score += 0.5;
                pointSound.setFramePosition(0);
                pointSound.start();
            }

            if (collision(bird, pipe) && !gameOver) {
                gameOver = true;
                hitSound.setFramePosition(0);
                hitSound.start();
                dieSound.setFramePosition(0);
                dieSound.start();
                if (score > highScore) {
                    highScore = score;
                    saveHighScore(highScore);
                }
            }

            if (pipe.x + pipe.width < 0) {
                pipes.remove(i);
                i--;
            }
        }
        if (score >= 5 && !placeMeteorsTimer.isRunning()) {
            placeMeteorsTimer.start();
            flashTimer = 180;
        }

        if (flashTimer > 0) {
            flashTimer--;
            showFlash = (flashTimer / 30) % 2 == 0;
        } else {
            showFlash = false;
        }

        for (int i = 0; i < meteors.size(); i++) {
            Meteor m = meteors.get(i);
            m.x += velocityMX;
            m.y += m.vy;

            if (m.x + m.width < 0 || m.y > boardHeight) {
                meteors.remove(i);
                i--;
                continue;
            }
            if (collision(bird, m) && !gameOver) {
                gameOver = true;
                hitSound.setFramePosition(0);
                hitSound.start();
                dieSound.setFramePosition(0);
                dieSound.start();
                if (score > highScore) {
                    highScore = score;
                    saveHighScore(highScore);
                }
            }
        }

        if (bird.y > boardHeight) {
            gameOver = true;
            dieSound.setFramePosition(0);
            dieSound.start();
            if (score > highScore) {
                highScore = score;
                saveHighScore(highScore);
            }
        }
        animFrame = (animFrame + 1) % 3;
        switch (animFrame) {
            case 0:
                bird.img = mid;
                break;
            case 1:
                bird.img = up;
                break;
            case 2:
                bird.img = down;
                break;
        }
    }

    public boolean collision(Bird a, Pipe b) {
        return a.x < b.x + b.width &&
                a.x + a.width > b.x &&
                a.y < b.y + b.height &&
                a.y + a.height > b.y;
    }

    public boolean collision(Bird a, Meteor b) {
        return a.x < b.x + b.width &&
                a.x + a.width > b.x &&
                a.y < b.y + b.height &&
                a.y + a.height > b.y;
    }

    boolean spacePressed = false;

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !spacePressed) {
            spacePressed = true;

            if (!gameStarted) {
                gameStarted = true;
                placePipesTimer.start();
            }
            velocityY = -9;
        }
        if (e.getKeyCode() == KeyEvent.VK_R) {
            if (gameOver) {
                reset();
            }
        }
    }

    public void reset() {
        bird.y = birdY;
        velocityY = 0;
        pipes.clear();
        meteors.clear();
        score = 0;
        flashTimer = 0;
        showFlash = false;
        gameOver = false;
        gameStarted = false;
        placePipesTimer.stop();
        placeMeteorsTimer.stop();
    }

    public void saveHighScore(double score) {
        try {
            String path = System.getProperty("user.home") + "/MaffyBird_highscore.txt";
            Files.writeString(Path.of(path), String.valueOf((int) score));
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static double loadHighScore() {
        try {
            String path = System.getProperty("user.home") + "/MaffyBird_highscore.txt";
            return Double.parseDouble(Files.readString(Path.of(path)));
        } catch (Exception e) { return 0; }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
            spacePressed = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
