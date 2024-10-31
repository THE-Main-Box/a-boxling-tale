package game.entity_related.animation_related.entity_renderer;

import game.entity_related.models.blocks.StaticTileBlock;

import java.awt.*;

public class StaticTileBlockRenderer {

    private StaticTileBlock staticTileBlock;
    private GameObjectRenderer renderer;

    public StaticTileBlockRenderer(StaticTileBlock staticTileBlock) {
        this.staticTileBlock = staticTileBlock;
        this.renderer = new GameObjectRenderer();
    }

    public void render(Graphics g) {
        renderer.render(
                g,
                staticTileBlock.getSpriteByIndex(
                        staticTileBlock.getAnimationPlayer().getCurrentSprite().getIndexX(),
                        staticTileBlock.getAnimationPlayer().getCurrentSprite().getIndexY(),
                        staticTileBlock.getCANVAS_WIDTH(),
                        staticTileBlock.getCANVAS_HEIGHT(),
                        StaticTileBlock.spriteSheet
                ),
                (int) staticTileBlock.getxPos(),
                (int) staticTileBlock.getyPos(),
                staticTileBlock.getRENDER_WIDTH(),
                staticTileBlock.getRENDER_HEIGHT(),
                null
        );
    }

}
