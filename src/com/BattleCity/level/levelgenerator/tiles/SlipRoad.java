package com.BattleCity.level.levelgenerator.tiles;

import com.BattleCity.assests.Assets;
import com.BattleCity.core.B_Object;
import com.BattleCity.core.BattleCity;
import com.BattleCity.tank.Tank;

public class SlipRoad extends B_Object {
    private long prevTime;
    public SlipRoad(int x, int y) {
        super(x, y, 8, 8, false, Assets.slipRoadSprite);
        prevTime = System.currentTimeMillis();
    }

    @Override
    public void update() {
        for(B_Object b : BattleCity.Tanks){
            if(b.getX() + b.getWidth() / 2 >= x && b.getX() + b.getWidth() / 2 <= x + width){
                if(b.getY() + b.getHeight() / 2 >= y && b.getY() + b.getHeight() / 2 <= y + height){
                    if(System.currentTimeMillis() - prevTime >= 50) {
                        ((Tank)b).slipRoad();
                        prevTime = System.currentTimeMillis();
                    }
                }
            }
        }
    }
}
