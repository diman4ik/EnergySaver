package com.denis.game;

import com.badlogic.gdx.math.Vector3;

/**
 * Created by loki on 19.06.16.
 */
public class Lightning extends GameObject {

    public static final float LIGHTNING_WIDTH = 2.0f;
    public static final float LIGHTNING_HEIGHT = 2.0f;

    public float time;
    public boolean hide = false;

    public Lightning(float x, float y) {
        super(x, y, LIGHTNING_WIDTH, LIGHTNING_HEIGHT);
    }

    public void update (float deltaTime) {
        time += deltaTime;
    }

    public boolean touched(Vector3 touchPoint) {
        return bounds.contains(touchPoint.x, touchPoint.y);
    }

    public void setStop(boolean hide) {
        this.hide = hide;
    }
}
