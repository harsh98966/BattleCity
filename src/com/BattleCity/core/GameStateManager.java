package com.BattleCity.core;

import com.BattleCity.core.gameStates.ConstructLevel;
import com.BattleCity.core.gameStates.Information;
import com.BattleCity.core.gameStates.levelState.Gameplay;
import com.BattleCity.core.gameStates.levelState.LevelState;
import com.BattleCity.core.gameStates.Menu;
import com.BattleCity.core.gameStates.GameState;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;


public class GameStateManager {

    public enum gameStates {
        MENU,
        CONSTRUCT_LEVEL,
        INFORMATION,
        LEVEL
    }

    private GameState currState;

    // font name
    private String fontName;

    public GameStateManager() {
        loadFont();
        changeState(gameStates.MENU);
    }

    public void update() {
        currState.update();
    }

    public void render(B_Render render, Canvas canvas) {
        BufferStrategy bs = canvas.getBufferStrategy();
        if (bs == null) {
            canvas.createBufferStrategy(3);
            return;
        }
        render.clearBackground();

        currState.render(render);
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        BufferedImage image = render.getImage();
        g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
        currState.draw(g);
        g.dispose();
        bs.show();

    }

    // change current state
    public void changeState(gameStates state) {

        Gameplay.B_Object_List.clear();
        Gameplay.animations.clear();
        Gameplay.grasses.clear();
        Gameplay.Tanks.clear();

        // delay to avoid miss clicks
        try {
            Thread.sleep(100);
        } catch (Exception e){
            e.printStackTrace();
        }

        switch (state) {
            case MENU -> currState = new Menu(this);
            case LEVEL -> currState = new LevelState(this);
            case INFORMATION -> currState = new Information(this);
            case CONSTRUCT_LEVEL -> currState = new ConstructLevel(this);
        }

    }

    private void loadFont(){
        try{
            Font font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/Font/kirbyss.ttf")).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);

            // setting font name
            fontName = font.getFontName();

        } catch (Exception e){
            System.out.println("gameStateManager: ");
            e.printStackTrace();
        }
    }

    public void keyPressed(int k) {
        currState.keyPressed(k);
    }

    public String getFontName(){
        return fontName;
    }

}
