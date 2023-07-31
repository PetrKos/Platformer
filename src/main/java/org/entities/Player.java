package org.entities;


import static org.utils.Constants.PlayerConstants.ATTACK_1;
import static org.utils.Constants.PlayerConstants.IDLE;
import static org.utils.Constants.PlayerConstants.RUNNING;
import static org.utils.Constants.PlayerConstants.getSpriteAmount;
import org.utils.LoadSave;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player extends Entity {
  private BufferedImage[][] animations;
  private int animationTick = 25;
  private int animationIndex = 25;
  private int animationSpeed = 25;
  private int playerAction = IDLE;
  private boolean moving = false;
  private boolean attacking = false;
  private boolean left;
  private boolean up;
  private boolean right;
  private boolean down;
  private float playerSpeed = 2.0f;

  public Player(float x, float y, int width, int height) {
    super(x, y, width, height);
    loadAnimations();
  }

  public void update() {
    updatePosition();
    updateAnimationTick();
    setAnimation();
  }

  public void render(Graphics g) {
    g.drawImage(animations[playerAction][animationIndex], (int) x, (int) y, width, height, null);
  }

  private void updateAnimationTick() {
    animationTick++;
    if (animationTick >= animationSpeed) {
      animationTick = 0;
      animationIndex++;
      if (animationIndex >= getSpriteAmount(playerAction)) {
        animationIndex = 0;
        attacking = false;
      }

    }
  }

  private void setAnimation() {
    int startAnimation = playerAction;
    playerAction = moving ? RUNNING : IDLE;
    if (attacking) {
      playerAction = ATTACK_1;
    }
    if (startAnimation != playerAction) {
      resetAnimationTick();
    }
  }

  private void resetAnimationTick() {
    animationTick = 0;
    animationIndex = 0;
  }

  private void updatePosition() {
    moving = false;

    if (left && !right) {
      x -= playerSpeed;
      moving = true;
    } else if (right && !left) {
      x += playerSpeed;
      moving = true;
    }

    if (up && !down) {
      y -= playerSpeed;
      moving = true;
    } else if (down && !up) {
      y += playerSpeed;
      moving = true;
    }
  }

  private void loadAnimations() {
    BufferedImage bufferedImage = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS);

    animations = new BufferedImage[9][6];
    for (int j = 0; j < animations.length; j++)
      for (int i = 0; i < animations[j].length; i++)
        animations[j][i] = bufferedImage.getSubimage(i * 64, j * 40, 64, 40);
  }

  public void resetDirBooleans() {
    left = false;
    right = false;
    up = false;
    down = false;
  }

  public void setAttacking(boolean attacking) {
    this.attacking = attacking;
  }

  public boolean isLeft() {
    return left;
  }

  public void setLeft(boolean left) {
    this.left = left;
  }

  public boolean isUp() {
    return up;
  }

  public void setUp(boolean up) {
    this.up = up;
  }

  public boolean isRight() {
    return right;
  }

  public void setRight(boolean right) {
    this.right = right;
  }

  public boolean isDown() {
    return down;
  }

  public void setDown(boolean down) {
    this.down = down;
  }

  public void resetDirectionBooleans() {
    left = false;
    down = false;
    right = false;
    up = false;
  }
}