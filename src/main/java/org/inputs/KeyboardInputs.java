package org.inputs;

import org.demo.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {

  private final GamePanel gamePanel;
  public KeyboardInputs(GamePanel gamePanel) {
    this.gamePanel = gamePanel;
  }

  @Override
  public void keyTyped(KeyEvent keyEvent) {
  }

  @Override
  public void keyPressed(KeyEvent keyEvent) {
    switch (keyEvent.getKeyCode()) {
      case KeyEvent.VK_W -> gamePanel.changeYDelta(-5);
      case KeyEvent.VK_A -> gamePanel.changeXDelta(-5);
      case KeyEvent.VK_S -> gamePanel.changeYDelta(5);
      case KeyEvent.VK_D -> gamePanel.changeXDelta(5);
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {

  }
}
