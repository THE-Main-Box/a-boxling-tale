package game.window_related;

import game.input_related.KeyboardInput;
import game.input_related.MouseInput;
import game.player_related.Movable;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private Movable test = new Movable(0, 0, 80, 160);
    private int frames = 0;

    private KeyboardInput keyInputs;
    private MouseInput mouseInputs;

    public GamePanel() {
        keyInputs = new KeyboardInput(this);
        mouseInputs = new MouseInput(this);

        setFocusable(true);

        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        addKeyListener(keyInputs);
    }

    public void movePlayer(double speedX, double speedY) {
        test.setxVelocity(speedX);
        test.setyVelocity(speedY);
    }

    public void changePlayerPos(int x, int y) {
        test.setxPos(x);
        test.setyPos(y);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        test.updatePosition();
        g.drawRect(test.getxPos(), test.getyPos(), test.getWidth(), test.getHeight());

    }
}
