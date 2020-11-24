package com.BattleCity.tank;

import com.BattleCity.animation.Animation;
import com.BattleCity.assests.AnimatedSprite;
import com.BattleCity.assests.Assets;
import com.BattleCity.assests.TankSprite;
import com.BattleCity.core.*;
import com.BattleCity.core.gameStates.levelState.Gameplay;
import com.BattleCity.tank.missile.Missile;
import com.BattleCity.tank.player.Player;

import java.awt.*;


public abstract class Tank extends B_Object {

    public static final int UP = 0;
    public static final int LEFT = 1;
    public static final int DOWN = 2;
    public static final int RIGHT = 3;

    // Tank States
    protected enum tankStates {
        BORN,
        ALIVE,
        DEAD
    }


    private boolean freeze;
    private long freezeStartTime;

    //current state of tank
    protected tankStates currState;

    //born animation
    private Animation bornAnimation;

    protected int tankLevel;
    protected int direction, damage, life;
    protected double speed;
    private boolean aMissile;
    private Missile missile;

    private long prevMissileTime;
    private long invincibleStartTime;
    private long invincibleEndTime;
    private final AnimatedSprite invincibleAnimation;
    private Animation animation;

    protected boolean invincible;

    protected TankSprite[] tankSprite;

    public Tank(int x, int y, int tankLevel, TankSprite[] sprite) {
        super(x, y, 16, 16, true, sprite[0].getSprite());
        currState = tankStates.BORN;
        bornAnimation = new Animation(x + width / 2,
                y + width / 2,
                3,
                new AnimatedSprite(12, Assets.tankBornSprites)
        );

        this.tankSprite = sprite;
        this.tankLevel = tankLevel;
        loadLevelValues();

        direction = UP;
        aMissile = false;
        prevMissileTime = System.currentTimeMillis();
        freeze = false;
        invincible = false;
        invincibleAnimation = new AnimatedSprite(20, Assets.invincibleSprites);
    }

    @Override
    public void update() {

        if (currState == tankStates.BORN && bornAnimation.isRemoved()) {
            currState = tankStates.ALIVE;
            if (this instanceof Player) makeInvincible();
            bornAnimation = null;
        }

        if (currState == tankStates.ALIVE) {

            if (!aMissile) {
                missile = null;
            }
            if (invincible) {
                animation.changePos((int) x, (int) y);
                invincibleStartTime = System.currentTimeMillis();
                if (invincibleStartTime >= invincibleEndTime) {
                    invincible = false;
                    animation.forceRemove();
                    animation = null;
                }
            }
            if (freeze) {
                if (System.currentTimeMillis() - freezeStartTime >= 10000) freeze = false;
            }

        }
    }

    protected boolean move(int direction) {
        if (currState == tankStates.ALIVE) {
            if (!freeze) {
                if (this.direction != direction) {
                    changeDirection(direction);
                }
                sprite = tankSprite[tankLevel].getSprite(this);
                double tempX = this.x;
                double tempY = this.y;
                switch (direction) {
                    default -> y -= speed;
                    case DOWN -> y += speed;
                    case RIGHT -> x += speed;
                    case LEFT -> x -= speed;
                }
                if (!moveAllowed()) {
                    x = tempX;
                    y = tempY;
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    private boolean moveAllowed() {
        if (currState == tankStates.ALIVE) {
            if (!Collision.outOfBounds((int) x, (int) y, width, height)) {
                for (B_Object obj : Gameplay.B_Object_List) {
                    if (obj != this && obj != missile && obj.isSolid()) {
                        if (Collision.areColliding(this, obj)) {
                            return false;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    protected void shootMissile() {
        if (currState == tankStates.ALIVE) {
            if (!freeze) {
                if (System.currentTimeMillis() - prevMissileTime >= 200 && !aMissile) {
                    prevMissileTime = System.currentTimeMillis();
                    aMissile = true;
                    double midX = x + width / 2;
                    double midY = y + height / 2;
                    missile = new Missile((int) midX, (int) midY, this);
                }
            }
        }
    }

    private void changeDirection(int direction) {
        if (currState == tankStates.ALIVE) {
            if ((this.direction == UP || this.direction == DOWN) && (direction == RIGHT || direction == LEFT)) {
                int rem = (int) (y % 8);
                if (rem != 0) {
                    if (rem <= 4) {
                        y -= rem;
                    } else {
                        y += 8 - rem;
                    }
                }
            } else if ((this.direction == RIGHT || this.direction == LEFT) && (direction == UP || direction == DOWN)) {
                int rem = (int) (x % 8);
                if (rem != 0) {
                    if (rem <= 4) {
                        x -= rem;
                    } else {
                        x += 8 - rem;
                    }
                }
            }
            this.direction = direction;
        }
    }

    @Override
    public void remove() {
        if (currState == tankStates.ALIVE) {
            if (!invincible) {
                super.remove();
                AI.currAI.tankDied(this);
                currState = tankStates.DEAD;
                new Animation((int) (x + width / 2), (int) (y + height / 2), 0, new AnimatedSprite(10, Assets.tankBlastSprites));
            }
        }
    }

    protected abstract void loadLevelValues();

    public void levelUp() {
        if (currState == tankStates.ALIVE) {
            if (tankLevel < 3) {
                tankLevel++;
            }
            loadLevelValues();
        }
    }


    public void slipRoad() {
        move(direction);
    }

    public int getDirection() {
        return direction;
    }

    public void setMissile(boolean missile) {
        this.aMissile = missile;
    }

    public int getDamage() {
        return damage;
    }

    public void freeze() {
        if (currState == tankStates.ALIVE) {
            freeze = true;
            freezeStartTime = System.currentTimeMillis();
        }
    }

    public int getLevel() {
        return tankLevel;
    }

    public void makeInvincible() {
        if (currState == tankStates.ALIVE) {
            invincibleStartTime = System.currentTimeMillis();
            invincibleEndTime = System.currentTimeMillis() + 12000;
            invincible = true;
            if (animation == null)
                animation = new Animation((int) (x + width / 2), (int) (y + height / 2), -1, invincibleAnimation);
        }
    }

    public void onHit(int damage) {
        if (currState == tankStates.ALIVE) {
            if (!invincible) {
                life -= damage;
                if (life <= 0) {
                    remove();
                } else {
                    if (life > 100 && life <= 200) {
                        tankLevel = 2;
                        loadLevelValues();
                    } else if (life > 200) {
                        tankLevel = 3;
                        loadLevelValues();
                    }

                }
            }
        }
    }

    @Override
    public void render(B_Render render) {
        if (currState == tankStates.ALIVE) super.render(render);
    }


}
