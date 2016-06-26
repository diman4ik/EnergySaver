package com.denis.game.objects;

import com.denis.game.GameObject;

/**
 * Created by loki on 24.06.16.
 */
public class Radio extends GameObject {
    public static final float RADIO_WIDTH = 0.9f;
    public static final float RADIO_HEIGHT = 1.5f;

    public Radio(float x, float y) {
        super(x, y, RADIO_WIDTH, RADIO_HEIGHT);
    }
}
