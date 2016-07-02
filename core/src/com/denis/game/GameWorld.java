package com.denis.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.denis.game.objects.Air;
import com.denis.game.objects.Blender;
import com.denis.game.objects.Fan;
import com.denis.game.objects.Indicator;
import com.denis.game.objects.Iron;
import com.denis.game.objects.Kettler;
import com.denis.game.objects.Lamp;
import com.denis.game.objects.Microwave;
import com.denis.game.objects.Plate;
import com.denis.game.objects.R3Condition;
import com.denis.game.objects.R3Lamp;
import com.denis.game.objects.R3Mac;
import com.denis.game.objects.R3Outlet;
import com.denis.game.objects.R3Switch;
import com.denis.game.objects.R3TV;
import com.denis.game.objects.R3Telephone;
import com.denis.game.objects.R4Condition;
import com.denis.game.objects.R4Lamp;
import com.denis.game.objects.R4Switch;
import com.denis.game.objects.R4TV;
import com.denis.game.objects.Radio;
import com.denis.game.objects.Refridgerator;
import com.denis.game.objects.ScoreOut;
import com.denis.game.objects.SoundLeft;
import com.denis.game.objects.SoundRight;
import com.denis.game.objects.Switch;
import com.denis.game.objects.Toster;
import com.denis.game.objects.Washing;

import java.util.Random;

/**
 * Created by loki on 19.06.16.
 */
public class GameWorld {

    public Lightning lightning;
    public Boom boom;
    public Indicator indicator;
    public Array<GameObject> gameObjects = new Array<GameObject>();
    public Array<ScoreOut> flyingScores = new Array<ScoreOut>();
    int [] boomIndexes = new int [] { -1, -1, -1 };

    public final float lightningSpawnInterval = 4f;
    public final float lightningMaxInterval = 2f;
    public float curSpawnInterval = 1.0f;
    final Random random = new Random();

    float lightningTime = 0f;

    public int lifes = 3;

    public int level = 1;

    public int percent = 0;
    public int score = 100; // Общее количество очков
    public int curScore = 100;// Текущее количество очков

    boolean update = true;


    public GameWorld() {
        lightning = new Lightning(5, 5);
        lightning.setVisibility(false);
        boom = new Boom(3, 3);
        boom.setVisibility(false);
        indicator = new Indicator(1.5f, 14.5f);

        setLevel(1);

        /*for( int i = 0; i < gameObjects.size; i++ ) {
            addBrokenObject(i);
        }*/

        //addBrokenObject(6);

        //stop();
        //percent = 60;

        //ScoreOut sout = new ScoreOut( spawnCoords[0].x, spawnCoords[0].y, curScore);
        //flyingScores.add(sout);
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
            lightning.update(delta);

            for( ScoreOut scout : flyingScores) {
                scout.lift();
            }
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
            new Vector2(5f, 8.4f),
            new Vector2(7.1f, 9.25f),
            new Vector2(8.2f, 12),
            new Vector2(11.05f, 8.95f),
            new Vector2(12, 14)
    };

    // утюг
    // стиралка
    // фен
    // радио
    // выключатель
    private static final Vector2[] spawnCoordsLevel2 =
    {
            new Vector2(7.1f, 4.1f),
            new Vector2(1.6f, 4.25f),
            new Vector2(3.2f, 7.5f),
            new Vector2(15f, 10),
            new Vector2(4.5f, 10.5f)
    };

    // телек
    // кондиционер
    // выключатель
    // лампа
    // телефон
    // розетка
    // комп
    private static final Vector2[] spawnCoordsLevel3 =
    {
        new Vector2(4.6f, 7.2f),
        new Vector2(3.5f, 11f),
        new Vector2(6.5f, 11.5f),
        new Vector2(10f, 6.9f),
        new Vector2(12.0f, 2.8f),
        new Vector2(10.1f, 11),
        new Vector2(16.8f, 7.5f),
    };

    // лампа1
    // лампа2
    // кондиционер
    // левые колонки
    // телек
    // правые колонки
    // освежитель
    // выключатель
    private static final Vector2[] spawnCoordsLevel4 =
    {
            new Vector2(6.3f, 8.3f),
            new Vector2(2.7f, 5.4f),
            new Vector2(8, 13.6f),
            new Vector2(11.5f, 11f),
            new Vector2(13, 11f),
            new Vector2(15, 8.8f),
            new Vector2(16.3f, 6.3f),
            new Vector2(16.5f, 10.5f),
    };

