package Tic_Tac_Toe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPanel gamePane;
    public JTextField player1;
    public JTextField xo;
    public JTextField player2;
    public JButton resetBut;
    public JButton back;
    private JButton b11, b12, b13, b21, b22, b23, b31, b32, b33;
    private JLabel label;

    private int countX = 0;
    private int countO = 0;

    public GamePanel() {
        setLayout(null);
        setBounds(0, 0, 540, 530);
        setBackground(new Color(207, 223, 252));

        player1 = new JTextField();
        player1.setFont(new Font("Tahoma", Font.BOLD, 12));
        player1.setForeground(new Color(0, 128, 255));
        player1.setBackground(new Color(207, 223, 252));
        player1.setColumns(10);
        player1.setBounds(10, 64, 86, 20);
        player1.setEditable(false);
        add(player1);

        xo = new JTextField();
        xo.setFont(new Font("Tahoma", Font.BOLD, 12));
        xo.setForeground(new Color(0, 128, 255));
        xo.setBackground(new Color(207, 223, 252));
        xo.setColumns(10);
        xo.setBounds(214, 63, 86, 20);
        add(xo);

        player2 = new JTextField();
        player2.setFont(new Font("Tahoma", Font.BOLD, 12));
        player2.setBackground(new Color(207, 223, 252));
        player2.setForeground(new Color(0, 128, 255));
        player2.setColumns(10);
        player2.setBounds(424, 64, 86, 20);
        player2.setEditable(false);
        add(player2);

        label = new JLabel("Tic Tac Toe");
        label.setFont(new Font("Tahoma", Font.BOLD, 18));
        label.setBounds(203, 11, 200, 20);
        add(label);

        b11 = new JButton("");
        b11.setBackground(new Color(207, 223, 252));
        b11.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = xo.getText();
                String parts[] = s.split(" ");
                if (b11.getText().isEmpty()) {
                    b11.setText(parts[0]);
                    if (parts[0].equals("X"))
                        b11.setForeground(new Color(255, 128, 192));
                    else
                        b11.setForeground(new Color(0, 128, 255));
                }
                if (parts[0].equals("X"))
                    xo.setText("O Turn");
                else
                    xo.setText("X Turn");
                Boolean bool = checkWinner();
                if (!bool) {
                    checkTie();
                    if (player2.getText().contains("Bot") && xo.getText().equals("O Turn")) {
                        autoPlay();
                    }
                }

            }
        });
        b11.setActionCommand("b11");
        b11.setFont(new Font("Tahoma", Font.BOLD, 50));
        b11.setBounds(10, 128, 160, 110);
        add(b11);

        b21 = new JButton("");
        b21.setBackground(new Color(207, 223, 252));
        b21.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = xo.getText();
                String parts[] = s.split(" ");
                if (b21.getText().isEmpty()) {
                    b21.setText(parts[0]);
                    if (parts[0].equals("X"))
                        b21.setForeground(new Color(255, 128, 192));
                    else
                        b21.setForeground(new Color(0, 128, 255));
                }
                if (parts[0].equals("X"))
                    xo.setText("O Turn");
                else
                    xo.setText("X Turn");
                Boolean bool = checkWinner();
                if (!bool) {
                    checkTie();
                    if (player2.getText().contains("Bot") && xo.getText().equals("O Turn")) {
                        autoPlay();
                    }
                }

            }
        });
        b21.setActionCommand("b21");
        b21.setFont(new Font("Tahoma", Font.BOLD, 50));
        b21.setBounds(10, 249, 160, 110);
        add(b21);

        b31 = new JButton("");
        b31.setBackground(new Color(207, 223, 252));
        b31.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = xo.getText();
                String parts[] = s.split(" ");
                if (b31.getText().isEmpty()) {
                    b31.setText(parts[0]);
                    if (parts[0].equals("X"))
                        b31.setForeground(new Color(255, 128, 192));
                    else
                        b31.setForeground(new Color(0, 128, 255));
                }
                if (parts[0].equals("X"))
                    xo.setText("O Turn");
                else
                    xo.setText("X Turn");
                Boolean bool = checkWinner();
                if (!bool) {
                    checkTie();
                    if (player2.getText().contains("Bot") && xo.getText().equals("O Turn")) {
                        autoPlay();
                    }
                }

            }
        });
        b31.setActionCommand("b31");
        b31.setFont(new Font("Tahoma", Font.BOLD, 50));
        b31.setBounds(10, 370, 160, 110);
        add(b31);

        b32 = new JButton("");
        b32.setBackground(new Color(207, 223, 252));
        b32.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = xo.getText();
                String parts[] = s.split(" ");
                if (b32.getText().isEmpty()) {
                    b32.setText(parts[0]);
                    if (parts[0].equals("X"))
                        b32.setForeground(new Color(255, 128, 192));
                    else
                        b32.setForeground(new Color(0, 128, 255));
                }
                if (parts[0].equals("X"))
                    xo.setText("O Turn");
                else
                    xo.setText("X Turn");
                Boolean bool = checkWinner();
                if (!bool) {
                    checkTie();
                    if (player2.getText().contains("Bot") && xo.getText().equals("O Turn")) {
                        autoPlay();
                    }
                }


            }
        });
        b32.setActionCommand("b32");
        b32.setFont(new Font("Tahoma", Font.BOLD, 50));
        b32.setBounds(180, 368, 160, 110);
        add(b32);

        b33 = new JButton("");
        b33.setBackground(new Color(207, 223, 252));
        b33.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = xo.getText();
                String parts[] = s.split(" ");
                if (b33.getText().isEmpty()) {
                    b33.setText(parts[0]);
                    if (parts[0].equals("X"))
                        b33.setForeground(new Color(255, 128, 192));
                    else
                        b33.setForeground(new Color(0, 128, 255));
                }
                if (parts[0].equals("X"))
                    xo.setText("O Turn");
                else
                    xo.setText("X Turn");
                Boolean bool = checkWinner();
                if (!bool) {
                    checkTie();
                    if (player2.getText().contains("Bot") && xo.getText().equals("O Turn")) {
                        autoPlay();
                    }
                }
            }
        });
        b33.setActionCommand("b33");
        b33.setFont(new Font("Tahoma", Font.BOLD, 50));
        b33.setBounds(350, 370, 160, 110);
        add(b33);

        b22 = new JButton("");
        b22.setBackground(new Color(207, 223, 252));
        b22.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = xo.getText();
                String parts[] = s.split(" ");
                if (b22.getText().isEmpty()) {
                    b22.setText(parts[0]);
                    if (parts[0].equals("X"))
                        b22.setForeground(new Color(255, 128, 192));
                    else
                        b22.setForeground(new Color(0, 128, 255));
                }
                if (parts[0].equals("X"))
                    xo.setText("O Turn");
                else
                    xo.setText("X Turn");
                Boolean bool = checkWinner();
                if (!bool) {
                    checkTie();
                    if (player2.getText().contains("Bot") && xo.getText().equals("O Turn")) {
                        autoPlay();
                    }
                }
            }
        });
        b22.setActionCommand("b22");
        b22.setFont(new Font("Tahoma", Font.BOLD, 50));
        b22.setBounds(180, 249, 160, 110);
        add(b22);

        b12 = new JButton("");
        b12.setBackground(new Color(207, 223, 252));
        b12.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = xo.getText();
                String parts[] = s.split(" ");
                if (b12.getText().isEmpty()) {
                    b12.setText(parts[0]);
                    if (parts[0].equals("X"))
                        b12.setForeground(new Color(255, 128, 192));
                    else
                        b12.setForeground(new Color(0, 128, 255));
                }
                if (parts[0].equals("X"))
                    xo.setText("O Turn");
                else
                    xo.setText("X Turn");
                Boolean bool = checkWinner();
                if (!bool) {
                    checkTie();
                    if (player2.getText().contains("Bot") && xo.getText().equals("O Turn")) {
                        autoPlay();
                    }
                }
            }
        });
        b12.setActionCommand("b12");
        b12.setFont(new Font("Tahoma", Font.BOLD, 50));
        b12.setBounds(180, 128, 160, 110);
        add(b12);

        b13 = new JButton("");
        b13.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = xo.getText();
                String parts[] = s.split(" ");
                if (b13.getText().isEmpty()) {
                    b13.setText(parts[0]);
                    if (parts[0].equals("X"))
                        b13.setForeground(new Color(255, 128, 192));
                    else
                        b13.setForeground(new Color(0, 128, 255));

                }
                if (parts[0].equals("X"))
                    xo.setText("O Turn");
                else
                    xo.setText("X Turn");
                Boolean bool = checkWinner();
                if (!bool) {
                    checkTie();
                    if (player2.getText().contains("Bot") && xo.getText().equals("O Turn")) {
                        autoPlay();
                    }
                }
            }
        });
        b13.setActionCommand("b13");
        b13.setBackground(new Color(207, 223, 252));
        b13.setFont(new Font("Tahoma", Font.BOLD, 50));
        b13.setBounds(350, 128, 160, 110);
        add(b13);

        b23 = new JButton("");
        b23.setBackground(new Color(207, 223, 252));
        b23.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = xo.getText();
                String parts[] = s.split(" ");
                if (b23.getText().isEmpty()) {
                    b23.setText(parts[0]);
                    if (parts[0].equals("X"))
                        b23.setForeground(new Color(255, 128, 192));
                    else
                        b23.setForeground(new Color(0, 128, 255));

                }
                if (parts[0].equals("X"))
                    xo.setText("O Turn");
                else
                    xo.setText("X Turn");
                Boolean bool = checkWinner();
                if (!bool) {
                    checkTie();
                    if (player2.getText().contains("Bot") && xo.getText().equals("O Turn")) {
                        autoPlay();
                    }
                }
            }
        });
        b23.setActionCommand("b23");
        b23.setFont(new Font("Tahoma", Font.BOLD, 50));
        b23.setBounds(350, 249, 160, 110);
        add(b23);

        resetBut = new JButton("Reset");
        resetBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton[] all = {b11, b12, b13, b21, b22, b23, b31, b32, b33};
                for (JButton b : all) {
                    b.setText("");
                }
                if(xo.getText().equals("O Turn"))
                    xo.setText("O Turn");
                else
                    xo.setText("X Turn");
                for (JButton b : all) {
                    b.setText("");
                    b.setEnabled(true);
                }
                resetGrounds();
                label.setText("Tic Tac Toe");
                label.setBounds(203, 11, 200, 20);
                if (player2.getText().contains("Bot") && xo.getText().equals("O Turn")) {
                    autoPlay();
                }
            }
        });
        resetBut.setBounds(165, 94, 89, 23);
        resetBut.setForeground(new Color(0, 128, 255));
        resetBut.setBackground(new Color(207, 223, 252));
        add(resetBut);

        Random rand = new Random();
        int choice = rand.nextInt(2);
        if (choice > 0)
            xo.setText("X Turn");
        else
            xo.setText("O Turn");

        back = new JButton("Back");
        back.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JButton[] all = {b11, b12, b13, b21, b22, b23, b31, b32, b33};
                for (JButton b : all) {
                    b.setText("");
                }
                if (xo.getText().equals("O Turn"))
                        xo.setText("O Turn");
                else
                    xo.setText("X Turn");
                for (JButton b : all) {
                    b.setText("");
                    b.setEnabled(true);
                }
                resetGrounds();
                countX = 0;
                countO = 0;
                label.setText("Tic Tac Toe");
                label.setBounds(203, 11, 200, 20);
                if (player2.getText().contains("Bot") && xo.getText().equals("O Turn")) {
                    autoPlay();
                }
            }

        });
        back.setBounds(260, 94, 89, 23);
        back.setForeground(new Color(0, 128, 255));
        back.setBackground(new Color(207, 223, 252));
        add(back);

    }

    public boolean checkWinner() {
        Color winBg = new Color(0, 255, 128);
        Color winFg = new Color(0, 125, 32);
        String t11 = b11.getText(), t12 = b12.getText(), t13 = b13.getText();
        String t21 = b21.getText(), t22 = b22.getText(), t23 = b23.getText();
        String t31 = b31.getText(), t32 = b32.getText(), t33 = b33.getText();

        if (!t11.isEmpty() && t11.equals(t12) && t12.equals(t13)) {
            highlight(b11, b12, b13, winBg, winFg);
            updateScore(t11);
            return true;
        } else if (!t21.isEmpty() && t21.equals(t22) && t22.equals(t23)) {
            highlight(b21, b22, b23, winBg, winFg);
            updateScore(t21);
            return true;
        } else if (!t31.isEmpty() && t31.equals(t32) && t32.equals(t33)) {
            highlight(b31, b32, b33, winBg, winFg);
            updateScore(t31);
            return true;
        } else if (!t11.isEmpty() && t11.equals(t21) && t21.equals(t31)) {
            highlight(b11, b21, b31, winBg, winFg);
            updateScore(t11);
            return true;
        } else if (!t12.isEmpty() && t12.equals(t22) && t22.equals(t32)) {
            highlight(b12, b22, b32, winBg, winFg);
            updateScore(t12);
            return true;
        } else if (!t13.isEmpty() && t13.equals(t23) && t23.equals(t33)) {
            highlight(b13, b23, b33, winBg, winFg);
            updateScore(t13);
            return true;
        } else if (!t11.isEmpty() && t11.equals(t22) && t22.equals(t33)) {
            highlight(b11, b22, b33, winBg, winFg);
            updateScore(t11);
            return true;
        } else if (!t13.isEmpty() && t13.equals(t22) && t22.equals(t31)) {
            highlight(b13, b22, b31, winBg, winFg);
            updateScore(t13);
            return true;
        }
        return false;
    }

    private void highlight(JButton b1, JButton b2, JButton b3, Color bg, Color fg) {
        b1.setBackground(bg);
        b1.setForeground(fg);
        b2.setBackground(bg);
        b2.setForeground(fg);
        b3.setBackground(bg);
        b3.setForeground(fg);
        disableButtons();
    }

    private void disableButtons() {
        JButton[] all = {b11, b12, b13, b21, b22, b23, b31, b32, b33};
        for (JButton b : all) {
            b.setEnabled(false);
        }
    }

    public void resetGrounds() {
        JButton[] all = {b11, b12, b13, b21, b22, b23, b31, b32, b33};
        for (JButton b : all) {
            b.setBackground(new Color(207, 223, 252));
        }
    }

    private void updateScore(String player) {
        if (player.equals("X")) {
            countX++;
            player1.setText(player1.getText().split(":")[0] + ":" + countX);
            label.setText("!!Winner " + player1.getText().split("-")[0] + "!!");
            label.setBounds(195, 11, 200, 20);
        } else {
            countO++;
            player2.setText(player2.getText().split(":")[0] + ":" + countO);
            label.setText("!!Winner " + player2.getText().split("-")[0] + "!!");
            label.setBounds(185, 11, 200, 20);
        }
    }

    public void checkTie() {
        boolean bool = true;
        JButton[] all = {b11, b12, b13, b21, b22, b23, b31, b32, b33};
        {
            for (JButton b : all) {
                if (b.getText().isEmpty())
                    bool = false;
            }
            if (bool) {
                label.setText("!!Tie Game!!");
                for (JButton b1 : all) {
                    b1.setBackground(Color.yellow);
                    b1.setEnabled(false);

                }
            }
        }
    }

    //	public void autoPlay() { metoda fara ai
