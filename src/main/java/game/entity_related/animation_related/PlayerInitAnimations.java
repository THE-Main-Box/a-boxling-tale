package game.entity_related.animation_related;

import game.entity_related.animation_related.sprite_related.ObjectAnimationPlayer;
import game.entity_related.animation_related.sprite_related.Sprite;

import java.util.List;

public class PlayerInitAnimations {

    private ObjectAnimationPlayer bodyAniPlayer;
    private ObjectAnimationPlayer weaponAniPlayer;
    private ObjectAnimationPlayer headAniPlayer;

    public PlayerInitAnimations(ObjectAnimationPlayer bodyPlayer, ObjectAnimationPlayer weaponPlayer, ObjectAnimationPlayer headPlayer) {
        this.bodyAniPlayer = bodyPlayer;
        this.weaponAniPlayer = weaponPlayer;
        this.headAniPlayer = headPlayer;
    }

    public void initializeHeadEyesClosedAnimation(List<Sprite> idleHeadEyesClosedAni) {
        idleHeadEyesClosedAni.add(new Sprite(2, 0, 1000));
        idleHeadEyesClosedAni.add(new Sprite(3, 0, 1000));

        headAniPlayer.addAnimation("idle-closed_e", idleHeadEyesClosedAni);
    }

    public void initializeHeadFaceUpAnimation(List<Sprite> upLookHeadAni) {
        upLookHeadAni.add(new Sprite(0, 3, 1000));

        headAniPlayer.addAnimation("look-up", upLookHeadAni);
    }

    public void initializeHeadFaceDownAnimation(List<Sprite> downLookHeadAni) {
        downLookHeadAni.add(new Sprite(1, 3, 1000));

        headAniPlayer.addAnimation("look-down", downLookHeadAni);
    }

    // Método para inicializar as animações da arma de Dex
    public void initializeDexGunAnimations(List<Sprite> dexGunFowardAni, List<Sprite> dexGunUpAni, List<Sprite> dexGunDownAni) {
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
    public void initializeFallAnimation(List<Sprite> fallBodyAni, List<Sprite> fallHeadAni) {
        fallHeadAni.add(new Sprite(1, 2));
        fallHeadAni.add(new Sprite(2, 2));

        fallBodyAni.add(new Sprite(2, 3));
        fallBodyAni.add(new Sprite(3, 3));

        bodyAniPlayer.addAnimation("fall", fallBodyAni);
        headAniPlayer.addAnimation("fall", fallHeadAni);
    }

    // Método para inicializar as animações de pulo
    public void initializeJumpAnimation(List<Sprite> jumpBodyAni, List<Sprite> jumpHeadAni) {
        jumpBodyAni.add(new Sprite(0, 3));
        jumpBodyAni.add(new Sprite(1, 3));

        jumpHeadAni.add(new Sprite(3, 1));
        jumpHeadAni.add(new Sprite(0, 2));

        bodyAniPlayer.addAnimation("jump", jumpBodyAni);
        headAniPlayer.addAnimation("jump", jumpHeadAni);
    }

    // Método para inicializar as animações de dano
    public void initializeDamageAnimation(List<Sprite> damageBodyAni, List<Sprite> damageHeadAni) {
        damageHeadAni.add(new Sprite(3, 2, 300));

        damageBodyAni.add(new Sprite(0, 4, 300));

        bodyAniPlayer.addAnimation("dmg", damageBodyAni);
        headAniPlayer.addAnimation("dmg", damageHeadAni);
    }

    // Método para inicializar as animações de idle
    public void initializeIdleAnimation(List<Sprite> idleBodyAni, List<Sprite> idleHeadAni) {
        idleBodyAni.add(new Sprite(0, 0, 1000));
        idleBodyAni.add(new Sprite(1, 0, 1000));

        idleHeadAni.add(new Sprite(0, 0, 1000));
        idleHeadAni.add(new Sprite(1, 0, 1000));

        bodyAniPlayer.addAnimation("idle", idleBodyAni);
        headAniPlayer.addAnimation("idle", idleHeadAni);
    }

    // Método para inicializar as animações de corrida
    public void initializeRunAnimation(List<Sprite> runBodyAni, List<Sprite> runHeadAni) {
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


}
