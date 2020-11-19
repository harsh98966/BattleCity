package com.BattleCity.tank.enemy;

import com.BattleCity.assests.Assets;
import com.BattleCity.tank.Tank;

import java.util.Random;

public class Enemy extends Tank {
    private final static Random rand = new Random(System.nanoTime());
    private final Random random;
    private int moveDirection;
    private long time;
    private long lastDirectionChange;
    private long missileShootTime;

    public Enemy(int x, int y, int level) {
        super(x, y, 0, Assets.enemyTankSprites);
        random = new Random(rand.nextLong());
        tankLevel = random.nextInt(4);
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

        if (!move(moveDirection) || System.currentTimeMillis() - time >= 3000) {
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

        if (missileShootTime == random.nextInt(60)) {
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

    }


}
