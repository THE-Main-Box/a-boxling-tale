package game.entity_related.models.entities;

public abstract class Movable extends GameObject {

    // Velocidade e aceleração em ambos os eixos
    protected float xVelocity, yVelocity;
    protected float xAcceleration, yAcceleration;

    // Limites de velocidade
    protected float xMaxSpeed, yMaxSpeed;

    // Parâmetros para controle de movimento e física
    protected float deceleration;
    protected boolean accelerating;
    protected double weight;

    public Movable(float posX, float posY, int width, int height, double weight) {
        this.xPos = posX;
        this.yPos = posY;
        this.width = width;
        this.height = height;
        this.weight = weight;
        this.xVelocity = 0;
        this.yVelocity = 0;
    }

    // Método para atualizar a posição do objeto com base na física
    public void updatePosition(float deltaTime) {
        // Atualiza a velocidade com base na aceleração
        if (accelerating) {

            if (xAcceleration != 0) {

                xVelocity += (xAcceleration / weight);

                // Limita a velocidade máxima no eixo X
                if (Math.abs(xVelocity) > xMaxSpeed) {

                    xVelocity = xMaxSpeed * Math.signum(xVelocity);

                }

            }

            if (yAcceleration != 0) {

                yVelocity += (yAcceleration / weight);

                // Limita a velocidade máxima no eixo Y
                if (Math.abs(yVelocity) > yMaxSpeed) {

                    yVelocity = yMaxSpeed * Math.signum(yVelocity);

                }

            }

        } else {

            xAcceleration = 0;
            yAcceleration = 0;

            yVelocity *= deceleration;
            xVelocity *= deceleration;

            if (Math.abs(xVelocity) < 0.01) {
                xVelocity = 0;
            }

            if (Math.abs(yVelocity) < 0.01) {
                yVelocity = 0;
            }

        }

        // Atualiza a posição com base na velocidade calculada

            xPos += xVelocity * deltaTime;
            yPos += yVelocity * deltaTime;
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

    public void setDeceleration(float deceleration) {
        this.deceleration = deceleration;
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
}
