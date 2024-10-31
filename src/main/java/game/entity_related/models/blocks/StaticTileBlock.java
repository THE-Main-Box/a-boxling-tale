package game.entity_related.models.blocks;

import game.entity_related.animation_related.entity_renderer.BlockRenderer;
import game.entity_related.animation_related.sprite_related.Sprite;
import game.entity_related.models.BlockType;

import java.awt.*;
import java.awt.image.BufferedImage;

public class StaticTileBlock extends TileBlock{
    private final int
            CANVAS_WIDTH = 40,
            CANVAS_HEIGHT = 40,
            RENDER_WIDTH = CANVAS_WIDTH * 2,
            RENDER_HEIGHT = CANVAS_HEIGHT * 2;

    public static BufferedImage spriteSheet;

    public StaticTileBlock(float xPos, float yPos, int width, int height, boolean animated, Sprite blockSprite) {
        super(xPos, yPos, width, height, animated);

        this.addBlockType(BlockType.STATIC);

        this.currentSprite = blockSprite;
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
}
