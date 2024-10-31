package game.entity_related.animation_related.entity_renderer;

import game.entity_related.models.entities.Player;

import java.awt.*;

public class PlayerRenderer {

    private int spritePosX,spritePosY;
    private int spriteRenderWidth, spriteRenderHeight;

    private GameObjectRenderer gameObjectRenderer;
    private Player player;

    public PlayerRenderer(Player player) {
        this.gameObjectRenderer = new GameObjectRenderer();
        this.player = player;
    }

    private void updateRenderVariables() {
        if (player.isFacingForward()) {
            spritePosX = (int) (player.getxPos() - player.GET_OFFSET_X());
            spritePosY = (int) (player.getyPos() - player.GET_OFFSET_Y());
            spriteRenderWidth = player.GET_RENDER_WIDTH();
            spriteRenderHeight = player.GET_RENDER_HEIGHT();
        } else {
            spritePosX = (int) (player.getxPos() + player.GET_CANVAS_WIDTH() - player.GET_OFFSET_X() / 2 + player.getWidth());
            spritePosY = (int) (player.getyPos() - player.GET_OFFSET_Y());
            spriteRenderWidth = -player.GET_RENDER_WIDTH();
            spriteRenderHeight = player.GET_RENDER_HEIGHT();
        }
    }


    private void renderPLayerBody(Graphics g) {
        this.gameObjectRenderer.render(
                g,
                player.getSpriteByIndex(
                        player.getBodyAniPlayer().getCurrentSprite().getIndexX(),
                        player.getBodyAniPlayer().getCurrentSprite().getIndexY(),
                        player.GET_CANVAS_WIDTH(),
                        player.GET_CANVAS_HEIGHT(),
                        Player.bodySpriteSheet
                ),
                spritePosX,
                spritePosY,
                spriteRenderWidth,
                spriteRenderHeight,
                null
        );
    }

    private void renderPLayerWeapon(Graphics g) {
        this.gameObjectRenderer.render(
                g,
                player.getSpriteByIndex(
                        player.getWeaponAniPlayer().getCurrentSprite().getIndexX(),
                        player.getWeaponAniPlayer().getCurrentSprite().getIndexY(),
                        Player.GET_CANVAS_WIDTH(),
                        Player.GET_CANVAS_HEIGHT(),
                        Player.dexWeaponSpriteSheet
                ),
                spritePosX,
                spritePosY,
                spriteRenderWidth,
                spriteRenderHeight,
                null
        );
    }

    private void renderPLayerHead(Graphics g) {
        this.gameObjectRenderer.render(
                g,
                player.getSpriteByIndex(
                        player.getHeadAniPlayer().getCurrentSprite().getIndexX(),
                        player.getHeadAniPlayer().getCurrentSprite().getIndexY(),
                        Player.GET_CANVAS_WIDTH(),
                        Player.GET_CANVAS_HEIGHT(),
                        Player.headSpriteSheet
                ),
                spritePosX,
                spritePosY,
                spriteRenderWidth,
                spriteRenderHeight,
                null
        );
    }

    private void showColision(Graphics g){
        g.drawRect( // mostra as dimensões do jogador
                (int) player.getxPos(),
                (int) player.getyPos(),
                player.getWidth(),
                player.getHeight()
        );
    }

    public void render(Graphics g){
        this.updateRenderVariables();

        this.renderPLayerBody(g);
        this.renderPLayerHead(g);

        this.showColision(g);

        if(this.player.isUsingWeapon()) {
            this.renderPLayerWeapon(g);
        }

    }

}
