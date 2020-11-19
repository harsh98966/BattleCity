package com.BattleCity.level.levelgenerator;

import com.BattleCity.core.BattleCity;
import com.BattleCity.level.levelgenerator.tiles.*;
import com.BattleCity.level.levelgenerator.tiles.walls.types.HardWall;
import com.BattleCity.level.levelgenerator.tiles.walls.types.NormalWall;
import com.BattleCity.level.queen.QueenAlive;
import com.BattleCity.level.queen.QueenBorder;

import java.io.*;
import java.util.Objects;

public class LevelGenerator {
    private String path;

    public LevelGenerator(int Level) {
        String file = "/walls.csv";
        path = "Levels/" + Level + file;
        generateLevel();
    }

    private void generateLevel() {

        try {
            InputStreamReader ir = new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(path)));
            BufferedReader br = new BufferedReader(ir);
            String line = br.readLine();
            new QueenBorder(QueenBorder.Type.Normal);
            new QueenAlive();
            createVoidTiles();
            int x;
            int y = 8;
            while (line != null) {
                x = 16;
                String[] values = line.split(",");
                for (String value : values) {
                    value = value.trim();
                    int intValue = Integer.parseInt(value);
                    if (intValue != -1) {
                        switch(intValue){
                            case 0 -> new NormalWall(x, y);
                            case 1 -> new HardWall(x, y);
                            case 2 -> new BlueGrass(x, y);
                            case 3 -> new GreenGrass(x, y);
                            case 4 -> new SlipRoad(x, y);
                            case 5 -> new River(x, y);
                        }
                    }
                    x += 8;
                }
                y += 8;
                line = br.readLine();
            }
        } catch (Exception e) {
            System.out.println("LevelGenerator: " + e.getClass().getName());
        }
    }

    private void createVoidTiles(){
//        for(int x = 0; x < BattleCity.WIDTH; x += 8){
//            for(int y = 0; y < 1; y++){
//                new VoidTile(x, y);
//            }
//        }
//
//        for(int x = 0; x < BattleCity.WIDTH; x += 8){
//            for(int y = 216; y < 217; y++){
//                new VoidTile(x, y);
//            }
//        }
//
//        for(int x = 0; x < 16; x += 8){
//            for(int y = 8; y < BattleCity.HEIGHT - 8; y+=8){
//                new VoidTile(x, y);
//            }
//        }
//
//        for(int x = 224; x < BattleCity.WIDTH; x += 8){
//            for(int y = 8; y < BattleCity.HEIGHT - 8; y+=8){
//                new VoidTile(x, y);
//            }
//        }



    }



}
