package game.entity_related.models.blocks;

import game.entity_related.animation_related.entity_renderer.StaticTileBlockRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class StaticTileBlock extends TileBlock{
    private final int
            CANVAS_WIDTH = 40,
            CANVAS_HEIGHT = 40,
            RENDER_WIDTH = CANVAS_WIDTH * 2,
            RENDER_HEIGHT = CANVAS_HEIGHT * 2;

    private StaticTileBlockRenderer renderer;

    public static BufferedImage spriteSheet;

    public StaticTileBlock(float xPos, float yPos, int width, int height, boolean animated) {
        super(xPos, yPos, width, height, animated);

        renderer = new StaticTileBlockRenderer(this);
    }

    public void render(Graphics g){
        renderer.render(g);
    }

    public int getCANVAS_WIDTH() {
        return CANVAS_WIDTH;
    }

    public int getCANVAS_HEIGHT() {
        return CANVAS_HEIGHT;
    }

    public int getRENDER_WIDTH() {
        return RENDER_WIDTH;
    }

    public int getRENDER_HEIGHT() {
        return RENDER_HEIGHT;
    }

    public StaticTileBlockRenderer getRenderer() {
        return renderer;
    }

    public void setRenderer(StaticTileBlockRenderer renderer) {
        this.renderer = renderer;
    }
}
