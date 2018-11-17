package com.flizzet.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.interfaces.Renderable;
import com.flizzet.interfaces.Updatable;

/**
 * Manages state exiting, entering and current state.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class StateManager implements Updatable, Renderable {
    
    private GameState currentState;

    /** Default instantiable constructor */
    public StateManager() {}


    @Override
    public void update(float delta) {
	currentState.update(delta);
    }
    
    @Override
    public void render(SpriteBatch batch) {
	currentState.render(batch);
    }

    /**
     * Enters a game state using the {@link State} enum.
     * 
     * @param newState
     *            - The state being entered, specified by its {@link State} enum
     *            value
     */
    public void enterState(State newState) {
	/* Exit the current state */
	if (newState != State.LOADING) currentState.exited();
	
	/* Set the new state, and enter it */
	currentState = newState.getState();
	currentState.entered();
    }
    
    public void dispose() {
	for (State s : State.values()) {
	    s.getState().dispose();
	}
    }
    
    public GameState getCurrentState()	{ return this.currentState; }

}
