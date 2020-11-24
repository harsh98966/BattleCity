package com.BattleCity.core.gameStates.levelState;

import com.BattleCity.core.AI;
import com.BattleCity.core.B_Object;
import com.BattleCity.core.B_Render;
import com.BattleCity.core.BattleCity;
import com.BattleCity.core.gameStates.GameState;
import com.BattleCity.level.Level;
import com.BattleCity.level.queen.QueenAlive;
import com.BattleCity.level.queen.QueenBorder;
import com.BattleCity.level.queen.QueenDied;
import com.BattleCity.tank.Tank;
import com.BattleCity.tank.player.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Gameplay extends GameState {


    public static final List<B_Object> B_Object_List = new ArrayList<>();
    public static List<B_Object> grasses = new ArrayList<>();
    public static List<B_Object> Tanks = new ArrayList<>();
    public static List<B_Object> animations = new ArrayList<>();

    private final LevelState levelState;

    private AI ai;

    public Gameplay(LevelState levelState, int stage, int difficulty) {
        this.levelState = levelState;
        this.gsm = levelState.getGsm();


        B_Object_List.clear();
        grasses.clear();
        Tanks.clear();
        animations.clear();
        if(!LevelState.customStage) new Level(stage);
        else new Level(LevelState.customFile);
        new Player();
        ai = new AI(levelState, difficulty);

        //resetting custom stage flag
        LevelState.customStage = false;
        LevelState.customFile = null;
    }


    @Override
    public void update() {
        QueenBorder.queenBorder.update();
        for (int i = 0; i < B_Object_List.size(); i++) {
            B_Object b = B_Object_List.get(i);
            if (b.isRemoved()) {
                if (b instanceof Tank) {
                    Tanks.remove(b);
                }
                if (b instanceof QueenAlive) {
                    new QueenDied();
                    levelState.changeState(LevelState.levelStates.GAME_OVER);
                }
                B_Object_List.remove(b);
                i--;
            } else {
                if (b instanceof Tank) {
                    if (b.getY() < 24) {
                        int x = b.getX();

                        // left corner
                        if (x < 32) ai.setPosFree(0, false);
                        // mid
                        if (x > 16 * 6 && x < 16 * 9) ai.setPosFree(1, false);
                        // right corner
                        if (x > 16 * 12) ai.setPosFree(2, false);
                    }
                }
                b.update();
            }
        }

        for (int i = 0; i < grasses.size(); i++) {
            B_Object g = grasses.get(i);
            g.update();
        }

        for (int i = 0; i < animations.size(); i++) {
            B_Object anim = animations.get(i);
            if (anim.isRemoved()) {
                animations.remove(anim);
                i--;
            } else {
                anim.update();
            }
        }
        ai.update();

    }

    @Override
    public void render(B_Render renderer) {
        renderer.clearBackground();
        for (B_Object r : B_Object_List) {
            r.render(renderer);
        }
        for (B_Object anim : animations) {
            anim.render(renderer);
        }
        for (B_Object r : grasses) {
            r.render(renderer);
        }

    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        int SCALE = BattleCity.SCALE;
        g.setFont(new Font("arial", 1, 17));
        g.drawString("Created by: Harsh", 227 * SCALE, 215 * SCALE);
        ai.draw(g);
    }

    @Override
    public void keyPressed(int k) {
    }

}
