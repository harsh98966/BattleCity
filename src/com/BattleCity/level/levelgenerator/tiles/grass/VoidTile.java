package com.BattleCity.level.levelgenerator.tiles.grass;

import com.BattleCity.assests.Assets;
import com.BattleCity.core.B_Object;

public class VoidTile extends B_Object {

    public VoidTile(int x, int y) {
        super(x, y, 8, 8,  true, Assets.voidTile);
    }

    @Override
    public void update() {
    }


}
