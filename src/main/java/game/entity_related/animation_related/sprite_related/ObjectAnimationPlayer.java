package game.entity_related.animation_related.sprite_related;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectAnimationPlayer {

    private List<Sprite> currentAnimation;              // animação atual
    private Sprite currentSprite;                       // sprite atual baseado no anitick
    private List<Sprite> newAnimation;                  // animação para trocar a currentanimation
    private String currentAnimationKey;                 // a chave da animação atual
    private Map<String, List<Sprite>> animations;       // lista de animações associadas com um chave String
    private int aniTick;                                // contagem da animação
    private float frameDuration;                        // duração da animação
    private float elapsedTime;                          // tempo decorrido desde a ultima troca de sprites
    private boolean autoUpdateAni;                      // se deve atualizar a animação automaticamente

    public ObjectAnimationPlayer() {
        this.animations = new HashMap<>();
        this.autoUpdateAni = true;
    }

    public void addAnimation(String animationTitle, List<Sprite> animation) {
        animations.put(animationTitle, animation);
    }

    public void update(float deltaTime) {
        if (currentAnimation == null || currentAnimation.isEmpty() || !autoUpdateAni) return;

        elapsedTime += deltaTime;                 // Atualiza o tempo decorrido

        currentSprite = getCurrentSprite();       // seleciona o sprite atual com base no aniTick

        // se a duração for menor ou igual a0 pula essa divisão
        if (currentSprite.getDuration() > 0) {
            frameDuration = currentSprite.getDuration() / 1000.0f; // Converte a duração do frame para segundos
        }

        // se o tempo decorrido for maior ou igual a duração do frame atualiza ele
        if (elapsedTime >= frameDuration) {

            aniTick++;
            elapsedTime -= frameDuration;

            if (aniTick >= currentAnimation.size()) {  // reseta o anitick para reiniciar a animação
                aniTick = 0;
            }

        }

    }

    public void setCurrentAnimation(List<Sprite> animation) {
        if (animation != null && !animation.isEmpty()) {
            this.currentAnimation = animation;
            this.aniTick = 0;
            this.elapsedTime = 0;
            this.currentAnimationKey = null; // Resetando a chave já que foi setada diretamente
        }
    }

    // Método para definir uma nova animação
    public void setAnimation(String title) {
        newAnimation = animations.get(title);

        // verifica se a animação a ser atualizada existe antes de atualizar
        if (newAnimation != null && !newAnimation.isEmpty() && currentAnimation != newAnimation) {
            // se existe resetamos as contagens e setamos a currentAnimation
            currentAnimation = newAnimation;
            currentAnimationKey = title;
            aniTick = 0;
            elapsedTime = 0;

        }
    }

    public void setCurrentSprite(String animationTitle, int frameIndex) {

        this.setAnimation(animationTitle); // seta a animação para a solicitada

        if (frameIndex >= 0 && frameIndex < this.currentAnimation.size()) {
            aniTick = frameIndex;
        }
    }

    public int getAniTick() {
        return aniTick;
    }

    public float getFrameDuration() {
        return frameDuration;
    }

    public void setFrameDuration(float frameDuration) {
        this.frameDuration = frameDuration;
    }

    public Sprite getCurrentSprite() {
        return currentAnimation.get(aniTick);
    }

    public List<Sprite> getCurrentAnimation() {
        return currentAnimation;
    }

    public boolean isAutoUpdateAni() {
        return autoUpdateAni;
    }

    public void setAutoUpdateAni(boolean autoUpdateAni) {
        this.autoUpdateAni = autoUpdateAni;
    }

    public String getCurrentAnimationKey() {
        return currentAnimationKey;
    }
}
