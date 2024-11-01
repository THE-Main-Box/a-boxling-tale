package game;

import game.entity_related.animation_related.sprite_related.Sprite;
import game.entity_related.models.blocks.StaticTileBlock;
import game.entity_related.models.entities.Player;
import game.window_related.GamePanel;
import game.window_related.GameWindow;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    private Thread gameThread;

    private Player player; // TODO: alterar o meio como os inputs são processados para impedir que o jogador trave quando mudar de direção

    private List<StaticTileBlock> staticTileBlocks = new ArrayList<>();

    // dimensões padroes do player w=52 h=60

    public Game() {
        player = new Player(100,100, 52,60, 5);

        this.initData();

        this.gamePanel = new GamePanel(player, staticTileBlocks);
        this.gameWindow = new GameWindow(gamePanel);

        this.gamePanel.requestFocus();


        startGameLoop();
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void initData(){
        this.loadPlayerSpriteSheet();           // carrega a sprite sheet de todos os componentes do jogador
        this.loadStaticBlockSpriteSheet();      // carrega todos os sprites de todos os blocos estaticos

        this.loadLevelStaticBlocks();           //carrega todos os blocos estaticos da fase atual
    }

    private void loadLevelStaticBlocks() {
        this.staticTileBlocks.addAll(
                Arrays.asList(
                        new StaticTileBlock(100, 150, 80, 80, false, new Sprite(1, 0)),
                        new StaticTileBlock(180, 150, 80, 80, false, new Sprite(1, 0)),
                        new StaticTileBlock(260, 150, 80, 80, false, new Sprite(1, 0)),
                        new StaticTileBlock(340, 150, 80, 80, false, new Sprite(1, 0)),
                        new StaticTileBlock(420, 150, 80, 80, false, new Sprite(1, 0)),
                        new StaticTileBlock(500, 150, 80, 80, false, new Sprite(1, 0)),
                        new StaticTileBlock(580, 150, 80, 80, false, new Sprite(1, 0)),
                        new StaticTileBlock(660, 150, 80, 80, false, new Sprite(1, 0)),
                        new StaticTileBlock(740, 150, 80, 80, false, new Sprite(1, 0)),
                        new StaticTileBlock(180, 150, 80, 80, false, new Sprite(1, 0)),
                        new StaticTileBlock(260, 150, 80, 80, false, new Sprite(1, 0)),
                        new StaticTileBlock(340, 150, 80, 80, false, new Sprite(1, 0)),
                        new StaticTileBlock(420, 150, 80, 80, false, new Sprite(1, 0)),
                        new StaticTileBlock(500, 150, 80, 80, false, new Sprite(1, 0)),
                        new StaticTileBlock(580, 150, 80, 80, false, new Sprite(1, 0)),
                        new StaticTileBlock(660, 150, 80, 80, false, new Sprite(1, 0)),
                        new StaticTileBlock(740, 150, 80, 80, false, new Sprite(1, 0))
                )
        );
    }

    private void loadStaticBlockSpriteSheet(){
        try {

            InputStream staticBlockTileSheet = getClass().getResourceAsStream("/sprites/staticBlock-tile_sheet.png");

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
