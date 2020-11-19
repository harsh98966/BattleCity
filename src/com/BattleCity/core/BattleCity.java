package com.BattleCity.core;


import com.BattleCity.animation.Animation;
import com.BattleCity.assests.AnimatedSprite;
import com.BattleCity.assests.Assets;
import com.BattleCity.input.Keyboard;
import com.BattleCity.level.Level;
import com.BattleCity.level.levelgenerator.tiles.Grass;
import com.BattleCity.level.queen.QueenAlive;
import com.BattleCity.level.queen.QueenBorder;
import com.BattleCity.level.queen.QueenDied;
import com.BattleCity.powerup.types.*;
import com.BattleCity.tank.Tank;
import com.BattleCity.tank.enemy.Enemy;
import com.BattleCity.tank.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class BattleCity implements Runnable {

    public static final List<B_Object> B_Object_List = new ArrayList<>();
    private static List<B_Object> grasses = new ArrayList<>();
    public static List<B_Object> Tanks = new ArrayList<>();

    // Size of our screen
    public static final int WIDTH = 344;
    public static final int HEIGHT = 224;
    private final int SCALE = 2;

    private String _Title;
    private final Thread THREAD;
    private final JFrame FRAME;
    private final Canvas CANVAS;

    private final B_Render renderer;

    private Keyboard keyboard;

    //GameStateManage
    private GameStateManager gsm;


    // frame per second
    // updates per second
    private int fps, ups;

    private boolean running;

    public BattleCity() {
        _Title = "BattleCity, FPS: " + fps + ", UPS: " + ups;
        //canvas size
        Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
        THREAD = new Thread(this, "MainGame");
        CANVAS = new Canvas();
        CANVAS.setPreferredSize(size);
        FRAME = new JFrame(_Title);
        FRAME.add(CANVAS);
        FRAME.pack();
        FRAME.setLocationRelativeTo(null);
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FRAME.setResizable(false);
        FRAME.setVisible(true);

        renderer = new B_Render(WIDTH, HEIGHT);
        running = true;
        new Level();
        keyboard = new Keyboard();

        new Player();
        new Time(8 * 16, 5 * 16);
        new Enemy(16 + (5 * 16), 8, 3);
        new Enemy(16 + (6 * 16), 8, 3);
        new Enemy(16 + (7 * 16), 8, 3);
        new Enemy(16 + (8 * 16), 8, 3);
        new Enemy(16 + (9 * 16), 8, 3);

        new Enemy(16 + (5 * 16), 8 + (16 * 5), 3);
        new Enemy(16 + (6 * 16), 8 + (16 * 5), 3);
        new Enemy(16 + (7 * 16), 8 + (16 * 5), 3);
        new Enemy(16 + (8 * 16), 8 + (16 * 5), 3);
        new Enemy(16 + (9 * 16), 8 + (16 * 5), 3);


        new Star(16, 8);
        new Cap(16 + 16, 8);
//        new Axe(64, 8);

        gsm = new GameStateManager();

        FRAME.addKeyListener(keyboard);

        THREAD.start();
    }

    @Override
    public void run() {
        long prevTime = System.nanoTime(); // to cap Update_per_sec
        long prevSecTime = System.currentTimeMillis(); // To measure 1 second
        long reqFPS = 1000000000 / 60;
        long delta = 0;

        while (running) {
            long currTime = System.nanoTime();
            delta += (currTime - prevTime);
            prevTime = System.nanoTime();
            if (delta >= reqFPS) {
                delta = 0;
                update();
            }
            render();


            if (System.currentTimeMillis() - prevSecTime >= 1000) { // runs every second
                prevSecTime = System.currentTimeMillis();
                _Title = "BattleCity, FPS: " + fps + ", UPS: " + ups;
                FRAME.setTitle(_Title);
                ups = 0;
                fps = 0;
            }

        }
    }

    @SuppressWarnings("SuspiciousListRemoveInLoop")
    private void update() {
        ups++;
        grasses.clear();
        if (QueenBorder.queenBorder != null) {
            QueenBorder.queenBorder.update();
        }
        for (int i = 0; i < B_Object_List.size(); i++) {
            B_Object b = B_Object_List.get(i);
            if (b instanceof Grass) {
                grasses.add(b);
            }
            if (b.isRemoved()) {
                if (b.hasdyingAnimation) {
                    new Animation(b.getX() + b.getWidth() / 2, b.getY() + b.getHeight() / 2, new AnimatedSprite(10, b.dyingAnimationSprites));
                }
                if (b instanceof Tank) {
                    Tanks.remove(b);
                }
                if (b instanceof QueenAlive) {
                    new QueenDied();
                }
                B_Object_List.remove(i);
            } else {
                b.update();
            }
        }

        gsm.update();

    }

    private void render() {
        fps++;
        BufferStrategy bs = CANVAS.getBufferStrategy();
        if (bs == null) {
            CANVAS.createBufferStrategy(2);
            return;
        }


        renderer.clearBackground();
        for (B_Object r : B_Object_List) {
            r.render(renderer);
        }
        for (B_Object r : grasses) {
            r.render(renderer);
        }
        gsm.render(renderer);

        BufferedImage image = renderer.getImage();
        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
        g.setColor(Color.BLACK);
        g.setFont(new Font("arial", 1, 17));
        g.drawString("Created by: Harsh", 227 * SCALE, 180 * SCALE);
        g.drawString("Sprites Downloaded from:", 227 * SCALE, 200 * SCALE);
        g.drawString("www.spriters-resource.com", 227 * SCALE, 210 * SCALE);

        g.dispose();
        bs.show();

    }

    public boolean isRunning() {
        return running;
    }


    //main function
    public static void main(String[] args) {
        BattleCity b = new BattleCity();
        long time = System.currentTimeMillis();

        //runs gc every minute
        while (b.isRunning()) {
            if (System.currentTimeMillis() - time > 60000) {
                time = System.currentTimeMillis();
                System.gc();
            }
        }

    }


}
