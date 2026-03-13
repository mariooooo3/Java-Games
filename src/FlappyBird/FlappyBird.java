package FlappyBird;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.util.Random;

public class FlappyBird extends JPanel implements KeyListener {
    int boardWidth = 360;
    int boardHeight = 640;

    Image backgroundImg;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg;

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

    Bird bird;
    int velocityY = 0;
    int velocityX = -4;
    int gravity = 1;

    ArrayList<Pipe> pipes;

    Timer placePipesTimer;
    Boolean gameOver = false;
    Boolean gameStarted = false;
    double score = 0;

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

    FlappyBird() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setFocusable(true);
        addKeyListener(this);

        backgroundImg = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();

        bird = new Bird(birdImg);
        pipes = new ArrayList<Pipe>();

        placePipesTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });

        pointSound = loadSound("/FlappyBird/Sounds/point.wav");
        hitSound = loadSound("/FlappyBird/Sounds/hit.wav");
        dieSound = loadSound("/FlappyBird/Sounds/die.wav");

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
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 32));

        if (!gameOver && gameStarted)
            g.drawString("Score: " + String.valueOf((int) score), 10, 35);

        if (!gameStarted) {
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Press SPACE to start", 80, boardHeight / 2 + 50);
        }
        if (gameOver) {
            g.drawString("Game Over: " + String.valueOf((int) score), 10, 35);
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString("Press R to restart", 100, boardHeight / 2 + 50);
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
            }

            if (pipe.x + pipe.width < 0) {
                pipes.remove(i);
                i--;
            }
        }

        if (bird.y > boardHeight) {
            gameOver = true;
            dieSound.setFramePosition(0);
            dieSound.start();
        }
    }

    public boolean collision(Bird a, Pipe b) {
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
                bird.y = birdY;
                velocityY = 0;
                pipes.clear();
                score = 0;
                gameOver = false;
                gameStarted = false;
                placePipesTimer.stop();
            }
        }
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
