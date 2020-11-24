package com.BattleCity.level.queen;

import com.BattleCity.level.levelgenerator.tiles.walls.HardWall;
import com.BattleCity.level.levelgenerator.tiles.walls.NormalWall;
import com.BattleCity.tank.Tank;
import com.BattleCity.tank.player.Player;

public class QueenBorder {
    public final class Type {
        public static final int Normal = 0;
        public static final int Hard = 1;
    }

    public static QueenBorder queenBorder;

    private int type;
    private HardWall[] hardWalls;
    private NormalWall[] normalWalls;
    private boolean powerUp;
    private long powerUpStartTime;

    public QueenBorder(int type) {
        this.type = type;
        hardWalls = new HardWall[8];
        normalWalls = new NormalWall[8];
        queenBorder = this;
        createBorder();
    }

    private void createBorder() {
        if (type == Type.Hard) {
            hardWalls[0] = new HardWall(7 * 16 - 8, 192);
            hardWalls[1] = new HardWall(7 * 16 - 8, 200);
            hardWalls[2] = new HardWall(7 * 16 - 8, 208);

            hardWalls[3] = new HardWall(7 * 16, 192);
            hardWalls[4] = new HardWall(7 * 16 + 8, 192);

            hardWalls[5] = new HardWall(8 * 16, 192);
            hardWalls[6] = new HardWall(8 * 16, 200);
            hardWalls[7] = new HardWall(8 * 16, 208);
        } else {
            normalWalls[0] = new NormalWall(7 * 16 - 8, 192);
            normalWalls[1] = new NormalWall(7 * 16 - 8, 200);
            normalWalls[2] = new NormalWall(7 * 16 - 8, 208);

            normalWalls[3] = new NormalWall(7 * 16, 192);
            normalWalls[4] = new NormalWall(7 * 16 + 8, 192);

            normalWalls[5] = new NormalWall(8 * 16, 192);
            normalWalls[6] = new NormalWall(8 * 16, 200);
            normalWalls[7] = new NormalWall(8 * 16, 208);
        }
    }

    public void update() {
        if (powerUp) {
            if (System.currentTimeMillis() - powerUpStartTime >= 15000) {
                removeBorder();
                type = Type.Normal;
                createBorder();
                powerUp = false;
            }
        }
    }

    private void removeBorder() {
        for (int i = 0; i < 8; i++) {
            if (normalWalls[i] != null) {
                normalWalls[i].remove();
                normalWalls[i] = null;
            }
            if (hardWalls[i] != null) {
                hardWalls[i].remove();
                hardWalls[i] = null;
            }
        }
    }


    public void axePowerUp(Tank tank) {
        removeBorder();
        if (tank instanceof Player) {
            powerUp = true;
            powerUpStartTime = System.currentTimeMillis();
            type = Type.Hard;
            createBorder();
        }
    }

}
