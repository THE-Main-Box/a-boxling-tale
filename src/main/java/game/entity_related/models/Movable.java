package game.entity_related.models;

import game.entity_related.animation_related.Sprite;

import java.awt.image.BufferedImage;
import java.util.List;

public class Movable {
    // Variáveis básicas para a posição e tamanho
    protected int xPos, yPos, width, height;

    // Velocidade e aceleração em ambos os eixos
    protected double xVelocity, yVelocity;
    protected double xAcceleration, yAcceleration;

    // Limites de velocidade
    protected int XMaxSpeed, YMaxSpeed;

    // Parâmetros para controle de movimento e física
    protected double deceleration;
    protected boolean accelerating;
    protected double weight;

    // Parâmetros relacionados à animação
    protected BufferedImage spriteSheet; // Folha de sprites
    protected List<Sprite> currentAnimation; // Animação atual
    protected boolean facingForward = true; // Direção em que o personagem está virado

    // Controle da animação
    protected int aniTick; // Índice atual do frame da animação
    protected long lastFrameChangeTime; // Tempo da última mudança de frame
    protected float elapsedTime; // Tempo decorrido desde a última atualização

    // Controle da animação automática e manual
    protected boolean autoUpdateAni; // Atualização automática de animação

    public Movable(int posX, int posY, int width, int height, double weight) {
        this.xPos = posX;
        this.yPos = posY;
        this.width = width;
        this.height = height;
        this.weight = weight;
        this.xVelocity = 0;
        this.yVelocity = 0;
        this.elapsedTime = 0;
        this.autoUpdateAni = true; // A animação começa no modo automático
    }

    // Método para atualizar a posição do objeto com base na física
    public void updatePosition(float deltaTime) {
        // Atualiza a velocidade com base na aceleração
        if (accelerating) {
            if (xAcceleration != 0) {
                xVelocity += (xAcceleration / weight);
                // Limita a velocidade máxima no eixo X
                if (Math.abs(xVelocity) > XMaxSpeed) {
                    xVelocity = XMaxSpeed * Math.signum(xVelocity);
                }
            }
            if (yAcceleration != 0) {
                yVelocity += (yAcceleration / weight);
                // Limita a velocidade máxima no eixo Y
                if (Math.abs(yVelocity) > YMaxSpeed) {
                    yVelocity = YMaxSpeed * Math.signum(yVelocity);
                }
            }
        } else {
            // Desaceleração quando não está acelerando
            xVelocity *= deceleration;
            yVelocity *= deceleration;

            // Para pequenas velocidades, considera-se parado
            if (Math.abs(xVelocity) < 0.01) {
                xVelocity = 0;
            }
            if (Math.abs(yVelocity) < 0.01) {
                yVelocity = 0;
            }
        }

        // Atualiza a posição com base na velocidade calculada
        xPos += (int) (xVelocity * deltaTime);
        yPos += (int) (yVelocity * deltaTime);
    }

    // Métodos de acesso para os parâmetros de aceleração e desaceleração
    public boolean isAccelerating() {
        return accelerating;
    }

    public void setAccelerating(boolean accelerating) {
        this.accelerating = accelerating;
    }

    public double getDeceleration() {
        return deceleration;
    }

    public void setDeceleration(double deceleration) {
        this.deceleration = deceleration;
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

    // Métodos para trabalhar com os sprites da folha de sprites
    public BufferedImage getSprite(int x, int y, int width, int height) {
        return spriteSheet.getSubimage(x, y, width, height);
    }

    public BufferedImage getSpriteByIndex(int indexX, int indexY, int canvasWidth, int canvasHeight) {
        int x = (indexX * canvasWidth);
        int y = (indexY * canvasHeight);
        return spriteSheet.getSubimage(x, y, canvasWidth, canvasHeight);
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

    public void setCurrentSprite(List<Sprite> currentAnimation, int frameIndex){
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
            float frameDuration = currentSprite.getDuration() / 1000.0f; // Converte a duração do frame para segundos

            if (elapsedTime >= frameDuration) {
                aniTick++;
                elapsedTime -= frameDuration; // Reduz o tempo decorrido

                // Reinicia o índice se ultrapassar o tamanho da animação
                if (aniTick >= currentAnimation.size()) {
                    aniTick = 0;
                }
            }

    }

    // Getters e setters adicionais
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

    public boolean isAutoUpdateAni() {
        return autoUpdateAni;
    }

    public void setAutoUpdateAni(boolean autoUpdateAni) {
        this.autoUpdateAni = autoUpdateAni;
    }

}
