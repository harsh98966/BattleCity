package com.BattleCity.core.gameStates;

import com.BattleCity.core.B_Render;
import com.BattleCity.core.GameStateManager;
import com.BattleCity.input.Keyboard;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.URI;
import java.util.Scanner;

public class Information extends GameState {

    int xx, yy;
    private File infoFile;

    public Information(GameStateManager gsm) {
        this.gsm = gsm;
        loadFile();
    }

    @Override
    public void update() {
        if (Keyboard.keys[KeyEvent.VK_UP]) {
            yy--;
        } else if (Keyboard.keys[KeyEvent.VK_DOWN]) {
            yy++;
        } else if (Keyboard.keys[KeyEvent.VK_LEFT]) {
            xx--;
        } else if (Keyboard.keys[KeyEvent.VK_RIGHT]) {
            xx++;
        }
        if (Keyboard.keys[KeyEvent.VK_SHIFT]) {
            System.out.println("X: " + xx + " ,YY: " + yy);
        }
    }

    @Override
    public void render(B_Render renderer) {

    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.WHITE);
        try {
            g.setFont(new Font("New Times Roman", Font.PLAIN, 15));
            Scanner sc = new Scanner(infoFile);
            int y = 40;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                g.drawString(line, 44, y);
                y += 20;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //
        g.setFont(new Font(gsm.getFontName(), Font.PLAIN, 20));
        g.drawString("Press Enter To Continue . . .", 80, 420);

    }

    @Override
    public void keyPressed(int k) {
        if(k == KeyEvent.VK_ENTER){
            gsm.changeState(GameStateManager.gameStates.MENU);
        }
    }


    private void loadFile() {
        try {
            URI infoURI = new URI(getClass().getResource("/Information.txt").toString());
            infoFile = new File(infoURI);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
