package game.entity_related.animation_related.entity_renderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class GameObjectRenderer {

    public void render(Graphics g, BufferedImage spriteToRender, int x, int y, int renderWidth, int renderHeight, ImageObserver observer){
                                // renderização de sprites
        g.drawImage(
                spriteToRender, // renderiza o sprite
                x,              //passado com a posição x, y da imagem
                y,
                renderWidth,    //  e com a largura e altura com que deve m ser renderizados
                renderHeight,
                observer
        );
    }

}
