package game.entity_related.models;

import game.entity_related.animation_related.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Player extends Movable {

    private static final int CANVAS_WIDTH = 80;
    private static final int CANVAS_HEIGHT = 40;
    private static final int OFFSET_X = 54;
    private static final int OFFSET_Y = 20;
    private static final int RENDER_WIDTH = CANVAS_WIDTH * 2;
    private static final int RENDER_HEIGHT = CANVAS_HEIGHT * 2;

    private List<Sprite> idleAni = new ArrayList<>();
    private List<Sprite> runAni = new ArrayList<>();


    public Player(int posX, int posY, int width, int height) {
        super(posX, posY, width, height);

        initializeIdleAni();
        initializeRunAnimation();

        setAnimation(runAni);
    }

    private void initializeIdleAni() {
        idleAni.add(new Sprite(0, 0, 1000));
        idleAni.add(new Sprite(1, 0, 1000));
    }

    private void initializeRunAnimation() {
        runAni.add(new Sprite(0, 1, 300));
        runAni.add(new Sprite(1, 1, 30));
        runAni.add(new Sprite(2, 1, 300));
        runAni.add(new Sprite(3, 1, 30));

        runAni.add(new Sprite(0, 2, 300));
        runAni.add(new Sprite(1, 2, 30));
        runAni.add(new Sprite(2, 2, 300));
        runAni.add(new Sprite(3, 2, 30));
    }



    public List<Sprite> getIdleAni() {
        return idleAni;
    }

    public List<Sprite> getRunAni() {
        return runAni;
    }

    public int getCanvasWidth() {
        return CANVAS_WIDTH;
    }

    public int getCanvasHeight() {
        return CANVAS_HEIGHT;
    }

    public int getOffsetX() {
        return OFFSET_X;
    }

    public int getOffsetY() {
        return OFFSET_Y;
    }

    public int getRenderWidth() {
        return RENDER_WIDTH;
    }

    public int getRenderHeight() {
        return RENDER_HEIGHT;
    }

}
