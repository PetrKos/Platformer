package org.demo;


import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GameWindow {

  private final JFrame jFrame;

  public GameWindow(GamePanel gamePanel) {
    jFrame = new JFrame();

    jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    jFrame.add(gamePanel);
    jFrame.setLocationRelativeTo(null);
    jFrame.setResizable(false);
    jFrame.pack();
    jFrame.setVisible(true);
  }
}
