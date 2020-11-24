package com.BattleCity.level.levelgenerator.tiles.grass;

import com.BattleCity.assests.Assets;
import com.BattleCity.core.B_Object;

public class GreenGrass extends B_Object implements Grass {
    public GreenGrass(int x, int y) {
        super(x, y, 8, 8, false, Assets.grassSprite);
    }

    @Override
    public void update() {
    }

}
