package com.denis.game.objects;

import com.denis.game.GameObject;

/**
 * Created by loki on 26.06.16.
 */
public class Indicator extends GameObject {
    public static final float INDICATOR_WIDTH = 3.5f;
    public static final float INDICATOR_HEIGHT = 1.2f;

    public Indicator(float x, float y) {
        super(x, y, INDICATOR_WIDTH, INDICATOR_HEIGHT);
    }
}
