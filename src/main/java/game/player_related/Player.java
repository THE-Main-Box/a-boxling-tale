package game.player_related;

public class Player extends Movable{

    private static final int CANVAS_WIDTH = 80;
    private static final int CANVAS_HEIGHT = 40;
    private static final int OFFSET_X = 54;
    private static final int OFFSET_Y= 20;
    private static final int RENDER_WIDTH = CANVAS_WIDTH * 2;
    private static final int RENDER_HEIGHT = CANVAS_HEIGHT * 2;

    public Player(int posX, int posY, int width, int height) {
        super(posX, posY, width, height);
    }

    public int getCanvasWidth(){
        return CANVAS_WIDTH;
    }
    public int getCanvasHeight(){
        return CANVAS_HEIGHT;
    }
    public int getOffsetX(){
        return OFFSET_X;
    }
    public int getOffsetY(){
        return OFFSET_Y;
    }
    public int getRenderWidth(){
        return RENDER_WIDTH;
    }
    public int getRenderHeight(){
        return RENDER_HEIGHT;
    }

}
