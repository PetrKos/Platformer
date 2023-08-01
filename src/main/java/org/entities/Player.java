package org.entities;


import org.demo.Game;
import static org.utils.Constants.PlayerConstants.ATTACK_1;
import static org.utils.Constants.PlayerConstants.IDLE;
import static org.utils.Constants.PlayerConstants.RUNNING;
import static org.utils.Constants.PlayerConstants.getSpriteAmount;
import static org.utils.HelpMethods.canMoveHere;
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
  private int[][] levelData;
  private float xDrawOffset = 21 * Game.SCALE;
  private float yDrawOffset = 4 * Game.SCALE;

  public Player(float x, float y, int width, int height) {
    super(x, y, width, height);
    loadAnimations();
    initializeHitbox(x, y, 20 * Game.SCALE, 28 * Game.SCALE);
  }

  public void update() {
    updatePosition();
    updateAnimationTick();
    setAnimation();
  }

  public void render(Graphics graphics) {
    graphics.drawImage(animations[playerAction][animationIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset), width, height, null);
    drawHitbox(graphics);
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

    if (!left && !right && !up && !down) {
      return;
    }

    float xSpeed = 0;
    float ySpeed = 0;

    if (left && !right) {
      xSpeed = -playerSpeed;
    } else if (right && !left) {
      xSpeed = playerSpeed;
    }

    if (up && !down) {
      ySpeed = -playerSpeed;

    } else if (down && !up) {
      ySpeed = playerSpeed;
    }

//    if (canMoveHere(x + xSpeed, y + ySpeed, width, height, levelData)) {
//      this.x += xSpeed;
//      this.y += ySpeed;
//      moving = true;
//    }

    if (canMoveHere(hitbox.x + xSpeed, hitbox.y + ySpeed, hitbox.width, hitbox.height, levelData)) {
      hitbox.x += xSpeed;
      hitbox.y += ySpeed;
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

  public void loadLevelData(int[][] levelData) {
    this.levelData = levelData;
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