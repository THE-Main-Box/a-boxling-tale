package game.input_related;

import game.entity_related.models.Directions;
import game.window_related.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener {

    private GamePanel gamePanel;

    private boolean isKeyArrowUpLastPressed = false;
    private boolean isKeyArrowLeftLastPressed = false;
    private boolean isKeyArrowDownLastPressed = false;
    private boolean isKeyArrowRightLastPressed = false;

    public KeyboardInput(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:

                gamePanel.movePlayer(Directions.UP);

                break;
            case KeyEvent.VK_LEFT:
                // insere o input de movimentação para esquerda e define essa tecla como a ultima a ser precionada
                // para impedir que o input dela cancele a da sua tecla oposta
                gamePanel.movePlayer(Directions.LEFT);

                isKeyArrowLeftLastPressed = true;
                isKeyArrowRightLastPressed = false;

                break;
            case KeyEvent.VK_DOWN:

                gamePanel.movePlayer(Directions.DOWN);

                break;
            case KeyEvent.VK_RIGHT:

                gamePanel.movePlayer(Directions.RIGHT);

                isKeyArrowLeftLastPressed = false;
                isKeyArrowRightLastPressed = true;

                break;
            case KeyEvent.VK_SPACE:



                break;
            case KeyEvent.VK_X:



                break;
            case KeyEvent.VK_Z:



                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:

                gamePanel.movePlayer(Directions.STEADY);

                break;
            case KeyEvent.VK_LEFT:

                if(isKeyArrowLeftLastPressed) {
                    gamePanel.movePlayer(Directions.STEADY);
                }

                break;
            case KeyEvent.VK_DOWN:

                gamePanel.movePlayer(Directions.STEADY);

                break;
            case KeyEvent.VK_RIGHT:

                if(isKeyArrowRightLastPressed){
                    gamePanel.movePlayer(Directions.STEADY);
                }

                break;
            case KeyEvent.VK_SPACE:



                break;
            case KeyEvent.VK_X:



                break;
            case KeyEvent.VK_Z:



                break;
        }

    }

}
