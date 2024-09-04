package game.entity_related.models.entities;

import game.entity_related.animation_related.PlayerInitAnimations;
import game.entity_related.animation_related.entity_renderer.PlayerRenderer;
import game.entity_related.animation_related.sprite_related.ObjectAnimationPlayer;
import game.entity_related.animation_related.sprite_related.Sprite;
import game.entity_related.animation_validators.PlayerAnimationValidation;

import java.awt.*;
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
    private boolean attacking;

    private boolean usingWeapon;
    private boolean usingItem;

    private boolean facingUpWards;

    private boolean facingDownWards;

    // Validadores de estado
    private PlayerAnimationValidation aniValidator;
    private PlayerInitAnimations initAnimations;
    private PlayerRenderer renderer;

    // Sprites e animações
    private BufferedImage dexGunSpriteSheet;
    private BufferedImage bodySpriteSheet;
    private BufferedImage headSpriteSheet;

    private ObjectAnimationPlayer weaponAniPlayer = new ObjectAnimationPlayer();
    private ObjectAnimationPlayer bodyAniPlayer = new ObjectAnimationPlayer();
    private ObjectAnimationPlayer headAniPlayer = new ObjectAnimationPlayer();


    private final List<Sprite> idleBodyAni = new ArrayList<>();
    private final List<Sprite> idleHeadAni = new ArrayList<>();

    private final List<Sprite> idleHeadEyesClosedAni = new ArrayList<>();

    private final List<Sprite> upLookHeadAni = new ArrayList<>();
    private final List<Sprite> downLookHeadAni = new ArrayList<>();

    private final List<Sprite> damageHeadAni = new ArrayList<>();
    private final List<Sprite> damageBodyAni = new ArrayList<>();

    private final List<Sprite> runBodyAni = new ArrayList<>();
    private final List<Sprite> runHeadAni = new ArrayList<>();

    private final List<Sprite> jumpBodyAni = new ArrayList<>();
    private final List<Sprite> jumpHeadAni = new ArrayList<>();

    private final List<Sprite> fallBodyAni = new ArrayList<>();
    private final List<Sprite> fallHeadAni = new ArrayList<>();

    private final List<Sprite> dexGunUpAni = new ArrayList<>();
    private final List<Sprite> dexGunDownAni = new ArrayList<>();
    private final List<Sprite> dexGunFowardAni = new ArrayList<>();

    public Player(int posX, int posY, int width, int height, double weight) {
        super(posX, posY, width, height, weight);

        setxMaxSpeed(200);
        setyMaxSpeed(800);

        setAcceleratingX(false);
        setAcceleratingY(false);

        setUsingWeapon(false);
        setAttacking(false);

        setFacingForward(true);
        setFacingDownWards(false);
        setFacingUpWards(false);

        setDecelerationX(0.8f);
        setDecelerationY(0.8f);

        // Inicialização das animações
        initAnimations = new PlayerInitAnimations(bodyAniPlayer, weaponAniPlayer, headAniPlayer);
        initiaizePlayerAnimations();

        aniValidator = new PlayerAnimationValidation(this);

        bodyAniPlayer.setAnimation("idle");
        headAniPlayer.setAnimation("idle");

        renderer = new PlayerRenderer(this);

    }

    private void initiaizePlayerAnimations(){
        initAnimations.initializeDexGunAnimations(dexGunFowardAni, dexGunUpAni,dexGunDownAni);
        initAnimations.initializeDamageAnimation(damageBodyAni,damageHeadAni);
        initAnimations.initializeFallAnimation(fallBodyAni,fallHeadAni);
        initAnimations.initializeHeadEyesClosedAnimation(idleHeadEyesClosedAni);
        initAnimations.initializeIdleAnimation(idleBodyAni,idleHeadAni);
        initAnimations.initializeJumpAnimation(jumpBodyAni,jumpHeadAni);
        initAnimations.initializeRunAnimation(runBodyAni,runHeadAni);
        initAnimations.initializeHeadFaceDownAnimation(downLookHeadAni);
        initAnimations.initializeHeadFaceUpAnimation(upLookHeadAni);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        updateAnimation(deltaTime);
    }

    // Método para atualizar as animações automaticamente
    private void updateAnimation(float deltaTime) {
        bodyAniPlayer.update(deltaTime);
        headAniPlayer.update(deltaTime);
        weaponAniPlayer.update(deltaTime);

        aniValidator.validateWeaponAttackFinalSprite();
        validateHeadDirection();
    }

    public void render(Graphics g){
        renderer.render(g);
    }

    private void validateHeadDirection() {
        if(facingDownWards && facingUpWards){

        }else if (facingUpWards) {
            aniValidator.updateHeadAnimation("look-up");
        } else if (facingDownWards) {
            aniValidator.updateHeadAnimation("look-down");
        } else if (!facingUpWards && !facingDownWards && (usingWeapon || usingItem)) {
            aniValidator.updateHeadAnimation("idle");
        }

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

    public BufferedImage getDexGunSpriteSheet() {
        return dexGunSpriteSheet;
    }

    public BufferedImage getBodySpriteSheet() {
        return bodySpriteSheet;
    }

    public BufferedImage getHeadSpriteSheet() {
        return headSpriteSheet;
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

    public void setDexGunSpriteSheet(BufferedImage dexGunSpriteSheet) {
        this.dexGunSpriteSheet = dexGunSpriteSheet;
    }

    public void setBodySpriteSheet(BufferedImage bodySpriteSheet) {
        this.bodySpriteSheet = bodySpriteSheet;
    }

    public void setHeadSpriteSheet(BufferedImage headSpriteSheet) {
        this.headSpriteSheet = headSpriteSheet;
    }

    public boolean isFacingUpWards() {
        return facingUpWards;
    }

    public void setFacingUpWards(boolean facingUpWards) {
        this.facingUpWards = facingUpWards;
    }

    public boolean isFacingDownWards() {
        return facingDownWards;
    }

    public void setFacingDownWards(boolean facingDownWards) {
        this.facingDownWards = facingDownWards;
    }

    public boolean isUsingItem() {
        return usingItem;
    }

    public void setUsingItem(boolean usingItem) {
        this.usingItem = usingItem;
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
