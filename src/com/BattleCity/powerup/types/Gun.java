package com.BattleCity.powerup.types;

import com.BattleCity.assests.Assets;
import com.BattleCity.powerup.PowerUps;
import com.BattleCity.tank.Tank;
import com.BattleCity.tank.player.Player;

public class Gun extends PowerUps {
    public Gun(int x, int y) {
        super(x, y, Assets.PowerUpsSprites.gunSprite);
    }


    @Override
    public void setProperties(Tank tank) {
        if(tank instanceof Player){
            tank.levelUp();
            tank.levelUp();
            tank.levelUp();
        }
    }
}
