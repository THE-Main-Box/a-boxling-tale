package game.input_related;

import game.entity_related.models.Movement;
import game.window_related.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener {

    private GamePanel gamePanel;


    public KeyboardInput(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamePanel.movePlayer(Movement.UP);
                break;
            case KeyEvent.VK_A:
                gamePanel.movePlayer(Movement.LEFT);
                break;
            case KeyEvent.VK_S:
                gamePanel.movePlayer(Movement.DOWN);
                break;
            case KeyEvent.VK_D:
                gamePanel.movePlayer(Movement.RIGHT);
                break;
            case KeyEvent.VK_SPACE:
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W,
                 KeyEvent.VK_A,
                 KeyEvent.VK_S,
                 KeyEvent.VK_D:
                gamePanel.movePlayer(Movement.STOP);
                break;
            case KeyEvent.VK_SPACE:
                break;
        }

    }

}
