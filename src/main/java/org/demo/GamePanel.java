package org.demo;

import org.inputs.KeyboardInputs;
import org.inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

  private final MouseInputs mouseInputs;
  private int xDelta = 100;
  private int yDelta = 100;
  public GamePanel() {
    mouseInputs = new MouseInputs(this);
    addKeyListener(new KeyboardInputs(this));
    addMouseListener(mouseInputs);
    addMouseMotionListener(mouseInputs);
  }

  public void changeXDelta(int value) {
    this.xDelta += value;
    repaint();
  }

  public void changeYDelta(int value) {
    this.yDelta += value;
    repaint();
  }

  public void setRectPosition(int x, int y) {
    this.xDelta = x;
    this.yDelta = y;
    repaint();
  }

  @Override
  public void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);

    graphics.fillRect(xDelta, yDelta, 200, 50);
  }
}
