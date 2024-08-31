package game.window_related;

import game.entity_related.animation_related.GameObjectRenderer;
import game.entity_related.animation_related.Sprite;
import game.entity_related.entity_controller.PlayerController;
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

    private Player player = new Player(100, 100, 52, 60, 5);
    private PlayerController playerController;
    private GameObjectRenderer renderer = new GameObjectRenderer();

    private KeyboardInput keyInputs;
    private MouseInput mouseInputs;

    private int spritePosX;
    private int spritePosY;
    private int spriteRenderWidth;
    private int spriteRenderHeight;

    public GamePanel() {
        importPlayerSpriteSheet();

        playerController = new PlayerController(player);

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
            playerController.moveRight();
        }
        if (playerDirection.equals(Movement.LEFT)) {
            playerController.moveLeft();
        }
        if (playerDirection.equals(Movement.UP)) {
            playerController.moveUp();
        }
        if (playerDirection.equals(Movement.DOWN)) {
            playerController.moveDown();
        }

        if (playerDirection.equals(Movement.STOP)) {
            playerController.stopMoving();
        }

    }

    public void attack(int x, int y) {
        if (player.getxPos() + (player.getWidth() / 2) > x) {
            player.setFacingForward(false);
            playerController.shootFoward();
        } else if (player.getxPos() + (player.getWidth() / 2) < x) {
            player.setFacingForward(true);
            playerController.shootFoward();
        }

        if (player.getyPos() > y) {

            playerController.shootUpward();

        } else if (player.getyPos() + player.getHeight() < y) {

            playerController.shootDownward();

        }

    }

    public void changePlayerPos(int x, int y) {
        player.setxPos(x);
        player.setyPos(y);
    }

    public void updateGame(float deltaTime) {
        player.updatePosition(deltaTime);           // Atualiza a posição do jogador
        player.updateAnimation(deltaTime);          // Atualiza a animação do jogador com deltaTime

    }

    private void updateRenderVariables() {
        /* verifica se o jogador está virado para frente ou para tras
         * e altera os valores para manter os sprites centralizados*/
        if (player.isFacingForward()) {
            spritePosX = (int) (player.getxPos() - player.GET_OFFSET_X());
            spritePosY = (int) (player.getyPos() - player.GET_OFFSET_Y());
            spriteRenderWidth = player.GET_RENDER_WIDTH();
            spriteRenderHeight = player.GET_RENDER_HEIGHT();
        } else {
            spritePosX = (int) (player.getxPos() + player.GET_CANVAS_WIDTH() - player.GET_OFFSET_X() / 2 + player.getWidth());
            spritePosY = (int) (player.getyPos() - player.GET_OFFSET_Y());
            spriteRenderWidth = -player.GET_RENDER_WIDTH();
            spriteRenderHeight = player.GET_RENDER_HEIGHT();
        }
    }

    private void renderPLayerBody(Graphics g) {
        renderer.renderPlayer(
                g,
                player.getSpriteByIndex(
                        player.getBodyAniPlayer().getCurrentSprite().getIndexX(),
                        player.getBodyAniPlayer().getCurrentSprite().getIndexY(),
                        player.GET_CANVAS_WIDTH(),
                        player.GET_CANVAS_HEIGHT(),
                        player.getBodySpriteSheet()
                ),
                spritePosX,
                spritePosY,
                spriteRenderWidth,
                spriteRenderHeight,
                null
        );
    }

    private void renderPLayerHead(Graphics g) {
        renderer.renderPlayer(
                g,
                player.getSpriteByIndex(
                        player.getHeadAniPlayer().getCurrentSprite().getIndexX(),
                        player.getHeadAniPlayer().getCurrentSprite().getIndexY(),
                        player.GET_CANVAS_WIDTH(),
                        player.GET_CANVAS_HEIGHT(),
                        player.getHeadSpriteSheet()
                ),
                spritePosX,
                spritePosY,
                spriteRenderWidth,
                spriteRenderHeight,
                null
        );
    }

    private void renderPLayerWeapon(Graphics g) {
        renderer.renderPlayer(
                g,
                player.getSpriteByIndex(
                        player.getWeaponAniPlayer().getCurrentSprite().getIndexX(),
                        player.getWeaponAniPlayer().getCurrentSprite().getIndexY(),
                        player.GET_CANVAS_WIDTH(),
                        player.GET_CANVAS_HEIGHT(),
                        player.getDexGunSprite()
                ),
                spritePosX,
                spritePosY,
                spriteRenderWidth,
                spriteRenderHeight,
                null
        );
    }

    private boolean canStopAttackAnimation() {
        return player.getWeaponAniPlayer().getCurrentAnimation() != null
                && player.isAttacking()
                && player.getWeaponAniPlayer().getCurrentSprite().equals(player.getWeaponAniPlayer().getCurrentAnimation().getLast());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        updateRenderVariables();

        renderPLayerBody(g);
        renderPLayerHead(g);

        if (this.canStopAttackAnimation()) {
            player.setAttacking(false);
            player.setAutoUpdateWeaponAnimation(false);

            player.setAutoUpdateHeadAnimation(true);

            player.getWeaponAniPlayer().setCurrentAnimation(player.getWeaponAniPlayer().getCurrentAnimation());

            player.getHeadAniPlayer().setAnimation(player.getBodyAniPlayer().getCurrentAnimationKey());

        }

        if (player.isShowingWeapon() && player.getWeaponAniPlayer().getCurrentAnimation() != null) {
            renderPLayerWeapon(g);
        }


        g.drawRect( // mostra as dimensões do jogador
                (int) player.getxPos(),
                (int) player.getyPos(),
                player.getWidth(),
                player.getHeight()
        );
    }

    private void importPlayerSpriteSheet() { // importa e define a sprite sheet do jogador direto da pasta resources
        try {
            InputStream rockGround = getClass().getResourceAsStream("/sprites/rock-ground-tile-map.png");
            InputStream dexBodySprites = getClass().getResourceAsStream("/sprites/dex-body-sprites.png");
            InputStream dexHeadSprites = getClass().getResourceAsStream("/sprites/dex-head-sprites.png");
            InputStream dexGunSprites = getClass().getResourceAsStream("/sprites/dex-gun.png");

            assert rockGround != null;
            assert dexHeadSprites != null;
            assert dexBodySprites != null;


            player.setBodySpriteSheet(ImageIO.read(dexBodySprites));
            player.setHeadSpriteSheet(ImageIO.read(dexHeadSprites));

            player.setDexGunSprite(ImageIO.read(dexGunSprites));

        } catch (IOException e) {
            throw new RuntimeException("Erro na importação da sprite sheet do jogador");
        }
    }
}
