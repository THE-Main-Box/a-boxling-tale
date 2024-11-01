package game.entity_related.entity_controller;

import game.entity_related.models.entities.Player;

public class PlayerMovement {

    private Player player;

    private float walkAcceleration = 50f;
    private float jumpAcceleration = 800f;

    public PlayerMovement(Player player) {
        this.player = player;
    }

    //mover e alterar sprites e animação para mover para a direita
    public void moveRight() {

        player.setAutoUpdateBodyAnimation(true);
        player.setAutoUpdateHeadAnimation(true);

        player.setFacingForward(true);
        player.setAcceleratingX(true);

        player.setxAcceleration(walkAcceleration);

        player.getHeadAniPlayer().setAnimation("run");
        player.getBodyAniPlayer().setAnimation("run");
    }


    //mover e alterar sprites e animação para mover para a esquerda
    public void moveLeft() {

        player.setAutoUpdateBodyAnimation(true);
        player.setAutoUpdateHeadAnimation(true);

        player.setFacingForward(false);
        player.setAcceleratingX(true);

        player.setxAcceleration(-walkAcceleration);

        player.getHeadAniPlayer().setAnimation("run");
        player.getBodyAniPlayer().setAnimation("run");
    }

    //mover e alterar sprites e animação para mover para cima
    public void moveUp() {

        player.setAutoUpdateBodyAnimation(false);
        player.setAutoUpdateHeadAnimation(false);

        player.setAcceleratingY(true);

//        player.setApplyGravity(true);
        player.setyAcceleration(-jumpAcceleration);


        player.getHeadAniPlayer().setCurrentSprite("jump", 1);
        player.getBodyAniPlayer().setCurrentSprite("jump", 1);
    }

    //mover e alterar sprites e animação para mover para baixo
    public void moveDown() {
        player.setAutoUpdateBodyAnimation(false);
        player.setAutoUpdateHeadAnimation(false);

        player.setAcceleratingY(true);

        player.setyAcceleration(jumpAcceleration);

        player.getHeadAniPlayer().setCurrentSprite("fall", 1);
        player.getBodyAniPlayer().setCurrentSprite("fall", 1);

    }

    //parar e alterar sprites e animação para mover de se mover
    public void stopMoving() {
        if (player.isAcceleratingX()) {
            player.setAcceleratingX(false);
            player.setAcceleratingY(false);
        }
        player.getBodyAniPlayer().setAutoUpdateAni(true);
        player.getHeadAniPlayer().setAutoUpdateAni(true);

        player.getBodyAniPlayer().setAnimation("idle");
        player.getHeadAniPlayer().setAnimation("idle");
    }

}
