package org.demo;

public class Game implements Runnable {

  private final int FPS_SET = 120;
  private GameWindow gameWindow;
  private GamePanel gamePanel;
  private Thread gameThread;

  public Game() {
    gamePanel = new GamePanel();
    gameWindow = new GameWindow(gamePanel);
    gamePanel.requestFocus();
    startGameLoop();
  }


  @Override
  public void run() {

    //we need ns here because when we're dealing with a game loop,
    //we are dealing with times that are so tiny, even millis wont do.
    double timePerFrame = 1_000_000_000.0 / FPS_SET;
    long lastFrame = System.nanoTime();
    long now;

    int frames = 0;
    long lastCheck = System.currentTimeMillis();

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

  private void startGameLoop() {
    gameThread = new Thread(this);
    gameThread.start();
  }
}
