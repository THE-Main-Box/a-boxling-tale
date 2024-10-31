package game.entity_related.models.entities;

public abstract class Movable extends GameObject {

    // Velocidade e aceleração em ambos os eixos
    protected float xVelocity, yVelocity;
    protected float xAcceleration, yAcceleration;

    // Limites de velocidade
    protected float xMaxSpeed, yMaxSpeed;

    // Parâmetros para controle de movimento e física
    protected float decelerationX;
    protected float decelerationY;
    protected boolean acceleratingX;
    protected boolean acceleratingY;
    protected double weight;
    protected double additionalWeight;
    protected final double WEIGHT_DEF;

    public Movable(float posX, float posY, int width, int height, double weight) {
        this.xPos = posX;
        this.yPos = posY;
        this.width = width;
        this.height = height;
        this.weight = weight;
        this.WEIGHT_DEF = weight;
        this.xVelocity = 0;
        this.yVelocity = 0;
    }

    // Método para atualizar a posição do objeto com base na física
    public void updatePosition(float deltaTime) {
        // Atualiza a velocidade com base na aceleração
        if (acceleratingX) {

            if (xAcceleration != 0) {

                xVelocity += (xAcceleration / weight);

                // Limita a velocidade máxima no eixo X
                if (Math.abs(xVelocity) > xMaxSpeed) {

                    xVelocity = xMaxSpeed * Math.signum(xVelocity);

                }

            }
        } else {

            xAcceleration = 0;

            xVelocity *= decelerationX;

            if (Math.abs(xVelocity) < 0.01) {
                xVelocity = 0;
            }

        }

        if (acceleratingY) {
            yVelocity += (yAcceleration / weight);
            // Limita a velocidade máxima no eixo Y
            if (Math.abs(yVelocity) > yMaxSpeed) {
                yVelocity = yMaxSpeed * Math.signum(yVelocity);
            }
        } else {
            yVelocity *= decelerationX;
            if (Math.abs(yVelocity) < 0.01) {
                yVelocity = 0;
            }
        }


        // Atualiza a posição com base na velocidade calculada

        xPos += xVelocity * deltaTime;
        yPos += yVelocity * deltaTime;
    }

    public void update(float deltaTime) {
        updatePosition(deltaTime);
    }

    // Métodos de acesso para os parâmetros de aceleração e desaceleração
    public boolean isAcceleratingX() {
        return acceleratingX;
    }

    public boolean isAcceleratingY() {
        return acceleratingY;
    }

    public void setAcceleratingY(boolean acceleratingY) {
        this.acceleratingY = acceleratingY;
    }

    public void setAcceleratingX(boolean acceleratingX) {
        this.acceleratingX = acceleratingX;
    }

    public double getDecelerationX() {
        return decelerationX;
    }

    public void setDecelerationX(float decelerationX) {
        this.decelerationX = decelerationX;
    }

    public double getxAcceleration() {
        return xAcceleration;
    }

    public void setxAcceleration(float xAcceleration) {
        this.xAcceleration = xAcceleration;
    }

    public double getyAcceleration() {
        return yAcceleration;
    }

    public void setyAcceleration(float yAcceleration) {
        this.yAcceleration = yAcceleration;
    }


    // Getters e setters adicionais
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(float xVelocity) {
        this.xVelocity = xVelocity;
    }

    public double getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(float yVelocity) {
        this.yVelocity = yVelocity;
    }


    public float getxMaxSpeed() {
        return xMaxSpeed;
    }

    public void setxMaxSpeed(int xMaxSpeed) {
        this.xMaxSpeed = xMaxSpeed;
    }

    public float getyMaxSpeed() {
        return yMaxSpeed;
    }

    public void setyMaxSpeed(int yMaxSpeed) {
        this.yMaxSpeed = yMaxSpeed;
    }

    public double getWEIGHT_DEF() {
        return WEIGHT_DEF;
    }

    public void setyMaxSpeed(float yMaxSpeed) {
        this.yMaxSpeed = yMaxSpeed;
    }

    public void setxMaxSpeed(float xMaxSpeed) {
        this.xMaxSpeed = xMaxSpeed;
    }

    public double getAdditionalWeight() {
        return additionalWeight;
    }

    public void setAdditionalWeight(double additionalWeight) {
        this.additionalWeight = additionalWeight;
    }

    public float getDecelerationY() {
        return decelerationY;
    }

    public void setDecelerationY(float decelerationY) {
        this.decelerationY = decelerationY;
    }
}
