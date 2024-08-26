package game.entity_related.entity_controller;

import game.entity_related.models.Player;

public class PlayerMovement {

    private Player player;

    private double walkAcceleration = 100.0;

    public PlayerMovement(Player player) {
        this.player = player;
    }

    public void moveRight() {
        player.setAutoUpdateAni(true);
        player.setFacingForward(true);
        player.setAccelerating(true);
        player.setxAcceleration(walkAcceleration);
        player.setAnimation(player.getRunAni());
    }

    public void moveLeft() {
        player.setAutoUpdateAni(true);
        player.setFacingForward(false);
        player.setAccelerating(true);
        player.setxAcceleration(-walkAcceleration);
        player.setAnimation(player.getRunAni());
    }

    public void stopMoving() {
        player.setAccelerating(false);
        player.setAnimation(player.getIdleAni());
    }

}
