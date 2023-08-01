package org.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public abstract class Entity {

  protected int width;
  protected int height;
  protected float x;
  protected float y;
  protected Rectangle2D.Float hitbox;

  public Entity(float x, float y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  protected void drawHitbox(Graphics graphics) {
    // for debugging hitbox
    graphics.setColor(Color.PINK);
    graphics.drawRect((int) hitbox.x, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
  }

  protected void initializeHitbox(float x, float y, float width, float height) {
    hitbox = new Rectangle2D.Float(x, y, width, height);
  }

//  protected void updateHitbox() {
//    hitbox.x = (int) x;
//    hitbox.y = (int) y;
//  }

  public Rectangle2D.Float getHitbox() {
    return hitbox;
  }

}
