package com.BattleCity.core.gameStates.levelState;

import com.BattleCity.assests.AnimatedSprite;
import com.BattleCity.assests.Assets;
import com.BattleCity.core.B_Render;
import com.BattleCity.core.gameStates.GameState;
import com.BattleCity.level.Level;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;


public class Loading extends GameState {

    private LevelState levelState;

    // which stage to load
    private int stage;

    // difficulty level
    private int difficulty;

    // custom font
    private Font font;

    // font to render numbers
    private final Font numberFont;

    // arrows animation
    private final AnimatedSprite upAnim;
    private final AnimatedSprite downAnim;
    private final AnimatedSprite rightAnim;
    private final AnimatedSprite leftAnim;


    public Loading(LevelState levelState) {
        this.levelState = levelState;
        stage = 1;
        difficulty = 1;
        gsm = levelState.getGsm();

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
        switch (difficulty) {
            case 1 -> g.drawString(" Easy ", 180, 340);
            case 2 -> g.drawString("Normal", 180, 340);
            case 3 -> g.drawString(" Hard ", 180, 340);
        }


        // drawing arrows
        BufferedImage image;

        if(!LevelState.customStage){

            // positions are hard-coded
            // because the screen size is fixed

            g.drawString("< Select Level > ", 80, 122);
            g.setFont(numberFont);

            g.drawString(Integer.toString(stage), 380, 124);
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
            levelState.changeState(LevelState.levelStates.GAMEPLAY);
        }
        if (k == KeyEvent.VK_UP) {
            stage--;
            if (stage <= 0) {
                stage = 2;
            }
        }
        if (k == KeyEvent.VK_DOWN) {
            stage++;
            if (stage > 2) {
                stage = 1;
            }
        }
        if (k == KeyEvent.VK_RIGHT) {
            difficulty++;
            if (difficulty > 3) {
                difficulty = 1;
            }
        }
        if (k == KeyEvent.VK_LEFT) {
            difficulty--;
            if (difficulty <= 0) {
                difficulty = 3;
            }
        }

    }

    public int getStage() {
        return stage;
    }
    public int getDifficulty(){
        return difficulty;
    }

}
