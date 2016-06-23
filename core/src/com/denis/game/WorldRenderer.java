package com.denis.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by loki on 19.06.16.
 */
public class WorldRenderer {

    private GameWorld world;
    OrthographicCamera cam;
    SpriteBatch batch;

    static final float FRUSTUM_WIDTH = 20;
    static final float FRUSTUM_HEIGHT = 15;


    public WorldRenderer (SpriteBatch batch, GameWorld world) {
        this.world = world;
        this.cam = new OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
        this.cam.position.set(FRUSTUM_WIDTH / 2, FRUSTUM_HEIGHT / 2, 0);
        this.batch = batch;
    }

    public void render () {
        cam.update();
        batch.setProjectionMatrix(cam.combined);
        renderBackground();
        renderObjects();
    }

    public void renderBackground () {
        batch.disableBlending();
        batch.begin();
        if(world.level == 1) {
            batch.draw(Assets.room1BackgroundRegion, cam.position.x - FRUSTUM_WIDTH / 2, cam.position.y - FRUSTUM_HEIGHT / 2,
                    FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
        }
        else if(world.level == 2) {
            batch.draw(Assets.room2BackgroundRegion, cam.position.x - FRUSTUM_WIDTH / 2, cam.position.y - FRUSTUM_HEIGHT / 2,
                    FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
        }
        else if(world.level == 3) {
            batch.draw(Assets.room3BackgroundRegion, cam.position.x - FRUSTUM_WIDTH / 2, cam.position.y - FRUSTUM_HEIGHT / 2,
                    FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
        }
        else if(world.level == 4) {
            batch.draw(Assets.room4BackgroundRegion, cam.position.x - FRUSTUM_WIDTH / 2, cam.position.y - FRUSTUM_HEIGHT / 2,
                    FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
        }

        batch.end();
    }

    public void renderObjects() {
        batch.enableBlending();
        batch.begin();
        renderLifes(world.lifes);
        renderBrokenObjects();
        renderBoom();
        renderLightning();
        batch.end();
    }

    private void renderLightning() {
        Lightning lightning = world.lightning;

        if(lightning.visible) {
            TextureRegion keyFrame = Assets.lightningAnimation.getKeyFrame(lightning.time, Animation.ANIMATION_LOOPING);
            batch.draw(keyFrame, lightning.position.x - Lightning.LIGHTNING_WIDTH/2, lightning.position.y - Lightning.LIGHTNING_HEIGHT/2,
                    Lightning.LIGHTNING_WIDTH, Lightning.LIGHTNING_HEIGHT);
        }
    }

    private void renderLifes(int lifes) {

        for( int i = 0; i < lifes; i++ ) {
            batch.draw(Assets.lifeRegion, 16f + i, 13.5f, 1f, 1f);
        }

        for( int i = lifes; i < 3; i++ ) {
            batch.draw(Assets.lifeEmptyRegion, 16f + lifes + (i - lifes), 13.5f, 1f, 1f);
        }
    }

    private void renderBoom() {
        Boom boom = world.boom;

        if(boom.visible) {
            TextureRegion keyFrame = Assets.boomAnimation.getKeyFrame(boom.time, Animation.ANIMATION_LOOPING);
            batch.draw(keyFrame, boom.position.x - Boom.BOOM_WIDTH/2, boom.position.y - Boom.BOOM_HEIGHT/2,
                    Boom.BOOM_WIDTH, Boom.BOOM_HEIGHT);
        }
    }

    private void renderBrokenObjects() {
        for(GameObject obj : world.brokenObjects) {
            if(obj instanceof Kettler) {
                batch.draw(Assets.brokenKettle, obj.position.x - Kettler.KETTLER_WIDTH/2, obj.position.y - 0.7f*Kettler.KETTLER_HEIGHT,
                        Kettler.KETTLER_WIDTH, Kettler.KETTLER_HEIGHT);
            }
            else if(obj instanceof Microwave) {
                batch.draw(Assets.brokenMicrovawe, obj.position.x - Microwave.MICROWAVE_WIDTH/2 + 0.45f, obj.position.y - 0.5f*Microwave.MICROWAVE_HEIGHT + 0.1f,
                        Microwave.MICROWAVE_WIDTH, Microwave.MICROWAVE_HEIGHT);
            }
            else if(obj instanceof Blender) {
                batch.draw(Assets.brokenBlender, obj.position.x - Blender.BLENDER_WIDTH/2 - 0.1f, obj.position.y - 0.9f*Blender.BLENDER_HEIGHT, Blender.BLENDER_WIDTH, Blender.BLENDER_HEIGHT);
            }
            else if(obj instanceof Refridgerator) {
                batch.draw(Assets.brokenRefrigeratorRegion, obj.position.x - Refridgerator.REFRIDGERATOR_WIDTH/2 + 0.65f, obj.position.y - 0.5f*Refridgerator.REFRIDGERATOR_HEIGHT + 0.35f,
                        Refridgerator.REFRIDGERATOR_WIDTH, Refridgerator.REFRIDGERATOR_HEIGHT);
            }
            else if(obj instanceof Toster) {
                batch.draw(Assets.brokenTosterRegion, obj.position.x - Toster.TOSTER_WIDTH/2 - 0.1f, obj.position.y - 0.35f*Toster.TOSTER_HEIGHT + 0.1f,
                        Toster.TOSTER_WIDTH, Toster.TOSTER_HEIGHT);
            }
            else if(obj instanceof Lamp) {
                batch.draw(Assets.brokenLampRegion, obj.position.x - Lamp.LAMP_WIDTH/2 - 0.2f, obj.position.y - 0.6f*Lamp.LAMP_HEIGHT + 0.55f,
                        Lamp.LAMP_WIDTH, Lamp.LAMP_HEIGHT);
            }
            else if(obj instanceof Switch) {
                batch.draw(Assets.brokenSwitchRegion, obj.position.x - Switch.SWITCH_WIDTH/2 - 0.1f, obj.position.y - 0.5f*Switch.SWITCH_HEIGHT - 0.2f,
                        Switch.SWITCH_WIDTH, Switch.SWITCH_HEIGHT);
            }
            else if(obj instanceof Plate) {
                batch.draw(Assets.brokenPlateRegion, obj.position.x - Plate.PLATE_WIDTH/2 + 0.2f, obj.position.y - 0.9f*Plate.PLATE_HEIGHT + 0.35f,
                        Plate.PLATE_WIDTH, Plate.PLATE_HEIGHT);
            }
        }
    }
}
