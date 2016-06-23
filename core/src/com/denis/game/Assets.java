package com.denis.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;

/**
 * Created by loki on 19.06.16.
 */
public class Assets {

    public static Texture startBackground;
    public static TextureRegion startBackgroundRegion;

    public static Texture startButton;
    public static TextureRegion startButtonRegion;

    public static Texture room1Background;
    public static TextureRegion room1BackgroundRegion;

    public static Texture room2Background;
    public static TextureRegion room2BackgroundRegion;

    public static Texture room3Background;
    public static TextureRegion room3BackgroundRegion;

    public static Texture room4Background;
    public static TextureRegion room4BackgroundRegion;

    public static Music roomMusic;
    public static Music lightningMusic;
    public static Music boomMusic;

    public static Animation lightningAnimation;
    public static TextureRegion [] lightningFrames = new TextureRegion[24];

    public static Animation boomAnimation;
    public static TextureRegion [] boomFrames = new TextureRegion[17];

    public static Texture life;
    public static TextureRegion lifeRegion;
    public static Texture lifeEmpty;
    public static TextureRegion lifeEmptyRegion;

    public static Texture finishBackground;
    public static TextureRegion finishBackgroundRegion;

    public static Texture restartButton;
    public static TextureRegion restartButtonRegion;

    public static Texture brokenBlender;
    public static TextureRegion brokenBlenderRegion;

    public static Texture brokenKettle;
    public static TextureRegion brokenKettleRegion;

    public static Texture brokenLamp;
    public static TextureRegion brokenLampRegion;

    public static Texture brokenMicrovawe;
    public static TextureRegion brokenMicrovaweRegion;

    public static Texture brokenPlate;
    public static TextureRegion brokenPlateRegion;

    public static Texture brokenRefrigerator;
    public static TextureRegion brokenRefrigeratorRegion;

    public static Texture brokenSwitch;
    public static TextureRegion brokenSwitchRegion;

    public static Texture brokenToster;
    public static TextureRegion brokenTosterRegion;

    public static Texture brokenWashing;
    public static TextureRegion brokenWashingRegion;

    public static Texture winScreen;
    public static TextureRegion winScreenRegion;

