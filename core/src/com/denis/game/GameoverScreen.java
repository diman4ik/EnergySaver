package com.denis.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by loki on 6/21/16.
 */
public class GameoverScreen extends ScreenAdapter {

    DenisGame game;

    OrthographicCamera guiCam;
    Rectangle startBounds;

    Vector3 touchPoint = new Vector3();

    static final float FRUSTUM_WIDTH = 320;
    static final float FRUSTUM_HEIGHT = 480;


    public GameoverScreen (DenisGame game) {
        this.game = game;

        guiCam = new OrthographicCamera(320, 480);
        guiCam.position.set(320 / 2, 480 / 2, 0);
        startBounds = new Rectangle( 100, 90, 120, 70);
    }

    public void update (float delta) {
        if (Gdx.input.justTouched()) {
            guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (startBounds.contains(touchPoint.x, touchPoint.y)) {
                game.setScreen(new GameScreen(game));
                return;
            }
        }
    }

    @Override
    public void render (float delta) {
        update(delta);
        GL20 gl = Gdx.gl;
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gl.glClearColor(0, 1, 0, 1);

        guiCam.update();
        game.batcher.setProjectionMatrix(guiCam.combined);

        game.batcher.disableBlending();
        game.batcher.begin();
        game.batcher.draw( Assets.finishBackgroundRegion, 0, 0, FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
        game.batcher.end();

        game.batcher.enableBlending();
        game.batcher.begin();
        game.batcher.draw(Assets.restartButton, 100, 90, 120, 70);
        game.batcher.end();
    }
}
