package com.BattleCity.core.gameStates.levelState;

import com.BattleCity.assests.AnimatedSprite;
import com.BattleCity.assests.Assets;
import com.BattleCity.core.B_Render;
import com.BattleCity.core.gameStates.GameState;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;


public class Loading extends GameState {

    private LevelState levelstate;

    // custom font
    private Font font;

    // font to render numbers
    private Font numberFont;

    // arrows animation
    private AnimatedSprite upAnim;
    private AnimatedSprite downAnim;
    private AnimatedSprite rightAnim;
    private AnimatedSprite leftAnim;


    public Loading(LevelState levelState) {
        this.levelstate = levelState;
        levelState.setDifficulty(1);
        init();
    }

    private void init() {
        gsm = levelstate.getGsm();

        font = new Font(gsm.getFontName(), Font.PLAIN, 30);
        numberFont = new Font("Times Roman", Font.BOLD, 30);

        upAnim = new AnimatedSprite(35, 21, 28, Assets.arrow_upImage);
        leftAnim = new AnimatedSprite(35, 28, 21, Assets.arrow_leftImage);
        rightAnim = new AnimatedSprite(35, 28, 21, Assets.arrow_rightImage);
        downAnim = new AnimatedSprite(35, 21, 28, Assets.arrow_downImage);

    }

    @Override
    public void update() {
    }

    @Override
    public void render(B_Render renderer) {
    }

    @Override
    public void draw(Graphics2D g) {

        g.setColor(Color.WHITE);


        g.setFont(font);
        g.drawString("< Select Difficulty > ", 70, 272);
        switch (levelstate.getDifficulty()) {
            case 1 -> g.drawString(" Easy ", 180, 340);
            case 2 -> g.drawString("Normal", 180, 340);
            case 3 -> g.drawString(" Hard ", 180, 340);
        }


        // drawing arrows
        BufferedImage image;

        if (!LevelState.customStage) {

            // positions are hard-coded
            // because the screen size is fixed

            g.drawString("< Select Level > ", 80, 122);
            g.setFont(numberFont);

            g.drawString(Integer.toString(levelstate.getStage()), 380, 124);
            // up arrow
            image = upAnim.getImage();
            g.drawImage(image, 375, 46, 375 + image.getWidth() + 10, 46 + image.getHeight() + 20, 0, 0, image.getWidth(), image.getHeight(), null);

            // down arrow
            image = downAnim.getImage();
            g.drawImage(image, 375, 133, 375 + image.getWidth() + 10, 133 + image.getHeight() + 20, 0, 0, image.getWidth(), image.getHeight(), null);
        } else {
            g.drawString("Custom Stage", 125, 133);
        }

        // right arrow
        image = rightAnim.getImage();
        g.drawImage(image, 300, 315, 300 + image.getWidth() + 25, 315 + image.getHeight() + 10, 0, 0, image.getWidth(), image.getHeight(), null);

        // left arrow
        image = leftAnim.getImage();
        g.drawImage(image, 115, 315, 115 + image.getWidth() + 25, 315 + image.getHeight() + 10, 0, 0, image.getWidth(), image.getHeight(), null);

    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER) {
            levelstate.changeState(LevelState.levelStates.GAMEPLAY);
        }
        if (k == KeyEvent.VK_UP) {
            levelstate.setStage(levelstate.getStage() - 1);
            if (levelstate.getStage() <= 0) {
                levelstate.setStage(2);
            }
        }
        if (k == KeyEvent.VK_DOWN) {
            levelstate.setStage(levelstate.getStage() + 1);
            if (levelstate.getStage() > 2) {
                levelstate.setStage(1);
            }
        }
        if (k == KeyEvent.VK_RIGHT) {
            int d = levelstate.getDifficulty();
            levelstate.setDifficulty(d + 1);
            if (levelstate.getDifficulty() > 3) {
                levelstate.setDifficulty(1);
            }
        }
        if (k == KeyEvent.VK_LEFT) {
            int d = levelstate.getDifficulty();
            levelstate.setDifficulty(d - 1);
            if (levelstate.getDifficulty() <= 0) {
                levelstate.setDifficulty(3);
            }
        }

    }


}
