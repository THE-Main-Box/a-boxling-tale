package game.entity_related.models.blocks;

import game.entity_related.animation_related.sprite_related.ObjectAnimationPlayer;
import game.entity_related.animation_related.sprite_related.Sprite;
import game.entity_related.models.BlockType;
import game.entity_related.models.entities.GameObject;

import java.util.ArrayList;
import java.util.List;

public abstract class TileBlock extends GameObject {

    protected boolean animated;

    protected List<BlockType> blockType = new ArrayList<>();

    protected ObjectAnimationPlayer animationPlayer;

    protected List<Sprite> sprites;

    protected Sprite currentSprite;

    public TileBlock(float xPos, float yPos, int width, int height, boolean animated) {
        super(xPos, yPos, width, height);

        this.animated = animated;

        if(animated){
            sprites = new ArrayList<>();
            animationPlayer = new ObjectAnimationPlayer();
            animationPlayer.setCurrentAnimation(sprites);
        }

    }

    public Sprite getCurrentSprite() {
        return currentSprite;
    }

    public void setCurrentSprite(Sprite currentSprite) {
        this.currentSprite = currentSprite;
    }

    public List<Sprite> getSprites() {
        return sprites;
    }

    public void setSprites(List<Sprite> sprites) {
        this.sprites = sprites;
    }

    public void addSprites(Sprite spriteToAdd){
        sprites.add(spriteToAdd);
    }

    public List<BlockType> getBlockType() {
        return blockType;
    }

    public void setBlockType(List<BlockType> blockType) {
        this.blockType = blockType;
    }

    public void addBlockType(BlockType blockType){
        this.blockType.add(blockType);
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
