package game.window_related;

import game.entity_related.animation_related.Sprite;
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

    private KeyboardInput keyInputs;
    private MouseInput mouseInputs;

    public GamePanel() {
        importPlayerSpriteSheet();

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

    public void movePlayer(String playerAction) {

        if(playerAction.equals("move-right")){
            player.setAutoUpdateAni(true);
            player.setFacingForward(true);
            player.setAccelerating(true);
            player.setxAcceleration(50f);
            player.setAnimation(player.getRunAni());
        }
        if(playerAction.equals("move-left")){
            player.setAutoUpdateAni(true);
            player.setFacingForward(false);
            player.setAccelerating(true);
            player.setxAcceleration(-50f);
            player.setAnimation(player.getRunAni());
        }

        if(playerAction.equals("stop-moving")){
            player.setAccelerating(false);
            player.setAnimation(player.getIdleAni());
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
                player.getSpriteByIndex(currentSprite.getIndexX(), currentSprite.getIndexY(), player.getCanvasWidth(), player.getCanvasHeight()),
                spritePosX,
                spritePosY,
                spriteRenderWidth,
                spriteRenderHeight,
                null
        );

        g.drawRect(
                player.getxPos(),
                player.getyPos(),
                player.getWidth(),
                player.getHeight()
        );
    }

    private void importPlayerSpriteSheet() {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/sprites/dex-sprites.png");

            assert inputStream != null;
            player.setSpriteSheet(ImageIO.read(inputStream));
        } catch (IOException e) {
            throw new RuntimeException("Erro na importação da sprite sheet do jogador");
        }
    }
}
