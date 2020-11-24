package com.BattleCity.powerup.types;

import com.BattleCity.assests.Assets;
import com.BattleCity.core.B_Object;
import com.BattleCity.core.BattleCity;
import com.BattleCity.core.gameStates.levelState.Gameplay;
import com.BattleCity.powerup.PowerUps;
import com.BattleCity.tank.Tank;
import com.BattleCity.tank.enemy.Enemy;
import com.BattleCity.tank.player.Player;

public class Bomb extends PowerUps {
    public Bomb(int x, int y) {
        super(x, y, Assets.PowerUpsSprites.bombSprite);
    }

    @Override
    public void setProperties(Tank tank) {

        if(tank instanceof Player){
            for(B_Object t : Gameplay.Tanks){
                if(t != tank){
                    t.remove();
                }
            }
        }

        else if(tank instanceof Enemy){
            for(B_Object t : Gameplay.Tanks){
                if(t != tank){
                    t.remove();
                }
            }
        }

    }
}