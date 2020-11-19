package com.BattleCity.level.levelgenerator.tiles.walls.types;

import com.BattleCity.assests.Assets;
import com.BattleCity.core.B_Object;
import com.BattleCity.tank.Tank;

public class NormalWall extends B_Object {

    public NormalWall(int x, int y) {
        super(x, y, 8, 8, true, Assets.wall_normalSprite);
    }

    @Override
    public void update() {
    }

    public void onHit(int direction) {
        if (spriteWidth <= 4 || spriteHeight <= 4 || spriteX != 0 || spriteY != 0) {
            remove();
        }
        switch (direction) {
            case Tank.DOWN -> spriteY += 4;
            case Tank.LEFT -> spriteWidth = 4;
            case Tank.RIGHT -> spriteX = 4;
            case Tank.UP -> spriteHeight = 4;
        }
    }


}
