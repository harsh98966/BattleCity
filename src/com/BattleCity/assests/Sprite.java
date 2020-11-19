package com.BattleCity.assests;

import com.BattleCity.core.B_Render;

import java.util.Arrays;

public class Sprite {

    private final int[] pixels;
    private final int WIDTH, HEIGHT;

    public Sprite(int x, int y, int w, int h, SpriteSheet spriteSheet) {

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

    public Sprite(int w, int h, int color) {
        WIDTH = w;
        HEIGHT = h;
        pixels = new int[WIDTH * HEIGHT];
        Arrays.fill(pixels, color);
    }

    public void render(B_Render render) {
//        render.render( this);
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
