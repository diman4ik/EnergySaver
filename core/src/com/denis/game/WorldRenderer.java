package com.denis.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.denis.game.objects.Air;
import com.denis.game.objects.Blender;
import com.denis.game.objects.Fan;
import com.denis.game.objects.Indicator;
import com.denis.game.objects.Iron;
import com.denis.game.objects.Kettler;
import com.denis.game.objects.Lamp;
import com.denis.game.objects.Microwave;
import com.denis.game.objects.Plate;
import com.denis.game.objects.R3Condition;
import com.denis.game.objects.R3Lamp;
import com.denis.game.objects.R3Mac;
import com.denis.game.objects.R3Outlet;
import com.denis.game.objects.R3Switch;
import com.denis.game.objects.R3TV;
import com.denis.game.objects.R3Telephone;
import com.denis.game.objects.R4Condition;
import com.denis.game.objects.R4Lamp;
import com.denis.game.objects.R4Switch;
import com.denis.game.objects.R4TV;
import com.denis.game.objects.Radio;
import com.denis.game.objects.Refridgerator;
import com.denis.game.objects.SoundLeft;
import com.denis.game.objects.SoundRight;
import com.denis.game.objects.Switch;
import com.denis.game.objects.Toster;
import com.denis.game.objects.Washing;

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
        renderIndicator();
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

    private void renderIndicator() {
        batch.draw( Assets.indicatorEmptyRegion, world.indicator.position.x - Indicator.INDICATOR_WIDTH / 2, world.indicator.position.y - Indicator.INDICATOR_HEIGHT/2,
                    Indicator.INDICATOR_WIDTH, Indicator.INDICATOR_HEIGHT + 0.1f);

        if( world.percent == 0)
            return;


        float part = ((float)world.percent)/100;

        int length = ((int)(Assets.indicatorFull.getWidth()*part));

        TextureRegion indicatorRegion = new TextureRegion(Assets.indicatorFull, 0, 0, length, 55);
        batch.draw( indicatorRegion, world.indicator.position.x - Indicator.INDICATOR_WIDTH / 2 + Indicator.INDICATOR_WIDTH*(1f - part) + 0.2f,
                world.indicator.position.y - Indicator.INDICATOR_HEIGHT/2 + 0.65f,
                Indicator.INDICATOR_WIDTH*part - 0.6f, Indicator.INDICATOR_HEIGHT*0.36f + 0.05f);
    }

    private void renderBrokenObjects() {
        for (GameObject obj : world.gameObjects) {
            if(obj.visible) {
                if (obj instanceof Kettler) {
                    if(obj.broken) {
                        batch.draw(Assets.brokenKettle, obj.position.x - Kettler.KETTLER_WIDTH / 2, obj.position.y - 0.7f * Kettler.KETTLER_HEIGHT,
                                Kettler.KETTLER_WIDTH, Kettler.KETTLER_HEIGHT);
                    }
                } else if (obj instanceof Microwave) {
                    if(obj.broken) {
                        batch.draw(Assets.brokenMicrovawe, obj.position.x - Microwave.MICROWAVE_WIDTH / 2 + 0.45f, obj.position.y - 0.5f * Microwave.MICROWAVE_HEIGHT + 0.1f,
                                Microwave.MICROWAVE_WIDTH, Microwave.MICROWAVE_HEIGHT);
                    }
                } else if (obj instanceof Blender) {
                    if(obj.broken) {
                        batch.draw(Assets.brokenBlender, obj.position.x - Blender.BLENDER_WIDTH / 2 - 0.1f, obj.position.y - 0.9f * Blender.BLENDER_HEIGHT,
                                Blender.BLENDER_WIDTH, Blender.BLENDER_HEIGHT);
                    }
                } else if (obj instanceof Refridgerator) {
                    if(obj.broken) {
                        batch.draw(Assets.brokenRefrigeratorRegion, obj.position.x - Refridgerator.REFRIDGERATOR_WIDTH / 2 + 0.65f, obj.position.y - 0.5f * Refridgerator.REFRIDGERATOR_HEIGHT + 0.35f,
                                Refridgerator.REFRIDGERATOR_WIDTH, Refridgerator.REFRIDGERATOR_HEIGHT);
                    }
                } else if (obj instanceof Toster) {
                    if(obj.broken) {
                        batch.draw(Assets.brokenTosterRegion, obj.position.x - Toster.TOSTER_WIDTH / 2 - 0.1f, obj.position.y - 0.35f * Toster.TOSTER_HEIGHT + 0.1f,
                                Toster.TOSTER_WIDTH, Toster.TOSTER_HEIGHT);
                    }
                } else if (obj instanceof Lamp) {
                    if(obj.broken) {
                        batch.draw(Assets.brokenLampRegion, obj.position.x - Lamp.LAMP_WIDTH / 2 - 0.2f, obj.position.y - 0.6f * Lamp.LAMP_HEIGHT + 0.55f,
                                Lamp.LAMP_WIDTH, Lamp.LAMP_HEIGHT);
                    }
                } else if (obj instanceof Switch) {
                    if(obj.broken) {
                        if(world.level == 1) {
                            batch.draw(Assets.brokenSwitchRegion, obj.position.x - Switch.SWITCH_WIDTH / 2 - 0.1f, obj.position.y - 0.5f * Switch.SWITCH_HEIGHT - 0.2f,
                                    Switch.SWITCH_WIDTH, Switch.SWITCH_HEIGHT);
                        }
                        else if(world.level == 2) {
                            batch.draw(Assets.brokenSwitchRegion2, obj.position.x - Switch.SWITCH_WIDTH / 2 - 0.1f, obj.position.y - 0.5f * Switch.SWITCH_HEIGHT - 0.2f,
                                    Switch.SWITCH_WIDTH, Switch.SWITCH_HEIGHT);
                        }
                    }
                    else {
                        batch.draw(Assets.switchRegion, obj.position.x - Switch.SWITCH_WIDTH / 2 - 0.1f, obj.position.y - 0.5f * Switch.SWITCH_HEIGHT - 0.2f,
                                Switch.SWITCH_WIDTH, Switch.SWITCH_HEIGHT);
                    }
                } else if (obj instanceof Plate) {
                    if(obj.broken) {
                        batch.draw(Assets.brokenPlateRegion, obj.position.x - Plate.PLATE_WIDTH / 2 + 0.2f, obj.position.y - 0.9f * Plate.PLATE_HEIGHT + 0.35f,
                                Plate.PLATE_WIDTH, Plate.PLATE_HEIGHT);
                    }
                }
                else if(obj instanceof Iron) {
                    if(obj.broken) {
                        batch.draw(Assets.brokenIronRegion, obj.position.x - Iron.IRON_WIDTH / 2 + 0.2f, obj.position.y - 0.9f * Iron.IRON_HEIGHT + 0.35f,
                                Iron.IRON_WIDTH, Iron.IRON_HEIGHT);
                    }
                    else {
                        batch.draw(Assets.ironRegion, obj.position.x - Iron.IRON_WIDTH / 2 + 0.2f, obj.position.y - 0.9f * Iron.IRON_HEIGHT + 0.35f,
                                Iron.IRON_WIDTH, Iron.IRON_HEIGHT);
                    }
                }
                else if(obj instanceof Washing) {
                    if(obj.broken) {
                        batch.draw(Assets.brokenWashingRegion, obj.position.x - Washing.WASHING_WIDTH / 2 + 0.2f, obj.position.y - Washing.WASHING_HEIGHT/2 + 0.35f,
                                Washing.WASHING_WIDTH, Washing.WASHING_HEIGHT);
                    }
                    else {
                        batch.draw(Assets.washingRegion, obj.position.x - Washing.WASHING_WIDTH / 2 + 0.2f, obj.position.y - Washing.WASHING_HEIGHT/2 + 0.35f,
                                Washing.WASHING_WIDTH, Washing.WASHING_HEIGHT);
                    }
                }
                else if(obj instanceof Fan) {
                    if(obj.broken) {
                        batch.draw(Assets.brokenFanRegion, obj.position.x - Fan.FAN_WIDTH / 2 + 0.2f, obj.position.y - 0.9f * Fan.FAN_HEIGHT + 0.35f,
                                Fan.FAN_WIDTH, Fan.FAN_HEIGHT);
                    }
                    else {
                        batch.draw(Assets.fanRegion, obj.position.x - Fan.FAN_WIDTH / 2 + 0.2f, obj.position.y - 0.9f * Fan.FAN_HEIGHT + 0.35f,
                                Fan.FAN_WIDTH, Fan.FAN_HEIGHT);
                    }
                }
                else if(obj instanceof Radio ) {
                    if(obj.broken) {
                        batch.draw(Assets.brokenRadioRegion, obj.position.x - Radio.RADIO_WIDTH / 2 + 0.2f, obj.position.y - 0.9f * Radio.RADIO_HEIGHT + 0.35f,
                                Radio.RADIO_WIDTH, Radio.RADIO_HEIGHT);
                    }
                    else {
                        batch.draw(Assets.radioRegion, obj.position.x - Radio.RADIO_WIDTH / 2 + 0.2f, obj.position.y - 0.9f * Radio.RADIO_HEIGHT + 0.35f,
                                Radio.RADIO_WIDTH, Radio.RADIO_HEIGHT);
                    }
                }
                else if(obj instanceof R3TV) {
                    if(obj.broken) {
                        batch.draw(Assets.brokenR3TVRegion, obj.position.x - R3TV.R3TV_WIDTH / 2, obj.position.y - R3TV.R3TV_HEIGHT/2,
                                R3TV.R3TV_WIDTH, R3TV.R3TV_HEIGHT);
                    }
                    else {
                        batch.draw(Assets.R3TVRegion, obj.position.x - R3TV.R3TV_WIDTH / 2, obj.position.y - R3TV.R3TV_HEIGHT/2,
                                R3TV.R3TV_WIDTH, R3TV.R3TV_HEIGHT);
                    }
                }
                else if(obj instanceof R3Condition) {
                    if(obj.broken) {
                        batch.draw(Assets.brokenR3ConditionRegion, obj.position.x - R3Condition.R3CONDITION_WIDTH / 2, obj.position.y - R3Condition.R3CONDITION_HEIGHT/2,
                                R3Condition.R3CONDITION_WIDTH, R3Condition.R3CONDITION_HEIGHT);
                    }
                    else {
                        batch.draw(Assets.R3ConditionRegion, obj.position.x - R3Condition.R3CONDITION_WIDTH / 2, obj.position.y - R3Condition.R3CONDITION_HEIGHT/2,
                                R3Condition.R3CONDITION_WIDTH, R3Condition.R3CONDITION_HEIGHT);
                    }
                }
                else if(obj instanceof R3Switch) {
                    if(obj.broken) {
                        batch.draw(Assets.brokenR3SwitchRegion, obj.position.x - R3Switch.R3SWITCH_WIDTH / 2, obj.position.y - R3Switch.R3SWITCH_HEIGHT/2,
                                R3Switch.R3SWITCH_WIDTH, R3Switch.R3SWITCH_HEIGHT);
                    }
                    else {
                        batch.draw(Assets.R3SwitchRegion, obj.position.x - R3Switch.R3SWITCH_WIDTH / 2, obj.position.y - R3Switch.R3SWITCH_HEIGHT/2,
                                R3Switch.R3SWITCH_WIDTH, R3Switch.R3SWITCH_HEIGHT);
                    }
                }
                else if(obj instanceof R3Lamp) {
                    if(obj.broken) {
                        batch.draw(Assets.brokenR3LampRegion, obj.position.x - R3Lamp.R3LAMP_WIDTH / 2, obj.position.y - R3Lamp.R3LAMP_HEIGHT/2,
                                R3Lamp.R3LAMP_WIDTH, R3Lamp.R3LAMP_HEIGHT);
                    }
                    else {
                        batch.draw(Assets.R3LampRegion, obj.position.x - R3Lamp.R3LAMP_WIDTH / 2, obj.position.y - R3Lamp.R3LAMP_HEIGHT/2,
                                R3Lamp.R3LAMP_WIDTH, R3Lamp.R3LAMP_HEIGHT);
                    }
                }
                else if(obj instanceof R3Telephone) {
                    if(obj.broken) {
                        batch.draw(Assets.brokenR3TelephoneRegion, obj.position.x - R3Telephone.R3TELEPHONE_WIDTH / 2, obj.position.y - R3Telephone.R3TELEPHONE_HEIGHT/2,
                                R3Telephone.R3TELEPHONE_WIDTH, R3Telephone.R3TELEPHONE_HEIGHT);
                    }
                    else {
                        batch.draw(Assets.R3TelephoneRegion, obj.position.x - R3Telephone.R3TELEPHONE_WIDTH / 2, obj.position.y - R3Telephone.R3TELEPHONE_HEIGHT/2,
                                R3Telephone.R3TELEPHONE_WIDTH, R3Telephone.R3TELEPHONE_HEIGHT);
                    }
                }
                else if(obj instanceof R3Outlet) {
                    if(obj.broken) {
                        batch.draw(Assets.brokenR3OutletRegion, obj.position.x - R3Outlet.R3OUTLET_WIDTH / 2, obj.position.y - R3Outlet.R3OUTLET_HEIGHT/2,
                                R3Outlet.R3OUTLET_WIDTH, R3Outlet.R3OUTLET_HEIGHT);
                    }
                    else {
                        batch.draw(Assets.R3OutletRegion, obj.position.x - R3Outlet.R3OUTLET_WIDTH / 2, obj.position.y - R3Outlet.R3OUTLET_HEIGHT/2,
                                R3Outlet.R3OUTLET_WIDTH, R3Outlet.R3OUTLET_HEIGHT);
                    }
                }
                else if(obj instanceof R3Mac) {
                    if(obj.broken) {
                        batch.draw(Assets.brokenR3MacRegion, obj.position.x - R3Mac.R3MAC_WIDTH / 2, obj.position.y - R3Mac.R3MAC_HEIGHT/2,
                                R3Mac.R3MAC_WIDTH, R3Mac.R3MAC_HEIGHT);
                    }
                    else {
                        batch.draw(Assets.R3MacRegion, obj.position.x - R3Mac.R3MAC_WIDTH / 2, obj.position.y - R3Mac.R3MAC_HEIGHT/2,
                                R3Mac.R3MAC_WIDTH, R3Mac.R3MAC_HEIGHT);
                    }
                }
                else if(obj instanceof R4Lamp) {
                    if(obj.broken) {
                        batch.draw(Assets.brokenR4LampRegion, obj.position.x - R4Lamp.R4LAMP_WIDTH / 2, obj.position.y - R4Lamp.R4LAMP_HEIGHT/2,
                                R4Lamp.R4LAMP_WIDTH, R4Lamp.R4LAMP_HEIGHT);
                    }
                    else {
                        batch.draw(Assets.R4LampRegion, obj.position.x - R4Lamp.R4LAMP_WIDTH / 2, obj.position.y - R4Lamp.R4LAMP_HEIGHT/2,
                                R4Lamp.R4LAMP_WIDTH, R4Lamp.R4LAMP_HEIGHT);
                    }
                }
                else if(obj instanceof R4Condition) {
                    if(obj.broken) {
                        batch.draw(Assets.brokenR4ConditionRegion, obj.position.x - R4Condition.R4CONDITION_WIDTH / 2, obj.position.y - R4Condition.R4CONDITION_HEIGHT/2,
                                R4Condition.R4CONDITION_WIDTH, R4Condition.R4CONDITION_HEIGHT);
                    }
                    else {
                        batch.draw(Assets.R4ConditionRegion, obj.position.x - R4Condition.R4CONDITION_WIDTH / 2, obj.position.y - R4Condition.R4CONDITION_HEIGHT/2,
                                R4Condition.R4CONDITION_WIDTH, R4Condition.R4CONDITION_HEIGHT);
                    }
                }
                else if(obj instanceof SoundLeft) {
                    if(obj.broken) {
                        batch.draw(Assets.brokenSoundLetfRegion, obj.position.x - SoundLeft.SOUNDLEFT_WIDTH/ 2, obj.position.y - SoundLeft.SOUNDLEFT_HEIGHT/2,
                                SoundLeft.SOUNDLEFT_WIDTH, SoundLeft.SOUNDLEFT_HEIGHT);
                    }
                    else {
                        batch.draw(Assets.SoundLetfRegion, obj.position.x - SoundLeft.SOUNDLEFT_WIDTH / 2, obj.position.y - SoundLeft.SOUNDLEFT_HEIGHT/2,
                                SoundLeft.SOUNDLEFT_WIDTH, SoundLeft.SOUNDLEFT_HEIGHT);
                    }
                }
                else if(obj instanceof R4TV) {
                    if(obj.broken) {
                        batch.draw(Assets.brokenR4TvRegion, obj.position.x - R4TV.R4TV_WIDTH/ 2, obj.position.y - R4TV.R4TV_HEIGHT/2,
                                R4TV.R4TV_WIDTH, R4TV.R4TV_HEIGHT);
                    }
                    else {
                        batch.draw(Assets.R4TvRegion, obj.position.x - R4TV.R4TV_WIDTH / 2, obj.position.y - R4TV.R4TV_HEIGHT/2,
                                R4TV.R4TV_WIDTH, R4TV.R4TV_HEIGHT);
                    }
                }
                else if(obj instanceof SoundRight) {
                    if(obj.broken) {
                        batch.draw(Assets.brokenSoundRightRegion, obj.position.x - SoundRight.SOUNDRIGHT_WIDTH/ 2, obj.position.y - SoundRight.SOUNDRIGHT_HEIGHT/2,
                                SoundRight.SOUNDRIGHT_WIDTH, SoundRight.SOUNDRIGHT_HEIGHT);
                    }
                    else {
                        batch.draw(Assets.SoundRightRegion, obj.position.x - SoundRight.SOUNDRIGHT_WIDTH / 2, obj.position.y - SoundRight.SOUNDRIGHT_HEIGHT/2,
                                SoundRight.SOUNDRIGHT_WIDTH, SoundRight.SOUNDRIGHT_HEIGHT);
                    }
                }
                else if(obj instanceof Air) {
                    if(obj.broken) {
                        batch.draw(Assets.brokenAirRegion, obj.position.x - Air.AIR_WIDTH/ 2, obj.position.y - Air.AIR_HEIGHT/2,
                                Air.AIR_WIDTH, Air.AIR_HEIGHT);
                    }
                    else {
                        batch.draw(Assets.AirRegion, obj.position.x - Air.AIR_WIDTH / 2, obj.position.y - Air.AIR_HEIGHT/2,
                                Air.AIR_WIDTH, Air.AIR_HEIGHT);
                    }
                }
                else if(obj instanceof R4Switch) {
                    if(obj.broken) {
                        batch.draw(Assets.brokenSwitch4Region, obj.position.x - R4Switch.R4SWITCH_WIDTH/ 2, obj.position.y - R4Switch.R4SWITCH_HEIGHT/2,
                                R4Switch.R4SWITCH_WIDTH, R4Switch.R4SWITCH_HEIGHT);
                    }
                    else {
                        batch.draw(Assets.Switch4Region, obj.position.x - R4Switch.R4SWITCH_WIDTH / 2, obj.position.y - R4Switch.R4SWITCH_HEIGHT/2,
                                R4Switch.R4SWITCH_WIDTH, R4Switch.R4SWITCH_HEIGHT);
                    }
                }
            }
        }
    }
}
