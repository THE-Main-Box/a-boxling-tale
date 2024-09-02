package game.entity_related.entity_controller;

import game.entity_related.models.Directions;
import game.entity_related.models.entities.Player;

public class PlayerController {

    private Player player;
    private PlayerMovement playerMovement;


//    jump burst looks like a good name for an ability

    public PlayerController(Player player) {
        this.player = player;
        this.playerMovement = new PlayerMovement(player);
    }

    // mover para a direita
    public void moveRight(){
        playerMovement.moveRight();
    }
    // mover para a esquerda
    public void moveLeft(){
        playerMovement.moveLeft();
    }
    // mover para a baixo
    public void moveDown(){
        playerMovement.moveDown();
    }
    // mover para a cima
    public void moveUp(){
        playerMovement.moveUp();
    }
    //parar de se mover
    public void stopMoving(){
        playerMovement.stopMoving();
    }


    public void attack(Directions direction) {
        if (direction.equals(Directions.RIGHT)) {
            player.setFacingForward(true);
            this.shootFoward();
        }
        if (direction.equals(Directions.LEFT)) {
            player.setFacingForward(false);
            this.shootFoward();
        }
        if (direction.equals(Directions.UP)) {
            this.shootUpward();
        }
        if (direction.equals(Directions.DOWN)) {
            this.shootDownward();
        }

    }

    public void canStopAttackAnimation() {
        if (player.isAttacking()) {
            if (player.getWeaponAniPlayer().getCurrentSprite().equals(player.getWeaponAniPlayer().getCurrentAnimation().getLast())) {
                player.setAttacking(false);
                player.setAutoUpdateWeaponAnimation(false);

                player.setAutoUpdateHeadAnimation(true);

                player.getWeaponAniPlayer().setCurrentAnimation(player.getWeaponAniPlayer().getCurrentAnimation());

                if (!player.getHeadAniPlayer().getCurrentAnimationKey().equals(player.getBodyAniPlayer().getCurrentAnimationKey())) {
                    player.getHeadAniPlayer().setAnimation(player.getBodyAniPlayer().getCurrentAnimationKey());
                }
            }
        }
    }

    public void shootFoward() {
        player.setAutoUpdateHeadAnimation(false);
        player.setAutoUpdateWeaponAnimation(true);
        player.setShowingWeapon(true);
        player.setAttacking(true);
        player.setUsingWeapon(true);

        player.getWeaponAniPlayer().setAnimation("gun-fwd");
        player.getHeadAniPlayer().setCurrentSprite("idle", 0);

    }

    public void shootDownward() {

        if (player.isFacingForward()) {

        } else {

        }

        player.setAutoUpdateHeadAnimation(false);
        player.setAutoUpdateWeaponAnimation(true);
        player.setShowingWeapon(true);
        player.setAttacking(true);
        player.setUsingWeapon(true);


        player.getWeaponAniPlayer().setAnimation("gun-dwd");
        player.getHeadAniPlayer().setCurrentSprite("look-down", 0);

    }

    public void shootUpward() {

        if (player.isFacingForward()) {

        } else {

        }

        player.setAutoUpdateHeadAnimation(false);
        player.setAutoUpdateWeaponAnimation(true);
        player.setShowingWeapon(true);
        player.setAttacking(true);
        player.setUsingWeapon(true);


        player.getWeaponAniPlayer().setAnimation("gun-uwd");
        player.getHeadAniPlayer().setCurrentSprite("look-up", 0);

    }

}
