package com.BattleCity.core;

import com.BattleCity.assests.Sprite;
import com.BattleCity.assests.SpriteSheet;
import com.BattleCity.level.Level;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

public class B_Render {
    private BufferedImage image;
    private int[] pixels;
    private int screenWidth, screenHeight;
    private final int BACKGROUND_COLOR = 0x000000;


    public B_Render(int w, int h) {
        screenWidth = w;
        screenHeight = h;
        loadImage();
        clearBackground();
    }

    // creates blank image
    private void loadImage() {
        image = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    }

    public void clearBackground() {
        Arrays.fill(pixels, BACKGROUND_COLOR);
    }

    public BufferedImage getImage(){
        return image;
    }

    public void render(int x, int y, Sprite sprite){
        int[] pix = sprite.getPixels();
        for(int yy = 0; yy < sprite.getHEIGHT(); yy++){
            for(int xx = 0; xx < sprite.getWIDTH(); xx++){
                if(x < 0 || y < 0 || x > Level.currLevel.getWIDTH_IN_TILES() * 16 || y + sprite.getHEIGHT() > (Level.currLevel.getHEIGHT_IN_TILES() * 16)) break;
                int col = pix[xx + yy * sprite.getWIDTH()];
                if(col != SpriteSheet.TRANSPARENT_COLOR) pixels[(x + xx) + (y + yy) * screenWidth] = col;
            }
        }
    }


}
