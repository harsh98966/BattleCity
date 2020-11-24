package com.BattleCity.assests;

import java.awt.image.BufferedImage;

public class AnimatedSprite {
    private final Sprite[] sprites;
    private final int totalFrames;
    private int currFrame;

    private long prevTime;
    private long timeGapInMillis;
    private boolean cycleComplete;

    // buffered image
    private BufferedImage image;
    private int x, y, w, h;

    public AnimatedSprite(int fps, Sprite[] sprites) {
        this.sprites = sprites;
        totalFrames = sprites.length;
        init(fps);
    }

    public AnimatedSprite(int fps, int w, int h, BufferedImage image) {
        totalFrames = 20;
        sprites = null;
        this.w = w;
        this.h = h;
        init(fps);
        this.image = image;

    }


    private void init(int fps){
        currFrame = 0;
        cycleComplete = false;
        timeGapInMillis = 1000 / fps;
        prevTime = System.currentTimeMillis();
        image = null;
    }

    public Sprite getSprite() {
        if (prevTime == 0) prevTime = System.currentTimeMillis();
        long currTime = System.currentTimeMillis();
        if (currTime - prevTime >= timeGapInMillis) {
            currFrame++;
            if (currFrame > totalFrames - 1) {
                currFrame = 0;
            }
            prevTime = currTime;
        }
        if (currFrame == totalFrames - 1) cycleComplete = true;
        return sprites[currFrame];
    }

    public BufferedImage getImage(){
        if (prevTime == 0) prevTime = System.currentTimeMillis();
        long currTime = System.currentTimeMillis();
        if (currTime - prevTime >= timeGapInMillis) {
            setimageFrame();
            prevTime = currTime;
        }
        return image.getSubimage(x, y, w, h);
    }

    // frame in image
    private void setimageFrame(){
        int nextX = x + w;
        int nextY = y;
        if(nextX > (image.getWidth() - w)){
            nextX = 0;
            nextY = y + h;
            if(nextY > (image.getHeight() - h)){
                nextY = 0;
            }
        }

        x = nextX;
        y = nextY;

    }

    public void setFps(int fps) {
        timeGapInMillis = 1000 / fps;
    }

    public boolean isCycleComplete() {
        return cycleComplete;
    }

    public int getWidth() {
        return sprites[0].getWIDTH();
    }

    public int getHeight() {
        return sprites[0].getHEIGHT();
    }

    public Sprite[] getSprites() {
        return sprites;
    }

    public void setframe(int frame) {
        cycleComplete = false;
        currFrame = frame;
    }

}
