package com.denis.game.objects;

import com.denis.game.GameObject;

/**
 * Created by loki on 21.06.16.
 */
public class Kettler  extends GameObject {
    public static final float KETTLER_WIDTH = 0.8f;
    public static final float KETTLER_HEIGHT = 1.5f;

    public Kettler(float x, float y) {
        super(x, y, KETTLER_WIDTH, KETTLER_HEIGHT);
    }
}
