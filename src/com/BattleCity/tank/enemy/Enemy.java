package com.BattleCity.tank.enemy;

import com.BattleCity.assests.Assets;
import com.BattleCity.powerup.types.*;
import com.BattleCity.tank.Tank;

import java.util.Random;

public class Enemy extends Tank {
    private final static Random rand = new Random(System.nanoTime());
    private final Random random;
    private int moveDirection;
    private long time;
    private long lastDirectionChange;
    private long missileShootTime;

    private boolean hasPowerUp;

    public Enemy(int x, int y) {
        super(x, y, 0, Assets.enemy1TankSprites);
        random = new Random(rand.nextLong());
        tankLevel = random.nextInt(4);
        if(random.nextInt(15) % 7 == 0){
            hasPowerUp = true;
        }
        loadLevelValues();
        moveDirection = random.nextInt(4);
        time = System.currentTimeMillis();
        lastDirectionChange = System.currentTimeMillis();
    }

    @Override
    public void update() {
        super.update();
        missileShootTime++;
        if (missileShootTime >= 60) {
            missileShootTime = 0;
        }

        if (!move(moveDirection) || System.currentTimeMillis() - time >= 7000) {
            time = System.currentTimeMillis();
            if (System.currentTimeMillis() - lastDirectionChange >= 1500) {
                int d = random.nextInt(4);
                while (d == moveDirection) {
                    d = random.nextInt(4);
                }
                moveDirection = d;
                lastDirectionChange = System.currentTimeMillis();
            }
        }

        int rand = random.nextInt(120);
        if (missileShootTime == rand || missileShootTime * 2 == rand) {
            shootMissile();
        }

    }

    protected void loadLevelValues() {
        if (tankLevel > 3) {
            tankLevel = 3;
        }

        damage = 100;
        life = 100;
        switch (tankLevel) {
            case 0 -> speed = 0.45;
            case 1 -> speed = 1.0;
            case 2 -> {
                speed = 0.75;
                life = 200;
            }
            case 3 -> {
                speed = 0.55;
                life = 300;
            }
        }
        if(hasPowerUp){
            tankSprite = Assets.enemy3TankSprites;
        }

    }

    @Override
    public void remove() {
        super.remove();
        if(hasPowerUp){
            generatePowerUP();
        }
    }

    private void generatePowerUP() {
        int power = new Random(random.nextLong()).nextInt(7);
        int x = new Random(random.nextLong()).nextInt(13) + 1;
        int y = new Random(random.nextLong()).nextInt(10) + 1;
        switch (power) {
            case 0 -> new Axe(x * 16, y * 16);
            case 1 -> new Bomb(x * 16, y * 16);
            case 2 -> new Cap(x * 16, y * 16);
            case 3 -> new Gun(x * 16, y * 16);
            case 4 -> new OneUP(x * 16, y * 16);
            case 5 -> new Star(x * 16, y * 16);
            case 6 -> new Time(x * 16, y * 16);
        }
    }
}
