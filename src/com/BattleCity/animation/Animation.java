package com.BattleCity.animation;

import com.BattleCity.assests.AnimatedSprite;
import com.BattleCity.core.B_Object;

public class Animation extends B_Object {

    private final int centerX, centerY;
    private final boolean removeAfterCycle;
    private boolean forceRemove;
    private int removeAfter;


    public Animation(int CenterX, int CenterY, int removeAfter, AnimatedSprite animatedSprite) {
        super(CenterX, CenterY, animatedSprite.getWidth(), animatedSprite.getHeight(), false, animatedSprite);
        this.centerX = CenterX;
        this.centerY = CenterY;
        centerPosition();
        this.removeAfterCycle = removeAfter >= 0;
        if(removeAfter != 0){
            this.removeAfter = removeAfter;
        }
    }


    private void centerPosition(){
        x = centerX - ((centerX + width / 2) - centerX);
        y = centerY - ((centerY + height / 2) - centerY);
    }

    @Override
    public void update() {
//        if(animatedSprite.isCycleComplete() && removeAfter == -1) remove();
        if(forceRemove) remove();
        if(removeAfterCycle){
            if (animatedSprite.isCycleComplete() && removeAfter == 0) remove();
            else{
                if(removeAfter > 0){
                    if(animatedSprite.isCycleComplete()){
                        animatedSprite.setframe(0);
                        removeAfter--;
                    }
                }
            }
        }
    }

    public void changePos(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void forceRemove(){
        forceRemove = true;
    }

    public boolean isCycleComplete(){
        return animatedSprite.isCycleComplete();
    }




}
