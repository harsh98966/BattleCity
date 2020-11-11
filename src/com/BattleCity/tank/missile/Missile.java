package com.BattleCity.tank.missile;


import com.BattleCity.assests.Sprite;
import com.BattleCity.core.B_Render;
import com.BattleCity.core.B_Renderer;
import com.BattleCity.core.BattleCity;
import com.BattleCity.level.Level;
import com.BattleCity.tank.Tank;

public class Missile extends B_Renderer {

    private int xIncrement, yIncrement;
    private Sprite sprite;
    private final Tank tank;

    public Missile(Tank tank) {
        this.tank = tank;
        setSprite();
        x = (tank.getX() + tank.getSprite().getWIDTH() / 2) - sprite.getWIDTH() / 2;
        y = (tank.getY() + tank.getSprite().getHEIGHT() / 2) - sprite.getHEIGHT() / 2 ;
        y--;
        x--;
        BattleCity.objectRender.add(this);
    }

    private void setSprite() {
        if (tank.getDirection() == Tank.UP) {
            sprite = Sprite.missileSprites[Tank.UP];
            yIncrement = -5;
        }
        if (tank.getDirection() == Tank.DOWN) {
            sprite = Sprite.missileSprites[Tank.DOWN];
            yIncrement = 5;
        }
        if (tank.getDirection() == Tank.RIGHT) {
            sprite = Sprite.missileSprites[Tank.RIGHT];
            xIncrement = 5;
        }
        if (tank.getDirection() == Tank.LEFT) {
            sprite = Sprite.missileSprites[Tank.LEFT];
            xIncrement = -5;
        }
    }


    @Override
    public void update() {
        if (x < 0 || x + sprite.getWIDTH() >= Level.currLevel.getWIDTH_IN_TILES() * 16 || y < 0 || y + sprite.getHEIGHT() >= Level.currLevel.getHEIGHT_IN_TILES() * 16) {
            tank.setMissile(false);
            remove();
        } else {
            x += xIncrement;
            y += yIncrement;
        }

    }

    @Override
    public void render(B_Render render) {
        render.render(x, y, sprite);
    }

    private boolean collide(){

        return false;
    }


}
