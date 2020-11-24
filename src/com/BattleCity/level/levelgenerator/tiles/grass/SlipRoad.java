package com.BattleCity.level.levelgenerator.tiles.grass;

import com.BattleCity.assests.Assets;
import com.BattleCity.core.B_Object;
import com.BattleCity.core.gameStates.levelState.Gameplay;
import com.BattleCity.tank.Tank;

public class SlipRoad extends B_Object implements Grass {
    private long prevTime;
    public SlipRoad(int x, int y) {
        super(x, y, 8, 8, false, Assets.slipRoadSprite);
        prevTime = System.currentTimeMillis();
    }

    @Override
    public void update() {
        for(int i = 0; i < Gameplay.Tanks.size(); i++){
            B_Object b = Gameplay.Tanks.get(i);
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
