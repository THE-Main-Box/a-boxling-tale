package game.entity_related.models.blocks;

import game.entity_related.animation_related.entity_renderer.GameObjectRenderer;
import game.entity_related.animation_related.sprite_related.ObjectAnimationPlayer;
import game.entity_related.animation_related.sprite_related.Sprite;
import game.entity_related.models.entities.GameObject;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class TileBlock extends GameObject {

    protected BufferedImage tileSheet;
    protected boolean animated;

    protected ObjectAnimationPlayer animationPlayer;

    protected List<Sprite> sprites = new ArrayList<>();

    public TileBlock(float xPos, float yPos, int width, int height, boolean animated) {
        super(xPos, yPos, width, height);

        this.animated = animated;
        animationPlayer = new ObjectAnimationPlayer();

        animationPlayer.setCurrentAnimation(sprites);

        if(!animated){
            animationPlayer.setAutoUpdateAni(false);
            animationPlayer.setAniTick(0);
        }

    }

    public BufferedImage getTileSheet() {
        return tileSheet;
    }

    public void setTileSheet(BufferedImage tileSheet) {
        this.tileSheet = tileSheet;
    }

    public boolean isAnimated() {
        return animated;
    }

    public void setAnimated(boolean animated) {
        this.animated = animated;
    }

    public ObjectAnimationPlayer getAnimationPlayer() {
        return animationPlayer;
    }
}