    public static void load () {
        startBackground = loadTexture("cover.png");
        startBackgroundRegion = new TextureRegion(startBackground, 0, 0, 1920, 1080);
        startButton = loadTexture("start_but.png");
        startButtonRegion = new TextureRegion(startBackground, 0, 0, 507, 134);

        room1Background = loadTexture("room1/room1_example.png");
        room1BackgroundRegion = new TextureRegion(room1Background, 0, 0, 1920, 1080);

        room2Background = loadTexture("room2/room2.png");
        room2BackgroundRegion = new TextureRegion(room2Background, 0, 0, 1920, 1080);

        room3Background = loadTexture("room3/room3.png");
        room3BackgroundRegion = new TextureRegion(room3Background, 0, 0, 1920, 1080);

        room4Background = loadTexture("room4/room4.png");
        room4BackgroundRegion = new TextureRegion(room4Background, 0, 0, 1920, 1080);

        finishBackground = loadTexture("gameover/gameover_cover.png");
        finishBackgroundRegion = new TextureRegion(finishBackground, 0, 0, 1920, 1080);
        restartButton = loadTexture("gameover/restart_button.png");
        restartButtonRegion = new TextureRegion(finishBackground, 0, 0, 808, 164);

        {
            Texture lightning1 = loadTexture("molniya/molniya0001.png");
            TextureRegion lightning1Region = new TextureRegion(lightning1, 0, 0, 147, 147);
            lightningFrames[0] = lightning1Region;
        }

        {
            Texture lightning2 = loadTexture("molniya/molniya0002.png");
            TextureRegion lightning2Region = new TextureRegion( lightning2, 0, 0, 147, 147);
            lightningFrames[1] = lightning2Region;
        }

        {
            Texture lightning3 = loadTexture("molniya/molniya0003.png");
            TextureRegion lightning3Region = new TextureRegion( lightning3, 0, 0, 147, 147);
            lightningFrames[2] = lightning3Region;
        }

        {
            Texture lightning4 = loadTexture("molniya/molniya0004.png");
            TextureRegion lightning4Region = new TextureRegion( lightning4, 0, 0, 147, 147);
            lightningFrames[3] = lightning4Region;
        }

        {
            Texture lightning5 = loadTexture("molniya/molniya0005.png");
            TextureRegion lightning5Region = new TextureRegion( lightning5, 0, 0, 147, 147);
            lightningFrames[4] = lightning5Region;
        }

        {
            Texture lightning6 = loadTexture("molniya/molniya0006.png");
            TextureRegion lightning6Region = new TextureRegion( lightning6, 0, 0, 147, 147);
            lightningFrames[5] = lightning6Region;
        }


        {
            Texture lightning7 = loadTexture("molniya/molniya0007.png");
            TextureRegion lightning7Region = new TextureRegion( lightning7, 0, 0, 147, 147);
            lightningFrames[6] = lightning7Region;
        }

        {
            Texture lightning8 = loadTexture("molniya/molniya0008.png");
            TextureRegion lightning8Region = new TextureRegion( lightning8, 0, 0, 147, 147);
            lightningFrames[7] = lightning8Region;
        }

        {
            Texture lightning9 = loadTexture("molniya/molniya0009.png");
            TextureRegion lightning9Region = new TextureRegion( lightning9, 0, 0, 147, 147);
            lightningFrames[8] = lightning9Region;
        }

        {
            Texture lightning10 = loadTexture("molniya/molniya0010.png");
            TextureRegion lightning10Region = new TextureRegion( lightning10, 0, 0, 147, 147);
            lightningFrames[9] = lightning10Region;
        }

        {
            Texture lightning11 = loadTexture("molniya/molniya0011.png");
            TextureRegion lightning11Region = new TextureRegion( lightning11, 0, 0, 147, 147);
            lightningFrames[10] = lightning11Region;
        }

        {
            Texture lightning12 = loadTexture("molniya/molniya0012.png");
            TextureRegion lightning12Region = new TextureRegion( lightning12, 0, 0, 147, 147);
            lightningFrames[11] = lightning12Region;
        }

        {
            Texture lightning13 = loadTexture("molniya/molniya0013.png");
            TextureRegion lightning13Region = new TextureRegion( lightning13, 0, 0, 147, 147);
            lightningFrames[12] = lightning13Region;
        }

        {
            Texture lightning14 = loadTexture("molniya/molniya0014.png");
            TextureRegion lightning14Region = new TextureRegion( lightning14, 0, 0, 147, 147);
            lightningFrames[13] = lightning14Region;
        }

        {
            Texture lightning15 = loadTexture("molniya/molniya0015.png");
            TextureRegion lightning15Region = new TextureRegion( lightning15, 0, 0, 147, 147);
            lightningFrames[14] = lightning15Region;
        }

        {
            Texture lightning16 = loadTexture("molniya/molniya0016.png");
            TextureRegion lightning16Region = new TextureRegion( lightning16, 0, 0, 147, 147);
            lightningFrames[15] = lightning16Region;
        }

        {
            Texture lightning17 = loadTexture("molniya/molniya0017.png");
            TextureRegion lightning17Region = new TextureRegion( lightning17, 0, 0, 147, 147);
            lightningFrames[16] = lightning17Region;
        }

        {
            Texture lightning18 = loadTexture("molniya/molniya0018.png");
            TextureRegion lightning18Region = new TextureRegion( lightning18, 0, 0, 147, 147);
            lightningFrames[17] = lightning18Region;
        }

        {
            Texture lightning19 = loadTexture("molniya/molniya0019.png");
            TextureRegion lightning19Region = new TextureRegion( lightning19, 0, 0, 147, 147);
            lightningFrames[18] = lightning19Region;
        }

        {
            Texture lightning20 = loadTexture("molniya/molniya0020.png");
            TextureRegion lightning20Region = new TextureRegion( lightning20, 0, 0, 147, 147);
            lightningFrames[19] = lightning20Region;
        }

        {
            Texture lightning21 = loadTexture("molniya/molniya0021.png");
            TextureRegion lightning21Region = new TextureRegion( lightning21, 0, 0, 147, 147);
            lightningFrames[20] = lightning21Region;
        }

        {
            Texture lightning22 = loadTexture("molniya/molniya0022.png");
            TextureRegion lightning22Region = new TextureRegion( lightning22, 0, 0, 147, 147);
            lightningFrames[21] = lightning22Region;
        }

        {
            Texture lightning23 = loadTexture("molniya/molniya0023.png");
            TextureRegion lightning23Region = new TextureRegion( lightning23, 0, 0, 147, 147);
            lightningFrames[22] = lightning23Region;
        }

        {
            Texture lightning24 = loadTexture("molniya/molniya0024.png");
            TextureRegion lightning24Region = new TextureRegion( lightning24, 0, 0, 147, 147);
            lightningFrames[23] = lightning24Region;
        }

        lightningAnimation = new Animation(0.2f, lightningFrames);

        {
            Texture boom1 = loadTexture("boom/boom0001.png");
            TextureRegion boom1Region = new TextureRegion( boom1, 0, 0, 420, 420);
            boomFrames[0] = boom1Region;
        }

        {
            Texture boom2 = loadTexture("boom/boom0002.png");
            TextureRegion boom2Region = new TextureRegion( boom2, 0, 0, 420, 420);
            boomFrames[1] = boom2Region;
        }

        {
            Texture boom3 = loadTexture("boom/boom0003.png");
            TextureRegion boom3Region = new TextureRegion( boom3, 0, 0, 420, 420);
            boomFrames[2] = boom3Region;
        }

        {
            Texture boom4 = loadTexture("boom/boom0004.png");
            TextureRegion boom4Region = new TextureRegion( boom4, 0, 0, 420, 420);
            boomFrames[3] = boom4Region;
        }

        {
            Texture boom5 = loadTexture("boom/boom0005.png");
            TextureRegion boom5Region = new TextureRegion( boom5, 0, 0, 420, 420);
            boomFrames[4] = boom5Region;
        }

        {
            Texture boom6 = loadTexture("boom/boom0006.png");
            TextureRegion boom6Region = new TextureRegion( boom6, 0, 0, 420, 420);
            boomFrames[5] = boom6Region;
        }

        {
            Texture boom7 = loadTexture("boom/boom0007.png");
            TextureRegion boom7Region = new TextureRegion( boom7, 0, 0, 420, 420);
            boomFrames[6] = boom7Region;
        }

        {
            Texture boom8 = loadTexture("boom/boom0008.png");
            TextureRegion boom8Region = new TextureRegion( boom8, 0, 0, 420, 420);
            boomFrames[7] = boom8Region;
        }

        {
            Texture boom9 = loadTexture("boom/boom0009.png");
            TextureRegion boom9Region = new TextureRegion( boom9, 0, 0, 420, 420);
            boomFrames[8] = boom9Region;
        }

        {
            Texture boom10 = loadTexture("boom/boom0010.png");
            TextureRegion boom10Region = new TextureRegion( boom10, 0, 0, 420, 420);
            boomFrames[9] = boom10Region;
        }

        {
            Texture boom11 = loadTexture("boom/boom0011.png");
            TextureRegion boom11Region = new TextureRegion( boom11, 0, 0, 420, 420);
            boomFrames[10] = boom11Region;
        }

        {
            Texture boom12 = loadTexture("boom/boom0012.png");
            TextureRegion boom12Region = new TextureRegion( boom12, 0, 0, 420, 420);
            boomFrames[11] = boom12Region;
        }

        {
            Texture boom13 = loadTexture("boom/boom0013.png");
            TextureRegion boom13Region = new TextureRegion( boom13, 0, 0, 420, 420);
            boomFrames[12] = boom13Region;
        }

        {
            Texture boom14 = loadTexture("boom/boom0014.png");
            TextureRegion boom14Region = new TextureRegion( boom14, 0, 0, 420, 420);
            boomFrames[13] = boom14Region;
        }

        {
            Texture boom15 = loadTexture("boom/boom0015.png");
            TextureRegion boom15Region = new TextureRegion( boom15, 0, 0, 420, 420);
            boomFrames[14] = boom15Region;
        }

        {
            Texture boom16 = loadTexture("boom/boom0016.png");
            TextureRegion boom16Region = new TextureRegion( boom16, 0, 0, 420, 420);
            boomFrames[15] = boom16Region;
        }

        {
            Texture boom17 = loadTexture("boom/boom0017.png");
            TextureRegion boom17Region = new TextureRegion( boom17, 0, 0, 420, 420);
            boomFrames[16] = boom17Region;
        }

        boomAnimation = new Animation(0.1f, boomFrames);

        roomMusic = Gdx.audio.newMusic(Gdx.files.internal("roomsound.mp3"));
        lightningMusic = Gdx.audio.newMusic(Gdx.files.internal("electric.mp3"));
        boomMusic = Gdx.audio.newMusic(Gdx.files.internal("boom.mp3"));

        life = loadTexture("heart_fill.png");
        lifeRegion = new TextureRegion(life, 0, 0, 83, 77);
        lifeEmpty = loadTexture("heart.png");
        lifeEmptyRegion = new TextureRegion(lifeEmpty, 0, 0, 83, 77);

        brokenBlender = loadTexture("room1/broken/blender.png");
        brokenBlenderRegion = new TextureRegion(brokenBlender, 0, 0, 76, 138);

        brokenKettle = loadTexture("room1/broken/kettle.png");
        brokenKettleRegion = new TextureRegion(brokenKettle, 0, 0, 72, 113);

        brokenLamp = loadTexture("room1/broken/lamp.png");
        brokenLampRegion = new TextureRegion(brokenLamp, 0, 0, 223, 680);

        brokenMicrovawe = loadTexture("room1/broken/microwave.png");
        brokenMicrovaweRegion = new TextureRegion(brokenMicrovawe, 0, 0, 190, 202);

        brokenPlate = loadTexture("room1/broken/plate.png");
        brokenPlateRegion = new TextureRegion(brokenPlate, 0, 0, 221, 330);

        brokenRefrigerator = loadTexture("room1/broken/refrigerator.png");
        brokenRefrigeratorRegion = new TextureRegion(brokenRefrigerator, 0, 0, 250, 624);

        brokenSwitch = loadTexture("room1/broken/switch.png");
        brokenSwitchRegion = new TextureRegion(brokenSwitch, 0, 0, 44, 66);

        brokenToster = loadTexture("room1/broken/toster.png");
        brokenTosterRegion = new TextureRegion(brokenToster, 0, 0, 101, 100);

        brokenWashing = loadTexture("room1/broken/washing.png");
        brokenWashingRegion = new TextureRegion(brokenWashing, 0, 0, 224, 325);

        winScreen = loadTexture("professor/professor_example.png");
        winScreenRegion = new TextureRegion(winScreen, 0, 0, 1920, 1080);
    }

    public static Texture loadTexture (String file) {
        return new Texture(Gdx.files.internal(file));
    }
}
