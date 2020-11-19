package com.BattleCity.level.queen;

import com.BattleCity.assests.Assets;
import com.BattleCity.core.B_Object;

public class QueenDied extends B_Object {
    public QueenDied() {
        super(7 * 16, 200, 16, 16, false, Assets.queenDeadSprite);
    }

    @Override
    public void update() {
    }

}
