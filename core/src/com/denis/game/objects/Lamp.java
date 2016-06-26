package com.denis.game.objects;

import com.denis.game.GameObject;

/**
 * Created by loki on 21.06.16.
 */
public class Lamp extends GameObject {
    public static final float LAMP_WIDTH = 2.4f;
    public static final float LAMP_HEIGHT = 9.0f;

    public Lamp(float x, float y) {
        super(x, y, LAMP_WIDTH, LAMP_HEIGHT);
    }
}
