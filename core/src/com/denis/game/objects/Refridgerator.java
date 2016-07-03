package com.denis.game.objects;

import com.badlogic.gdx.math.Vector3;
import com.denis.game.GameObject;

/**
 * Created by loki on 21.06.16.
 */
public class Refridgerator extends GameObject {
    public static final float REFRIDGERATOR_WIDTH = 2.6f;
    public static final float REFRIDGERATOR_HEIGHT = 8.8f;

    public boolean bouncing = false;
    public int bounceState = 0;

    public Refridgerator(float x, float y) {
        super(x, y, REFRIDGERATOR_WIDTH, REFRIDGERATOR_HEIGHT);
    }

    public void startBouncing() {
        bouncing = true;
    }

    public int nextBounce() {
        bounceState += 1;
        return bounceState;
    }

    public float time = 0;
    public float touchInterval = 0;

    public void update(float deltaTime) {
        time += deltaTime;
        touchInterval += deltaTime;
    }

    public boolean touched(Vector3 touchPoint) {
        if(touchInterval > 0.8f) {
            touchInterval = 0;
            return bounds.contains(touchPoint.x, touchPoint.y);
        }

        return false;
    }
}
