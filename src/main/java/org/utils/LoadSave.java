package org.utils;

import org.demo.Game;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {

  public static final String PLAYER_ATLAS = "player_sprites.png";
  public static final String LEVEL_ATLAS = "outside_sprites.png";
  public static final String LEVEL_ONE_DATA = "level_one_data.png";

  public static BufferedImage getSpriteAtlas(String fileName) {
    BufferedImage bufferedImage = null;
    try (InputStream inputStream = LoadSave.class.getResourceAsStream("/" + fileName)) {
      bufferedImage = ImageIO.read(inputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return bufferedImage;
  }

  public static int[][] getLevelData() {
    int[][] levelData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
    BufferedImage image = getSpriteAtlas(LEVEL_ONE_DATA);
    for (int j = 0; j < image.getHeight(); j++) {
      for (int i = 0; i < image.getWidth(); i++) {
        Color color = new Color(image.getRGB(i, j));
        int value = color.getRed();
        if (value >= 48) {
          value = 0;
        }
        levelData[j][i] = value;
      }
    }
    return levelData;
  }

}
