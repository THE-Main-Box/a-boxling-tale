package game.entity_related.animation_related.entity_renderer;

import game.entity_related.models.blocks.StaticTileBlock;

import java.awt.*;

public class StaticBlockRenderer {

    private GameObjectRenderer renderer;        // usa o renderizador padrão de blocos
    private StaticTileBlock staticTileBlock;    // bloco statico para ser renderizado

    public StaticBlockRenderer() {      //inicializa o renderizador padrão
        this.renderer = new GameObjectRenderer();
    }

    public void render(Graphics g, StaticTileBlock staticTileBlock){ // chama a logica da renderização do bloco estatico

        this.staticTileBlock = staticTileBlock;

        if(staticTileBlock.isAnimated()){ // verifica se o bloco é animado ou não para renderizar de acordo
            renderAnimatedStaticBlock(g);
        } else {
            this.renderInanimatedStaticBlock(g);
        }

    }

    private void renderAnimatedStaticBlock(Graphics g){
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

    private void renderInanimatedStaticBlock(Graphics g){
        renderer.render(
                g,
                staticTileBlock.getSpriteByIndex(
                        staticTileBlock.getCurrentSprite().getIndexX(),
                        staticTileBlock.getCurrentSprite().getIndexY(),
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
