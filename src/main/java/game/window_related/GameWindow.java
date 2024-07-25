package game.window_related;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    public GameWindow(GamePanel gamePanel) {

        setSize(800,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(gamePanel);

        setVisible(true);
    }
}
