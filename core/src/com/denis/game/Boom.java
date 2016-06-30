package com.denis.game;

/**
 * Created by loki on 6/21/16.
 */
public class Boom extends GameObject  {

    public static final float BOOM_WIDTH = 4.0f;
    public static final float BOOM_HEIGHT = 3.0f;

    public final float MAX_BOOM_TIME = 1.8f;

    public float time;

    public Boom(float x, float y) {
        super(x, y, BOOM_WIDTH, BOOM_HEIGHT);
    }

    public void update (float deltaTime) {
        time += deltaTime;

        if(time > MAX_BOOM_TIME) {
            setVisibility(false);
            time = 0;
        }
    }
}
