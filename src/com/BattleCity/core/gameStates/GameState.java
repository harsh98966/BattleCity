package com.BattleCity.core.gameStates;

import com.BattleCity.core.B_Render;
import com.BattleCity.core.GameStateManager;

import java.awt.*;

public abstract class GameState {

    protected GameStateManager gsm;

    public abstract void update();
    public abstract void render(B_Render renderer);
    public abstract void draw(Graphics2D g);
    public abstract void keyPressed(int k);

    public GameStateManager getGsm(){
        return gsm;
    }
}
