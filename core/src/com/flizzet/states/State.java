package com.flizzet.states;

/**
 * Holds all {@link GameState} instances used through the game.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public enum State {

    LOADING(new LoadingState()),
    PLAY(new PlayState()),
    RESTARTING(new RestartingState());
    
    private GameState state;
    
    State(GameState state) {
	this.state = state;
	state.setEnum(this);
    }
    
    public int getId()		{ return this.ordinal(); }
    
    public GameState getState()	{ return this.state; }
    
}
