package com.denis.game.objects;

import com.denis.game.GameObject;

/**
 * Created by loki on 24.06.16.
 */
public class Washing extends GameObject {
    public static final float WASHING_WIDTH = 2.7f;
    public static final float WASHING_HEIGHT = 5f;

    public Washing(float x, float y) {
        super(x, y, WASHING_WIDTH, WASHING_HEIGHT);
    }
}
