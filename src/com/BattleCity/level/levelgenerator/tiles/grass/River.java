package com.BattleCity.level.levelgenerator.tiles.grass;

import com.BattleCity.assests.Assets;
import com.BattleCity.core.B_Object;

public class River extends B_Object  implements Grass{
    public River(int x, int y) {
        super(x, y, 8, 8, true, Assets.riverAnimatedSprite);
    }

    @Override
    public void update() {
    }
}
