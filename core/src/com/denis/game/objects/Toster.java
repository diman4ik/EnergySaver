package com.denis.game.objects;

import com.denis.game.GameObject;

/**
 * Created by loki on 21.06.16.
 */
public class Toster extends GameObject {
    public static final float TOSTER_WIDTH = 1.1f;
    public static final float TOSTER_HEIGHT = 1.5f;

    public Toster(float x, float y) {
        super(x, y, TOSTER_WIDTH, TOSTER_HEIGHT);
    }
}
