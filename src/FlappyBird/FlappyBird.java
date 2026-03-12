package FlappyBird;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Random;

public class FlappyBird extends JPanel implements ActionListener, KeyListener{
    int boardWidth = 360;
    int boardHeight = 640;

    Image backgroundImg;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg;

    int birdX = boardWidth/8;
    int birdY = boardHeight/2;
    int birdWidth = 34;
    int birdHeight = 24;

    public class Bird
    {
        int x = birdX;
        int y = birdY;
        int width = birdWidth;
        int height = birdHeight;
        Image img;

        public Bird(Image img)
        {
            this.img = img;
        }
    }

    int pipeX = boardWidth;
    int pipeY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    public class Pipe{
        int x = pipeX;
        int y = pipeY;
        int width = pipeWidth;
        int height = pipeHeight;
        Image img;
        Boolean passed = false;

        public Pipe(Image img)
        {
            this.img = img;
        }
    }

    Bird bird;
    int velocityY = 0;
    int veloictyX = -4;
    int gravity = 1;

    ArrayList<Pipe> pipes;
    Random rand = new Random();

    Timer gameLoopTimer;
    Timer placePipesTimer;



    FlappyBird()
    {
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
            }});

        placePipesTimer.start();

        gameLoopTimer = new Timer(1000/60, this);
        gameLoopTimer.start();

    }

    public void placePipes()
    {
        int randomPipeY = (int) (pipeY-pipeHeight/4 - Math.random()*(pipeHeight/2));
        int openingSpace = boardHeight/4;

        Pipe topPipe = new Pipe(topPipeImg);
        topPipe.y = randomPipeY;
        pipes.add(topPipe);

        Pipe bottomPipe = new Pipe(bottomPipeImg);
        bottomPipe.y = topPipe.y + pipeHeight + openingSpace;
        pipes.add(bottomPipe);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g)
    {
        g.drawImage(backgroundImg, 0, 0,  boardWidth, boardHeight, null);
        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);
        for(int i = 0; i< pipes.size(); i++)
        {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null);
        }
    }

    public void move()
    {
        velocityY += gravity;
        bird.y = bird.y + velocityY;
        bird.y = Math.max(bird.y, 0);

        for(int i = 0; i< pipes.size(); i++)
        {
            Pipe pipe = pipes.get(i);
            pipe.x += veloictyX;

            if(pipe.x + pipe.width < 0)
            {
                pipes.remove(i);
                i--;
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            velocityY = -9;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
