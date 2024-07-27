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
        gameThread.start(); // Use start() em vez de run()
    }

    @Override
    public void run() {
        long lastFrameTime = System.nanoTime();
        long now;
        double timePerFrame = 1000000000.0 / FPS_SET;

        int frames = 0;
        long lastCheck = System.currentTimeMillis();

        while (true) {
            now = System.nanoTime();
            double deltaTime = (now - lastFrameTime) / 1000000000.0; // Convertendo para segundos

            if (now - lastFrameTime >= timePerFrame) {
                gamePanel.updateGame((float) deltaTime); // Passa deltaTime real para o GamePanel
                gamePanel.repaint();
                lastFrameTime = now;
                frames++;
            }

            // FPS display
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
    }
}
