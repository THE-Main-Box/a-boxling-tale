package game.entity_related.models;

import game.entity_related.animation_related.Sprite;

import java.awt.image.BufferedImage;
import java.util.List;

public class Movable {
    protected int xPos, yPos, width, height;
    protected double xVelocity, yVelocity;
    protected int XMaxSpeed, YMaxSpeed;


    protected double weight;

    protected double xAcceleration, yAcceleration;

    protected BufferedImage spriteSheet;
    protected List<Sprite> currentAnimation;
    protected boolean facingForward = true;

    protected int aniTick;
    protected long lastFrameChangeTime;
    protected float elapsedTime; // Tempo decorrido desde a última atualização

    public Movable(int posX, int posY, int width, int height, double weight) {
        this.xPos = posX;
        this.yPos = posY;
        this.width = width;
        this.height = height;
        this.weight = weight;
        this.xVelocity = 0;
        this.yVelocity = 0;
        this.elapsedTime = 0;
    }

    public void updatePosition(float deltaTime) {
        // Atualizar posições com base nas velocidades

        if (xVelocity < XMaxSpeed || xAcceleration < 0) {
            xVelocity += (xAcceleration / weight);
        }
        if (yVelocity < YMaxSpeed || yAcceleration < 0) {
            yVelocity += (yAcceleration / weight);
        }
        // Atualiza a posição com base na velocidade
        xPos += (int) (xVelocity * deltaTime);
        yPos += (int) (yVelocity * deltaTime);

    }

    public double getxAcceleration() {
        return xAcceleration;
    }

    public void setxAcceleration(double xAcceleration) {
        this.xAcceleration = xAcceleration;
    }

    public double getyAcceleration() {
        return yAcceleration;
    }

    public void setyAcceleration(double yAcceleration) {
        this.yAcceleration = yAcceleration;
    }

    public BufferedImage getSprite(int x, int y, int width, int height) {
        return spriteSheet.getSubimage(x, y, width, height);
    }

    public BufferedImage getSpriteByIndex(int indexX, int indexY, int canvasWidth, int canvasHeight) {
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

    public boolean isFacingForward() {
        return facingForward;
    }

    public void setFacingForward(boolean facingForward) {
        this.facingForward = facingForward;
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

    // Getters e Setters
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
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

    public int getXMaxSpeed() {
        return XMaxSpeed;
    }

    public void setXMaxSpeed(int XMaxSpeed) {
        this.XMaxSpeed = XMaxSpeed;
    }

    public int getYMaxSpeed() {
        return YMaxSpeed;
    }

    public void setYMaxSpeed(int YMaxSpeed) {
        this.YMaxSpeed = YMaxSpeed;
    }
}
