package Tic_Tac_Toe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

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

    private JButton easyBut;
    private JButton mediumBut;
    private JButton hardBut;
    private JLabel difficultyLabel;
    private String selectedDifficulty = "MEDIUM";

    private static final Color EASY_SEL = new Color(50, 160, 50);
    private static final Color EASY_UNSEL = new Color(180, 220, 180);
    private static final Color MED_SEL = new Color(200, 130, 0);
    private static final Color MED_UNSEL = new Color(255, 220, 150);
    private static final Color HARD_SEL = new Color(200, 50, 50);
    private static final Color HARD_UNSEL = new Color(255, 190, 190);

    public String getDifficulty() {
        return selectedDifficulty;
    }

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


        difficultyLabel = new JLabel("Bot Difficulty:");
        difficultyLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        difficultyLabel.setBounds(38, 205, 120, 16);
        difficultyLabel.setVisible(false);
        add(difficultyLabel);

        easyBut = makeDiffButton("Easy", 38, 225, 68);
        mediumBut = makeDiffButton("Medium", 112, 225, 80);
        hardBut = makeDiffButton("Hard", 198, 225, 68);
        add(easyBut);
        add(mediumBut);
        add(hardBut);

        mediumBut.setBackground(MED_SEL);
        mediumBut.setForeground(Color.WHITE);

        easyBut.addActionListener(e -> selectDifficulty("EASY"));
        mediumBut.addActionListener(e -> selectDifficulty("MEDIUM"));
        hardBut.addActionListener(e -> selectDifficulty("HARD"));


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
                setDifficultyVisible(false);
                lblNewLabel_1.setVisible(true);
            }
        });

        oneBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String botName = getBotName();
                playersText.setVisible(true);
                firstLabel.setVisible(true);
                firstField.setVisible(true);
                secondLabel.setVisible(false);
                secondField.setVisible(false);
                firstField.setText("");
                secondField.setText(botName);
                startBut.setVisible(true);
                infiniteMode.setVisible(true);
                setDifficultyVisible(true);
                lblNewLabel_1.setVisible(false);
            }
        });
    }


    private JButton makeDiffButton(String text, int x, int y, int w) {
        JButton b = new JButton(text);
        b.setFont(new Font("Tahoma", Font.BOLD, 11));
        b.setBounds(x, y, w, 24);
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setOpaque(true);
        b.setVisible(false);
        return b;
    }

    private void selectDifficulty(String d) {
        selectedDifficulty = d;

        easyBut.setBackground(EASY_UNSEL);
        easyBut.setForeground(new Color(30, 100, 30));
        mediumBut.setBackground(MED_UNSEL);
        mediumBut.setForeground(new Color(120, 70, 0));
        hardBut.setBackground(HARD_UNSEL);
        hardBut.setForeground(new Color(150, 30, 30));

        switch (d) {
            case "EASY":
                easyBut.setBackground(EASY_SEL);
                easyBut.setForeground(Color.WHITE);
                break;
            case "HARD":
                hardBut.setBackground(HARD_SEL);
                hardBut.setForeground(Color.WHITE);
                break;
            default:
                mediumBut.setBackground(MED_SEL);
                mediumBut.setForeground(Color.WHITE);
                break;
        }
    }

    private void setDifficultyVisible(boolean v) {
        difficultyLabel.setVisible(v);
        easyBut.setVisible(v);
        mediumBut.setVisible(v);
        hardBut.setVisible(v);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(255, 255, 255, 180));
        g2.fillRoundRect(20, 70, 465, 230, 25, 25);
    }

    public String getBotName() {
        Random rand = new Random();
        switch (rand.nextInt(5)) {
            case 0:
                return "Bot Greg";
            case 1:
                return "Bot Marc";
            case 2:
                return "Bot Roger";
            case 3:
                return "Bot Bob";
            case 4:
                return "Bot Jack";
        }
        return "";
    }
}