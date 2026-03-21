package ChromeDino;

import javax.swing.*;

public class App extends JFrame {
    App()
    {
        int boardWidth = 750;
        int boardHeight = 250;

        JFrame frame = new JFrame("Chrome Dinosaur");

        setSize(boardWidth, boardHeight);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Dino Adventure");
        setLocationRelativeTo(null);

        ChromeDino dino = new ChromeDino();
        add(dino);
        pack();
        dino.requestFocus();
        setVisible(true);
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
