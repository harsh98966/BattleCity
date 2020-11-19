package com.BattleCity.level.levelgenerator.tiles.walls;

import com.BattleCity.level.levelgenerator.tiles.walls.types.HardWall;
import com.BattleCity.level.levelgenerator.tiles.walls.types.NormalWall;

public class Wall {
    private final int x, y, wallCode;
    public Wall(int x, int y, int wallCode) {
        this.x = x;
        this.y = y;
        this.wallCode = wallCode;
        switch (wallCode){
            case 0, 5 -> fullWall();
            case 1, 6 -> leftHalf();
            case 2, 7 -> downHalf();
            case 3, 8 -> rightHalf();
            case 4, 9 -> upHalf();
        }
    }

    private void fullWall(){
        if(wallCode == 0){
            new NormalWall(x, y);
            new NormalWall(x + 8, y);
            new NormalWall(x, y + 8);
            new NormalWall(x + 8, y + 8);
        }
        else{
            new HardWall(x, y);
            new HardWall(x + 8, y);
            new HardWall(x, y + 8);
            new HardWall(x + 8, y + 8);
        }
    }

    private void rightHalf(){
        if(wallCode == 3){
            new NormalWall(x, y);
            new NormalWall(x, y + 8);
        }
        else{
            new HardWall(x, y);
            new HardWall(x, y + 8);
        }
    }

    private void downHalf(){
        if(wallCode == 2){
            new NormalWall(x, y + 8);
            new NormalWall(x + 8, y + 8);
        }
        else{
            new HardWall(x, y + 8);
            new HardWall(x + 8, y + 8);
        }
    }

    private void leftHalf(){
        if(wallCode == 1){
            new NormalWall(x + 8, y);
            new NormalWall(x + 8, y + 8);
        }
        else{
            new HardWall(x + 8, y);
            new HardWall(x + 8, y + 8);
        }
    }

    private void upHalf(){
        if(wallCode == 4){
            new NormalWall(x, y);
            new NormalWall(x + 8, y);
        }
        else{
            new HardWall(x, y);
            new HardWall(x + 8, y);
        }
    }

}
