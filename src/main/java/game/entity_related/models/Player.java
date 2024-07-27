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
    private List<Sprite> jumpAni = new ArrayList<>();
    private List<Sprite> fallAni = new ArrayList<>();
    private List<Sprite> jumpPrepAni = new ArrayList<>(); // Lista para animação de preparação para o pulo

    public Player(int posX, int posY, int width, int height, double weight) {
        super(posX, posY, width, height, weight);

        setXMaxSpeed(250);
        setYMaxSpeed(250);
        setAccelerating(false);
        setDeceleration(0.9);

        initializeIdleAni();
        initializeRunAnimation();
        initializeJumpAnimation();
        initializeFallAnimation();
        initializeJumpPrepAnimation(); // Inicializar animação de preparação para o pulo

        setAnimation(idleAni);
    }

    private void initializeFallAnimation(){
        fallAni.add(new Sprite(2, 3));
        fallAni.add(new Sprite(3, 3));
        fallAni.add(new Sprite(0, 3));
    }

    private void initializeJumpAnimation() {
        jumpAni.add(new Sprite(0, 3));
        jumpAni.add(new Sprite(1, 4));
    }

    private void initializeJumpPrepAnimation() {
        jumpPrepAni.add(new Sprite(0, 3));
        jumpPrepAni.add(new Sprite(0, 3));
    }

    private void initializeIdleAni() {
        idleAni.add(new Sprite(0, 0, 1000));
        idleAni.add(new Sprite(1, 0, 1000));
    }

    private void initializeRunAnimation() {
        runAni.add(new Sprite(0, 1, 200));
        runAni.add(new Sprite(1, 1, 20));
        runAni.add(new Sprite(2, 1, 200));
        runAni.add(new Sprite(3, 1, 20));

        runAni.add(new Sprite(0, 2, 200));
        runAni.add(new Sprite(1, 2, 20));
        runAni.add(new Sprite(2, 2, 200));
        runAni.add(new Sprite(3, 2, 20));
    }

//    @Override
//    public void updateAnimation(float deltaTime) {
//
//
//        super.updateAnimation(deltaTime);
//    }

    public List<Sprite> getIdleAni() {
        return idleAni;
    }

    public List<Sprite> getRunAni() {
        return runAni;
    }

    public List<Sprite> getJumpAni() {
        return jumpAni;
    }

    public List<Sprite> getFallAni() {
        return fallAni;
    }

    public List<Sprite> getJumpPrepAni() {
        return jumpPrepAni;
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
