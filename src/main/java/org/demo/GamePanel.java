package org.demo;

import org.inputs.KeyboardInputs;
import org.inputs.MouseInputs;
import static org.utils.Constants.Directions.DOWN;
import static org.utils.Constants.Directions.LEFT;
import static org.utils.Constants.Directions.RIGHT;
import static org.utils.Constants.Directions.UP;
import static org.utils.Constants.PlayerConstants.IDLE;
import static org.utils.Constants.PlayerConstants.RUNNING;
import static org.utils.Constants.PlayerConstants.getSpriteAmount;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


public class GamePanel extends JPanel {

  private final MouseInputs mouseInputs;
  private float xDelta = 100;
  private float yDelta = 100;
  private BufferedImage image;
  private BufferedImage[][] animations;

  private int animationTick = 15;
  private int animationIndex = 15;
  private int animationSpeed = 15;
  private int playerAction = IDLE;
  private int playerDirection = -1;
  private boolean moving = false;


  public GamePanel() {
    mouseInputs = new MouseInputs(this);

    importImage();
    loadAnimations();

    setPanelSize();
    addKeyListener(new KeyboardInputs(this));
    addMouseListener(mouseInputs);
    addMouseMotionListener(mouseInputs);
  }

  private void loadAnimations() {
    animations = new BufferedImage[9][6];
    for (int j = 0; j < animations.length; j++) {
      for (int i = 0; i < animations[j].length; i++) {
        animations[j][i] = image.getSubimage(i * 64, j * 40, 64, 40);

      }
    }
  }

  private void importImage() {
    try (InputStream inputStream = getClass().getResourceAsStream("/player_sprites.png")) {
      image = ImageIO.read(inputStream);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  private void setPanelSize() {
    Dimension size = new Dimension(1280, 800);
    //setMinimumSize(size);
    setPreferredSize(size);
    //setMaximumSize(size);
  }

  public void setDirection(int direction) {
    this.playerDirection = direction;
    moving = true;
  }

  public void setMoving(boolean moving) {
    this.moving = moving;
  }

  @Override
  public void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    updateAnimationTick();
    setAnimation();
    updatePosition();

    graphics.drawImage(animations[playerAction][animationIndex], (int) xDelta, (int) yDelta, 256, 160, null);

  }

  private void updatePosition() {
    if (moving) {
      switch (playerDirection) {
        case LEFT -> xDelta -= 5;
        case UP -> yDelta -= 5;
        case RIGHT -> xDelta += 5;
        case DOWN -> yDelta += 5;
      }
    }
  }

  private void setAnimation() {
    playerAction = moving ? RUNNING : IDLE;
  }

  private void updateAnimationTick() {
    animationTick++;
    if (animationTick >= animationSpeed) {
      animationTick = 0;
      animationIndex++;
      if (animationIndex >= getSpriteAmount(playerAction)) {
        animationIndex = 0;
      }
    }
  }

}
