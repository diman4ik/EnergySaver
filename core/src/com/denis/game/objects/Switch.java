package com.denis.game.objects;

import com.denis.game.GameObject;

/**
 * Created by loki on 21.06.16.
 */
public class Switch extends GameObject {
    public static final float SWITCH_WIDTH = 0.55f;
    public static final float SWITCH_HEIGHT = 1.1f;

    public Switch(float x, float y) {
        super(x, y, SWITCH_WIDTH, SWITCH_HEIGHT);
    }
}
