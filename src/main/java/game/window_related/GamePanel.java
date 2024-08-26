package game.window_related;

import game.entity_related.animation_related.Sprite;
import game.entity_related.entity_controller.PlayerMovement;
import game.entity_related.models.Movement;
import game.input_related.KeyboardInput;
import game.input_related.MouseInput;
import game.entity_related.models.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class GamePanel extends JPanel {

    private Player player = new Player(0, 0, 52, 60, 5);
    private PlayerMovement playerMovement;

    private KeyboardInput keyInputs;
    private MouseInput mouseInputs;

    public GamePanel() {
        importPlayerSpriteSheet();

        playerMovement = new PlayerMovement(player);

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

    public void movePlayer(Movement playerDirection) {

        if (playerDirection.equals(Movement.RIGHT)) {
            playerMovement.moveRight();
        }
        if (playerDirection.equals(Movement.LEFT)) {
            playerMovement.moveLeft();
        }

        if (playerDirection.equals(Movement.STOP)) {
            playerMovement.stopMoving();
        }

    }

    public void changePlayerPos(int x, int y) {
        player.setxPos(x);
        player.setyPos(y);
    }

    public void updateGame(float deltaTime) {
        player.updateAnimation(deltaTime); // Atualiza a animação do jogador com deltaTime
        player.updatePosition(deltaTime);  // Atualiza a posição do jogador com delta time
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Sprite currentSprite = player.getCurrentSprite();

        int spritePosX = 0;
        int spritePosY = 0;
        int spriteRenderWidth = 0;
        int spriteRenderHeight = 0;

        /* verifica se o jogador está virado para frente ou para tras
         * e altera os valores para manter os sprites centralizados*/
        if (player.isFacingForward()) {
            spritePosX = player.getxPos() - player.getOffsetX();
            spritePosY = player.getyPos() - player.getOffsetY();
            spriteRenderWidth = player.getRenderWidth();
            spriteRenderHeight = player.getRenderHeight();
        } else {
            spritePosX = player.getxPos() + player.getCanvasWidth() - player.getOffsetX() / 2 + player.getWidth();
            spritePosY = player.getyPos() - player.getOffsetY();
            spriteRenderWidth = -player.getRenderWidth();
            spriteRenderHeight = player.getRenderHeight();
        }

        g.drawImage(
                player.getSpriteByIndex( //renderizo a imagem correspondente aos indexes do sprite atualna sprite-sheet
                        currentSprite.getIndexX(),  // index x
                        currentSprite.getIndexY(),  // index y
                        player.getCanvasWidth(),    // tamanho da largura canvas com que foi desenhada a pixel art
                        player.getCanvasHeight()    // tamanho da altura canvas com que foi desenhada a pixel art
                ),
                spritePosX,         //posição horizontal do sprite em relação à hitbox do jogador
                spritePosY,         //posição vertical do sprite em relação à hitbox do jogador
                spriteRenderWidth,  //largura de renderização do sprite
                spriteRenderHeight, //altura de renderização do sprite
                null
        );

        g.drawRect( // mostra as dimensões do jogador
                player.getxPos(),
                player.getyPos(),
                player.getWidth(),
                player.getHeight()
        );
    }

    private void importPlayerSpriteSheet() { // importa e define a sprite sheet do jogador direto da pasta resources
        try {
            InputStream inputStream = getClass().getResourceAsStream("/sprites/dex-sprites.png");

            assert inputStream != null;
            player.setSpriteSheet(ImageIO.read(inputStream));
        } catch (IOException e) {
            throw new RuntimeException("Erro na importação da sprite sheet do jogador");
        }
    }
}
