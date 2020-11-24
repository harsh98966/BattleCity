package com.BattleCity.core;

import com.BattleCity.input.Keyboard;

import javax.swing.*;
import java.awt.*;



public class BattleCity implements Runnable {

    // screen size
    public static final int WIDTH = 344;
    public static final int HEIGHT = 224;
    public static final int SCALE = 2;

    private String _Title;
    private final Thread THREAD;
    private final JFrame FRAME;
    private final Canvas CANVAS;


    private final B_Render renderer;
    private final Keyboard keyboard;

    //GameStateManage
    public static GameStateManager gsm;


    // frame per second
    // updates per second
    private int fps, ups;

    private boolean running;

    public BattleCity() {
        _Title = "BattleCity, FPS: " + fps + ", UPS: " + ups;
        //canvas size
        THREAD = new Thread(this, "MainGame");
        CANVAS = new Canvas();
        CANVAS.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        FRAME = new JFrame(_Title);
        FRAME.add(CANVAS);
        FRAME.pack();
        FRAME.setLocationRelativeTo(null);
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FRAME.setResizable(false);
        FRAME.setVisible(true);

        renderer = new B_Render(WIDTH, HEIGHT);
        running = true;

        keyboard = new Keyboard();

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

        if (gsm != null)gsm.update();

    }

    private void render() {
        fps++;
        if (gsm != null) gsm.render(renderer, CANVAS);
    }

    public boolean isRunning() {
        return running;
    }


    //main function
    public static void main(String[] args) {
        BattleCity b = new BattleCity();
        gsm = new GameStateManager();
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
