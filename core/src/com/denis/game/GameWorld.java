package com.denis.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

/**
 * Created by loki on 19.06.16.
 */
public class GameWorld {

    public Lightning lightning;
    public Boom boom;
    public Array<GameObject> brokenObjects = new Array<GameObject>();
    int [] boomIndexes = new int [] { -1, -1, -1 };

    public final float lightningSpawnInterval = 6f;
    public final float lightningMaxInterval = 3f;
    public float curSpawnInterval = 2.0f;
    final Random random = new Random();

    float lightningTime = 0f;

    public int lifes = 3;

    public int level = 1;

    boolean update = true;


    public GameWorld() {
        lightning = new Lightning(5, 5);
        lightning.setVisibility(false);
        boom = new Boom(3, 3);
        boom.setVisibility(false);

        /*for( int i = 0; i < 8; i++ ) {
            addBrokenObject(i);
        }*/
        setLevel(2);
    }

    public void update(float delta) {
        if(update) {
            curSpawnInterval += delta;

            if (lightning.visible) {
                lightningTime += delta;

                if (lightningTime > lightningMaxInterval) {
                    lightning.setVisibility(false);
                    Assets.lightningMusic.stop();
                    lightningTime = 0;
                    curSpawnInterval -= 2f;

                    addBoom(prevIndex);
                    addBrokenObject(prevIndex);

                    if (lifes > 0)
                        lifes -= 1;
                }
            }

            if (curSpawnInterval > lightningSpawnInterval) {
                if (!lightning.visible) {
                    lightningTime = 0;
                    spawnLightning();
                }

                curSpawnInterval = 0f;
            }

            boom.update(delta);
            lightning.update(delta);/**/
        }
    }

    private static Vector2 [] spawnCoords;

    // чайник
    // микроволновка
    // холодильник
    // тостер
    // духовка
    // миксер
    // лампа
    // выключатель
    private static final Vector2[] spawnCoordsLevel1 =
    {
            new Vector2(10, 5),
            new Vector2(15f, 9),
            new Vector2(17f, 7),
            new Vector2(5f, 8.5f),
            new Vector2(7f, 10f),
            new Vector2(8.2f, 12),
            new Vector2(10.8f, 8),
            new Vector2(12, 14)
    };

    private static final Vector2[] spawnCoordsLevel2 =
    {
            new Vector2(10, 5),
            new Vector2(15f, 9)
    };

    private static final Vector2[] spawnCoordsLevel3 =
    {
        new Vector2(10, 5),
        new Vector2(15f, 9)
    };

    private static final Vector2[] spawnCoordsLevel4 =
    {
        new Vector2(10, 5),
        new Vector2(15f, 9)
    };

    int prevIndex = -1;

    public void spawnLightning() {

        int index = random.nextInt(spawnCoords.length);

        if(index == prevIndex)
            index = random.nextInt(spawnCoords.length);

        while( index == boomIndexes[0] || index == boomIndexes[1] || index == boomIndexes[2])
            index = random.nextInt(spawnCoords.length);

        Vector2 spawnPoint = spawnCoords[index];

        prevIndex = index;

        lightning.setPosition(spawnPoint.x, spawnPoint.y);
        lightning.time = 0;
        lightning.visible = true;

        Assets.lightningMusic.setVolume(0.5f);
        Assets.lightningMusic.play();
    }

    public void addBrokenObject(int index) {
        if(level == 1) {
            if (index == 0) {
                brokenObjects.add(new Kettler(spawnCoords[0].x, spawnCoords[0].y));
            } else if (index == 1) {
                brokenObjects.add(new Microwave(spawnCoords[1].x, spawnCoords[1].y));
            } else if (index == 2) {
                brokenObjects.add(new Refridgerator(spawnCoords[2].x, spawnCoords[2].y));
            } else if (index == 3) {
                brokenObjects.add(new Toster(spawnCoords[3].x, spawnCoords[3].y));
            } else if (index == 4) {
                brokenObjects.add(new Plate(spawnCoords[4].x, spawnCoords[4].y));
            } else if (index == 5) {
                brokenObjects.add(new Blender(spawnCoords[5].x, spawnCoords[5].y));
            } else if (index == 6) {
                brokenObjects.add(new Lamp(spawnCoords[6].x, spawnCoords[6].y));
            } else if (index == 7) {
                brokenObjects.add(new Switch(spawnCoords[7].x, spawnCoords[7].y));
            }
        }
        else if(level == 2) {

        }
    }

    public void addBoom(int index) {
        for( int i = 0; i < 3; i++ ) {
            if( boomIndexes[i] == -1) {
                boomIndexes[i] = index;
                break;
            }
        }

        boom.setPosition( spawnCoords[index].x, spawnCoords[index].y);
        boom.time = 0;
        boom.setVisibility(true);
        Assets.lightningMusic.setVolume(0.6f);
        Assets.boomMusic.play();
    }

    public void setLevel(int level) {
        this.level = level;

        spawnCoords = spawnCoordsLevel1;

        if(level == 2) {
            spawnCoords = spawnCoordsLevel2;

        }
        else if(level == 3)
            spawnCoords = spawnCoordsLevel3;
        else if(level == 4)
            spawnCoords = spawnCoordsLevel4;

        start();
    }

    public void stop() {
        update = false;
    }

    public void start() {
        boomIndexes = new int [] { -1, -1, -1 };
        curSpawnInterval = 2.0f;
        lightningTime = 0f;
        update = true;
        lifes = 3;
    }
}
