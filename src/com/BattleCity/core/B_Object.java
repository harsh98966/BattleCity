package com.BattleCity.core;

import com.BattleCity.assests.AnimatedSprite;
import com.BattleCity.assests.Sprite;
import com.BattleCity.tank.Tank;

public abstract class B_Object {

    protected double x, y;
    protected int width, height;
    protected int spriteWidth, spriteHeight, spriteX, spriteY;
    protected boolean solid;
    private boolean remove;
    protected Sprite sprite;
    protected AnimatedSprite animatedSprite;
    protected boolean hasdyingAnimation;
    protected Sprite[] dyingAnimationSprites;


    public B_Object(int x, int y, int width, int height, boolean solid, Sprite sprite){
        this.x = x;
        this.y = y;
        spriteX = 0;
        spriteY = 0;
        this.sprite = sprite;
        this.width = width;
        this.height = height;
        this.solid = solid;
        spriteWidth = sprite.getWIDTH();
        spriteHeight = sprite.getHEIGHT();
        add();
    }

    public B_Object(int x, int y, int width, int height, boolean solid, AnimatedSprite animatedSprite){
        this.x = x;
        this.y = y;
        spriteX = 0;
        spriteY = 0;
        this.animatedSprite = animatedSprite;
        this.width = width;
        this.height = height;
        this.solid = solid;
        this.sprite = animatedSprite.getSprite();
        spriteWidth = sprite.getWIDTH();
        spriteHeight = sprite.getHEIGHT();
        add();
    }


    public abstract void update();

    public void render(B_Render render){

        if(animatedSprite != null){
            sprite = animatedSprite.getSprite();
        }
        if(sprite != null) render.render(this);
    }

    public boolean OutOfBounds(){
        return Collision.outOfBounds((int)x, (int)y, width, height);
    }

    boolean isRemoved(){
        return remove;
    }

    public void remove(){
        remove = true;
    }

    public int getX(){
        return (int)x;
    }

    public int getY(){
        return (int)y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSpriteWidth() {
        return spriteWidth;
    }

    public int getSpriteHeight() {
        return spriteHeight;
    }

    public int getSpriteX() {
        return spriteX;
    }

    public int getSpriteY() {
        return spriteY;
    }

    public boolean isSolid(){
        return solid;
    }

    public Sprite getSprite(){
        return sprite;
    }

    private void add(){
        if(this instanceof Tank){
            BattleCity.Tanks.add(this);
        }
        BattleCity.B_Object_List.add(this);
    }

}
