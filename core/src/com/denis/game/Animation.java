package com.denis.game;

/**
 * Created by loki on 19.06.16.
 */
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by loki on 2/9/15.
 */
public class Animation {
    public static final int ANIMATION_LOOPING = 0;
    public static final int ANIMATION_NONLOOPING = 1;

    private int loopCount = 0;

    final TextureRegion[] keyFrames;
    final float frameDuration;

    public Animation (float frameDuration, TextureRegion... keyFrames) {
        this.frameDuration = frameDuration;
        this.keyFrames = keyFrames;
    }

    public TextureRegion getKeyFrame (float stateTime, int mode) {
        int frameNumber = getKeyFrameIndex(stateTime, mode);
        return keyFrames[frameNumber];
    }

    public int getKeyFrameIndex(float stateTime, int mode) {
        int frameNumber = (int)(stateTime / frameDuration);

        if (mode == ANIMATION_NONLOOPING) {
            frameNumber = Math.min(keyFrames.length - 1, frameNumber);
        } else {
            frameNumber = frameNumber % keyFrames.length;
        }

        return frameNumber;
    }

    public boolean isLastFrame(float stateTime, int mode) {
        return getKeyFrameIndex(stateTime, mode) == keyFrames.length - 1;
    }
}
