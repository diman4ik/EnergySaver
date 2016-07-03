package com.denis.game.objects;

import com.denis.game.GameObject;

/**
 * Created by loki on 03.07.16.
 */
public class Curling extends GameObject {
    public static final float CURLING_WIDTH = 1.05f;
    public static final float CURLING_HEIGHT = 2.3f;

    public Curling(float x, float y) {
        super(x, y, CURLING_WIDTH, CURLING_WIDTH);
    }
}
