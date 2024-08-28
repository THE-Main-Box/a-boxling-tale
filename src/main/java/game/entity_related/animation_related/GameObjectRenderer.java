package game.entity_related.animation_related;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class GameObjectRenderer {

    public void renderPlayer(Graphics g, BufferedImage playerSprite, int x, int y, int renderWidth, int renderHeight, ImageObserver observer){
        g.drawImage(
                playerSprite,
                x,
                y,
                renderWidth,
                renderHeight,
                observer
        );
    }

}
