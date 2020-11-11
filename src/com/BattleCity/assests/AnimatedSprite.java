package com.BattleCity.assests;

public class AnimatedSprite {
    private final Sprite[] sprites;
    private final int totalFrames;
    private int currFrame;

    private long prevTime;
    private long timeGapInMillis;
    private boolean cycleComplete;

    public AnimatedSprite(int fps, Sprite[] sprites) {
        this.sprites = sprites;
        currFrame = 0;
        totalFrames = sprites.length;
        prevTime = System.currentTimeMillis();
        timeGapInMillis = 1000 / fps;
        cycleComplete = false;
    }

    public AnimatedSprite(int fps, int x, int y,int totalFrames, int sheetWidth, Sprite[] sprites){
        this.sprites = new Sprite[totalFrames];
        currFrame = 0;
        this.totalFrames = totalFrames;
        prevTime = System.currentTimeMillis();
        timeGapInMillis = 1000/fps;
        cycleComplete = false;


        for(int xx = 0; xx < totalFrames; xx++){
            sprites[xx] = sprites[x + y * sheetWidth];
            x++;
        }
        System.out.println("AnimatedSprite: " + sprites[0]);
    }

    public Sprite getSprite() {
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

    public void setFps(int fps) {
        timeGapInMillis = 1000 / fps;
    }

    public boolean isCycleComplete() {
        return cycleComplete;
    }

}
