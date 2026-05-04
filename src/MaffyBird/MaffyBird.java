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

    public class PowerUp {
        int x = boardWidth;
        int y;
        int width = 30;
        int height = 30;

        public PowerUp(int y) {
            this.y = y;
        }
    }

    Bird bird;
    int velocityY = 0;
    int velocityX = -4;
    int gravity = 1;
    int velocityMX = -4;

    ArrayList<Pipe> pipes;
    ArrayList<Meteor> meteors;
    ArrayList<PowerUp> powerUps;

    Timer placePipesTimer;
    Timer placeMeteorsTimer;
    Timer placePowerUpsTimer;
    Boolean gameOver = false;
    Boolean gameStarted = false;
    double score = 0;
    double highScore = loadHighScore();
    int animFrame = 0;
    int flashTimer = 0;
    boolean showFlash = false;
    boolean newBest = false;
    JButton back;

    boolean shieldActive = false;
    int shieldTimer = 0;
    static final int SHIELD_DURATION = 180;

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
        powerUps = new ArrayList<PowerUp>();
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

        placePowerUpsTimer = new Timer(10000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                placePowerUp();
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

    public void placePowerUp() {
        int y = (int) (boardHeight * 0.15 + Math.random() * boardHeight * 0.55);
        powerUps.add(new PowerUp(y));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.drawImage(backgroundImg, 0, 0, boardWidth, boardHeight, null);

        for (PowerUp pu : powerUps) {
            g2d.setColor(new Color(255, 215, 0, 220));
            g2d.fillOval(pu.x, pu.y, pu.width, pu.height);
            g2d.setColor(new Color(200, 130, 0));
            g2d.setStroke(new BasicStroke(2f));
            g2d.drawOval(pu.x, pu.y, pu.width, pu.height);
            g2d.setColor(Color.WHITE);
            Font starFont = new Font("Arial", Font.BOLD, 15);
            g2d.setFont(starFont);
            FontMetrics fm = g2d.getFontMetrics(starFont);
            String star = "★";
            int sx = pu.x + (pu.width - fm.stringWidth(star)) / 2;
            int sy = pu.y + (pu.height + fm.getAscent() - fm.getDescent()) / 2;
            g2d.drawString(star, sx, sy);
        }

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null);
        }
        for (int i = 0; i < meteors.size(); i++) {
            Meteor meteor = meteors.get(i);
            g.drawImage(meteor.img, meteor.x, meteor.y, meteor.width, meteor.height, null);
        }

        if (shieldActive) {
            int margin = 9;
            g2d.setColor(new Color(0, 150, 255, 70));
            g2d.fillOval(bird.x - margin, bird.y - margin, bird.width + margin * 2, bird.height + margin * 2);
            g2d.setColor(new Color(0, 220, 255, 210));
            g2d.setStroke(new BasicStroke(2.5f));
            g2d.drawOval(bird.x - margin, bird.y - margin, bird.width + margin * 2, bird.height + margin * 2);
        }

        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);

        if (shieldActive && gameStarted && !gameOver) {
            int barW = 80;
            int barH = 7;
            int barX = boardWidth / 2 - barW / 2;
            int barY = 48;
            g2d.setColor(new Color(0, 0, 0, 110));
            g2d.fillRoundRect(barX - 1, barY - 1, barW + 2, barH + 2, 4, 4);
            g2d.setColor(new Color(0, 220, 255));
            int filled = (int) (barW * shieldTimer / (double) SHIELD_DURATION);
            g2d.fillRoundRect(barX, barY, filled, barH, 4, 4);
            g2d.setColor(new Color(0, 220, 255, 160));
            g2d.setFont(new Font("Arial", Font.PLAIN, 11));
            g2d.drawString("SHIELD", barX + barW + 5, barY + barH);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 32));

        if (!gameOver && gameStarted) {
            g.drawString("Score: " + (int) score, 10, 35);
            g.drawString("Best: " + (int) highScore, 220, 35);
        }

        if (!gameStarted) {
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Press SPACE to start", 80, boardHeight / 2 + 93);
            g.drawImage(messageImg, 75, 100, null);
            g.drawImage(backImg, 20, 50, 32, 32, null);
        }

        if (gameOver) {
            g2d.setColor(new Color(0, 0, 0, 130));
            g2d.fillRect(0, 0, boardWidth, boardHeight);

            g.drawImage(gameOverImg, 80, 140, null);

            int panelH = newBest ? 115 : 95;
            int panelY = 300;
            g2d.setColor(new Color(0, 0, 0, 165));
            g2d.fillRoundRect(45, panelY, 270, panelH, 18, 18);
            g2d.setColor(new Color(255, 255, 255, 55));
            g2d.setStroke(new BasicStroke(1.5f));
            g2d.drawRoundRect(45, panelY, 270, panelH, 18, 18);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 26));
            g.drawString("Score: " + (int) score, 65, panelY + 35);

            g.setFont(new Font("Arial", Font.PLAIN, 22));
            g.setColor(new Color(210, 210, 210));
            g.drawString("Best:  " + (int) highScore, 65, panelY + 63);

            if (newBest) {
                g.setFont(new Font("Arial", Font.BOLD, 21));
                g.setColor(new Color(255, 215, 0));
                g.drawString("★ NEW BEST!", 100, panelY + 95);
            }

            int hintY = panelY + panelH + 38;
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString("Press R to restart", 100, hintY);

            g.drawImage(backImg, 20, 50, 32, 32, null);
        }

        if (showFlash && !gameOver) {
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
                triggerGameOver(true);
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
            if (collision(bird, m) && !gameOver && !shieldActive) {
                triggerGameOver(true);
            }
        }

        for (int i = 0; i < powerUps.size(); i++) {
            PowerUp pu = powerUps.get(i);
            pu.x += velocityX;
            if (pu.x + pu.width < 0) {
                powerUps.remove(i);
                i--;
                continue;
            }
            if (collisionPowerUp(bird, pu)) {
                shieldActive = true;
                shieldTimer = SHIELD_DURATION;
                powerUps.remove(i);
                i--;
            }
        }

        if (shieldActive) {
            shieldTimer--;
            if (shieldTimer <= 0) {
                shieldActive = false;
            }
        }

        if (bird.y > boardHeight && !gameOver) {
            triggerGameOver(false);
        }

        animFrame = (animFrame + 1) % 3;
        switch (animFrame) {
            case 0: bird.img = mid; break;
            case 1: bird.img = up; break;
            case 2: bird.img = down; break;
        }
    }

    private void triggerGameOver(boolean playHit) {
        gameOver = true;
        if (playHit) {
            hitSound.setFramePosition(0);
            hitSound.start();
        }
        dieSound.setFramePosition(0);
        dieSound.start();
        if (score > highScore) {
            newBest = true;
            highScore = score;
            saveHighScore(highScore);
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

    public boolean collisionPowerUp(Bird a, PowerUp b) {
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
                placePowerUpsTimer.start();
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
        powerUps.clear();
        score = 0;
        flashTimer = 0;
        showFlash = false;
        gameOver = false;
        gameStarted = false;
        newBest = false;
        shieldActive = false;
        shieldTimer = 0;
        placePipesTimer.stop();
        placeMeteorsTimer.stop();
        placePowerUpsTimer.stop();
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
    public void keyTyped(KeyEvent e) {}
}
