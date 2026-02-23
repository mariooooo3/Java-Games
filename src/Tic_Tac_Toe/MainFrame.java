package Tic_Tac_Toe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {

    private JPanel contentPane;
    private CardLayout cardLayout;

    public MainFrame() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 540, 540);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        contentPane = new JPanel(cardLayout);
        setContentPane(contentPane);

        BackgroundPanel backgroundPanel =
                new BackgroundPanel("/tic_tac_toe.png");

        backgroundPanel.setLayout(new BorderLayout());

        MenuPanel menuPanel = new MenuPanel();
        menuPanel.setOpaque(false);
        backgroundPanel.add(menuPanel, BorderLayout.CENTER);

        GamePanel gamePanel = new GamePanel();

        contentPane.add(backgroundPanel, "MENU");
        contentPane.add(gamePanel, "GAME");

        cardLayout.show(contentPane, "MENU");

        ImageIcon original = new ImageIcon(getClass().getResource("/smile.png"));

        Image scaled = original.getImage().getScaledInstance(
                32, 32, Image.SCALE_SMOOTH);

        ImageIcon icon = new ImageIcon(scaled);


        menuPanel.startBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!(menuPanel.secondField.getText().isEmpty() || menuPanel.firstField.getText().isEmpty())) {
                    cardLayout.show(contentPane, "GAME");
                    gamePanel.player1.setText(menuPanel.firstField.getText() + "-X");
                    gamePanel.player2.setText(menuPanel.secondField.getText() + "-O");
                } else {
                    System.out.println("Enter players' names");
                    JOptionPane.showMessageDialog(null, "Please enter players' names:",
                            "Missing Names", JOptionPane.ERROR_MESSAGE, icon);
                }
                if (gamePanel.player2.getText().equals("Bot Greg-O") && gamePanel.xo.getText().equals("O Turn")) {
                    gamePanel.autoPlay();
                }
            }
        });

        menuPanel.infiniteMode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!(menuPanel.secondField.getText().isEmpty() || menuPanel.firstField.getText().isEmpty())) {
                    cardLayout.show(contentPane, "GAME");
                    gamePanel.player1.setText(menuPanel.firstField.getText() + "-X");
                    gamePanel.player2.setText(menuPanel.secondField.getText() + "-O");
                    gamePanel.label.setText("Infinite Mode");
                } else {
                    System.out.println("Enter players' names");
                    JOptionPane.showMessageDialog(null, "Please enter players' names:",
                            "Missing Names", JOptionPane.ERROR_MESSAGE, icon);
                }
            }
        });

        gamePanel.back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPane, "MENU");
            }

        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame frame = new MainFrame();
                frame.setVisible(true);
            }
        });
    }
}