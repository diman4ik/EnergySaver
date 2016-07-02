package com.denis.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;


/**
 * Created by loki on 19.06.16.
 */
public class GameScreen  extends ScreenAdapter {

    DenisGame game;
    OrthographicCamera guiCam;
    Vector3 touchPoint;
    GameWorld world;
    WorldRenderer renderer;
    int succeded = 0;
    boolean gameover = false;
    boolean win = false;
    float endGameInterval = 0;
    float maxEndgameInterval = 3.0f;

    //public static final float WIDTH = 320;
    //public static final float HEIGHT = 480;

    public static final float WIDTH = 800;
    public static final float HEIGHT = 480;

    public GameScreen(DenisGame game) {
        this.game = game;

        guiCam = new OrthographicCamera(WIDTH, HEIGHT);
        guiCam.position.set(WIDTH / 2, HEIGHT / 2, 0);
        touchPoint = new Vector3();
        world = new GameWorld();
        renderer = new WorldRenderer(game.batcher, world);
    }

    @Override
    public void render (float delta) {
        update(delta);
        GL20 gl = Gdx.gl;
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gl.glClearColor(0, 1, 0, 1);

        renderer.render();

        guiCam.update();
        game.batcher.setProjectionMatrix(guiCam.combined);
        //game.batcher.disableBlending();
        game.batcher.enableBlending();
        game.batcher.begin();
        Assets.scoreFontBig.setColor(1, 1, 1, 1);
        Assets.scoreFontBig.draw(game.batcher, "score: " + world.score, 10, 440);
        renderer.renderFlyingScores();
        game.batcher.end();
    }

    public void update(float delta) {
        if(!gameover && !win) {
            if (Gdx.input.isTouched()) {
                touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                Vector3 touchPointTr = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                guiCam.unproject(touchPointTr);
                renderer.cam.unproject(touchPoint);
                if (world.lightning.touched(touchPoint)) {

                    if (world.lightning.visible && !world.lightning.hide) {
                        succeded += 1;

                        Assets.pointMusic.setVolume(0.3f);
                        Assets.pointMusic.play();

                        if(succeded <= 20) {
                            world.score += world.curScore;
                            world.percent += 5;
                        }

                        world.addflyingScore(touchPointTr);
                    }

                    world.lightning.setStop(true);
                    world.lightningTime = 0;
                    Assets.lightningMusic.stop();

                    if (succeded >= 20) {
                        world.stop();

                        if(world.level == 4) {
                            win = true;
                            gameover = true;
                        }
                        else {
                            win = true;
                            world.flyingScores.clear();
                        }

                        succeded = 0;
                    }

                    return;
                }
            }

            world.update(delta);

            if (world.lifes == 0) {
                gameover = true;
            }
        }
        else {
            endGameInterval += delta;

            world.boom.setVisibility(false);
            world.lightning.setVisibility(false);

            if(endGameInterval >= maxEndgameInterval) {
                endGameInterval = 0;

                if (gameover && !win) {
                    world.curScore = 100;
                    game.setScreen(new GameoverScreen(game));
                }
                else if(gameover && win){
                    game.setScreen(new WinScreen(game));
                }
                else {
                    world.setLevel(world.level + 1);
                    win = false;
                }
            }
        }
    }
}
