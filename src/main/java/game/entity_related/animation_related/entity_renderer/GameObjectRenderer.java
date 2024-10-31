package game.entity_related.animation_related.entity_renderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class GameObjectRenderer {

    public void render(Graphics g, BufferedImage spriteToRender, int x, int y, int renderWidth, int renderHeight, ImageObserver observer){
        g.drawImage(
                spriteToRender,
                x,
                y,
                renderWidth,
                renderHeight,
                observer
        );
    }

}
