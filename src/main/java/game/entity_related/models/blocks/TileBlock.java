package game.entity_related.models.blocks;

import game.entity_related.animation_related.sprite_related.BlockSpriteDefiner;
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
    protected BlockSpriteDefiner blockSpriteDefiner;

    protected List<Sprite> sprites = new ArrayList<>();

    public TileBlock(float xPos, float yPos, int width, int height, boolean animated) {
        super(xPos, yPos, width, height);

        this.animated = animated;

        if(animated){
            animationPlayer = new ObjectAnimationPlayer();
            animationPlayer.setCurrentAnimation(sprites);
        } else {
            sprites.add(new Sprite(0,0));
            blockSpriteDefiner = new BlockSpriteDefiner(sprites.getFirst()); // seta o sprite com base na lista de sprites
        }

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

    public boolean isAnimated() {
        return animated;
    }

    public void setAnimated(boolean animated) {
        this.animated = animated;
    }

    public ObjectAnimationPlayer getAnimationPlayer() {
        return animationPlayer;
    }

    public BlockSpriteDefiner getBlockSpriteDefiner() {
        return blockSpriteDefiner;
    }

    public void setCurrentInanimatedSprite(int index){
        this.blockSpriteDefiner.setSprite(sprites.get(index));
    }
}
