package MaffyBird;
import javax.swing.*;

public class App {
    public static void main(String[] args)
    {
        int boardwidth = 360;
        int boardheight = 640;

        JFrame frame = new JFrame("Maffy Bird");
        frame.setVisible(true);
        frame.setSize(boardwidth, boardheight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MaffyBird flappyBird = new MaffyBird();
        frame.add(flappyBird);
        frame.pack();
        flappyBird.requestFocus();
        frame.setVisible(true);
    }
}
