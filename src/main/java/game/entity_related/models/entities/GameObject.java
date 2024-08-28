package game.entity_related.models.entities;

import game.entity_related.animation_related.Sprite;

import java.awt.image.BufferedImage;
import java.util.List;

public class GameObject {
    // Variáveis básicas para a posição e tamanho
    protected int xPos, yPos, width, height;

    // Parâmetros relacionados à animação
    protected BufferedImage spriteSheet; // Folha de sprites
    protected List<Sprite> currentAnimation; // Animação atual
    protected boolean facingForward; // Direção em que o personagem está virado

    protected float frameDuration;

    // Controle da animação
    protected int aniTick; // Índice atual do frame da animação
    protected long lastFrameChangeTime; // Tempo da última mudança de frame
    protected float elapsedTime; // Tempo decorrido desde a última atualização
    protected float atackTime;

    // Controle da animação automática e manual
    protected boolean autoUpdateAni; // Atualização automática de animação

    public GameObject(int xPos, int yPos, int width, int height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
    }

    public GameObject() {}

    // Métodos para trabalhar com os sprites da folha de sprites
    public BufferedImage getSprite(int x, int y, int width, int height, BufferedImage spriteSheet) {
        return spriteSheet.getSubimage(x, y, width, height);
    }

    public BufferedImage getSpriteByIndex(int indexX, int indexY, int canvasWidth, int canvasHeight, BufferedImage spriteSheet) {
        return spriteSheet.getSubimage(indexX * canvasWidth, indexY * canvasHeight, canvasWidth, canvasHeight);
    }

    // Método para definir uma nova animação
    public void setAnimation(List<Sprite> newAnimation) {
        if (currentAnimation != newAnimation) {
            currentAnimation = newAnimation;
            aniTick = 0; // Reinicia o índice da animação
            lastFrameChangeTime = System.nanoTime(); // Reinicia o tempo
            elapsedTime = 0; // Reinicia o tempo decorrido
        }
    }


    public void setCurrentSprite(List<Sprite> currentAnimation, int frameIndex) {
        this.setAnimation(currentAnimation);

        if (frameIndex >= 0 && frameIndex < this.currentAnimation.size()) {
            aniTick = frameIndex;
        }
    }

    // Método para atualizar a animação automaticamente
    public void updateAnimation(float deltaTime) {
        if (currentAnimation == null || currentAnimation.isEmpty() || !autoUpdateAni) return;

        elapsedTime += deltaTime; // Atualiza o tempo decorrido

        Sprite currentSprite = getCurrentSprite();
        frameDuration = currentSprite.getDuration() / 1000.0f; // Converte a duração do frame para segundos

        if (elapsedTime >= frameDuration) {
            aniTick++;
            elapsedTime -= frameDuration; // Reduz o tempo decorrido

            // Reinicia o índice se ultrapassar o tamanho da animação
            if (aniTick >= currentAnimation.size()) {
                aniTick = 0;
            }
        }

    }

    // Define a direção do personagem
    public boolean isFacingForward() {
        return facingForward;
    }

    public void setFacingForward(boolean facingForward) {
        this.facingForward = facingForward;
    }

    // Métodos de acesso à animação e sprites
    public List<Sprite> getCurrentAnimation() {
        return currentAnimation;
    }

    public Sprite getCurrentSprite() {
        return currentAnimation.get(aniTick);
    }

    public int getAniTick() {
        return aniTick;
    }

    public long getLastFrameChangeTime() {
        return lastFrameChangeTime;
    }

    public float getElapsedTime() {
        return elapsedTime;
    }

    public boolean isAutoUpdateAni() {
        return autoUpdateAni;
    }

    public void setAutoUpdateAni(boolean autoUpdateAni) {
        this.autoUpdateAni = autoUpdateAni;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public BufferedImage getSpriteSheet() {
        return spriteSheet;
    }

    public void setSpriteSheet(BufferedImage spriteSheet) {
        this.spriteSheet = spriteSheet;
    }

    public void setCurrentAnimation(List<Sprite> currentAnimation) {
        this.currentAnimation = currentAnimation;
    }
}
