package game.input_related;

import game.entity_related.models.Directions;
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
                gamePanel.movePlayer(Directions.UP);
                break;
            case KeyEvent.VK_A:
                gamePanel.movePlayer(Directions.LEFT);
                break;
            case KeyEvent.VK_S:
                gamePanel.movePlayer(Directions.DOWN);
                break;
            case KeyEvent.VK_D:
                gamePanel.movePlayer(Directions.RIGHT);
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
                gamePanel.movePlayer(Directions.STEADY);
                break;
            case KeyEvent.VK_SPACE:
                break;
        }

    }

}
