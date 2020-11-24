package com.BattleCity.level.levelgenerator.tiles.grass;

import com.BattleCity.assests.Assets;
import com.BattleCity.core.B_Object;

public class BlueGrass extends B_Object implements Grass {
    public BlueGrass(int x, int y) {
        super(x, y, 8, 8, true, Assets.blueGrassSprite);
    }

    @Override
    public void update() {
    }

}
