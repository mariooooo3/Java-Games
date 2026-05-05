package Tic_Tac_Toe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class GamePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPanel gamePane;
    public JTextField player1;
    public JTextField xo;
    public JTextField player2;
    public JButton resetBut;
    public JButton back;
    public JCheckBox check;
    public JButton b11, b12, b13, b21, b22, b23, b31, b32, b33;
    public JLabel label;
    Queue<JButton> queue = new LinkedList<JButton>();
    int contor = 0;
    Boolean infiniteBool = false;

    private int countX = 0;
    private int countO = 0;
    private String difficulty = "MEDIUM";

    private static final Color BG_MAIN = new Color(26, 26, 46);
    private static final Color BG_CELL = new Color(22, 33, 62);
    private static final Color BG_BUTTON = new Color(15, 52, 96);
    private static final Color FG_TEXT = new Color(200, 210, 255);

    public void setDifficulty(String d) {
        this.difficulty = d;
    }


    private void animatePlacement(JButton button) {
        final int[] sizes = {72, 58, 52, 50};
        Timer timer = new Timer(38, null);
        final int[] step = {0};
        timer.addActionListener(e -> {
            button.setFont(new Font("Tahoma", Font.BOLD, sizes[step[0]]));
            step[0]++;
            if (step[0] >= sizes.length) ((Timer) e.getSource()).stop();
        });
        timer.start();
    }

    private void animateWin(JButton b1, JButton b2, JButton b3, Color winBg, Color winFg) {
        SoundManager.playWin();
        Color baseBg = check.isSelected() ? BG_CELL : new Color(159, 196, 255);
        Timer timer = new Timer(110, null);
        int[] count = {0};
        timer.addActionListener(e -> {
            Color c = (count[0] % 2 == 0) ? winBg : baseBg;
            b1.setBackground(c);
            b2.setBackground(c);
            b3.setBackground(c);
            count[0]++;
            if (count[0] >= 6) {
                b1.setBackground(winBg);
                b1.setForeground(winFg);
                b2.setBackground(winBg);
                b2.setForeground(winFg);
                b3.setBackground(winBg);
                b3.setForeground(winFg);
                ((Timer) e.getSource()).stop();
            }
        });
        timer.start();
    }

    private void animateRemoval(JButton button) {
        button.setEnabled(false);
        Color restBg = check.isSelected() ? BG_CELL : new Color(159, 196, 255);
        Color flashBg = new Color(110, 80, 160);
        Timer timer = new Timer(70, null);
        int[] step = {0};
        timer.addActionListener(e -> {
            if (step[0] == 0) {
                button.setBackground(flashBg);
            } else {
                button.setText("");
                button.setFont(new Font("Tahoma", Font.BOLD, 50));
                button.setBackground(restBg);
                button.setEnabled(true);
                ((Timer) e.getSource()).stop();
            }
            step[0]++;
        });
        timer.start();
    }

    private JButton createGameButton(String actionCommand, int x, int y, int width, int height) {
        JButton button = new JButton("");
        button.setBackground(BG_CELL);
        button.setActionCommand(actionCommand);
        button.setFont(new Font("Tahoma", Font.BOLD, 50));
        button.setBounds(x, y, width, height);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = xo.getText();
                String parts[] = s.split(" ");
                if (button.getText().isEmpty()) {
                    button.setText(parts[0]);
                    if (parts[0].equals("X")) {
                        button.setForeground(new Color(255, 128, 192));
                        SoundManager.playButX();
                    } else {
                        button.setForeground(new Color(0, 128, 255));
                        SoundManager.playButY();
                    }
                    animatePlacement(button);
                    queue.add(button);
                    if (parts[0].equals("X"))
                        xo.setText("O Turn");
                    else
                        xo.setText("X Turn");
                    contor++;
                    Boolean bool = checkWinner();
                    if (!bool) {
                        checkTie();
                        if (player2.getText().contains("Bot") && xo.getText().equals("O Turn")
                                && label.getText().equals("Infinite Mode")) {
                            infiniteGameBot();
                        } else if (player2.getText().contains("Bot") && xo.getText().equals("O Turn")) {
                            autoPlay();
                        } else if (label.getText().equals("Infinite Mode"))
                            infiniteGame();
                    }
                    if (label.getText().equals("Infinite Mode"))
                        infiniteBool = true;
                    else if (label.getText().equals("Tic Tac Toe"))
                        infiniteBool = false;
                }
            }
        });
        return button;
    }

    public GamePanel() {
        setLayout(null);
        setBounds(0, 0, 540, 530);
        setBackground(BG_MAIN);

        player1 = new JTextField();
        player1.setFont(new Font("Tahoma", Font.BOLD, 12));
        player1.setForeground(FG_TEXT);
        player1.setBackground(BG_BUTTON);
        player1.setCaretColor(FG_TEXT);
        player1.setBorder(BorderFactory.createLineBorder(new Color(15, 52, 96), 1));
        player1.setColumns(10);
        player1.setBounds(10, 64, 110, 20);
        player1.setEditable(false);
        add(player1);

        xo = new JTextField();
        xo.setFont(new Font("Tahoma", Font.BOLD, 12));
        xo.setForeground(FG_TEXT);
        xo.setBackground(BG_BUTTON);
        xo.setCaretColor(FG_TEXT);
        xo.setBorder(BorderFactory.createLineBorder(new Color(15, 52, 96), 1));
        xo.setColumns(10);
        xo.setBounds(214, 63, 90, 20);
        add(xo);

        player2 = new JTextField();
        player2.setFont(new Font("Tahoma", Font.BOLD, 12));
        player2.setForeground(FG_TEXT);
        player2.setBackground(BG_BUTTON);
        player2.setCaretColor(FG_TEXT);
        player2.setBorder(BorderFactory.createLineBorder(new Color(15, 52, 96), 1));
        player2.setColumns(10);
        player2.setBounds(404, 64, 110, 20);
        player2.setEditable(false);
        add(player2);

        label = new JLabel("Tic Tac Toe");
        label.setFont(new Font("Tahoma", Font.BOLD, 18));
        label.setForeground(FG_TEXT);
        label.setBounds(203, 11, 200, 20);
        add(label);

        b11 = createGameButton("b11", 10, 128, 160, 110);
        b12 = createGameButton("b12", 180, 128, 160, 110);
        b13 = createGameButton("b13", 350, 128, 160, 110);
        b21 = createGameButton("b21", 10, 249, 160, 110);
        b22 = createGameButton("b22", 180, 249, 160, 110);
        b23 = createGameButton("b23", 350, 249, 160, 110);
        b31 = createGameButton("b31", 10, 370, 160, 110);
        b32 = createGameButton("b32", 180, 368, 160, 110);
        b33 = createGameButton("b33", 350, 370, 160, 110);

        add(b11);
        add(b12);
        add(b13);
        add(b21);
        add(b22);
        add(b23);
        add(b31);
        add(b32);
        add(b33);

        check = new JCheckBox("Dark Theme");
        check.setFont(new Font("Tahoma", Font.BOLD, 12));
        check.setForeground(FG_TEXT);
        check.setBackground(BG_BUTTON);
        check.setSelected(true);
        check.setBounds(10, 40, 110, 20);
        add(check);
        check.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton[] all = {b11, b12, b13, b21, b22, b23, b31, b32, b33};
                if (check.isSelected()) {
                    player1.setForeground(FG_TEXT);
                    player1.setBackground(BG_BUTTON);
                    xo.setForeground(FG_TEXT);
                    xo.setBackground(BG_BUTTON);
                    player2.setBackground(BG_BUTTON);
                    player2.setForeground(FG_TEXT);
                    resetBut.setForeground(FG_TEXT);
                    resetBut.setBackground(BG_BUTTON);
                    back.setForeground(FG_TEXT);
                    back.setBackground(BG_BUTTON);
                    check.setForeground(FG_TEXT);
                    check.setBackground(BG_BUTTON);
                    setBackground(BG_MAIN);
                    label.setForeground(FG_TEXT);
                    for (JButton b : all) b.setBackground(BG_CELL);
                } else {
                    player1.setForeground(new Color(0, 128, 255));
                    player1.setBackground(new Color(159, 196, 255));
                    xo.setForeground(new Color(0, 128, 255));
                    xo.setBackground(new Color(159, 196, 255));
                    player2.setBackground(new Color(159, 196, 255));
                    player2.setForeground(new Color(0, 128, 255));
                    resetBut.setForeground(new Color(0, 128, 255));
                    resetBut.setBackground(new Color(159, 196, 255));
                    back.setForeground(new Color(0, 128, 255));
                    back.setBackground(new Color(159, 196, 255));
                    check.setForeground(new Color(0, 128, 255));
                    check.setBackground(new Color(159, 196, 255));
                    setBackground(new Color(207, 223, 252));
                    label.setForeground(new Color(0, 128, 255));
                    for (JButton b : all) b.setBackground(new Color(159, 196, 255));
                }
            }
        });

        resetBut = new JButton("Reset");
        resetBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton[] all = {b11, b12, b13, b21, b22, b23, b31, b32, b33};
                for (JButton b : all) {
                    b.setText("");
                    b.setFont(new Font("Tahoma", Font.BOLD, 50));
                    b.setEnabled(true);
                }
                if (xo.getText().equals("O Turn"))
                    xo.setText("O Turn");
                else
                    xo.setText("X Turn");
                resetGrounds();
                if (infiniteBool)
                    label.setText("Infinite Mode");
                else
                    label.setText("Tic Tac Toe");
                label.setBounds(203, 11, 200, 20);
                if (player2.getText().contains("Bot") && xo.getText().equals("O Turn")) {
                    autoPlay();
                }
                contor = 0;
                queue.clear();
            }
        });
        resetBut.setBounds(165, 94, 89, 23);
        resetBut.setForeground(FG_TEXT);
        resetBut.setBackground(BG_BUTTON);
        resetBut.setFocusPainted(false);
        resetBut.setBorderPainted(false);
        resetBut.setOpaque(true);
        add(resetBut);

        Random rand = new Random();
        int choice = rand.nextInt(2);
        if (choice > 0) xo.setText("X Turn");
        else xo.setText("O Turn");

        back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton[] all = {b11, b12, b13, b21, b22, b23, b31, b32, b33};
                for (JButton b : all) {
                    b.setText("");
                    b.setFont(new Font("Tahoma", Font.BOLD, 50));
                    b.setEnabled(true);
                }
                if (xo.getText().equals("O Turn"))
                    xo.setText("O Turn");
                else
                    xo.setText("X Turn");
                resetGrounds();
                countX = 0;
                countO = 0;
                if (infiniteBool)
                    label.setText("Infinite Mode");
                else
                    label.setText("Tic Tac Toe");
                label.setBounds(203, 11, 200, 20);
                if (player2.getText().contains("Bot") && xo.getText().equals("O Turn")) {
                    autoPlay();
                }
                contor = 0;
                queue.clear();
            }
        });
        back.setBounds(260, 94, 89, 23);
        back.setForeground(FG_TEXT);
        back.setBackground(BG_BUTTON);
        back.setFocusPainted(false);
        back.setBorderPainted(false);
        back.setOpaque(true);
        add(back);
    }

    public boolean checkWinner() {
        Color winBg = new Color(61, 43, 107);
        Color winWhite = new Color(100, 160, 255);
        Color winFg = new Color(255, 255, 255);
        String t11 = b11.getText(), t12 = b12.getText(), t13 = b13.getText();
        String t21 = b21.getText(), t22 = b22.getText(), t23 = b23.getText();
        String t31 = b31.getText(), t32 = b32.getText(), t33 = b33.getText();

        Color bg = check.isSelected() ? winBg : winWhite;

        if (!t11.isEmpty() && t11.equals(t12) && t12.equals(t13)) {
            highlight(b11, b12, b13, bg, winFg);
            updateScore(t11);
            return true;
        }
        if (!t21.isEmpty() && t21.equals(t22) && t22.equals(t23)) {
            highlight(b21, b22, b23, bg, winFg);
            updateScore(t21);
            return true;
        }
        if (!t31.isEmpty() && t31.equals(t32) && t32.equals(t33)) {
            highlight(b31, b32, b33, bg, winFg);
            updateScore(t31);
            return true;
        }
        if (!t11.isEmpty() && t11.equals(t21) && t21.equals(t31)) {
            highlight(b11, b21, b31, bg, winFg);
            updateScore(t11);
            return true;
        }
        if (!t12.isEmpty() && t12.equals(t22) && t22.equals(t32)) {
            highlight(b12, b22, b32, bg, winFg);
            updateScore(t12);
            return true;
        }
        if (!t13.isEmpty() && t13.equals(t23) && t23.equals(t33)) {
            highlight(b13, b23, b33, bg, winFg);
            updateScore(t13);
            return true;
        }
        if (!t11.isEmpty() && t11.equals(t22) && t22.equals(t33)) {
            highlight(b11, b22, b33, bg, winFg);
            updateScore(t11);
            return true;
        }
        if (!t13.isEmpty() && t13.equals(t22) && t22.equals(t31)) {
            highlight(b13, b22, b31, bg, winFg);
            updateScore(t13);
            return true;
        }
        return false;
    }

    private void highlight(JButton b1, JButton b2, JButton b3, Color bg, Color fg) {
        disableButtons();
        animateWin(b1, b2, b3, bg, fg);
    }

    private void disableButtons() {
        JButton[] all = {b11, b12, b13, b21, b22, b23, b31, b32, b33};
        for (JButton b : all) b.setEnabled(false);
    }

    public void resetGrounds() {
        JButton[] all = {b11, b12, b13, b21, b22, b23, b31, b32, b33};
        for (JButton b : all) {
            if (check.isSelected()) {
                b.setBackground(BG_CELL);
            } else {
                b.setBackground(new Color(159, 196, 255));
            }
            b.setOpaque(true);
            b.setBorderPainted(false);
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
        for (JButton b : all) {
            if (b.getText().isEmpty()) {
                bool = false;
                break;
            }
        }
        if (bool) {
            label.setText("!!Tie Game!!");
            SoundManager.playTie();
            Color tieBg = check.isSelected() ? new Color(74, 63, 107) : new Color(180, 180, 220);
            for (JButton b : all) {
                b.setBackground(tieBg);
                b.setForeground(new Color(200, 210, 255));
                b.setEnabled(false);
            }
        }
    }

    public void autoPlay() {
        JButton move = null;

        if (difficulty.equals("EASY")) {
            JButton[] all = {b11, b12, b13, b21, b22, b23, b31, b32, b33};
            ArrayList<JButton> empty = new ArrayList<>();
            for (JButton b : all) if (b.getText().isEmpty()) empty.add(b);
            if (!empty.isEmpty()) move = empty.get(new Random().nextInt(empty.size()));

        } else if (difficulty.equals("HARD")) {
            move = findBestMoveHard();

        } else {
            move = findWinningMove("O");
            if (move == null) move = findWinningMove("X");
            if (move == null && b22.getText().isEmpty()) move = b22;
            if (move == null) {
                JButton[] corners = {b11, b13, b31, b33};
                for (JButton b : corners)
                    if (b.getText().isEmpty()) {
                        move = b;
                        break;
                    }
            }
            if (move == null) {
                JButton[] all = {b11, b12, b13, b21, b22, b23, b31, b32, b33};
                for (JButton b : all)
                    if (b.getText().isEmpty()) {
                        move = b;
                        break;
                    }
            }
        }

        if (move != null) {
            move.setText("O");
            move.setForeground(new Color(0, 128, 255));
            SoundManager.playButY();
            animatePlacement(move);
            xo.setText("X Turn");
            queue.add(move);
        }
        if (!checkWinner()) checkTie();
    }

    private JButton findWinningMove(String player) {
        JButton[][] lines = {
                {b11, b12, b13}, {b21, b22, b23}, {b31, b32, b33},
                {b11, b21, b31}, {b12, b22, b32}, {b13, b23, b33},
                {b11, b22, b33}, {b13, b22, b31}
        };
        for (JButton[] line : lines) {
            int cnt = 0;
            JButton empty = null;
            for (JButton b : line) {
                if (b.getText().equals(player)) cnt++;
                else if (b.getText().isEmpty()) empty = b;
            }
            if (cnt == 2 && empty != null) return empty;
        }
        return null;
    }

    private JButton findBestMoveHard() {
        JButton[][] grid = {
                {b11, b12, b13},
                {b21, b22, b23},
                {b31, b32, b33}
        };
        String[][] board = new String[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = grid[i][j].getText();

        int bestScore = Integer.MIN_VALUE;
        JButton bestMove = null;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].isEmpty()) {
                    board[i][j] = "O";
                    int score = minimax(board, 0, false);
                    board[i][j] = "";
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = grid[i][j];
                    }
                }
            }
        }
        return bestMove;
    }

    private int minimax(String[][] board, int depth, boolean isMaximizing) {
        String result = evaluateBoard(board);
        if (result != null) {
            if (result.equals("O")) return 10 - depth;
            if (result.equals("X")) return depth - 10;
            return 0;
        }
        if (isMaximizing) {
            int best = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    if (board[i][j].isEmpty()) {
                        board[i][j] = "O";
                        best = Math.max(best, minimax(board, depth + 1, false));
                        board[i][j] = "";
                    }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    if (board[i][j].isEmpty()) {
                        board[i][j] = "X";
                        best = Math.min(best, minimax(board, depth + 1, true));
                        board[i][j] = "";
                    }
            return best;
        }
    }

    private String evaluateBoard(String[][] b) {
        for (int i = 0; i < 3; i++) {
            if (!b[i][0].isEmpty() && b[i][0].equals(b[i][1]) && b[i][1].equals(b[i][2])) return b[i][0];
            if (!b[0][i].isEmpty() && b[0][i].equals(b[1][i]) && b[1][i].equals(b[2][i])) return b[0][i];
        }
        if (!b[0][0].isEmpty() && b[0][0].equals(b[1][1]) && b[1][1].equals(b[2][2])) return b[0][0];
        if (!b[0][2].isEmpty() && b[0][2].equals(b[1][1]) && b[1][1].equals(b[2][0])) return b[0][2];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (b[i][j].isEmpty()) return null;
        return "tie";
    }

    // ─── Infinite mode ────────────────────────────────────────────────────────

    public void infiniteGame() {
        if (contor > 5) {
            JButton old = queue.poll();
            if (old != null) animateRemoval(old);
        }
    }

    public void infiniteGameBot() {
        autoPlay();
        if (!checkWinner()) {
            while (queue.size() > 6) {
                JButton old = queue.poll();
                if (old != null) old.setText("");
            }
        }
    }
}
