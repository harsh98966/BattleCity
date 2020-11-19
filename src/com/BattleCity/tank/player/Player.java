package com.BattleCity.tank.player;

import com.BattleCity.assests.Assets;
import com.BattleCity.input.Keyboard;
import com.BattleCity.level.Level;
import com.BattleCity.tank.Tank;

import java.awt.event.KeyEvent;

public class Player extends Tank {

    public Player() {
        super(9 * 16, Level.currLevel.getHeight() - 32, 0, Assets.playerTankSprites);
    }

    @Override
    public void update() {
        super.update();
        if(!invincible) makeInvincible();
        if (Keyboard.keys[KeyEvent.VK_UP]) {
            move(UP);
        } else if (Keyboard.keys[KeyEvent.VK_DOWN]) {
            move(DOWN);
        } else if (Keyboard.keys[KeyEvent.VK_LEFT]) {
            move(LEFT);
        } else if (Keyboard.keys[KeyEvent.VK_RIGHT]) {
            move(RIGHT);
        }

        if (Keyboard.keys[KeyEvent.VK_SHIFT]) {
            shootMissile();
//            makeInvincible();
        }

    }

    protected void loadLevelValues() {
        if (tankLevel > 3) {
            tankLevel = 3;
        }

        damage = 100;
        life = 100;
        switch (tankLevel) {
            case 0 -> speed = 0.7;
            case 1 -> speed = 0.9;
            case 2 -> {
                speed = 1.0;
                life = 200;
                damage = 200;
            }
            case 3 -> {
                speed = 1.3;
                life = 300;
                damage = 200;
            }
        }

    }

}
