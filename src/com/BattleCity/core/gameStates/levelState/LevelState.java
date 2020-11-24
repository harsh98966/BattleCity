package com.BattleCity.core.gameStates.levelState;

import com.BattleCity.core.B_Render;
import com.BattleCity.core.BattleCity;
import com.BattleCity.core.GameStateManager;
import com.BattleCity.core.gameStates.GameState;



import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;


public class LevelState extends GameState {
    public enum levelStates {
        LOADING,
        GAMEPLAY,
        GAME_OVER,
        PLAYER_WIN,
        PAUSE
    }

    // flag to load custom stage
    public static boolean customStage;
    //file to load custom map;
    public static File customFile;


    private GameState currState;
    private levelStates currStateNormal;

    // which stage to load
    private int stage;

    // difficulty
    private int difficulty;

    private Font font;

    public LevelState(GameStateManager gsm) {
        this.gsm = gsm;
        stage = 1;
        difficulty = 1;
        changeState(levelStates.LOADING);
        font = new Font(gsm.getFontName(), Font.PLAIN, 50);
    }


    @Override
    public void update() {
        if (currStateNormal != levelStates.PAUSE) currState.update();
    }

    @Override
    public void render(B_Render renderer) {
        currState.render(renderer);
    }

    @Override
    public void draw(Graphics2D g) {
        currState.draw(g);
        g.setFont(font);
        g.setColor(Color.WHITE);
        if (currStateNormal == levelStates.PAUSE) {
            g.drawString("Pause", BattleCity.WIDTH - 150, BattleCity.HEIGHT);
        }
    }

    @Override
    public void keyPressed(int k) {
        if (currStateNormal != levelStates.PAUSE) currState.keyPressed(k);
        if (k == KeyEvent.VK_ESCAPE) {
            if (currStateNormal != levelStates.PAUSE) currStateNormal = levelStates.PAUSE;
            else currStateNormal = levelStates.GAMEPLAY;
        }
    }

    public void changeState(levelStates state) {
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        switch (state) {
            case LOADING -> {
                currState = new Loading(this);
                currStateNormal = levelStates.LOADING;
            }
            case GAMEPLAY -> {
                currState = new Gameplay(this, stage, difficulty);
                currStateNormal = levelStates.GAMEPLAY;

            }
            case GAME_OVER -> {
                currState = new GameOver(this);
                currStateNormal = levelStates.GAME_OVER;
            }
            case PLAYER_WIN -> {
                currState = new PlayerVictory(this);
                currStateNormal = levelStates.PLAYER_WIN;
            }
            case PAUSE -> currStateNormal = levelStates.PAUSE;
        }
    }

    public String getFontName() {
        return gsm.getFontName();
    }

    public String getDifficultyName() {
        String name = "";
        switch (difficulty) {
            case 1 -> name = "Easy";
            case 2 -> name = "Normal";
            case 3 -> name = "Hard";
        }
        return name;
    }

    public void setStage(int stage) {
        if (stage <= 0) this.stage = 2;
        else if (stage > 2) this.stage = 1;
        else this.stage = stage;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getStage() {
        return stage;
    }

    public int getDifficulty() {
        return difficulty;
    }
}
