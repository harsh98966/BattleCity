package com.BattleCity.tank;

import com.BattleCity.assests.Sprite;
import com.BattleCity.assests.TankSprite;
import com.BattleCity.input.Keyboard;
import com.BattleCity.core.B_Render;
import com.BattleCity.core.B_Renderer;
import com.BattleCity.core.BattleCity;
import com.BattleCity.level.Collision;
import com.BattleCity.tank.missile.Missile;
import java.awt.event.KeyEvent;

public class Tank extends B_Renderer {
    public static final int UP = 0;
    public static final int LEFT = 1;
    public static final int DOWN = 2;
    public static final int RIGHT = 3;

    protected int direction, life, damage, speed;
    protected TankSprite animatedSprites;
    protected Sprite sprite;
    protected boolean moving;
    protected boolean missile;

    public Tank(int x, int y, int life, int damage, int speed, TankSprite sprite) {
        this.x = x;
        this.y = y;
        this.life = life;
        this.damage = damage;
        this.speed = speed;
        this.animatedSprites = sprite;
        direction = DOWN;
        this.sprite = animatedSprites.getSprite(this);
        moving = false;
        missile = false;
        BattleCity.objectRender.add(this);
    }


    @Override
    public void update() {
        if (Keyboard.keys[KeyEvent.VK_W]) {
            direction = UP;
            move();
        } else if (Keyboard.keys[KeyEvent.VK_S]) {
            direction = DOWN;
            move();
        } else if (Keyboard.keys[KeyEvent.VK_A]) {
            direction = LEFT;
            move();
        } else if (Keyboard.keys[KeyEvent.VK_D]) {
            direction = RIGHT;
            move();
        }

        if (Keyboard.keys[KeyEvent.VK_SPACE] && !missile) {
            missile = true;
            new Missile(this);
        }

    }

    public void move() {
        sprite = animatedSprites.getSprite(this);
        int nextX = x;
        int nextY = y;
        switch (direction) {
            default -> nextY -= speed;
            case DOWN -> nextY += speed;
            case RIGHT -> nextX += speed;
            case LEFT -> nextX -= speed;
        }
        if (!collide(nextX, nextY)) {
            x = nextX;
            y = nextY;
        }
    }

    private boolean collide(int x, int y) {
        return Collision.collision(x, y) || Collision.TankCollision(x, y, this);
    }

    @Override
    public void render(B_Render render) {
        render.render(x, y, sprite);
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    @Override
    protected void remove() {
        super.remove();
        //TODO: add dying animation
    }

    public int getDirection() {
        return direction;
    }

    public void setMissile(boolean missile) {
        this.missile = missile;
    }

    public Sprite getSprite() {
        return sprite;
    }

}
