package game;

import game.window_related.GamePanel;
import game.window_related.GameWindow;

public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    private Thread gameThread;

    public Game() {
        this.gamePanel = new GamePanel();
        this.gameWindow = new GameWindow(gamePanel);

        this.gamePanel.requestFocus();

        startGameLoop();
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        long lastFrameTime = System.nanoTime();
        long lastUpdateTime = System.nanoTime();
        long now;
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        while (true) {
            now = System.nanoTime();

            // FPS Logic
            if (now - lastFrameTime >= timePerFrame) {
                gamePanel.repaint();
                lastFrameTime = now;
                frames++;
            }

            // UPS Logic
            if (now - lastUpdateTime >= timePerUpdate) {
                double deltaTime = (now - lastUpdateTime) / 1000000000.0; // Convertendo para segundos
                gamePanel.updateGame((float) deltaTime); // Passa deltaTime real para o GamePanel
                lastUpdateTime = now;
                updates++;
            }

            // FPS and UPS Display
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }
}
