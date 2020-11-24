package com.BattleCity.powerup.types;

import com.BattleCity.assests.Assets;
import com.BattleCity.core.AI;
import com.BattleCity.powerup.PowerUps;
import com.BattleCity.tank.Tank;

public class OneUP extends PowerUps {
    public OneUP(int x, int y) {
        super(x, y, Assets.PowerUpsSprites.oneUpSprite);
    }


    @Override
    public void setProperties(Tank tank) {
        AI.currAI.OneUP(tank);
    }

}
