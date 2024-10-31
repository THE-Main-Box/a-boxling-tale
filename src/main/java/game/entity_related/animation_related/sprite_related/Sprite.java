package game.entity_related.animation_related.sprite_related;

public class Sprite {
    int indexX, indexY;
    long duration;

    public Sprite(int indexX, int indexY, long duration) {
        this.indexX = indexX;
        this.indexY = indexY;
        this.duration = duration;
    }

    public Sprite(int indexX, int indexY) {
        this.indexX = indexX;
        this.indexY = indexY;
        this.duration = -1;
    }

    public int getIndexX() {
        return indexX;
    }

    public void setIndexX(int indexX) {
        this.indexX = indexX;
    }

    public int getIndexY() {
        return indexY;
    }

    public void setIndexY(int indexY) {
        this.indexY = indexY;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
