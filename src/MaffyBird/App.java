package MaffyBird;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame {
    private JPanel contentPane;
    private CardLayout cardLayout;



    App() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MaffyBird");
        Menu menu = new Menu();
        MaffyBird maffyBird = new MaffyBird();

        cardLayout = new CardLayout();
        contentPane = new JPanel(cardLayout);
        setContentPane(contentPane);

        contentPane.add(maffyBird, "Game");
        contentPane.add(menu, "Menu");

        cardLayout.show(contentPane, "Menu");
        setVisible(true);
        pack();
        setLocationRelativeTo(null);

        maffyBird.back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPane, "Menu");
                menu.updateHighScore(maffyBird.highScore);
            }
        });

        menu.start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPane, "Game");
                maffyBird.requestFocusInWindow();
                maffyBird.reset();
            }
        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                App app = new App();
                app.setVisible(true);
            }
        });
    }
}
