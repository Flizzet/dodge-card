package com.flizzet.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.assets.Assets;
import com.flizzet.containers.Container;
import com.flizzet.dodgecard.GameWorld;
import com.flizzet.entity.Entity;
import com.flizzet.guicomponent.GuiComponent;

/**
 * Abstract game state class to be extended by concrete game state classes.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
abstract class GameState {

    protected State state;
    protected Container<Entity> entityContainer = GameWorld.INSTANCE.entityContainer;
    protected Container<GuiComponent> guiContainer = GameWorld.INSTANCE.guiContainer;
    protected Assets assets = GameWorld.INSTANCE.assets;
    protected StateManager states = GameWorld.INSTANCE.states;
    protected GameWorld world = GameWorld.INSTANCE;
    
    /** Default instantiable constructor */
    public GameState() {}
    
    /** Called when the game state is entered */
    public abstract void entered();
    
    /**
     * Called every frame. Called before render.
     * 
     * @param delta
     *            - The time between the current frame and the previous frame
     */
    public abstract void update(float delta);
    
    /** Called every frame, renders all objects in the game and displays
     * @param batch - The {@link SpriteBatch} displaying */
    public abstract void render(SpriteBatch batch);
    
    /** Called when entering a different {@link GameState} while in the current state. */
    public abstract void exited();
    
    /** Called at the closing of the game. */
    public abstract void dispose();
    
    /** Attaches a {@link State} enum to this GameState object */
    public void setEnum(State newState) 	{ this.state = newState; }
    public State getEnum()			{ return this.state; }
    
    public int compareTo(GameState state) {
	return this.getEnum().compareTo(state.getEnum());
    }

}
