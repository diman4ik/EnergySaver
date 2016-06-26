package com.denis.game.objects;

import com.denis.game.GameObject;

/**
 * Created by loki on 21.06.16.
 */
public class Plate extends GameObject {
    public static final float PLATE_WIDTH = 2.5f;
    public static final float PLATE_HEIGHT = 4.65f;

    public Plate(float x, float y) {
        super(x, y, PLATE_WIDTH, PLATE_HEIGHT);
    }
}
