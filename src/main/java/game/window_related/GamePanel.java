package game.window_related;

import game.input_related.KeyboardInput;
import game.input_related.MouseInput;
import game.player_related.Player;

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


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        player.updatePosition();

        g.drawImage(
                player.getSpriteByIndex(0, 0, player.getCanvasWidth(), player.getCanvasHeight()),
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
            throw new RuntimeException("erro na importação da sprite sheet do jogador");
        }
    }
}
