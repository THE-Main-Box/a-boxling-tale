package game.entity_related.entity_controller;

import game.entity_related.models.entities.Player;

public class PlayerController {

    private Player player;

    private float walkAcceleration = 50f;
    private float jumpAcceleration = 800f;

//    jump burst looks like a good name for an ability

    public PlayerController(Player player) {
        this.player = player;
    }

    // mover para a direita
    public void moveRight() {

        player.setAutoUpdateBodyAnimation(true);
        player.setAutoUpdateHeadAnimation(true);

        player.setFacingForward(true);
        player.setAcceleratingX(true);

        player.setxAcceleration(walkAcceleration);

        player.getHeadAniPlayer().setAnimation("run");
        player.getBodyAniPlayer().setAnimation("run");
    }


    //mover para a esquerda
    public void moveLeft() {

        player.setAutoUpdateBodyAnimation(true);
        player.setAutoUpdateHeadAnimation(true);

        player.setFacingForward(false);
        player.setAcceleratingX(true);

        player.setxAcceleration(-walkAcceleration);

        player.getHeadAniPlayer().setAnimation("run");
        player.getBodyAniPlayer().setAnimation("run");
    }

    //mover para cima
    public void moveUp(){

        player.setAutoUpdateBodyAnimation(false);
        player.setAutoUpdateHeadAnimation(false);

        player.setAcceleratingY(true);

//        player.setApplyGravity(true);
        player.setyAcceleration(-jumpAcceleration);


        player.getHeadAniPlayer().setCurrentSprite("jump", 1);
        player.getBodyAniPlayer().setCurrentSprite("jump", 1);
    }

    //mover para baixo
    public void moveDown(){
        player.setAutoUpdateBodyAnimation(false);
        player.setAutoUpdateHeadAnimation(false);

        player.setAcceleratingY(true);

        player.setyAcceleration(jumpAcceleration);

        player.getHeadAniPlayer().setCurrentSprite("fall", 1);
        player.getBodyAniPlayer().setCurrentSprite("fall", 1);

    }

    //parar de se mover
    public void stopMoving() {
        player.setAcceleratingX(false);
        player.setAcceleratingY(false);

//        player.setApplyGravity(false);

        player.getBodyAniPlayer().setAnimation("idle");
        player.getHeadAniPlayer().setAnimation("idle");
    }


    public void shootFoward(){

        if(player.isFacingForward()){

        }else{

        }

        player.setAutoUpdateHeadAnimation(false);
        player.setAutoUpdateWeaponAnimation(true);
        player.setShowingWeapon(true);
        player.setAttacking(true);
        player.setUsingWeapon(true);

        player.getWeaponAniPlayer().setAnimation("gun-fwd");
        player.getHeadAniPlayer().setCurrentSprite ("idle", 0);
    }

    public void shootDownward(){

        if(player.isFacingForward()){

        }else{

        }

        player.setAutoUpdateHeadAnimation(false);
        player.setAutoUpdateWeaponAnimation(true);
        player.setShowingWeapon(true);
        player.setAttacking(true);
        player.setUsingWeapon(true);


        player.getWeaponAniPlayer().setAnimation("gun-dwd");
        player.getHeadAniPlayer().setCurrentSprite ("look-down", 0);

    }

    public void shootUpward(){

        if(player.isFacingForward()){

        }else{

        }

        player.setAutoUpdateHeadAnimation(false);
        player.setAutoUpdateWeaponAnimation(true);
        player.setShowingWeapon(true);
        player.setAttacking(true);
        player.setUsingWeapon(true);



        player.getWeaponAniPlayer().setAnimation("gun-uwd");
        player.getHeadAniPlayer().setCurrentSprite ("look-up", 0);

    }

}
