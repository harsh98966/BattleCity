package com.BattleCity.tank.missile;


import com.BattleCity.animation.Animation;
import com.BattleCity.assests.AnimatedSprite;
import com.BattleCity.assests.Assets;
import com.BattleCity.core.B_Object;
import com.BattleCity.core.Collision;
import com.BattleCity.core.gameStates.levelState.Gameplay;
import com.BattleCity.level.levelgenerator.tiles.grass.Grass;
import com.BattleCity.level.levelgenerator.tiles.walls.HardWall;
import com.BattleCity.level.levelgenerator.tiles.walls.NormalWall;
import com.BattleCity.level.queen.QueenAlive;
import com.BattleCity.tank.Tank;
import com.BattleCity.tank.enemy.Enemy;
import com.BattleCity.tank.player.Player;

public class Missile extends B_Object {

    private double xIncrement, yIncrement;
    private final Tank tank;
    private final int missileDirection;
    private boolean missileHit;

    public Missile(int x, int y, Tank tank) {
        super(x - 3, y - 3, 5, 5, true, Assets.missileSprites[0]);
        this.tank = tank;
        missileDirection = tank.getDirection();
        setSprite();
    }

    private void setSprite() {
        double speed = 2;
        switch (tank.getLevel()) {
            case 1 -> speed = 3.5;
            case 2 -> speed = 4;
            case 3 -> speed = 5;
        }


        if (tank.getDirection() == Tank.UP) {
            sprite = Assets.missileSprites[Tank.UP];
            yIncrement = -speed;
        }
        if (tank.getDirection() == Tank.DOWN) {
            sprite = Assets.missileSprites[Tank.DOWN];
            yIncrement = speed;
        }
        if (tank.getDirection() == Tank.RIGHT) {
            sprite = Assets.missileSprites[Tank.RIGHT];
            xIncrement = speed;
        }
        if (tank.getDirection() == Tank.LEFT) {
            sprite = Assets.missileSprites[Tank.LEFT];
            xIncrement = -speed;
        }
    }


    @Override
    public void update() {

        x += xIncrement;
        y += yIncrement;
        if (Collision.outOfBounds((int) x, (int) y, width, height)) {
            x -= xIncrement;
            y -= yIncrement;
            new Animation((int) (x + width / 2), (int) (y + height / 2), 0,new AnimatedSprite(10, Assets.missileBlastSprites));
            tank.setMissile(false);
            super.remove();
        } else {
            Collision();
        }

    }


    private void onCollide(B_Object obj) {
        if (obj != tank) {
            if (obj instanceof Player) {
                if (tank instanceof Enemy) ((Player) obj).onHit(tank.getDamage());
            } else if (obj instanceof Enemy) {
                if (tank instanceof Player) ((Enemy) obj).onHit(tank.getDamage());
            } else if (obj instanceof Missile) {
                ((Missile) obj).missileRemove();
                super.remove();
                tank.setMissile(false);
                return;
            } else if (obj instanceof HardWall) {
                if (tank.getDamage() > 100) {
                    obj.remove();
                }
            } else if(obj instanceof NormalWall){
                ((NormalWall) obj).onHit(missileDirection);
            } else if(obj instanceof QueenAlive){
                obj.remove();
            }
            if(!(obj instanceof Grass)) remove();
        }


    }

    private void Collision() {

        if (Collision.outOfBounds((int) x, (int) y, width, height)) {
            remove();
            return;
        }

        for (int i = 0; i < Gameplay.B_Object_List.size(); i++) {
            B_Object obj = Gameplay.B_Object_List.get(i);
            if (obj != this && obj != tank && obj.isSolid()) {
                if (Collision.areColliding(this, obj)) {
                    onCollide(obj);
                }
            }
        }
    }

    public void missileRemove(){
        super.remove();
        tank.setMissile(false);
    }

    @Override
    public void remove() {
        super.remove();
        tank.setMissile(false);
        new Animation((int)(x + width / 2), (int)(y + height / 2), 0, new AnimatedSprite(10, Assets.missileBlastSprites));
    }
}
