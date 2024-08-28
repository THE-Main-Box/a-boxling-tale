package game.entity_related.models.entities;

import game.entity_related.animation_related.Sprite;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Player extends Movable {

    private static final int CANVAS_WIDTH = 80;
    private static final int CANVAS_HEIGHT = 80;
    private static final int OFFSET_X = 54;
    private static final int OFFSET_Y = 60;
    private static final int RENDER_WIDTH = CANVAS_WIDTH * 2;
    private static final int RENDER_HEIGHT = CANVAS_HEIGHT * 2;

    private boolean showingGun = false;
    private boolean attacking = false;
    private boolean usingWeapon = false;

    private BufferedImage dexGunSprite;

    private int attackAniTick;
    private float attackFrameElapsedTime;
    private float attackFrameDuration;
    private Sprite currentAttackSprite;

    private List<Sprite> currentAttackAnimation = new ArrayList<>();

    private List<Sprite> idleAni = new ArrayList<>();
    private List<Sprite> idleEyesClosedAni = new ArrayList<>();
    private List<Sprite> damageAni = new ArrayList<>();
    private List<Sprite> runAni = new ArrayList<>();
    private List<Sprite> jumpAni = new ArrayList<>();
    private List<Sprite> fallAni = new ArrayList<>();
    private List<Sprite> dexGunUpAni = new ArrayList<>();
    private List<Sprite> dexGunDownAni = new ArrayList<>();
    private List<Sprite> dexGunFowardAni = new ArrayList<>();

    public Player(int posX, int posY, int width, int height, double weight) {
        super(posX, posY, width, height, weight);

        setxMaxSpeed(300);
        setyMaxSpeed(800);
        setAccelerating(false);
        setDeceleration(0.7);

        initializeDexGunAnimations();
        initializeIdleAnimation();
        initializeRunAnimation();
        initializeJumpAnimation();
        initializeFallAnimation();
        initializeIdleEyesClosedAnimation();
        initializeDamageAnimation();


        setAnimation(idleAni);
    }

    private void initializeDexGunAnimations() {
        dexGunDownAni.add(new Sprite(4, 0, 200));
        dexGunDownAni.add(new Sprite(5, 0, 200));

        dexGunFowardAni.add(new Sprite(0, 0, 200));
        dexGunFowardAni.add(new Sprite(1, 0, 200));

        dexGunUpAni.add(new Sprite(2, 0, 200));
        dexGunUpAni.add(new Sprite(3, 0, 200));
    }

    private void initializeFallAnimation() {
        fallAni.add(new Sprite(2, 3));
        fallAni.add(new Sprite(3, 3));
    }

    private void initializeJumpAnimation() {
        jumpAni.add(new Sprite(0, 3));
        jumpAni.add(new Sprite(1, 3));
    }

    private void initializeIdleEyesClosedAnimation() {
        idleEyesClosedAni.add(new Sprite(2, 0, 1000));
        idleEyesClosedAni.add(new Sprite(3, 0, 1000));
    }

    private void initializeDamageAnimation() {
        damageAni.add(new Sprite(0, 4, 300));
        damageAni.add(new Sprite(3, 0, 200));
    }

    private void initializeIdleAnimation() {
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


    public void updateAttackAnimation(float deltaTime) {
        if (currentAttackAnimation == null || currentAttackAnimation.isEmpty()) {
            return;
        }

        if (usingWeapon && !attacking) {
            attackAniTick = 0;
        }

        if (!attacking) {
            return;
        }

        attackFrameElapsedTime += deltaTime;
        currentAttackSprite = getCurrentAttackSprite();
        float attackFrameDuration = currentAttackSprite.getDuration() / 1000.0f;

        if (attackFrameElapsedTime >= attackFrameDuration) {
            attackAniTick++;
            attackFrameElapsedTime -= attackFrameDuration;

            if (attackAniTick >= currentAttackAnimation.size()) {
                attackAniTick = 0;
            }

        }

    }

    public Sprite getCurrentAttackSprite() {
        return currentAttackAnimation.get(attackAniTick);
    }

    public List<Sprite> getDexGunUpAni() {
        return dexGunUpAni;
    }

    public List<Sprite> getDexGunDownAni() {
        return dexGunDownAni;
    }

    public List<Sprite> getDexGunFowardAni() {
        return dexGunFowardAni;
    }

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

    public List<Sprite> getIdleEyesClosedAni() {
        return idleEyesClosedAni;
    }

    public List<Sprite> getDamageAni() {
        return damageAni;
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


    public List<Sprite> getCurrentAttackAnimation() {
        return currentAttackAnimation;
    }

    public void setCurrentAttackAnimation(List<Sprite> currentAttackAnimation) {
        this.currentAttackAnimation = currentAttackAnimation;
        attackAniTick = 0;
        attackFrameElapsedTime = 0;
    }

    public BufferedImage getDexGunSprite() {
        return dexGunSprite;
    }

    public void setDexGunSprite(BufferedImage dexGunSprite) {
        this.dexGunSprite = dexGunSprite;
    }

    public boolean isShowingGun() {
        return showingGun;
    }

    public void setShowingGun(boolean showingGun) {
        this.showingGun = showingGun;
    }

    public boolean isAttacking() {
        return attacking;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public boolean isUsingWeapon() {
        return usingWeapon;
    }

    public void setUsingWeapon(boolean usingWeapon) {
        this.usingWeapon = usingWeapon;
    }

    public void setCurrentAttackSprite(Sprite currentAttackSprite) {
        this.currentAttackSprite = currentAttackSprite;
    }

    public BufferedImage getGunSpriteByIndex(int x, int y) {
        return dexGunSprite.getSubimage(x * CANVAS_WIDTH, y * CANVAS_HEIGHT, CANVAS_WIDTH, CANVAS_HEIGHT);
    }

}
