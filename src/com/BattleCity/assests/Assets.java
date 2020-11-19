package com.BattleCity.assests;

public class Assets {

    //SpriteSheets
    public static final SpriteSheet playerTankSpriteSheet = new SpriteSheet("/spritesheets/Tanks/Player/player.png");
    public static final SpriteSheet enemyTankSpriteSheet = new SpriteSheet("/spritesheets/Tanks/Enemy/enemy.png");
    public static final SpriteSheet missileSpriteSheet = new SpriteSheet("/spritesheets/misc/missiles.png");
    public static final SpriteSheet mapSpriteSheet = new SpriteSheet("/spritesheets/map/map.png");
    public static final SpriteSheet powerUpsSpriteSheet = new SpriteSheet("/spritesheets/misc/powerUps.png");
    public static final SpriteSheet tankBlastSpriteSheet = new SpriteSheet("/spritesheets/misc/tankBlast.png");
    public static final SpriteSheet missileBlastSpriteSheet = new SpriteSheet("/spritesheets/misc/missileBlast.png");
    public static final SpriteSheet queenSpriteSheet = new SpriteSheet("/spritesheets/misc/Queen.png");
    public static final SpriteSheet invincibleSpriteSheet = new SpriteSheet("/spritesheets/misc/invincible.png");


    //Sprites
    public static final Sprite[] missileSprites = new Sprite[4];

    //Blast
    public static final Sprite[] missileBlastSprites = new Sprite[3];
    public static final Sprite[] tankBlastSprites = new Sprite[3];

    //powerups
    public static final class PowerUpsSprites {
        public static final Sprite capSprite = new Sprite(0, 0, 16, 16, powerUpsSpriteSheet);
        public static final Sprite timeSprite = new Sprite(16, 0, 16, 16, powerUpsSpriteSheet);
        public static final Sprite axeSprite = new Sprite(2 * 16, 0, 16, 16, powerUpsSpriteSheet);
        public static final Sprite starSprite = new Sprite(3 * 16, 0, 16, 16, powerUpsSpriteSheet);
        public static final Sprite bombSprite = new Sprite(4* 16, 0, 16, 16, powerUpsSpriteSheet);
        public static final Sprite oneUpSprite = new Sprite(5 * 16, 0, 16, 16, powerUpsSpriteSheet);
        public static final Sprite gunSprite = new Sprite(6 * 16, 0, 16, 16, powerUpsSpriteSheet);
    }

    //Tiles
    public static final Sprite voidTile = new Sprite(8, 8, 0xffb0b0b0);
    public static final Sprite wall_normalSprite = new Sprite(0, 0, 8, 8, mapSpriteSheet);
    public static final Sprite wall_HardSprite = new Sprite(8, 0, 8, 8, mapSpriteSheet);
    public static final Sprite blueGrassSprite = new Sprite(16, 0, 8, 8, mapSpriteSheet);
    public static final Sprite grassSprite = new Sprite(24, 0, 8, 8, mapSpriteSheet);
    public static final Sprite slipRoadSprite = new Sprite(32, 0, 8, 8, mapSpriteSheet);
    public static final Sprite[] riverSprites = new Sprite[2];
    public static final Sprite[] invincibleSprites = new Sprite[2];


    //queen
    public static final Sprite queenAliveSprite = new Sprite(0, 0, 16, 16, queenSpriteSheet);
    public static final Sprite queenDeadSprite = new Sprite(16, 0, 16, 16, queenSpriteSheet);



    //Animated Sprites
    public static final AnimatedSprite riverAnimatedSprite = new AnimatedSprite(2, riverSprites);

    //TankSprites
    public static final TankSprite[] playerTankSprites = new TankSprite[4];
    public static final TankSprite[] enemyTankSprites = new TankSprite[4];




    static {

        riverSprites[0] = new Sprite(40, 0, 8, 8, mapSpriteSheet);
        riverSprites[1] = new Sprite(48, 0, 8, 8, mapSpriteSheet);

        invincibleSprites[0] = new Sprite(0, 0, 16, 16, invincibleSpriteSheet);
        invincibleSprites[1] = new Sprite(16, 0, 16, 16, invincibleSpriteSheet);

        //missile
        for (int y = 0; y < missileSpriteSheet.getHeight() / 5; y++) {
            for (int x = 0; x < missileSpriteSheet.getWidth() / 5; x++) {
                missileSprites[x] = new Sprite(x * 5, y * 5, 5, 5, missileSpriteSheet);
            }
        }

        //Tanks
        for (int i = 0; i < playerTankSprites.length; i++) {
            playerTankSprites[i] = new TankSprite(i, playerTankSpriteSheet);
        }
        for (int i = 0; i < enemyTankSprites.length; i++) {
            enemyTankSprites[i] = new TankSprite(i, enemyTankSpriteSheet);
        }





        //Blast
        for (int i = 0; i < tankBlastSprites.length; i++) {
            tankBlastSprites[i] = new Sprite((i + 2) * 32, 0, 32, 32, tankBlastSpriteSheet);
        }
        for (int i = 0; i < missileBlastSprites.length; i++) {
            missileBlastSprites[i] = new Sprite(i * 16, 0, 16, 16, missileBlastSpriteSheet);
        }

    }

}
