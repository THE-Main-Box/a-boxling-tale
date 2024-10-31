package game.entity_related.models.blocks;

import game.entity_related.animation_related.entity_renderer.BlockRenderer;
import game.entity_related.models.BlockType;

import java.awt.*;
import java.awt.image.BufferedImage;

public class StaticTileBlock extends TileBlock{
    private final int
            CANVAS_WIDTH = 40,
            CANVAS_HEIGHT = 40,
            RENDER_WIDTH = CANVAS_WIDTH * 2,
            RENDER_HEIGHT = CANVAS_HEIGHT * 2;

    private BlockRenderer renderer;

    public static BufferedImage spriteSheet;

    public StaticTileBlock(float xPos, float yPos, int width, int height, boolean animated) {
        super(xPos, yPos, width, height, animated);
        this.blockType.add(BlockType.STATIC);

        renderer = new BlockRenderer(this);
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

    public BlockRenderer getRenderer() {
        return renderer;
    }

    public void setRenderer(BlockRenderer renderer) {
        this.renderer = renderer;
    }
}