//		ArrayList<JButton> list = new ArrayList<>();
//		JButton[] all = { b11, b12, b13, b21, b22, b23, b31, b32, b33 };
//		int count = 0;
//
//		for (JButton b : all) {
//			if (b.getText().isEmpty()) {
//				count++;
//				list.add(b);
//			}
//		}
//		Random rand = new Random();
//		int choice = rand.nextInt(count);
//		list.get(choice).setForeground(new Color(0, 128, 255));
//		list.get(choice).setText("O");
//		xo.setText("X Turn");
//		Boolean bool = checkWinner();
//		if (!bool)
//			checkTie();
//
//	}
    public void autoPlay() {

        JButton move = findWinningMove("O");
        if (move == null)
            move = findWinningMove("X");

        if (move == null && b22.getText().isEmpty())
            move = b22;

        if (move == null) {
            JButton[] corners = {b11, b13, b31, b33};
            for (JButton b : corners)
                if (b.getText().isEmpty())
                    move = b;
        }

        if (move == null) {
            JButton[] all = {b11, b12, b13, b21, b22, b23, b31, b32, b33};
            for (JButton b : all)
                if (b.getText().isEmpty())
                    move = b;
        }

        if (move != null) {
            move.setText("O");
            move.setForeground(new Color(0, 128, 255));
            xo.setText("X Turn");
        }

        if (!checkWinner())
            checkTie();
    }

    private JButton findWinningMove(String player) {

        JButton[][] lines = {
                {b11, b12, b13}, {b21, b22, b23}, {b31, b32, b33},
                {b11, b21, b31}, {b12, b22, b32}, {b13, b23, b33},
                {b11, b22, b33}, {b13, b22, b31}
        };

        for (JButton[] line : lines) {
            int countPlayer = 0;
            JButton empty = null;

            for (JButton b : line) {
                if (b.getText().equals(player))
                    countPlayer++;
                else if (b.getText().isEmpty())
                    empty = b;
            }

            if (countPlayer == 2 && empty != null)
                return empty;
        }
        return null;
    }

}