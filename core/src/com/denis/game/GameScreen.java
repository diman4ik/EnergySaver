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

    public GameScreen(DenisGame game) {
        this.game = game;

        guiCam = new OrthographicCamera(320, 480);
        guiCam.position.set(320 / 2, 480 / 2, 0);
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
        Assets.scoreFont.draw(game.batcher, "score: " + world.score, 15, 425);
        game.batcher.end();
    }

    public void update(float delta) {
        if(!gameover && !win) {
            if (Gdx.input.isTouched()) {
                touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                renderer.cam.unproject(touchPoint);
                if (world.lightning.touched(touchPoint)) {

                    if (world.lightning.visible) {
                        succeded += 1;
                        if(succeded <= 5) {
                            world.score += 20;
                            world.percent += 20;
                        }

                        if(world.percent == 100)
                            world.percent = 90;
                    }

                    world.lightning.setVisibility(false);
                    world.lightningTime = 0;
                    Assets.lightningMusic.stop();

                    if (succeded >= 5) {
                        world.stop();

                        if(world.level == 4) {
                            win = true;
                            gameover = true;
                        }
                        else {
                            win = true;
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

            if(endGameInterval >= maxEndgameInterval) {
                endGameInterval = 0;

                if (gameover && !win) {
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
