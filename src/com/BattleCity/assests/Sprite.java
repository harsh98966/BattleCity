package com.BattleCity.assests;

import com.BattleCity.main.B_Render;

public class Sprite {
    public static Sprite[] tankSprites;
    public static Sprite[] wallsSprite;
    public static Sprite[] grassSprite;
    public static Sprite[] miscSprite;

    //loading sprites
    static {
        tankSprites = new Sprite[256];
        wallsSprite = new Sprite[10];
        grassSprite = new Sprite[6];
        miscSprite = new Sprite[49];

        //tank sprites
        for (int y = 0; y < 256 / 16; y++) {
            for (int x = 0; x < 256 / 16; x++) {
                tankSprites[x + y * (256 / 16)] = new Sprite(x * 16, y * 16, 16, 16, true, SpriteSheet.tankSpriteSheet);
            }
        }

        //walls
        wallsSprite[0] = new Sprite(0, 0, 16, 16, true, SpriteSheet.wallSpriteSheet);
        wallsSprite[1] = new Sprite(16, 16, 16, 16, true, SpriteSheet.wallSpriteSheet);
        //TODO: initialize half bricks
//        wallsSprite[2] = new Sprite(16, 0, 16, 16, true, SpriteSheet.wallSpriteSheet);
//        wallsSprite[3] = new Sprite(32, 0, 16, 16, true, SpriteSheet.wallSpriteSheet);
//        wallsSprite[4] = new Sprite(48, 0, 16, 16, true, SpriteSheet.wallSpriteSheet);
//        wallsSprite[5] = new Sprite(64, 0, 16, 16, true, SpriteSheet.wallSpriteSheet);

//        for(int y = 0; y < SpriteSheet.wallSpriteSheet.getHeight() / 16; y++){
//            for(int x = 0; x < SpriteSheet.wallSpriteSheet.getWidth(); x++){
//                wallsSprite[x + y * 16] =
//            }
//        }

        //grass
        for (int y = 0; y < SpriteSheet.grassSpriteSheet.getHeight() / 16; y++) {
            for (int x = 0; x < SpriteSheet.grassSpriteSheet.getWidth() / 16; x++) {
                grassSprite[x + y * (SpriteSheet.grassSpriteSheet.getWidth() / 16)] = new Sprite(x * 16, y * 16, 16, 16, false, SpriteSheet.grassSpriteSheet);
            }
        }

        //misc
        for (int y = 0; y < SpriteSheet.miscSpriteSheet.getHeight() / 16; y++) {
            for (int x = 0; x < SpriteSheet.miscSpriteSheet.getWidth() / 16; x++) {
                miscSprite[x + y * (SpriteSheet.miscSpriteSheet.getWidth() / 16)] = new Sprite(x * 16, y * 16, 16, 16, false, SpriteSheet.miscSpriteSheet);
            }
        }


    }


    private int[] pixels;
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

    private void load() {

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
}
