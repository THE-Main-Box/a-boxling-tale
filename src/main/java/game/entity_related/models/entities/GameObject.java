package game.entity_related.models.entities;

import java.awt.image.BufferedImage;

public abstract class GameObject {
    // Variáveis básicas para a posição e tamanho
    protected float xPos, yPos;
    protected int width, height;

    // Parâmetros relacionados à animação
    protected boolean facingForward; // Direção em que o personagem está virado

    public GameObject(float xPos, float yPos, int width, int height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
    }

    public GameObject() {
    }

    // Métodos para trabalhar com os sprites da folha de sprites
    public BufferedImage getSprite(int x, int y, int width, int height, BufferedImage spriteSheet) {
        return spriteSheet.getSubimage(x, y, width, height);
    }

    // seleciona uma imagem da sprite sheet selecionada baseada nos indices, dimensoes do sprite e nas dimensoes do canvas
    public BufferedImage getSpriteByIndex(int indexX, int indexY, int canvasWidth, int canvasHeight, BufferedImage spriteSheet) {
        return spriteSheet.getSubimage(indexX * canvasWidth, indexY * canvasHeight, canvasWidth, canvasHeight);
    }

    // Define a direção do personagem
    public boolean isFacingForward() {
        return facingForward;
    }

    public void setFacingForward(boolean facingForward) {
        this.facingForward = facingForward;
    }

    public float getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public float getyPos() {
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
}
