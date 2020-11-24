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
        for(int y = 0; y < screenHeight; y++){
            for(int x = 0; x < screenWidth; x++){
                pixels[x + y * screenWidth] = 0xff95a3a2;
//                pixels[x + y * screenWidth] = 0xff636363;
                if(x > 16 && x < 224){
                    if(y > 8 && y < 216){
                        pixels[x + y * screenWidth] = BACKGROUND_COLOR;
                    }
                }
            }
        }
    }

    public BufferedImage getImage(){
        return image;
    }

    public void render(int x, int y, Sprite sprite){
        int[] pix = sprite.getPixels();
        for(int yy = 0; yy < sprite.getHEIGHT(); yy++){
            for(int xx = 0; xx < sprite.getWIDTH(); xx++){
                if(x < 0 || y < 0 || x + sprite.getWIDTH() > BattleCity.WIDTH || y + sprite.getHEIGHT() > BattleCity.HEIGHT) break;
                int col = pix[xx + yy * sprite.getWIDTH()];
                if(col != SpriteSheet.TRANSPARENT_COLOR) pixels[(x + xx) + (y + yy) * screenWidth] = col;
            }
        }
    }

    public void render(B_Object obj){
        int x = obj.getX();
        int y = obj.getY();
        Sprite sprite = obj.getSprite();

        int[] pix = obj.getSprite().getPixels();
        for(int yy = obj.getSpriteY(); yy < obj.getSpriteHeight(); yy++){
            for(int xx = obj.getSpriteX(); xx < obj.getSpriteWidth(); xx++){
                if(x < 0 || y < 0 || x + obj.getWidth() > BattleCity.WIDTH || y + obj.getHeight() > BattleCity.HEIGHT) break;
                if(xx < 0 || xx >= obj.getSpriteWidth() || yy < 0 || yy >= obj.getSpriteHeight()) continue;
                int index = xx + yy * sprite.getWIDTH();
                if(index < 0 || index >= pix.length) continue;
                int col = pix[index];
                if(col != SpriteSheet.TRANSPARENT_COLOR) pixels[(x + xx) + (y + yy) * screenWidth] = col;
            }
        }
    }
}
