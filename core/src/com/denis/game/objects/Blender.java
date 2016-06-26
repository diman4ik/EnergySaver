package com.denis.game.objects;

import com.denis.game.GameObject;

/**
 * Created by loki on 6/21/16.
 */
public class Blender  extends GameObject {
    public static final float BLENDER_WIDTH = 0.9f;
    public static final float BLENDER_HEIGHT = 1.8f;

    public Blender(float x, float y) {
        super(x, y, BLENDER_WIDTH, BLENDER_WIDTH);
    }
}
