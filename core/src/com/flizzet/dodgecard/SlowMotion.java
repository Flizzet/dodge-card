package com.flizzet.dodgecard;

import com.badlogic.gdx.Gdx;
import com.flizzet.interfaces.Updatable;

/**
 * Handles slow motion for the delta time.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class SlowMotion implements Updatable {

    public static final SlowMotion INSTANCE = new SlowMotion();
    
    private float speed;
    private float targetSpeed = 1;
    
    /** Suppressed constructor */
    private SlowMotion() {}

    @Override
    public void update(float delta) {
	/* Move the speed towards the targetSpeed at an ease */
	speed += ((targetSpeed - speed) / 2) * Gdx.graphics.getDeltaTime();
    }
    
    public void setTargetSpeed(float newSpeed)	{ this.targetSpeed = newSpeed; }
    public float getTargetSpeed()		{ return this.targetSpeed; }
    public float getSpeed()			{ return this.speed; }

}
