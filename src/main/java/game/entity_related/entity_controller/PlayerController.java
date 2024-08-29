package game.entity_related.entity_controller;

import game.entity_related.models.entities.Player;

public class PlayerController {

    private Player player;

    private double walkAcceleration = 100.0;

    public PlayerController(Player player) {
        this.player = player;
    }

    // mover para a direita
    public void moveRight() {

        player.setAutoUpdateBodyAnimation(true);
        player.setAutoUpdateHeadAnimation(true);

        player.setFacingForward(true);
        player.setAccelerating(true);

        player.setxAcceleration(walkAcceleration);

        player.setCurrentBodyAnimation("run");
        player.setCurrentHeadAnimation("run");
    }


    //mover para a esquerda
    public void moveLeft() {

        player.setAutoUpdateBodyAnimation(true);
        player.setAutoUpdateHeadAnimation(true);

        player.setFacingForward(false);
        player.setAccelerating(true);

        player.setxAcceleration(-walkAcceleration);

        player.setCurrentBodyAnimation("run");
        player.setCurrentHeadAnimation("run");
    }

    //mover para cima
    public void moveUp(){

        player.setAutoUpdateBodyAnimation(false);
        player.setAutoUpdateHeadAnimation(false);

        player.setAccelerating(true);

        player.setyAcceleration(-walkAcceleration);

        player.setCurrentHeadSprite("jump", 1);
        player.setCurrentBodySprite("jump", 1);
    }

    //mover para baixo
    public void moveDown(){
        player.setAutoUpdateBodyAnimation(false);
        player.setAutoUpdateHeadAnimation(false);

        player.setAccelerating(true);

        player.setyAcceleration(walkAcceleration);

        player.setCurrentBodySprite("fall", 1);
        player.setCurrentHeadSprite("fall", 1);

    }

    //parar de se mover
    public void stopMoving() {
        player.setAccelerating(false);

        player.setCurrentHeadAnimation("idle");
        player.setCurrentBodyAnimation("idle");
    }


    public void shootFoward(){

        if(player.isFacingForward()){

        }else{

        }

        player.setShowingWeapon(true);
        player.setAttacking(true);
        player.setUsingWeapon(true);

        player.setCurrentWeaponAnimation("gun-fwd");
    }

    public void shootDownward(){

        if(player.isFacingForward()){

        }else{

        }

        player.setShowingWeapon(true);
        player.setAttacking(true);
        player.setUsingWeapon(true);

        player.setCurrentWeaponAnimation("gun-dwd");

    }

    public void shootUpward(){

        if(player.isFacingForward()){

        }else{

        }

        player.setShowingWeapon(true);
        player.setAttacking(true);
        player.setUsingWeapon(true);

        player.setCurrentWeaponAnimation("gun-uwd");

    }

}
