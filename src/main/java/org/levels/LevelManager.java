package org.levels;

import org.demo.Game;
import static org.demo.Game.TILES_SIZE;
import org.utils.LoadSave;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class LevelManager {

  private Game game;
  private Level levelOne;
  private BufferedImage[] levelSprite;

  public LevelManager(Game game) {
    this.game = game;
    importOutsideSprites();
    levelOne = new Level(LoadSave.getLevelData());
  }

  private void importOutsideSprites() {
    BufferedImage image = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
    levelSprite = new BufferedImage[48];
    for (int j = 0; j < 4; j++) {
      for (int i = 0; i < 12; i++) {
        int index = j * 12 + i;
        levelSprite[index] = image.getSubimage(i * 32, j * 32, 32, 32);
      }
    }
  }


  public void draw(Graphics graphics) {
    for (int j = 0; j < Game.TILES_IN_HEIGHT; j++) {
      for (int i = 0; i < Game.TILES_IN_WIDTH; i++) {
        int index = levelOne.getSpriteIndex(i, j);
        graphics.drawImage(levelSprite[index], TILES_SIZE * i, TILES_SIZE * j, TILES_SIZE, TILES_SIZE, null);
      }
    }

  }

  public void update() {

  }


}