    int prevIndex = -1;

    public void spawnLightning() {

        int index = random.nextInt(spawnCoords.length);

        if(index == prevIndex)
            index = random.nextInt(spawnCoords.length);

        while( index == boomIndexes[0] || index == boomIndexes[1] || index == boomIndexes[2])
            index = random.nextInt(spawnCoords.length);

        //int index = 0;

        Vector2 spawnPoint = spawnCoords[index];

        prevIndex = index;

        lightning.setPosition(spawnPoint.x, spawnPoint.y);
        lightning.setStop(false);
        lightning.time = 0;
        lightning.visible = true;

        Assets.lightningMusic.setVolume(0.5f);
        Assets.lightningMusic.play();
    }

    public void addBrokenObject(int index) {
        gameObjects.get(index).setBroken(true);
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
        Assets.boomMusic.setVolume(0.6f);
        Assets.boomMusic.play();
    }

    public void addflyingScore(Vector3 touch) {
        ScoreOut sout = new ScoreOut( touch.x, touch.y, curScore);
        curScore += 20;

        flyingScores.add(sout);
    }

    public void setLevel(int level) {
        this.level = level;

        if(level == 1) {
            spawnCoords = spawnCoordsLevel1;

            gameObjects.clear();

            gameObjects.add(new Kettler(spawnCoords[0].x, spawnCoords[0].y));
            gameObjects.add(new Microwave(spawnCoords[1].x, spawnCoords[1].y));
            gameObjects.add(new Refridgerator(spawnCoords[2].x, spawnCoords[2].y));
            gameObjects.add(new Toster(spawnCoords[3].x, spawnCoords[3].y));
            gameObjects.add(new Plate(spawnCoords[4].x, spawnCoords[4].y));
            gameObjects.add(new Blender(spawnCoords[5].x, spawnCoords[5].y));
            gameObjects.add(new Lamp(spawnCoords[6].x, spawnCoords[6].y));
            gameObjects.add(new Switch(spawnCoords[7].x, spawnCoords[7].y));
        }
        if(level == 2) {
            spawnCoords = spawnCoordsLevel2;

            gameObjects.clear();

            gameObjects.add(new Iron(spawnCoords[0].x, spawnCoords[0].y));
            gameObjects.add(new Washing(spawnCoords[1].x, spawnCoords[1].y));
            gameObjects.add(new Fan(spawnCoords[2].x, spawnCoords[2].y));
            gameObjects.add(new Radio(spawnCoords[3].x, spawnCoords[3].y));
            gameObjects.add(new Switch(spawnCoords[4].x, spawnCoords[4].y));
        }
        else if(level == 3) {
            spawnCoords = spawnCoordsLevel3;

            gameObjects.clear();

            gameObjects.add(new R3TV(spawnCoords[0].x, spawnCoords[0].y));
            gameObjects.add(new R3Condition(spawnCoords[1].x, spawnCoords[1].y));
            gameObjects.add(new R3Switch(spawnCoords[2].x, spawnCoords[2].y));
            gameObjects.add(new R3Lamp(spawnCoords[3].x, spawnCoords[3].y));
            gameObjects.add(new R3Telephone(spawnCoords[4].x, spawnCoords[4].y));
            gameObjects.add(new R3Outlet(spawnCoords[5].x, spawnCoords[5].y));
            gameObjects.add(new R3Mac(spawnCoords[6].x, spawnCoords[6].y));
        }
        else if(level == 4) {
            spawnCoords = spawnCoordsLevel4;

            gameObjects.clear();

            gameObjects.add(new R4Lamp(spawnCoords[0].x, spawnCoords[0].y));
            gameObjects.add(new R4Lamp(spawnCoords[1].x, spawnCoords[1].y));
            gameObjects.add(new R4Condition(spawnCoords[2].x, spawnCoords[2].y));
            gameObjects.add(new SoundLeft(spawnCoords[3].x, spawnCoords[3].y));
            gameObjects.add(new R4TV(spawnCoords[4].x, spawnCoords[4].y));
            gameObjects.add(new SoundRight(spawnCoords[5].x, spawnCoords[5].y));
            gameObjects.add(new Air(spawnCoords[6].x, spawnCoords[6].y));
            gameObjects.add(new R4Switch(spawnCoords[7].x, spawnCoords[7].y));
        }

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
        percent = 0;
        flyingScores.clear();
        lightning.setVisibility(false);
        lightning.setStop(false);
        boom.setVisibility(false);
    }
}
