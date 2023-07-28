package org.demo;

import javax.swing.*;

public class GameWindow {

  private final JFrame jFrame;

  public GameWindow(GamePanel gamePanel) {
    jFrame = new JFrame();
    jFrame.setSize(400, 400);
    jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    jFrame.add(gamePanel);
    jFrame.setLocationRelativeTo(null);
    jFrame.setVisible(true);
  }
}