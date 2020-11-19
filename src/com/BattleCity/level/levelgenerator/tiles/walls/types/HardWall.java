package com.BattleCity.level.levelgenerator.tiles.walls.types;

import com.BattleCity.assests.Assets;
import com.BattleCity.core.B_Object;

public class HardWall extends B_Object {
    public HardWall(int x, int y) {
        super(x, y, 8, 8, true, Assets.wall_HardSprite);
    }

    @Override
    public void update() {
    }
}
