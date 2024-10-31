package game.entity_related.animation_related.entity_renderer;

import game.entity_related.models.blocks.StaticTileBlock;
import game.entity_related.models.blocks.TileBlock;

import java.awt.*;

public class BlockRenderer {

    private StaticTileBlock staticTileBlock;
    private StaticBlockRenderer staticBlockRenderer;

    public BlockRenderer(StaticTileBlock staticTileBlock) {
        this.staticTileBlock = staticTileBlock;
        this.staticBlockRenderer = new StaticBlockRenderer(staticTileBlock);
    }

    public void render(Graphics g) {

        if(staticTileBlock != null){
            staticBlockRenderer.render(g);
        }

    }


}
