package org.utils;

import org.demo.Game;

public class HelpMethods {

  public static boolean canMoveHere(float x, float y, float width, float height, int[][] levelData) {
    return !isSolid(x, y, levelData) &&
            (!isSolid(x + width, y + height, levelData)) &&
            (!isSolid(x + width, y, levelData)) &&
            (!isSolid(x, y + height, levelData));
  }

  private static boolean isSolid(float x, float y, int[][] levelData) {
    if (x < 0 || x >= Game.GAME_WIDTH) {
      return true;
    }
    if (y < 0 || y >= Game.GAME_HEIGHT) {
      return true;
    }

    float xIndex = x / Game.TILES_SIZE;
    float yIndex = y / Game.TILES_SIZE;

    int value = levelData[(int) yIndex][(int) xIndex];

    return value >= 48 || value < 0 || value != 11;

  }

}
