package com.BattleCity.assests;

import com.BattleCity.tank.Tank;

public class TankSprite {

    public static final TankSprite level1 = new TankSprite(1, SpriteSheet.tankSpriteSheet);

    private final AnimatedSprite[] animatedSprites;

    public TankSprite(int y, SpriteSheet sheet) {
        animatedSprites = new AnimatedSprite[4];

        Sprite[] up = new Sprite[2];
        Sprite[] down = new Sprite[2];
        Sprite[] left = new Sprite[2];
        Sprite[] right = new Sprite[2];

        for (int i = 0; i < 2; i++) {
            up[i] = new Sprite(i * 16, y * 16, 16, 16, true, sheet);
            left[i] = new Sprite((i + 2) * 16, y * 16, 16, 16, true, sheet);
            down[i] = new Sprite((i + 4) * 16, y * 16, 16, 16, true, sheet);
            right[i] = new Sprite((i + 6) * 16, y * 16, 16, 16, true, sheet);
        }

        animatedSprites[Tank.UP] = new AnimatedSprite(20, up);
        animatedSprites[Tank.RIGHT] = new AnimatedSprite(20, right);
        animatedSprites[Tank.DOWN] = new AnimatedSprite(20, down);
        animatedSprites[Tank.LEFT] = new AnimatedSprite(20, left);

    }

    public Sprite getSprite(Tank obj) {
        Sprite s;
        int direction = obj.getDirection();
        s = switch (direction) {
            default -> animatedSprites[Tank.UP].getSprite();
            case Tank.DOWN -> animatedSprites[Tank.DOWN].getSprite();
            case Tank.RIGHT -> animatedSprites[Tank.RIGHT].getSprite();
            case Tank.LEFT -> animatedSprites[Tank.LEFT].getSprite();
        };
        return s;
    }
}
