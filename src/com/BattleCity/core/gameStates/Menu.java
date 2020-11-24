package com.BattleCity.core.gameStates;


import com.BattleCity.core.B_Object;
import com.BattleCity.core.B_Render;
import com.BattleCity.core.BattleCity;
import com.BattleCity.core.GameStateManager;
import com.BattleCity.core.gameStates.levelState.Gameplay;
import com.BattleCity.level.Level;
import com.BattleCity.tank.Tank;
import com.BattleCity.tank.enemy.Enemy;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Menu extends GameState {
    private final String[] availableOptions = {"Start", "Construct Level", "Information", "Exit"};

    private int currOption;

    private final Font titleFont;
    private Font optionFont;
    private final Font creditFont;

    public Menu(GameStateManager gsm) {
        currOption = 0;
        this.gsm = gsm;
        new Level(-1);
        new Enemy(16, 8);
        new Enemy(16 * 13 / 2 + 8, 8);
        new Enemy(16 * 8, 8);
        new Enemy(16 * 13, 8);


        titleFont = new Font("Arial", Font.BOLD, 50);
        optionFont = new Font(gsm.getFontName(), Font.PLAIN, 22);
        creditFont = new Font("TimesRoman", Font.PLAIN, 14);

    }

    public void update() {

        for (int i = 0; i < Gameplay.B_Object_List.size(); i++) {
            B_Object b = Gameplay.B_Object_List.get(i);
            if (b.isRemoved()) {
                if (b instanceof Tank) {
                    Gameplay.Tanks.remove(b);
                }
                Gameplay.B_Object_List.remove(b);
                i--;
            } else {
                b.update();
            }
        }

        for (int i = 0; i < Gameplay.grasses.size(); i++) {
            B_Object g = Gameplay.grasses.get(i);
            g.update();
        }

        for (int i = 0; i < Gameplay.animations.size(); i++) {
            B_Object anim = Gameplay.animations.get(i);
            if (anim.isRemoved()) {
                Gameplay.animations.remove(anim);
                i--;
            } else {
                anim.update();
            }
        }

    }

    @Override
    public void render(B_Render renderer) {
        for (int i = 0; i < Gameplay.B_Object_List.size(); i++) {
            B_Object b = Gameplay.B_Object_List.get(i);
            b.render(renderer);
        }

        for (int i = 0; i < Gameplay.grasses.size(); i++) {
            B_Object g = Gameplay.grasses.get(i);
            g.render(renderer);
        }

        for (int i = 0; i < Gameplay.animations.size(); i++) {
            B_Object anim = Gameplay.animations.get(i);
            anim.render(renderer);
        }
    }

    @Override
    public void draw(Graphics2D g) {

        // drawing options
        g.setFont(optionFont);
        for (int i = 0; i < availableOptions.length; i++) {
            if (i == currOption) {
                g.setColor(Color.RED);
                g.drawString(">", 75 * BattleCity.SCALE, 140 * BattleCity.SCALE + (i * 30));
            } else {
                g.setColor(Color.WHITE);
            }
            g.drawString(availableOptions[i], 86 * BattleCity.SCALE, 140 * BattleCity.SCALE + (i * 30));
        }

        g.setColor(Color.WHITE);
        g.setFont(creditFont);
        g.drawString("Developer : Harsh", 20 * BattleCity.SCALE, 200 * BattleCity.SCALE);
        g.drawString("Sprites Downloaded from: www.spriters-resource.com", 20 * BattleCity.SCALE, 210 * BattleCity.SCALE);

    }

    private void changeOption(int k) {

        if (k == KeyEvent.VK_UP) {
            currOption--;
            if (currOption == -1) {
                currOption = availableOptions.length - 1;
            }
        }

        if (k == KeyEvent.VK_DOWN) {
            currOption++;
            if (currOption == availableOptions.length) {
                currOption = 0;
            }
        }

        if (k == KeyEvent.VK_ENTER) {

            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }

            switch (currOption) {
                case 0 -> gsm.changeState(GameStateManager.gameStates.LEVEL);
                case 1 -> gsm.changeState(GameStateManager.gameStates.CONSTRUCT_LEVEL);
                case 2 -> gsm.changeState(GameStateManager.gameStates.INFORMATION);
                case 3 -> System.exit(0);
            }

        }

    }

    @Override
    public void keyPressed(int k) {
        changeOption(k);
    }


}
