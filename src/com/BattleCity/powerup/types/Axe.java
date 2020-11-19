package com.BattleCity.powerup.types;

import com.BattleCity.assests.Assets;
import com.BattleCity.level.queen.QueenBorder;
import com.BattleCity.powerup.PowerUps;
import com.BattleCity.tank.Tank;

public class Axe extends PowerUps {
    public Axe(int x, int y) {
        super(x, y, Assets.PowerUpsSprites.axeSprite);
    }
    @Override
    public void setProperties(Tank tank) {
        QueenBorder.queenBorder.axePowerUp(tank);
    }

}
