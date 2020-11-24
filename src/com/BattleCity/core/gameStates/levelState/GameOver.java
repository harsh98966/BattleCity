package com.BattleCity.core.gameStates.levelState;

import com.BattleCity.core.B_Object;
import com.BattleCity.core.B_Render;
import com.BattleCity.core.GameStateManager;
import com.BattleCity.core.gameStates.GameState;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameOver extends GameState {

    private LevelState levelState;

    public GameOver(LevelState levelState) {
        this.levelState = levelState;
        gsm = levelState.getGsm();
    }


    @Override
    public void update() {

    }

    @Override
    public void render(B_Render renderer) {

        for(int i = 0; i < Gameplay.B_Object_List.size(); i++){
            B_Object b = Gameplay.B_Object_List.get(i);
            b.render(renderer);
        }

        for(int i = 0; i < Gameplay.grasses.size(); i++){
            B_Object b = Gameplay.grasses.get(i);
            b.render(renderer);
        }

    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.RED);
        g.setFont(new Font(gsm.getFontName(), Font.BOLD, 50));
        g.drawString("GameOver", 100, 100);
        g.setFont(new Font("Arial", Font.PLAIN, 25));
        g.drawString("Press Enter To Continue..", 100, 150);
    }

    @Override
    public void keyPressed(int k) {
        if(k == KeyEvent.VK_ENTER){
            levelState.changeState(LevelState.levelStates.LOADING);
            gsm.changeState(GameStateManager.gameStates.MENU);
        }
    }
}
