package game;

import game.window_related.GamePanel;
import game.window_related.GameWindow;

public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private final int FPS_SET = 60;
    private Thread gameThread;

    public Game() {
        this.gamePanel = new GamePanel();
        this.gameWindow = new GameWindow(gamePanel);

        this.gamePanel.requestFocus();
        startGameLoop();
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.run();
    }


    @Override
    public void run() {

        int frames = 0;
        long lastCheck = 0;

        double timePerFrame = 1000000000.0 / FPS_SET;
        long lastFrame = System.nanoTime();
        long now;

        while (true) {

            now = System.nanoTime();
            if (now - lastFrame >= timePerFrame) {

                gamePanel.repaint();
                lastFrame = now;
                frames++;
            }



            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }

    }
}
