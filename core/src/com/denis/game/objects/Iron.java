package com.denis.game.objects;

import com.denis.game.GameObject;

/**
 * Created by loki on 24.06.16.
 */
public class Iron extends GameObject {
    public static final float IRON_WIDTH = 1.1f;
    public static final float IRON_HEIGHT = 1.5f;

    public Iron(float x, float y) {
        super(x, y, IRON_WIDTH, IRON_HEIGHT);
    }
}
