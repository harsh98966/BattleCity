package com.BattleCity.animation;

import com.BattleCity.assests.AnimatedSprite;
import com.BattleCity.core.B_Object;

public class Animation extends B_Object {

    private final int centerX, centerY;
    private final boolean removeAfterCycle;
    private boolean forceRemove;

    public Animation(int CenterX, int CenterY, AnimatedSprite animatedSprite) {
        super(CenterX, CenterY, animatedSprite.getWidth(), animatedSprite.getHeight(), false, animatedSprite);
        this.centerX = CenterX;
        this.centerY = CenterY;
        centerPosition();
        removeAfterCycle = true;
    }

    public Animation(int CenterX, int CenterY, boolean removeAfterCycle, AnimatedSprite animatedSprite) {
        super(CenterX, CenterY, animatedSprite.getWidth(), animatedSprite.getHeight(), false, animatedSprite);
        this.centerX = CenterX;
        this.centerY = CenterY;
        centerPosition();
        this.removeAfterCycle = removeAfterCycle;
    }


    private void centerPosition(){
        x = centerX - ((centerX + width / 2) - centerX);
        y = centerY - ((centerY + height / 2) - centerY);
    }

    @Override
    public void update() {
        if(animatedSprite.isCycleComplete() && removeAfterCycle) remove();
        if(forceRemove) remove();
    }

    public void changePos(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void forceRemove(){
        forceRemove = true;
    }

}
