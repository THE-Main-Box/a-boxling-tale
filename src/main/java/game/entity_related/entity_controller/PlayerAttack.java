package game.entity_related.entity_controller;

import game.entity_related.models.entities.Player;

public class PlayerAttack {

    private Player player;

    public PlayerAttack(Player player) {
        this.player = player;
    }


    public void shootRight(){
        this.animateShootRight();
    }

    public void shootLeft(){
        this.animateShootLeft();
    }

    public void shootUp(){
        this.animateShootUp();
    }

    public void shootDown(){
        this.animateShootDown();
    }

    private void animateShootLeft(){
        player.setFacingForward(false);
        player.setAutoUpdateHeadAnimation(false);
        player.setAutoUpdateWeaponAnimation(true);
        player.setUsingWeapon(true);
        player.setAttacking(true);

        player.getWeaponAniPlayer().setAnimation("gun-fwd");
        player.getHeadAniPlayer().setCurrentSprite("idle", 0);
    }

    private void animateShootRight(){
        player.setFacingForward(true);
        player.setAutoUpdateHeadAnimation(false);
        player.setAutoUpdateWeaponAnimation(true);
        player.setUsingWeapon(true);
        player.setAttacking(true);

        player.getWeaponAniPlayer().setAnimation("gun-fwd");
        player.getHeadAniPlayer().setCurrentSprite("idle", 0);
    }

    private void animateShootDown(){
        player.setAutoUpdateHeadAnimation(false);
        player.setAutoUpdateWeaponAnimation(true);
        player.setUsingWeapon(true);
        player.setAttacking(true);


        player.getWeaponAniPlayer().setAnimation("gun-dwd");
        player.getHeadAniPlayer().setCurrentSprite("look-down", 0);
    }

    private void animateShootUp(){
        player.setAutoUpdateHeadAnimation(false);
        player.setAutoUpdateWeaponAnimation(true);
        player.setUsingWeapon(true);
        player.setAttacking(true);


        player.getWeaponAniPlayer().setAnimation("gun-uwd");
        player.getHeadAniPlayer().setCurrentSprite("look-up", 0);

    }

}
