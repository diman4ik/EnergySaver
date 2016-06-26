package com.denis.game.objects;

import com.denis.game.GameObject;

/**
 * Created by loki on 26.06.16.
 */
public class Air extends GameObject {
    public static final float AIR_WIDTH = 1f;
    public static final float AIR_HEIGHT = 2f;

    public Air(float x, float y) {
        super(x, y, AIR_WIDTH, AIR_HEIGHT);
    }
}
