package com.BattleCity.core.gameStates;

import com.BattleCity.core.B_Object;
import com.BattleCity.core.B_Render;
import com.BattleCity.core.Collision;
import com.BattleCity.core.GameStateManager;
import com.BattleCity.core.gameStates.levelState.Gameplay;
import com.BattleCity.core.gameStates.levelState.LevelState;
import com.BattleCity.level.levelgenerator.tiles.grass.*;
import com.BattleCity.level.levelgenerator.tiles.walls.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ConstructLevel extends GameState {

    //
    ArrayList<B_Object> tiles = new ArrayList<B_Object>();

    // pointer
    private int cursorX;
    private int cursorY;

    // current tile
    private int currTile;

    // to generate file with custom values
    private int[][] tilesArr = new int[26][26];

    public ConstructLevel(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    private void init() {
        cursorX = 16;
        cursorY = 8;
        currTile = 1;
        drawTiles(0);

        // filling whole array with -1
        for (int[] ints : tilesArr) {
            Arrays.fill(ints, -1);
        }

        LevelState.customStage = true;
    }

    @Override
    public void update() {
    }

    @Override
    public void render(B_Render renderer) {

        for (int i = 0; i < Gameplay.B_Object_List.size(); i++) {
            B_Object o = Gameplay.B_Object_List.get(i);
            if (o.isRemoved()) {
                Gameplay.B_Object_List.remove(i);
                i--;
                continue;
            }
            o.render(renderer);
        }

        for (int i = 0; i < Gameplay.grasses.size(); i++) {
            B_Object g = Gameplay.grasses.get(i);
            if (g.isRemoved()) {
                Gameplay.grasses.remove(i);
                i--;
                continue;
            }
            g.render(renderer);
        }

    }

    @Override
    public void draw(Graphics2D g) {

        g.setColor(Color.WHITE);
        g.drawRect(cursorX * 2, cursorY * 2, 32, 32);

        // drawing help desk
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.setColor(Color.BLACK);

        g.drawString("Press Arrow Keys to", 460, 58);
        g.drawString("move cursor.", 460, 58 + 20);

        g.drawString("Press Shift to switch", 460, 120);
        g.drawString("tiles.", 460, 140);

        g.drawString("Press Space Key to", 460, 183);
        g.drawString("draw tiles on map.", 460, 183 + 20);

    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_UP) {
            moveCursor(cursorX, cursorY - 16);
        } else if (k == KeyEvent.VK_DOWN) {
            moveCursor(cursorX, cursorY + 16);
        } else if (k == KeyEvent.VK_RIGHT) {
            moveCursor(cursorX + 16, cursorY);
        } else if (k == KeyEvent.VK_LEFT) {
            moveCursor(cursorX - 16, cursorY);
        } else if (k == KeyEvent.VK_ENTER) {
            generateMapFile();
            gsm.changeState(GameStateManager.gameStates.LEVEL);
        } else if (k == KeyEvent.VK_SHIFT) {
            currTile++;
            if (currTile > 14) currTile = 1;
            updateTiles();
            drawTiles(0);
        } else if (k == KeyEvent.VK_SPACE) {
            drawTiles(1);
        }

    }

    private void moveCursor(int x, int y) {
        if (!Collision.outOfBounds(x, y, 16, 16)) {
            cursorX = x;
            cursorY = y;
            updateTiles();
            drawTiles(0);
        }
    }

    private void updateTiles() {
        for(int i = 0; i < tiles.size(); i++){
            tiles.get(i).remove();
        }
    }

    private void drawTiles(int type) {

        switch (currTile) {
            case 1 -> {
                tiles.add(new NormalWall(cursorX, cursorY));
                tiles.add(new NormalWall(cursorX + 8, cursorY));
                tiles.add(new NormalWall(cursorX, cursorY + 8));
                tiles.add(new NormalWall(cursorX + 8, cursorY + 8));
            }
            case 2 -> {
                tiles.add(new NormalWall(cursorX, cursorY));
                tiles.add(new NormalWall(cursorX, cursorY + 8));
            }

            case 3 -> {
                tiles.add(new NormalWall(cursorX, cursorY));
                tiles.add(new NormalWall(cursorX + 8, cursorY));
            }

            case 4 -> {
                tiles.add(new NormalWall(cursorX + 8, cursorY));
                tiles.add(new NormalWall(cursorX + 8, cursorY + 8));
            }

            case 5 -> {
                tiles.add(new NormalWall(cursorX, cursorY + 8));
                tiles.add(new NormalWall(cursorX + 8, cursorY + 8));
            }

            case 6 -> {
                tiles.add(new HardWall(cursorX, cursorY));
                tiles.add(new HardWall(cursorX + 8, cursorY));
                tiles.add(new HardWall(cursorX, cursorY + 8));
                tiles.add(new HardWall(cursorX + 8, cursorY + 8));
            }

            case 7 -> {
                tiles.add(new HardWall(cursorX, cursorY));
                tiles.add(new HardWall(cursorX, cursorY + 8));
            }

            case 8 -> {
                tiles.add(new HardWall(cursorX, cursorY));
                tiles.add(new HardWall(cursorX + 8, cursorY));
            }

            case 9 -> {
                tiles.add(new HardWall(cursorX + 8, cursorY));
                tiles.add(new HardWall(cursorX + 8, cursorY + 8));
            }

            case 10 -> {
                tiles.add(new HardWall(cursorX, cursorY + 8));
                tiles.add(new HardWall(cursorX + 8, cursorY + 8));
            }

            case 11 -> {
                tiles.add(new GreenGrass(cursorX, cursorY));
                tiles.add(new GreenGrass(cursorX + 8, cursorY));
                tiles.add(new GreenGrass(cursorX, cursorY + 8));
                tiles.add(new GreenGrass(cursorX + 8, cursorY + 8));
            }

            case 12 -> {
                tiles.add(new BlueGrass(cursorX, cursorY));
                tiles.add(new BlueGrass(cursorX + 8, cursorY));
                tiles.add(new BlueGrass(cursorX, cursorY + 8));
                tiles.add(new BlueGrass(cursorX + 8, cursorY + 8));
            }

            case 13 -> {
                tiles.add(new River(cursorX, cursorY));
                tiles.add(new River(cursorX + 8, cursorY));
                tiles.add(new River(cursorX, cursorY + 8));
                tiles.add(new River(cursorX + 8, cursorY + 8));
            }

            case 14 -> {
                tiles.add(new SlipRoad(cursorX, cursorY));
                tiles.add(new SlipRoad(cursorX + 8, cursorY));
                tiles.add(new SlipRoad(cursorX, cursorY + 8));
                tiles.add(new SlipRoad(cursorX + 8, cursorY + 8));
            }

        }

        if (type == 1) {
            switch (currTile) {
                case 1, 6, 11, 12, 13, 14 -> {
                    for (int i = 0; i < 4; i++) {
                        int x = (tiles.get(tiles.size() - 1).getX() - 16) / 8;
                        int y = (tiles.get(tiles.size() - 1).getY() - 8) / 8;

                        switch (currTile){
                            case 1 -> tilesArr[y][x] = 0;
                            case 6 -> tilesArr[y][x] = 1;
                            case 11 -> tilesArr[y][x] = 3;
                            case 12 -> tilesArr[y][x] = 2;
                            case 13 -> tilesArr[y][x] = 5;
                            case 14 -> tilesArr[y][x] = 4;
                        }

                        tiles.remove(tiles.size() - 1);
                    }
                }
                case 2, 3, 4, 5, 7, 8, 9, 10 -> {

                    for (int i = 0; i < 2; i++) {
                        int x = (tiles.get(tiles.size() - 1).getX() - 16) / 8;
                        int y = (tiles.get(tiles.size() - 1).getY() - 8) / 8;
                        switch (currTile){
                            case 2,3,4,5 -> tilesArr[y][x] = 0;
                            case 7,8,9,10 -> tilesArr[y][x] = 1;
                        }
                        tiles.remove(tiles.size() - 1);
                    }

                }



            }
        }


    }

    // creates 2-d array with custom level
    private void generateMapFile() {
        try {
            FileWriter fw = new FileWriter("CustomMap.csv", false);
            for (int x = 0; x < tilesArr.length; x++) {
                for (int y = 0; y < tilesArr[x].length; y++) {
                    String num = Integer.toString(tilesArr[x][y]);
                    fw.write(num);
                    if (!(x == tilesArr.length - 1 && y == tilesArr[x].length - 1)) {
                        fw.write(",");
                    }

                }
                fw.write("\n");
            }
            LevelState.customFile = new File("CustomMap.csv");
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
