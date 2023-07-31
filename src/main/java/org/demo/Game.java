package org.demo;

import org.entities.Player;
import org.levels.LevelManager;

import java.awt.Graphics;

public class Game implements Runnable {

  public static final int TILES_DEFAULT_SIZE = 32;
  public static final float SCALE = 2f;
  public static final int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
  public static final int TILES_IN_WIDTH = 26;
  public static final int TILES_IN_HEIGHT = 14;
  public static final int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
  public static final int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
  private final int FPS_SET = 120;
  private final int UPS_SET = 200;
  private GameWindow gameWindow;
  private GamePanel gamePanel;
  private Thread gameThread;
  private Player player;
  private LevelManager levelManager;

  public Game() {
    initClasses();
    gamePanel = new GamePanel(this);
    gameWindow = new GameWindow(gamePanel);
    gamePanel.requestFocus();

    startGameLoop();

  }

  private void initClasses() {
    player = new Player(200, 200, (int) (64 * SCALE), (int) (40 * SCALE));
    levelManager = new LevelManager(this);
  }


  public Player getPlayer() {
    return player;
  }

  @Override
  public void run() {

    //we need ns here because when we're dealing with a game loop,
    //we are dealing with times that are so tiny, even millis wont do.
    double timePerFrame = 1_000_000_000.0 / FPS_SET;
    double timePerUpdate = 1_000_000_000.0 / UPS_SET;
    long previousTime = System.nanoTime();

    int frames = 0;
    int updates = 0;
    long lastCheck = System.currentTimeMillis();

    double deltaU = 0;
    double deltaF = 0;

    while (true) {

      long currentTime = System.nanoTime();

      deltaU += (currentTime - previousTime) / timePerUpdate;
      deltaF += (currentTime - previousTime) / timePerFrame;
      previousTime = currentTime;

      if (deltaU >= 1) {
        update();
        updates++;
        deltaU--; //wtf check again episode 0.6
      }

      if (deltaF >= 1) {
        gamePanel.repaint();
        frames++;
        deltaF--;
      }

      if (System.currentTimeMillis() - lastCheck >= 1000) {
        lastCheck = System.currentTimeMillis();
        System.out.println("FPS: " + frames + " | UPS: " + updates);
        frames = 0;
        updates = 0;
      }

    }

  }

  public void render(Graphics graphics) {
    levelManager.draw(graphics);
    player.render(graphics);
  }

  public void update() {
    player.update();
    levelManager.update();
  }

  private void startGameLoop() {
    gameThread = new Thread(this);
    gameThread.start();
  }

  public void windowFocusLost() {
    player.resetDirectionBooleans();
  }
}
