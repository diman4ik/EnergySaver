package com.denis.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by loki on 19.06.16.
 */
public class GameObject {
    public final Vector2 position;
    public final Rectangle bounds;
    public final float width;
    public final float height;

    public boolean visible = true;

    public GameObject (float x, float y, float width, float height) {
        this.position = new Vector2(x, y);
        this.bounds = new Rectangle(x - width / 2 - 0.5f, y - height / 2 - 0.5f, width + 1f, height + 1f);
        this.width = width;
        this.height = height;
    }

    public void setPosition(float x, float y) {
        position.x = x;
        position.y = y;
        bounds.setPosition(x - 0.5f, y - 0.5f );
    }

    public void setVisibility(boolean visibility) {
        visible = visibility;
    }
}
