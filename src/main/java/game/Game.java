package game;

import game.entity_related.models.blocks.StaticTileBlock;
import game.entity_related.models.entities.Player;
import game.window_related.GamePanel;
import game.window_related.GameWindow;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;

public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    private Thread gameThread;

    private Player player;

    // dimensões padroes do player w=52 h=60

    public Game() {
        player = new Player(100,100, 52,60, 5);

        this.initData();

        this.gamePanel = new GamePanel(player);
        this.gameWindow = new GameWindow(gamePanel);

        this.gamePanel.requestFocus();


        startGameLoop();
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void initData(){
        this.loadPlayerSpriteSheet();
        this.loadStaticBlockSpriteSheet();
    }

    private void loadStaticBlockSpriteSheet(){
        try {

            InputStream staticBlockTileSheet = getClass().getResourceAsStream("/sprites/rock-ground-tile-map.png");

            assert staticBlockTileSheet != null;

            StaticTileBlock.spriteSheet = ImageIO.read(staticBlockTileSheet);

        } catch (IOException e) {
            throw new RuntimeException("Erro na importação da sprite sheet dos blocos do jogo");
        }
    }

    private void loadPlayerSpriteSheet() {
        try {
            InputStream dexBodySprites = getClass().getResourceAsStream("/sprites/dex-body-sprites.png");
            InputStream dexHeadSprites = getClass().getResourceAsStream("/sprites/dex-head-sprites.png");
            InputStream dexGunSprites = getClass().getResourceAsStream("/sprites/dex-gun.png");

            assert dexHeadSprites != null;
            assert dexBodySprites != null;

            Player.bodySpriteSheet = ImageIO.read(dexBodySprites);
            Player.headSpriteSheet = ImageIO.read(dexHeadSprites);

            Player.dexWeaponSpriteSheet = ImageIO.read(dexGunSprites);

        } catch (IOException e) {
            throw new RuntimeException("Erro na importação da sprite sheet do jogador");
        }
    }

    @Override
    public void run() {
        long lastFrameTime = System.nanoTime();
        long lastUpdateTime = System.nanoTime();
        long now;

        long lastCheck = System.currentTimeMillis();

        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;
        double deltaTime;

        int frames = 0;
        int updates = 0;

        while (true) {
            now = System.nanoTime();

            deltaTime = (now - lastUpdateTime) / 1000000000.0; // Convertendo para segundos

            // UPS Logic
            if (now - lastUpdateTime >= timePerUpdate) {
                gamePanel.updateGame((float) deltaTime); // Passa deltaTime real para o GamePanel
                lastUpdateTime = now;
                updates++;
            }

            // FPS Logic
            if (now - lastFrameTime >= timePerFrame) {
                gamePanel.repaint();
                lastFrameTime = now;
                frames++;
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
