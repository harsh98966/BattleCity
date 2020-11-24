package com.BattleCity.level.levelgenerator.tiles.walls;

import com.BattleCity.assests.Assets;
import com.BattleCity.core.B_Object;

public class HardWall extends B_Object {
    public HardWall(int x, int y) {
        super(x, y, 8, 8, true, Assets.wall_HardSprite);
    }

    public HardWall(int x, int y, int w, int h){
        super(x, y, 4, 4, true, Assets.wall_HardSprite);
        spriteWidth = spriteHeight = 4;
    }

    @Override
    public void update() {
    }
}
