package com.flizzet.dodgecard;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.flizzet.assets.Assets;
import com.flizzet.camera.Camera;
import com.flizzet.camera.MainCamera;
import com.flizzet.containers.Container;
import com.flizzet.entity.Entity;
import com.flizzet.guicomponent.GuiComponent;
import com.flizzet.interfaces.Renderable;
import com.flizzet.interfaces.Updatable;
import com.flizzet.states.StateManager;

/**
 * Manages all game-required containers etc.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class GameWorld implements Renderable, Updatable {
    
    public static final GameWorld INSTANCE = new GameWorld();
    public final Container<Entity> entityContainer = new Container<Entity>();
    public final Container<GuiComponent> guiContainer = new Container<GuiComponent>();
    public final Assets assets = new Assets();
    public final StateManager states = new StateManager();
    public final Camera camera = new MainCamera();
    public PlayState currentState = PlayState.PLAY;
    public Stage stage;

    /** Suppressed constructor */
    private GameWorld() {}

    @Override
    public void update(float delta) {
	entityContainer.update(delta);
	guiContainer.update(delta);
	states.update(delta);
	camera.update(delta);
    }

    @Override
    public void render(SpriteBatch batch) {
	entityContainer.render(batch);
	guiContainer.render(batch);
	states.render(batch);
    }
    
    public void setPlayState(PlayState newState)	{ this.currentState = newState; }
    public void setStage(Stage newStage)		{ this.stage = newStage; }

}
