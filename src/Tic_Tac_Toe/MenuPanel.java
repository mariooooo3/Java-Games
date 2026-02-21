package Tic_Tac_Toe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MenuPanel extends JPanel {
    public JTextField firstField;
    public JTextField secondField;

    public JButton startBut;
    public JButton oneBut;
    public JButton twoBut;
    public JButton infiniteMode;

    public MenuPanel() {
        setLayout(null);

        JLabel lblNewLabel = new JLabel("Welcome to Tic Tac Toe");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel.setBounds(142, 11, 271, 14);
        add(lblNewLabel);

        JLabel gamemodeText = new JLabel("Choose the gamemode:");
        gamemodeText.setFont(new Font("Tahoma", Font.BOLD, 15));
        gamemodeText.setBounds(38, 93, 202, 37);
        add(gamemodeText);

        JLabel playersText = new JLabel("Enter players' names:");
        playersText.setFont(new Font("Tahoma", Font.BOLD, 15));
        playersText.setBounds(312, 93, 170, 37);
        playersText.setVisible(false);
        add(playersText);

        firstField = new JTextField();
        firstField.setColumns(10);
        firstField.setBounds(312, 155, 139, 20);
        firstField.setFont(new Font("Tahoma", Font.BOLD, 12));
        firstField.setVisible(false);
        firstField.setBackground(new Color(255, 128, 192));
        add(firstField);

        secondField = new JTextField();
        secondField.setColumns(10);
        secondField.setBounds(312, 211, 139, 20);
        secondField.setFont(new Font("Tahoma", Font.BOLD, 12));
        secondField.setVisible(false);
        secondField.setBackground(new Color(255, 128, 192));
        add(secondField);

        JLabel firstLabel = new JLabel("First Player");
        firstLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        firstLabel.setBounds(323, 141, 90, 14);
        firstLabel.setVisible(false);
        add(firstLabel);

        JLabel secondLabel = new JLabel("Second Player");
        secondLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        secondLabel.setBounds(322, 197, 91, 14);
        secondLabel.setVisible(false);
        add(secondLabel);

        startBut = new JButton("START");
        startBut.setFont(new Font("Tahoma", Font.BOLD, 18));
        startBut.setBounds(142, 320, 214, 58);
        startBut.setBackground(new Color(255, 128, 192));
        add(startBut);
        startBut.setVisible(false);

        infiniteMode = new JButton("Infinite Tic-Tac-Toe");
        infiniteMode.setFont(new Font("Tahoma", Font.BOLD, 16));
        infiniteMode.setBounds(142, 400, 214, 58);
        infiniteMode.setBackground(new Color(255, 128, 192));
        infiniteMode.setVisible(false);
        add(infiniteMode);


        twoBut = new JButton("Two Players");
        twoBut.setFont(new Font("Tahoma", Font.BOLD, 12));
        twoBut.setBounds(38, 137, 139, 23);
        twoBut.setBackground(new Color(255, 128, 192));
        add(twoBut);

        oneBut = new JButton("One Player");
        oneBut.setBounds(38, 175, 139, 23);
        oneBut.setFont(new Font("Tahoma", Font.BOLD, 12));
        oneBut.setBackground(new Color(255, 128, 192));
        add(oneBut);

        JLabel lblNewLabel_1 = new JLabel("About Tic Tac Toe");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblNewLabel_1.setForeground(new Color(0, 128, 255));
        lblNewLabel_1.setBounds(179, 230, 158, 16);
        add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Tic-Tac-Toe is a simple and fun game for 2 players, X and O. ");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_2.setBounds(38, 253, 500, 16);
        add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("It is played on a 3x3 grid. Each player's goal is to make 3 in a row.");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_3.setBounds(38, 273, 500, 16);
        add(lblNewLabel_3);

        twoBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playersText.setVisible(true);
                firstLabel.setVisible(true);
                firstField.setVisible(true);
                secondLabel.setVisible(true);
                secondField.setVisible(true);
                firstField.setText("");
                secondField.setText("");
                startBut.setVisible(true);
                infiniteMode.setVisible(true);
            }
        });

        oneBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playersText.setVisible(true);
                firstLabel.setVisible(true);
                firstField.setVisible(true);
                secondLabel.setVisible(false);
                secondField.setVisible(false);
                firstField.setText("");
                secondField.setText("Bot Greg");
                startBut.setVisible(true);
                infiniteMode.setVisible(false);
            }
        });

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(new Color(255, 255, 255, 180));
        g2.fillRoundRect(20, 70, 465, 230, 25, 25);
    }
}