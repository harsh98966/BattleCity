package com.BattleCity.core.gameStates;

import com.BattleCity.core.B_Render;
import com.BattleCity.core.GameStateManager;

public abstract class State{

    protected GameStateManager gsm;

    public abstract void update();
    public abstract void render(B_Render renderer);
}
