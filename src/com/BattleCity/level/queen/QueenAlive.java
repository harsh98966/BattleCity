package com.BattleCity.level.queen;

import com.BattleCity.assests.Assets;
import com.BattleCity.core.B_Object;

public class QueenAlive extends B_Object {

    public QueenAlive() {
        super(7 * 16, 200, 16, 16, true, Assets.queenAliveSprite);
    }

    @Override
    public void update() {
    }

    @Override
    public void remove() {
        super.remove();
    }
}
