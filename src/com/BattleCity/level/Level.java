package com.BattleCity.level;

import com.BattleCity.assests.Sprite;
import com.BattleCity.core.B_Render;
import com.BattleCity.core.B_Renderer;
import com.BattleCity.core.BattleCity;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Level extends B_Renderer {

    public static Level currLevel;

    public final Sprite[] levelSprites;
    private final int WIDTH_IN_TILES, HEIGHT_IN_TILES;

    public Level() {
        BattleCity.objectRender.add(this);
        WIDTH_IN_TILES = 25;
        HEIGHT_IN_TILES = 17;
        levelSprites = new Sprite[WIDTH_IN_TILES * HEIGHT_IN_TILES];
        load();
    }

    private void load() {
        try {
            InputStreamReader ir = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("Levels/level1/walls.csv"));
            BufferedReader br = new BufferedReader(ir);
            String line = br.readLine();
            int x ;
            int y = 0;
            while(line != null){
                x = 0;
                String[] values = line.split(",");
                Sprite currSprite = null;
                for(String value : values){
                    value = value.trim();
                    int intValue = Integer.parseInt(value);
                    if(intValue != -1){
                        currSprite = Sprite.levelSprites[intValue];
                    }
                    else{
                        currSprite = Sprite.voidSprite;
                    }
                    levelSprites[x + y * WIDTH_IN_TILES] = currSprite;
                    x++;
                }

                line = br.readLine();
                y++;
            }
            currLevel = this;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public void update() {
    }

    @Override
    public void render(B_Render render) {
        for(int y = 0; y < HEIGHT_IN_TILES; y++){
            for(int x = 0; x < WIDTH_IN_TILES; x++){
                render.render(x * 16, y * 16,levelSprites[x + y * WIDTH_IN_TILES]);
            }
        }
    }

    public int getWIDTH_IN_TILES() {
        return WIDTH_IN_TILES;
    }

    public int getHEIGHT_IN_TILES() {
        return HEIGHT_IN_TILES;
    }

}
