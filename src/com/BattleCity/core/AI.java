package com.BattleCity.core;

import com.BattleCity.assests.Assets;
import com.BattleCity.core.gameStates.GameState;
import com.BattleCity.core.gameStates.levelState.LevelState;
import com.BattleCity.level.Level;
import com.BattleCity.powerup.types.*;
import com.BattleCity.tank.Tank;
import com.BattleCity.tank.enemy.Enemy;
import com.BattleCity.tank.player.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class AI extends GameState {
    public static AI currAI;

    private final LevelState levelState;


    // number of enemy tanks to generate
    private int enemyNum;

    // how many enemies are alive
    private int enemyAlive;

    // max number of enemies allowed to be alive
    private int maxEnemy;

    // position to spawn enemies
    private int x, x1, x2, x3;

    // if true then current position is not occupied
    private boolean[] posFree;


    private boolean playerAlive;
    private int score;
    private int maxChances;


    private final int difficulty;

    private long time;

    private Random random;

    public AI(LevelState levelState, int difficulty) {
        this.levelState = levelState;
        this.difficulty = difficulty;
        init();
        generateTanks();
        currAI = this;
    }

    private void init() {
        switch (difficulty) {
            case 1 -> {
                enemyNum = 20;
                maxEnemy = 5;
                maxChances = 3;
            }
            case 2 -> {
                enemyNum = 30;
                maxEnemy = 8;
                maxChances = 4;
            }
            case 3 -> {
                enemyNum = 40;
                maxEnemy = 10;
                maxChances = 5;
            }
        }

        enemyAlive = 0;
        playerAlive = true;

        posFree = new boolean[3];
        posFree[0] = true;
        posFree[1] = true;
        posFree[2] = true;

        x1 = 16;
        x2 = 16 * 7;
        x3 = 16 * 13;
        x = x1;

        score = 0;

        time = 0;
        new Bomb(16, 13 * 16);
        random = new Random();
    }

    private void generateTanks() {

        if (System.currentTimeMillis() - time > 3500) {

            int freeX = -1;
            if (posFree[0]) freeX = x1;
            else if (posFree[1]) freeX = x2;
            else if (posFree[2]) freeX = x3;

            // randomly selecting position to spawn enemy
            int index = random.nextInt(3);
            if (posFree[index]) {
                if (index == 0) x = x1;
                else if (index == 1) x = x2;
                else x = x3;
            } else x = freeX;

            if (enemyAlive < maxEnemy && enemyNum > 0 && x != -1) {
                new Enemy(x, 8);
                enemyAlive++;
                enemyNum--;
            }

            time = System.currentTimeMillis();
        }

        if (!playerAlive && maxChances > 0) {
            new Player();
            playerAlive = true;
        } else if (maxChances <= 0) {
            levelState.changeState(LevelState.levelStates.GAME_OVER);
        }


    }



    public void setPosFree(int index, boolean value) {
        posFree[index] = value;
    }

    public void tankDied(Tank tank) {
        if (tank instanceof Enemy) {
            enemyAlive--;
            score += 100;
        }
        if (tank instanceof Player) {
            playerAlive = false;
            maxChances--;
        }
    }


    @Override
    public void update() {
        if (enemyAlive == 0 && enemyNum == 0) {
            levelState.changeState(LevelState.levelStates.PLAYER_WIN);
        }
        generateTanks();
        posFree[0] = true;
        posFree[1] = true;
        posFree[2] = true;
    }

    @Override
    public void render(B_Render renderer) {

    }

    @Override
    public void draw(Graphics2D g) {
        BufferedImage image = Assets.menuTank;
        // drawing rectangle
        g.drawRect(232 * 2, 24, 166, 187);
        g.setColor(new Color(0x636363));
        g.fillRect(233 * 2, 25, 164, 186);

        int x = 232 * 2 + 10, y = 32, printed = 0;

        for (int i = 0; i < enemyNum; i++) {
            if (printed >= 6) {
                printed = 0;
                x = 232 * 2 + 10;
                y += 25;
            }
            g.drawImage(image, x, y, x + 20, y + 20, 0, 0, image.getWidth(), image.getHeight(), null);

            printed++;
            x += 25;
        }

        g.setFont(Font.getFont(levelState.getFontName()));
        g.setColor(Color.BLACK);

        String stage = "Stage: " + Level.currLevel.getStage();
        g.drawString(stage, 233 * 2, 230);

        String diff = "Difficulty: " + levelState.getDifficultyName();
        g.drawString(diff, 233 * 2, 250);

        String score = "Score: " + this.score;
        g.drawString(score, 233 * 2, 300);

        String chance = "Player: " + maxChances;
        g.drawString(chance, 233 * 2, 330);


    }

    @Override
    public void keyPressed(int k) {
    }


}
