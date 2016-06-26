package com.denis.game.objects;

import com.denis.game.GameObject;

/**
 * Created by loki on 24.06.16.
 */
public class Vibrator extends GameObject {
    public static final float VIBRATOR_WIDTH = 0.9f;
    public static final float VIBRATOR_HEIGHT = 1.8f;

    public Vibrator(float x, float y) {
        super(x, y, VIBRATOR_WIDTH, VIBRATOR_HEIGHT);
    }
}
