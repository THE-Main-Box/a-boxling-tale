package game.entity_related.animation_related.entity_renderer;

import game.entity_related.models.blocks.StaticTileBlock;
import game.entity_related.models.blocks.TileBlock;

import java.awt.*;

public class BlockRenderer {

    private StaticBlockRenderer staticBlockRenderer;

    // renderizadopr de blocos

    public BlockRenderer() { // inicializa o renderizador para renderizar os blocos staticos
        this.staticBlockRenderer = new StaticBlockRenderer();
    }

    public void render(Graphics g, StaticTileBlock staticTileBlock) {

        if(staticTileBlock != null){
            staticBlockRenderer.render(g, staticTileBlock);
        }

    }


}
