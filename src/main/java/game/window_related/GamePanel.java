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

    private Player player = new Player(0, 0, 52, 60, 10);

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

    public void movePlayer(char direction, double acceleration) {
        if (direction == 'X') {

            player.setxAcceleration(acceleration);

            if (acceleration > 0) {
                player.setFacingForward(true);
                player.setAnimation(player.getRunAni());
            } else if (acceleration < 0) {
                player.setFacingForward(false);
                player.setAnimation(player.getRunAni());
            } else {
                player.setAnimation(player.getIdleAni());
            }

        } else {

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

        if (player.isFacingForward()) {
            g.drawImage(
                    player.getSpriteByIndex(currentSprite.getIndexX(), currentSprite.getIndexY(), player.getCanvasWidth(), player.getCanvasHeight()),
                    player.getxPos() - player.getOffsetX(),
                    player.getyPos() - player.getOffsetY(),
                    player.getRenderWidth(),
                    player.getRenderHeight(),
                    null
            );
        } else {
            g.drawImage(
                    player.getSpriteByIndex(currentSprite.getIndexX(), currentSprite.getIndexY(), player.getCanvasWidth(), player.getCanvasHeight()),
                    player.getxPos() + player.getCanvasWidth() - player.getOffsetX() / 2 + player.getWidth(),
                    player.getyPos() - player.getOffsetY(),
                    -player.getRenderWidth(),
                    player.getRenderHeight(),
                    null
            );
        }

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
