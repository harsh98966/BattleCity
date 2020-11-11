package com.BattleCity.assests;

import com.BattleCity.core.B_Render;

import java.util.Arrays;

public class Sprite {
    public static Sprite[] tankSprites;
    public static Sprite[] levelSprites;
    public static Sprite voidSprite = new Sprite(16, 16, false, 0x000000);
    public static Sprite[] missileSprites;

    //loading sprites
    static {
        tankSprites = new Sprite[(SpriteSheet.tankSpriteSheet.getWidth() / 16) * (SpriteSheet.tankSpriteSheet.getHeight() / 16)];
        levelSprites = new Sprite[(SpriteSheet.levelSpriteSheet.getWidth() / 16) * (SpriteSheet.levelSpriteSheet.getHeight() / 16)];
        missileSprites = new Sprite[4];

        //tank sprites
        for (int y = 0; y < SpriteSheet.tankSpriteSheet.getHeight() / 16; y++) {
            for (int x = 0; x < SpriteSheet.tankSpriteSheet.getWidth() / 16; x++) {
                tankSprites[x + y * (SpriteSheet.tankSpriteSheet.getWidth() / 16)] = new Sprite(x * 16, y * 16, 16, 16, true, SpriteSheet.tankSpriteSheet);
            }
        }

        //level sprites
        for (int y = 0; y < SpriteSheet.levelSpriteSheet.getHeight() / 16; y++) {
            boolean solid = true;
            for (int x = 0; x < SpriteSheet.levelSpriteSheet.getWidth() / 16; x++) {
                if (y > 0 && x > 0) solid = false;
                levelSprites[x + y * (SpriteSheet.levelSpriteSheet.getWidth() / 16)] = new Sprite(x * 16, y * 16, 16, 16, solid, SpriteSheet.levelSpriteSheet);
            }
        }

        //missile
        for (int y = 0; y < SpriteSheet.missileSpriteSheet.getHeight() / 5; y++) {
            for (int x = 0; x < SpriteSheet.missileSpriteSheet.getWidth() / 5; x++) {
                missileSprites[x  ] = new Sprite(x * 5, y * 5, 5, 5, true, SpriteSheet.missileSpriteSheet);
            }
        }

    }


    private final int[] pixels;
    private final int WIDTH, HEIGHT;
    private final boolean SOLID;


    public Sprite(int x, int y, int w, int h, boolean solid, SpriteSheet spriteSheet) {
        SOLID = solid;

        pixels = new int[w * h];
        WIDTH = w;
        HEIGHT = h;
        int[] pix = spriteSheet.getPixels();
        for (int yy = 0; yy < h; yy++) {
            for (int xx = 0; xx < w; xx++) {
                pixels[xx + yy * w] = pix[(x + xx) + (y + yy) * spriteSheet.getWidth()];
            }
        }
    }

    public Sprite(int w, int h, boolean solid, int color) {
        SOLID = solid;
        WIDTH = w;
        HEIGHT = h;
        pixels = new int[WIDTH * HEIGHT];
        Arrays.fill(pixels, color);
    }

    public void render(B_Render render) {
        render.render(0, 0, this);
    }

    public int[] getPixels() {
        return pixels;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public boolean isSOLID() {
        return SOLID;
    }
}
