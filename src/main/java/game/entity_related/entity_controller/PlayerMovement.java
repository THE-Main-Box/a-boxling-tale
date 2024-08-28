package game.entity_related.entity_controller;

import game.entity_related.models.entities.Player;

public class PlayerMovement {

    private Player player;

    private double walkAcceleration = 100.0;

    public PlayerMovement(Player player) {
        this.player = player;
    }

    // mover para a direita
    public void moveRight() {
        player.setAutoUpdateAni(true);
        player.setFacingForward(true);
        player.setAccelerating(true);
        player.setxAcceleration(walkAcceleration);
        player.setAnimation(player.getRunAni());
    }

    //mover para a esquerda
    public void moveLeft() {
        player.setAutoUpdateAni(true);
        player.setFacingForward(false);
        player.setAccelerating(true);
        player.setxAcceleration(-walkAcceleration);
        player.setAnimation(player.getRunAni());
    }

    //mover para cima
    public void moveUp(){
        player.setAutoUpdateAni(false);
        player.setAccelerating(true);
        player.setyAcceleration(-walkAcceleration);
        player.setCurrentSprite(player.getJumpAni(), 1);
    }

    //mover para baixo
    public void moveDown(){
        player.setAutoUpdateAni(false);
        player.setAccelerating(true);
        player.setyAcceleration(walkAcceleration);
        player.setCurrentSprite(player.getFallAni(), 1);
    }

    //parar de se mover
    public void stopMoving() {
        player.setAccelerating(false);
        player.setAnimation(player.getIdleAni());
    }

}
