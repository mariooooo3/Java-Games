package MaffyBird;
import Tic_Tac_Toe.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        Menu menu = new Menu();
        MaffyBird maffyBird = new MaffyBird();
        add(maffyBird);
        pack();
        maffyBird.requestFocus();

        cardLayout = new CardLayout();
        contentPane = new JPanel(cardLayout);
        setContentPane(contentPane);

        contentPane.add(maffyBird, "Game");
        contentPane.add(menu, "Menu");

        cardLayout.show(contentPane, "Menu");
        setVisible(true);

        menu.start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPane, "Game");
                maffyBird.requestFocusInWindow();
            }
        });
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                App app = new App();
                app.setVisible(true);
            }
        });
    }
}
