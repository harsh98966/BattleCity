package com.BattleCity.core;

import com.BattleCity.core.gameStates.LevelState;
import com.BattleCity.core.gameStates.MenuState;
import com.BattleCity.core.gameStates.State;

import java.util.ArrayList;
import java.util.List;

public class GameStateManager {
    //
    public enum gameStates{
        MENU,
        LEVEL
    }

    // different states
    private List<State> statesList = new ArrayList<>();

    private gameStates currState;

    public GameStateManager(){
        currState = gameStates.MENU;

        //adding all states to arraylist;
        statesList.add(new MenuState(this));
        statesList.add(new LevelState(this));
    }

    public void update(){
        statesList.get(currState.ordinal()).update();
    }

    public void render(B_Render render){
        statesList.get(currState.ordinal()).render(render);
    }

    // change current state
    public void changeState(gameStates state){
    }

    // getters
    public gameStates getCurrState(){
        return currState;
    }


}
