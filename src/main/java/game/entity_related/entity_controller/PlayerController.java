package game.entity_related.entity_controller;

import game.entity_related.models.Directions;
import game.entity_related.models.entities.Player;

public class PlayerController {

    private Player player;
    private PlayerMovement playerMovement;
    private PlayerAttack playerAttack;

//    jump burst looks like a good name for an ability

/*O controlador serve apenas para controlar/gerenciar o que acontece com o jogador.
    EX: Se o jogador se mover para um lado ele irá mudar o que precisa ser mudado pro jogador se mover
            mas não irá manter a lógica de sua movimentação
*/

    public PlayerController(Player player) {
        this.player = player;
        this.playerMovement = new PlayerMovement(player);
        this.playerAttack = new PlayerAttack(player);
    }

    // mover para a direita
    public void moveRight() {
        playerMovement.moveRight();
    }

    // mover para a esquerda
    public void moveLeft() {
        playerMovement.moveLeft();
    }

    // mover para a baixo
    public void moveDown() {
        playerMovement.moveDown();
    }

    // mover para a cima
    public void moveUp() {
        playerMovement.moveUp();
    }

    //parar de se mover
    public void stopMoving() {
        playerMovement.stopMoving();
    }


    public void attack(Directions direction) {
        if (direction.equals(Directions.RIGHT)) {
            playerAttack.shootRight();
        }
        if (direction.equals(Directions.LEFT)) {
            playerAttack.shootLeft();
        }
        if (direction.equals(Directions.UP)) {
            playerAttack.shootUp();
        }
        if (direction.equals(Directions.DOWN)) {
            playerAttack.shootDown();
        }

    }


}
