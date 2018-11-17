package com.flizzet.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.dodgecard.PlayState;

/**
 * Restarting state.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class RestartingState extends GameState {

    private int cooldown = 3;
    
    /** Default instantiable constructor */
    public RestartingState() {}

    @Override
    public void entered() {
	world.setPlayState(PlayState.PLAY);
    }

    @Override
    public void update(float delta) {
	cooldown--;
	if (cooldown <= 0) {
	    world.states.enterState(State.PLAY);
	}
    }

    @Override
    public void render(SpriteBatch batch) {}

    @Override
    public void exited() {}

    @Override
    public void dispose() {}

}
