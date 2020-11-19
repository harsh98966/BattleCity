package com.BattleCity.powerup.types;

import com.BattleCity.assests.Assets;
import com.BattleCity.powerup.PowerUps;
import com.BattleCity.tank.Tank;
import com.BattleCity.tank.player.Player;

public class Star extends PowerUps {
    public Star(int x, int y) {
        super(x, y, Assets.PowerUpsSprites.starSprite);
    }

    @Override
    public void setProperties(Tank tank) {
        if(tank instanceof Player){
            tank.levelUp();
        }
    }
}