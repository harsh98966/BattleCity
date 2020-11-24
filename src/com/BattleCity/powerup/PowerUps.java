package com.BattleCity.powerup;

import com.BattleCity.assests.Sprite;
import com.BattleCity.core.B_Object;
import com.BattleCity.core.BattleCity;
import com.BattleCity.core.Collision;
import com.BattleCity.core.gameStates.levelState.Gameplay;
import com.BattleCity.tank.Tank;

public abstract class PowerUps extends B_Object {
    private long time;
    private Sprite origSprite;
    public PowerUps(int x, int y, Sprite sprite) {
        super(x, y, 16, 16, false, sprite);
        origSprite = sprite;
        time = System.currentTimeMillis();
    }

    @Override
    public void update() {
        if(System.currentTimeMillis() - time >= 400){
            time = System.currentTimeMillis();
            switchSprite();
        }
        onCollide((Tank)Collide());
    }

    private void switchSprite(){
        if(sprite == origSprite){
            sprite = null;
        } else if(sprite == null){
            sprite = origSprite;
        }

    }

    public abstract void setProperties(Tank tank);

    private B_Object Collide() {
        for (B_Object tank : Gameplay.Tanks) {
            /*int tankX = tank.getX() + tank.getWidth() / 2;
            int tankY = tank.getY() + tank.getHeight() / 2;
            if (tankX > x && tankX < x + width) {
                if (tankY > y && tankY < y + height) {
                    return tank;
                }
            }*/
            if(Collision.areColliding(this, tank)) return tank;
        }
        return null;
    }

    private void onCollide(Tank tank) {
        if(tank != null){
            remove();
            setProperties(tank);
        }
    }

}
