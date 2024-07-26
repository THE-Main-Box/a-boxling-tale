package game.entity_related.models;

import game.entity_related.animation_related.Sprite;

import java.awt.image.BufferedImage;
import java.util.List;

public class Movable {
    private int xPos, yPos, width, height;
    private double xVelocity, yVelocity;
    private BufferedImage spriteSheet;
    private List<Sprite> currentAnimation;

    private int aniTick;
    private long lastFrameChangeTime;
    private float elapsedTime; // Tempo decorrido desde a última atualização

    public Movable(int posX, int posY, int width, int height) {
        this.xPos = posX;
        this.yPos = posY;
        this.width = width;
        this.height = height;
        this.xVelocity = 0;
        this.yVelocity = 0;
        this.elapsedTime = 0;
    }

    public void updatePosition(){
        this.xPos += this.xVelocity;
        this.yPos += this.yVelocity;
    }

    public BufferedImage getSprite(int x, int y, int width, int height){
        return spriteSheet.getSubimage(x, y, width, height);
    }

    public BufferedImage getSpriteByIndex(int indexX, int indexY, int canvasWidth, int canvasHeight){
        int x = (indexX * canvasWidth);
        int y = (indexY * canvasHeight);
        return spriteSheet.getSubimage(x, y, canvasWidth, canvasHeight);
    }

    public void setAnimation(List<Sprite> newAnimation) {
        if (currentAnimation != newAnimation) {
            currentAnimation = newAnimation;
            aniTick = 0; // Reinicia o índice da animação quando muda
            lastFrameChangeTime = System.nanoTime(); // Reinicia o tempo
            elapsedTime = 0; // Reinicia o tempo decorrido
        }
    }

    public List<Sprite> getCurrentAnimation() {
        return currentAnimation;
    }

    public Sprite getCurrentSprite() {
        return currentAnimation.get(aniTick);
    }

    public void updateAnimation(float deltaTime) {
        if (currentAnimation == null || currentAnimation.isEmpty()) return;

        elapsedTime += deltaTime; // Atualiza o tempo decorrido

        Sprite currentSprite = getCurrentSprite();
        float frameDuration = currentSprite.getDuration() / 1000.0f; // Convertendo a duração do frame para segundos

        if (elapsedTime >= frameDuration) {
            aniTick++;
            elapsedTime -= frameDuration; // Reduz o tempo decorrido pelo tempo do frame atual

            if (aniTick >= currentAnimation.size()) {
                aniTick = 0; // Reinicia o índice se ultrapassar o tamanho
            }
        }
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

    public double getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public double getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    public void setSpriteSheet(BufferedImage spriteSheet) {
        this.spriteSheet = spriteSheet;
    }

    public BufferedImage getSpriteSheet() {
        return spriteSheet;
    }
}
