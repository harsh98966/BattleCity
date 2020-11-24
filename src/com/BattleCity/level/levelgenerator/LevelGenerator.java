package com.BattleCity.level.levelgenerator;


import com.BattleCity.level.levelgenerator.tiles.walls.HardWall;
import com.BattleCity.level.levelgenerator.tiles.walls.NormalWall;
import com.BattleCity.level.levelgenerator.tiles.grass.BlueGrass;
import com.BattleCity.level.levelgenerator.tiles.grass.GreenGrass;
import com.BattleCity.level.levelgenerator.tiles.grass.River;
import com.BattleCity.level.levelgenerator.tiles.grass.SlipRoad;
import com.BattleCity.level.queen.QueenAlive;
import com.BattleCity.level.queen.QueenBorder;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class LevelGenerator {
    private String path;

    private int type;
    private File file;


    public LevelGenerator(int Level) {
        type = Level;
        if (Level == -1) {
            path = "Levels/menuMap.csv";
        } else {
            path = "Levels/" + Level + ".csv";
        }
        file = null;
        generateLevel();
    }

    public LevelGenerator(File file) {
        path = file.getPath();
        this.file = file;
        generateLevel();
    }


    private void generateLevel() {

        try {
            Scanner sc;
            if (file == null) {
                sc = new Scanner(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(path)));
            } else {
                sc = new Scanner(file);
            }

            String line;
            if (type != -1) {
                new QueenBorder(QueenBorder.Type.Normal);
                new QueenAlive();
            }
            int x;
            int y = 8;
            while (sc.hasNextLine()) {
                x = 16;
                line = sc.nextLine();
                String[] values = line.split(",");
                for (String value : values) {
                    value = value.trim();
                    int intValue = Integer.parseInt(value);
                    if (intValue != -1) {

                        if (y == 8 || y == 16) {

                            // enemy spawn locations;
                            if (x == 16 || x == 24 || x == 16 * 7 || x == 16 * 7 + 8 || x >= 16 * 13) {
                                x += 8;
                                continue;
                            }

                        }

                        if (y >= 192) {
                            if (x >= 5 * 16 && x <= 8 * 16) {
                                x += 8;
                                continue;
                            }
                        }


                        switch (intValue) {
                            case 0 -> {
                                if (type == -1) {
                                    new NormalWall(x, y, 4, 4);
                                } else {
                                    new NormalWall(x, y);
                                }
                            }
                            case 1 -> new HardWall(x, y);
                            case 2 -> new BlueGrass(x, y);
                            case 3 -> new GreenGrass(x, y);
                            case 4 -> new SlipRoad(x, y);
                            case 5 -> new River(x, y);
                        }
                    }
                    if (type == -1) x += 4;
                    else x += 8;
                }
                if (type == -1) y += 4;
                else y += 8;

            }
            sc.close();
        } catch (Exception e) {
            System.out.println("LevelGenerator: " + e.getClass().getName());
        }
    }


}
