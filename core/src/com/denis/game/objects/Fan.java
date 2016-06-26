package com.denis.game.objects;

import com.denis.game.GameObject;

/**
 * Created by loki on 24.06.16.
 */
public class Fan extends GameObject {
    public static final float FAN_WIDTH = 1.35f;
    public static final float FAN_HEIGHT = 1f;

    public Fan(float x, float y) {
        super(x, y, FAN_WIDTH, FAN_HEIGHT);
    }
}
