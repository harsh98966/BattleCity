package com.BattleCity.core;


import com.BattleCity.assests.TankSprite;
import com.BattleCity.input.Keyboard;
import com.BattleCity.level.Level;
import com.BattleCity.tank.Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class BattleCity implements Runnable {
    // Size of our screen
    private final int WIDTH = 400;
    private final int HEIGHT = 272;
    private final int SCALE = 2;

    public static final List<B_Renderer> objectRender = new ArrayList<>();

    private String _Title;
    private final Thread THREAD;
    private final JFrame FRAME;
    private final Canvas CANVAS;

    private final B_Render renderer;

    private Level level;
    private Keyboard keyboard;



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
        level = new Level();
        keyboard = new Keyboard();

//        Tank tank = new Tank(50, 90, 100, 100, 0, TankSprite.level1);
        Tank tank1 = new Tank(200, 100, 100, 100, 2, TankSprite.level1);


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

    private void update() {
        ups++;
        for (int i = 0; i < objectRender.size(); i++) {
            B_Renderer b = objectRender.get(i);
            if (b.isRemoved())
                objectRender.remove(i);
            else
                b.update();
        }

        System.out.println("BattleCity: "+ objectRender.size());


    }
    private void render() {
        fps++;
        BufferStrategy bs = CANVAS.getBufferStrategy();
        if (bs == null) {
            CANVAS.createBufferStrategy(2);
            return;
        }


        renderer.clearBackground();
        for (B_Renderer r : objectRender) {
            r.render(renderer);
        }

        BufferedImage image = renderer.getImage();
        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);

        g.dispose();
        bs.show();



    }


    //main function
    public static void main(String[] args) {
        BattleCity game = new BattleCity();
    }


}
