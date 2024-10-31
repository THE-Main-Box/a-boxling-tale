package game.entity_related.animation_validators;

import game.entity_related.models.entities.Player;

public class PlayerAnimationValidation {

    private Player player;

    public PlayerAnimationValidation(Player player) {
        this.player = player;
    }

    public void updateHeadAnimation(String animationKey) {
        player.setAutoUpdateHeadAnimation(false);
        player.getHeadAniPlayer().setCurrentSprite(animationKey, 0);
    }

    public void validateWeaponAttackFinalSprite() {

        if (player.isAttacking()) {
            if (isOnLastSprite()) {
                endWeaponAttackAnimation();
            }
        }

    }

    private void endWeaponAttackAnimation(){

        player.setAttacking(false);

        player.getWeaponAniPlayer().setAutoUpdateAni(false);

        player.getHeadAniPlayer().setAutoUpdateAni(true);

        player.getWeaponAniPlayer().setCurrentAnimation(player.getWeaponAniPlayer().getCurrentAnimation());

        player.getHeadAniPlayer().setAnimation(player.getBodyAniPlayer().getCurrentAnimationKey());

    }

    private boolean isOnLastSprite() {
        return player.getWeaponAniPlayer().getCurrentSprite().equals(player.getWeaponAniPlayer().getCurrentAnimation().getLast());
    }


}
