package com.BattleCity.core;

public abstract class B_Renderer {

    protected int x, y;
    private boolean remove;

    protected abstract void update();

    protected abstract void render(B_Render render);

    boolean isRemoved(){
        return remove;
    }

    protected void remove(){
        remove = true;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
