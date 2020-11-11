package com.BattleCity.level;

import com.BattleCity.core.B_Render;
import com.BattleCity.core.B_Renderer;
import com.BattleCity.core.BattleCity;
import com.BattleCity.tank.Tank;

public class Collision {
    public static boolean collision(int x, int y) {
        int LevelWidth = Level.currLevel.getWIDTH_IN_TILES();
        int LevelHeight = Level.currLevel.getHEIGHT_IN_TILES();
        if (x < 0 || y < 0 || ((x + 15) / 16) >= LevelWidth || ((y + 15) / 16) >= LevelHeight) return true;

        // 4-points collision detection
        int width = 15;
        int height = 15;

        int px2 = x + width;
        int py2 = y;

        int px3 = x;
        int py3 = y + height;

        int px4 = x + width;
        int py4 = y + height;

        if (Level.currLevel.levelSprites[(x / 16) + (y / 16) * Level.currLevel.getWIDTH_IN_TILES()].isSOLID())
            return true;
        if (Level.currLevel.levelSprites[(px2 / 16) + (py2 / 16) * Level.currLevel.getWIDTH_IN_TILES()].isSOLID())
            return true;
        if (Level.currLevel.levelSprites[(px3 / 16) + (py3 / 16) * Level.currLevel.getWIDTH_IN_TILES()].isSOLID())
            return true;
        return Level.currLevel.levelSprites[(px4 / 16) + (py4 / 16) * Level.currLevel.getWIDTH_IN_TILES()].isSOLID();

    }

    public static boolean TankCollision(int x, int y, Tank tank) {
        int wx = x + 15;
        int wy = y + 15;

        B_Renderer objRenderer;
        for (int i = 0; i < BattleCity.objectRender.size(); i++) {
            objRenderer = BattleCity.objectRender.get(i);
            if (objRenderer instanceof Tank && objRenderer != tank) {
                int ex = BattleCity.objectRender.get(i).getX();
                int ey = BattleCity.objectRender.get(i).getY();
                int ewx = ex + 15;
                int ewy = ey + 15;

                if (((wy > ey && wy < ewy) || (y > ey && y < ewy)) && ((wx > ex && wx < ewx) || (x < ewx && x > ex))) {
                    return true;
                }

            }


        }

        return false;
    }
}
