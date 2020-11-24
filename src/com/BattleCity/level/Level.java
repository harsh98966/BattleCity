package com.BattleCity.level;

import com.BattleCity.level.levelgenerator.LevelGenerator;

import java.io.File;

public class Level {

    private final int MAX_LEVELS  = 2;
    public static Level currLevel;
    private final int LevelWidthInTiles = 13, LevelHeightInTiles = 13;
    private int stage;
    public Level(int level) {
        currLevel = this;
        if(level > MAX_LEVELS) level = MAX_LEVELS;
        stage = level;
        new LevelGenerator(level);
    }

    public Level(File file){
        currLevel = this;
        new LevelGenerator(file);
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

    public int getStage() { return stage; }
}
