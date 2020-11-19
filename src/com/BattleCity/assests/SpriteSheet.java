package com.BattleCity.assests;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {

    public static final int TRANSPARENT_COLOR = 0xff008000; //background color used in spritesheets



    private int[] pixels;
    private final String path;
    private int width, height;

    public SpriteSheet(String path) {
        this.path = path;
        load();
    }

    private void load() {
        try {
            BufferedImage image = ImageIO.read(this.getClass().getResource(path));
            width = image.getWidth();
            height = image.getHeight();
            pixels = new int[width * height];
            image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int[] getPixels() {
        return pixels;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
