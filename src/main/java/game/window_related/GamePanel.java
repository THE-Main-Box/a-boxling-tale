package game.window_related;

import game.entity_related.animation_related.GameObjectRenderer;
import game.entity_related.animation_related.Sprite;
import game.entity_related.entity_controller.PlayerMovement;
import game.entity_related.models.Movement;
import game.input_related.KeyboardInput;
import game.input_related.MouseInput;
import game.entity_related.models.entities.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class GamePanel extends JPanel {

    private Player player = new Player(0, 0, 52, 60, 5);
    private PlayerMovement playerMovement;
    private GameObjectRenderer renderer = new GameObjectRenderer();

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

        player.setCurrentAttackAnimation(player.getDexGunFowardAni());

        player.setShowingGun(true);
        player.setUsingWeapon(true);
        player.setAttacking(true);
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
        if (playerDirection.equals(Movement.UP)) {
            playerMovement.moveUp();
        }
        if (playerDirection.equals(Movement.DOWN)) {
            playerMovement.moveDown();
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

        if (player.isAttacking()) {
            player.updateAttackAnimation(deltaTime);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Sprite currentSprite = player.getCurrentSprite();

        int spritePosX;
        int spritePosY;
        int spriteRenderWidth;
        int spriteRenderHeight;

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

        renderer.renderPlayer(
                g,
                player.getSpriteByIndex( //renderizo a imagem correspondente aos indexes do sprite atual na sprite-sheet
                        currentSprite.getIndexX(),  // index x do sprite no canvas
                        currentSprite.getIndexY(),  // index y do sprite no canvas
                        player.getCanvasWidth(),    // tamanho da largura canvas com que foi desenhado o sprite
                        player.getCanvasHeight(),   // tamanho da altura canvas com que foi desenhado o sprite
                        player.getSpriteSheet()
                ),
                spritePosX,         //posição horizontal do sprite em relação à hitbox do jogador
                spritePosY,         //posição vertical do sprite em relação à hitbox do jogador
                spriteRenderWidth,  //largura de renderização do sprite
                spriteRenderHeight, //altura de renderização do sprite
                null
        );


        if(player.isUsingWeapon() && player.isShowingGun()) {
            renderer.renderPlayer(
                    g,
                    player.getGunSpriteByIndex(
                            player.getCurrentAttackSprite().getIndexX(),
                            player.getCurrentAttackSprite().getIndexY()
                    ),
                    spritePosX,
                    spritePosY,
                    spriteRenderWidth,
                    spriteRenderHeight,
                    null
            );
        }

        g.drawRect( // mostra as dimensões do jogador
                player.getxPos(),
                player.getyPos(),
                player.getWidth(),
                player.getHeight()
        );
    }

    private void importPlayerSpriteSheet() { // importa e define a sprite sheet do jogador direto da pasta resources
        try {
            InputStream rockGround = getClass().getResourceAsStream("/sprites/rock-ground-tile-map.png");
            InputStream dexSprites = getClass().getResourceAsStream("/sprites/dex-sprites.png");
            InputStream dexGunSprites = getClass().getResourceAsStream("/sprites/dex-gun.png");

            assert rockGround != null;
            assert dexSprites != null;

            player.setSpriteSheet(ImageIO.read(dexSprites));
            player.setDexGunSprite(ImageIO.read(dexGunSprites));

        } catch (IOException e) {
            throw new RuntimeException("Erro na importação da sprite sheet do jogador");
        }
    }
}
