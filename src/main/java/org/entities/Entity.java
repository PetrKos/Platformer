package org.entities;

public abstract class Entity {

  protected int width;
  protected int height;
  protected float x;
  protected float y;

  public Entity(float x, float y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }
}
