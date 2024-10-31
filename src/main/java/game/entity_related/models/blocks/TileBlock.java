package game.entity_related.models.blocks;

import game.entity_related.animation_related.entity_renderer.GameObjectRenderer;
import game.entity_related.animation_related.sprite_related.ObjectAnimationPlayer;
import game.entity_related.animation_related.sprite_related.Sprite;
import game.entity_related.models.BlockType;
import game.entity_related.models.entities.GameObject;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class TileBlock extends GameObject {

    protected boolean animated;

    protected BlockType blockType;

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

    public List<Sprite> getSprites() {
        return sprites;
    }

    public BlockType getBlockType() {
        return blockType;
    }

    public void setBlockType(BlockType blockType) {
        this.blockType = blockType;
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
