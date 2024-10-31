package game.window_related;

import game.entity_related.animation_related.entity_renderer.PlayerRenderer;
import game.entity_related.animation_related.sprite_related.Sprite;
import game.entity_related.entity_controller.PlayerController;
import game.entity_related.models.Directions;
import game.entity_related.models.blocks.StaticTileBlock;
import game.input_related.KeyboardInput;
import game.input_related.MouseInput;
import game.entity_related.models.entities.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {

    private Player player;
    private PlayerController playerController;

    private KeyboardInput keyInputs;
    private MouseInput mouseInputs;

    private StaticTileBlock testBlock= new StaticTileBlock(50,10,40,40,false);

    public GamePanel(Player player) {
        this.player = player;
        this.playerController = new PlayerController(player);

        keyInputs = new KeyboardInput(this);
        mouseInputs = new MouseInput(this);

        setPanelSize();
        setFocusable(true);

        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        addKeyListener(keyInputs);

    }

    private void setPanelSize() {
        Dimension size = new Dimension(1080, 720);

        setPreferredSize(size);
        setMaximumSize(size);
        setMinimumSize(size);
    }

    public void movePlayer(Directions playerDirection) {

        if (playerDirection.equals(Directions.RIGHT)) {
            playerController.moveRight();
        }
        if (playerDirection.equals(Directions.LEFT)) {
            playerController.moveLeft();
        }
        if (playerDirection.equals(Directions.UP)) {
            playerController.moveUp();
        }
        if (playerDirection.equals(Directions.DOWN)) {
            playerController.moveDown();
        }

        if (playerDirection.equals(Directions.STEADY)) {
            playerController.stopMoving();
        }

    }

    public void attack(int x, int y) {

        if (player.getxPos() + (player.getWidth() / 2) > x) { // caso ataque seja para trás
            playerController.attack(Directions.LEFT);
        } else if (player.getxPos() + (player.getWidth() / 2) < x) { // caso seja para frente
            playerController.attack(Directions.RIGHT);
        }

        if (player.getyPos() > y) {  // caso seja para cima
            playerController.attack(Directions.UP);
        } else if (player.getyPos() + player.getHeight() < y) { //caso seja para baixo
            playerController.attack(Directions.DOWN);
        }

    }

    public void updateGame(float deltaTime) {
        player.update(deltaTime);          // Atualiza a animação do jogador com deltaTime

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        player.render(g);
        testBlock.render(g);

    }
}
