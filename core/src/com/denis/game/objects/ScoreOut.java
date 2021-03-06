package com.denis.game.objects;

import com.denis.game.GameObject;

/**
 * Created by loki on 6/29/16.
 */
public class ScoreOut extends GameObject {
    public static final float SCOREOUT_WIDTH = 2f;
    public static final float SCOREOUT_HEIGHT = 2f;

    public static final float LIFT_VELOCITY = 1f;

    public int score = 0;
    private int opacity = 200;

    public ScoreOut(float x, float y, int score) {
        super(x, y, SCOREOUT_WIDTH, SCOREOUT_HEIGHT);
        this.score = score;
    }

    public void lift() {
        setPosition(position.x, position.y + LIFT_VELOCITY);

        if(opacity > 0) {
            opacity -= 1;
        }
        else
            opacity = 0;
    }

    public int getOpacity() {
        return opacity/2;
    }
}
