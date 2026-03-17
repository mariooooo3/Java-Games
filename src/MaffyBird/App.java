package MaffyBird;
import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    private JPanel contentPane;
    private CardLayout cardLayout;


    App()
    {
        int boardwidth = 360;
        int boardheight = 640;

        setVisible(true);
        setSize(boardwidth, boardheight);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MaffyBird flappyBird = new MaffyBird();
        add(flappyBird);
        pack();
        flappyBird.requestFocus();
        setVisible(true);

        cardLayout = new CardLayout();
        contentPane = new JPanel(cardLayout);
        setContentPane(contentPane);
    }
}
