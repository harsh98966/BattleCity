package com.BattleCity.powerup.types;

import com.BattleCity.assests.Assets;
import com.BattleCity.core.B_Object;
import com.BattleCity.core.gameStates.levelState.Gameplay;
import com.BattleCity.powerup.PowerUps;
import com.BattleCity.tank.Tank;

public class Time extends PowerUps {
    public Time(int x, int y) {
        super(x, y, Assets.PowerUpsSprites.timeSprite);
    }

    @Override
    public void setProperties(Tank tank) {
        for (int i = 0; i < Gameplay.Tanks.size(); i++) {
            B_Object t = Gameplay.Tanks.get(i);
            if (t != tank) {
                ((Tank) t).freeze();
            }
        }
    }

}
