package com.BattleCity.powerup.types;

import com.BattleCity.assests.Assets;
import com.BattleCity.powerup.PowerUps;
import com.BattleCity.tank.Tank;

public class Cap extends PowerUps {
    public Cap(int x, int y) {
        super(x, y, Assets.PowerUpsSprites.capSprite);
    }

    @Override
    public void setProperties(Tank tank) {
        tank.makeInvincible();
    }
}
