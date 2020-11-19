package com.BattleCity.level;

import com.BattleCity.core.BattleCity;
import com.BattleCity.level.levelgenerator.LevelGenerator;

public class Level {

    public static Level currLevel;
    private final int LevelWidthInTiles = 13, LevelHeightInTiles = 13;
    public Level() {
        currLevel = this;
        new LevelGenerator(1);
    }

    public int getWidthInTiles() {
        return LevelWidthInTiles;
    }

    public int getHeightInTiles() {
        return LevelHeightInTiles;
    }

    public int getWidth() {
        return LevelWidthInTiles * 16;
    }

    public int getHeight() {
        return LevelHeightInTiles * 16;
    }

}
