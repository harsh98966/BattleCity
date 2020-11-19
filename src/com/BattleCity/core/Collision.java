package com.BattleCity.core;


import com.BattleCity.level.queen.QueenAlive;

public class Collision {
    public static boolean areColliding(B_Object obj1, B_Object obj2) {
        if (obj1 == null || obj2 == null ) return false;
        boolean colliding = false;
        B_Object t1;
        B_Object t2;
        int size1 = obj1.getWidth() * obj1.getHeight();
        int size2 = obj2.getWidth() * obj2.getHeight();

        if (size2 < size1) {
            t1 = obj2;
            t2 = obj1;
        } else {
            t1 = obj1;
            t2 = obj2;
        }


        if (checkCollision(t1.getX(), t1.getY(), t2)) colliding = true;
        if (checkCollision(t1.getX() + t1.getWidth(), t1.getY(), t2)) colliding = true;
        if (checkCollision(t1.getX(), t1.getY() + t1.getHeight(), t2)) colliding = true;
        if (checkCollision(t1.getX() + t1.getWidth(), t1.getY() + t1.getHeight(), t2)) colliding = true;

        if (checkCollision(t1.getX() + t1.getWidth() / 2, t1.getY(), t2)) colliding = true;
        if (checkCollision(t1.getX() + t1.getWidth() / 2, t1.getY() + t1.getHeight(), t2)) colliding = true;
        if (checkCollision(t1.getX(), t1.getY() + t1.getHeight() / 2, t2)) colliding = true;
        if (checkCollision(t1.getX() + t1.getWidth(), t1.getY() + t1.getHeight() / 2, t2)) colliding = true;



        return colliding;
    }

    private static boolean checkCollision(int x, int y, B_Object obj) {
        if (x > obj.getX() && x < obj.getX() + obj.getWidth()) {
            return y > obj.getY() && y < obj.getY() + obj.getHeight();
        }
        return false;
    }


    public static boolean outOfBounds(int x, int y, int w, int h) {
        return x < 16 || y < 8 || x + w > 224 || y + h > BattleCity.HEIGHT - 8;
    }


}
