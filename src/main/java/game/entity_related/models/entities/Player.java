package game.entity_related.models.entities;

import game.entity_related.animation_related.ObjectAnimationPlayer;
import game.entity_related.animation_related.Sprite;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Player extends Movable {

    // Constantes de configuração
    private static final int CANVAS_WIDTH = 80;
    private static final int CANVAS_HEIGHT = 80;
    private static final int OFFSET_X = 54;
    private static final int OFFSET_Y = 60;
    private static final int RENDER_WIDTH = CANVAS_WIDTH * 2;
    private static final int RENDER_HEIGHT = CANVAS_HEIGHT * 2;

    // Flags de estado
    private boolean showingWeapon = false;
    private boolean attacking = false;
    private boolean usingWeapon = false;

    // Sprites e animações
    private BufferedImage dexGunSprite;
    private BufferedImage bodySpriteSheet;
    private BufferedImage headSpriteSheet;

    private ObjectAnimationPlayer weaponAniPlayer = new ObjectAnimationPlayer();
    private ObjectAnimationPlayer bodyAniPlayer = new ObjectAnimationPlayer();
    private ObjectAnimationPlayer headAniPlayer = new ObjectAnimationPlayer();


    private List<Sprite> idleBodyAni = new ArrayList<>();
    private List<Sprite> idleHeadAni = new ArrayList<>();

    private List<Sprite> idleHeadEyesClosedAni = new ArrayList<>();

    private List<Sprite> upLookHeadAni = new ArrayList<>();
    private List<Sprite> downLookHeadAni = new ArrayList<>();

    private List<Sprite> damageHeadAni = new ArrayList<>();
    private List<Sprite> damageBodyAni = new ArrayList<>();

    private List<Sprite> runBodyAni = new ArrayList<>();
    private List<Sprite> runHeadAni = new ArrayList<>();

    private List<Sprite> jumpBodyAni = new ArrayList<>();
    private List<Sprite> jumpHeadAni = new ArrayList<>();

    private List<Sprite> fallBodyAni = new ArrayList<>();
    private List<Sprite> fallHeadAni = new ArrayList<>();

    private List<Sprite> dexGunUpAni = new ArrayList<>();
    private List<Sprite> dexGunDownAni = new ArrayList<>();
    private List<Sprite> dexGunFowardAni = new ArrayList<>();

    public Player(int posX, int posY, int width, int height, double weight) {
        super(posX, posY, width, height, weight);

        setxMaxSpeed(300);
        setyMaxSpeed(800);
        setAccelerating(false);
        setDeceleration(0.7);

        // Inicialização das animações
        initializeDexGunAnimations();
        initializeIdleAnimation();
        initializeHeadEyesClosedAnimation();
        initializeHeadFaceDownAnimation();
        initializeHeadFaceUpAnimation();
        initializeRunAnimation();
        initializeJumpAnimation();
        initializeFallAnimation();
        initializeDamageAnimation();

        bodyAniPlayer.setAnimation("idle");
        headAniPlayer.setAnimation("idle");

    }

    public void initializeHeadEyesClosedAnimation() {
        idleHeadEyesClosedAni.add(new Sprite(2, 0, 1000));
        idleHeadEyesClosedAni.add(new Sprite(3, 0, 1000));

        headAniPlayer.addAnimation("idle-closed_e", idleHeadEyesClosedAni);
    }

    private void initializeHeadFaceUpAnimation() {
        upLookHeadAni.add(new Sprite(0, 3, 1000));

        headAniPlayer.addAnimation("look-up", upLookHeadAni);
    }

    private void initializeHeadFaceDownAnimation() {
        downLookHeadAni.add(new Sprite(1, 3, 1000));

        headAniPlayer.addAnimation("look-down", downLookHeadAni);
    }

    // Método para inicializar as animações da arma de Dex
    private void initializeDexGunAnimations() {
        dexGunFowardAni.add(new Sprite(0, 0, 100));
        dexGunFowardAni.add(new Sprite(1, 0, 80));
        dexGunFowardAni.add(new Sprite(2, 0, 100));
        dexGunFowardAni.add(new Sprite(1, 0, 80));

        dexGunUpAni.add(new Sprite(3, 0, 100));
        dexGunUpAni.add(new Sprite(4, 0, 80));
        dexGunUpAni.add(new Sprite(5, 0, 100));
        dexGunUpAni.add(new Sprite(4, 0, 80));

        dexGunDownAni.add(new Sprite(6, 0, 100));
        dexGunDownAni.add(new Sprite(7, 0, 80));
        dexGunDownAni.add(new Sprite(8, 0, 100));
        dexGunDownAni.add(new Sprite(7, 0, 80));

        weaponAniPlayer.addAnimation("gun-fwd", dexGunFowardAni);
        weaponAniPlayer.addAnimation("gun-uwd", dexGunUpAni);
        weaponAniPlayer.addAnimation("gun-dwd", dexGunDownAni);
    }

    // Método para inicializar as animações de queda
    private void initializeFallAnimation() {
        fallHeadAni.add(new Sprite(1, 2));
        fallHeadAni.add(new Sprite(2, 2));

        fallBodyAni.add(new Sprite(2, 3));
        fallBodyAni.add(new Sprite(3, 3));

        bodyAniPlayer.addAnimation("fall", fallBodyAni);
        headAniPlayer.addAnimation("fall", fallHeadAni);
    }

    // Método para inicializar as animações de pulo
    private void initializeJumpAnimation() {
        jumpBodyAni.add(new Sprite(0, 3));
        jumpBodyAni.add(new Sprite(1, 3));

        jumpHeadAni.add(new Sprite(3, 1));
        jumpHeadAni.add(new Sprite(0, 2));

        bodyAniPlayer.addAnimation("jump", jumpBodyAni);
        headAniPlayer.addAnimation("jump", jumpHeadAni);
    }

    // Método para inicializar as animações de dano
    private void initializeDamageAnimation() {
        damageHeadAni.add(new Sprite(3, 2, 300));

        damageBodyAni.add(new Sprite(0, 4, 300));

        bodyAniPlayer.addAnimation("dmg", damageBodyAni);
        headAniPlayer.addAnimation("dmg", damageHeadAni);
    }

    // Método para inicializar as animações de idle
    private void initializeIdleAnimation() {
        idleBodyAni.add(new Sprite(0, 0, 1000));
        idleBodyAni.add(new Sprite(1, 0, 1000));

        idleHeadAni.add(new Sprite(0, 0, 1000));
        idleHeadAni.add(new Sprite(1, 0, 1000));

        bodyAniPlayer.addAnimation("idle", idleBodyAni);
        headAniPlayer.addAnimation("idle", idleHeadAni);
    }

    // Método para inicializar as animações de corrida
    private void initializeRunAnimation() {
        runBodyAni.add(new Sprite(0, 1, 200));
        runBodyAni.add(new Sprite(1, 1, 20));
        runBodyAni.add(new Sprite(2, 1, 200));
        runBodyAni.add(new Sprite(3, 1, 20));

        runBodyAni.add(new Sprite(0, 2, 200));
        runBodyAni.add(new Sprite(1, 2, 20));
        runBodyAni.add(new Sprite(2, 2, 200));
        runBodyAni.add(new Sprite(3, 2, 20));

        runHeadAni.add(new Sprite(2, 1, 200));
        runHeadAni.add(new Sprite(1, 1, 20));
        runHeadAni.add(new Sprite(0, 1, 200));
        runHeadAni.add(new Sprite(1, 1, 20));

        runHeadAni.add(new Sprite(2, 1, 200));
        runHeadAni.add(new Sprite(1, 1, 20));
        runHeadAni.add(new Sprite(0, 1, 200));
        runHeadAni.add(new Sprite(1, 1, 20));


        bodyAniPlayer.addAnimation("run", runBodyAni);
        headAniPlayer.addAnimation("run", runHeadAni);
    }

    // Método para atualizar as animações automaticamente
    public void updateAnimation(float deltaTime) {
        bodyAniPlayer.update(deltaTime);
        headAniPlayer.update(deltaTime);
        weaponAniPlayer.update(deltaTime);
    }

    public void setAutoUpdateBodyAnimation(boolean autoUpdate) {
        bodyAniPlayer.setAutoUpdateAni(autoUpdate);
    }

    public void setAutoUpdateHeadAnimation(boolean autoUpdate) {
        headAniPlayer.setAutoUpdateAni(autoUpdate);
    }

    public void setAutoUpdateWeaponAnimation(boolean autoUpdate) {
        weaponAniPlayer.setAutoUpdateAni(autoUpdate);
    }

    public ObjectAnimationPlayer getWeaponAniPlayer() {
        return weaponAniPlayer;
    }

    public ObjectAnimationPlayer getBodyAniPlayer() {
        return bodyAniPlayer;
    }

    public ObjectAnimationPlayer getHeadAniPlayer() {
        return headAniPlayer;
    }

    public BufferedImage getDexGunSprite() {
        return dexGunSprite;
    }

    public BufferedImage getBodySpriteSheet() {
        return bodySpriteSheet;
    }

    public BufferedImage getHeadSpriteSheet() {
        return headSpriteSheet;
    }

    public boolean isShowingWeapon() {
        return showingWeapon;
    }


    public void setShowingWeapon(boolean showingWeapon) {
        this.showingWeapon = showingWeapon;
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

    public void setDexGunSprite(BufferedImage dexGunSprite) {
        this.dexGunSprite = dexGunSprite;
    }

    public void setBodySpriteSheet(BufferedImage bodySpriteSheet) {
        this.bodySpriteSheet = bodySpriteSheet;
    }

    public void setHeadSpriteSheet(BufferedImage headSpriteSheet) {
        this.headSpriteSheet = headSpriteSheet;
    }


    public List<Sprite> getUpLookHeadAni() {
        return upLookHeadAni;
    }

    public List<Sprite> getDownLookHeadAni() {
        return downLookHeadAni;
    }

    public List<Sprite> getIdleBodyAni() {
        return idleBodyAni;
    }

    public List<Sprite> getIdleHeadAni() {
        return idleHeadAni;
    }

    public List<Sprite> getDamageBodyAni() {
        return damageBodyAni;
    }

    public List<Sprite> getDamageHeadAni() {
        return damageHeadAni;
    }

    public List<Sprite> getRunBodyAni() {
        return runBodyAni;
    }

    public List<Sprite> getRunHeadAni() {
        return runHeadAni;
    }

    public List<Sprite> getJumpBodyAni() {
        return jumpBodyAni;
    }

    public List<Sprite> getJumpHeadAni() {
        return jumpHeadAni;
    }

    public List<Sprite> getFallHeadAni() {
        return fallHeadAni;
    }

    public List<Sprite> getFallBodyAni() {
        return fallBodyAni;
    }

    public List<Sprite> getDexGunFowardAni() {
        return dexGunFowardAni;
    }

    public List<Sprite> getDexGunDownAni() {
        return dexGunDownAni;
    }

    public List<Sprite> getDexGunUpAni() {
        return dexGunUpAni;
    }

    public static int GET_CANVAS_WIDTH() {
        return CANVAS_WIDTH;
    }

    public static int GET_CANVAS_HEIGHT() {
        return CANVAS_HEIGHT;
    }

    public static int GET_OFFSET_X() {
        return OFFSET_X;
    }

    public static int GET_OFFSET_Y() {
        return OFFSET_Y;
    }

    public static int GET_RENDER_WIDTH() {
        return RENDER_WIDTH;
    }

    public static int GET_RENDER_HEIGHT() {
        return RENDER_HEIGHT;
    }

}
