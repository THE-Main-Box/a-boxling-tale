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

    private Player player = new Player(0, 0, 52, 60);

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

    public void movePlayer(double speedX, double speedY) {
        player.setxVelocity(speedX);
        player.setyVelocity(speedY);
    }

    public void changePlayerPos(int x, int y) {
        player.setxPos(x);
        player.setyPos(y);
    }

    public void updateAnimation(float deltaTime) {
        player.updateAnimation(deltaTime); // Atualiza a animação do jogador com deltaTime
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        player.updatePosition(); // Atualiza a posição do jogador
        updateAnimation(0.016f); // Passa um valor padrão para deltaTime; isso deve ser atualizado com o valor real no futuro

        Sprite currentSprite = player.getCurrentSprite();

        g.drawImage(
                player.getSpriteByIndex(currentSprite.getIndexX(), currentSprite.getIndexY(), player.getCanvasWidth(), player.getCanvasHeight()),
                player.getxPos() - player.getOffsetX(),
                player.getyPos() - player.getOffsetY(),
                player.getRenderWidth(),
                player.getRenderHeight(),
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
