package game.player_related;

import java.awt.image.BufferedImage;

public class Movable {
    private int xPos, yPos, width, height;
    private double xVelocity, yVelocity;
    private BufferedImage spriteSheet;

    public Movable(int posX, int posY, int width, int height) {
        this.xPos = posX;
        this.yPos = posY;
        this.width = width;
        this.height = height;
        this.xVelocity = 0;
        this.yVelocity = 0;
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
