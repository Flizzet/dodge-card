package com.flizzet.dodgecard;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.flizzet.input.InputController;
import com.flizzet.states.State;

public class Game extends ApplicationAdapter {
    
    private SpriteBatch batch;
    private GameWorld world;
    private SlowMotion slowMo;

    @Override
    public void create() {
	batch = new SpriteBatch();
	world = GameWorld.INSTANCE;
	slowMo = SlowMotion.INSTANCE;
	
	world.states.enterState(State.LOADING);
	world.camera.createCamera();
	
	ScreenViewport screen = new ScreenViewport();
	screen.setCamera(world.camera.getOrthoCamera());
	world.setStage(new Stage(screen, batch));
	
	InputMultiplexer inputMultiplexer = new InputMultiplexer();
	inputMultiplexer.addProcessor(new GestureDetector(new InputController()));
	inputMultiplexer.addProcessor(world.stage);
	Gdx.input.setCatchBackKey(true);
	Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render() {
	
	/* Creating new delta time */
	slowMo.update(Gdx.graphics.getDeltaTime());
	float delta = Gdx.graphics.getDeltaTime() * slowMo.getSpeed();
	
	Gdx.gl.glClearColor(0, 0, 0, 0);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	batch.begin();
	batch.setProjectionMatrix(world.camera.getCombinedMatrix());
	
	/* Updating */
	switch (world.currentState) {
	case COUNTDOWN:
	    world.guiContainer.update(delta);
	    break;
	case PLAY:
	    world.update(delta);
	    world.stage.act(delta);
	    break;
	case GAMEOVER:
	    world.guiContainer.update(delta);
	    break;
	case PAUSE:
	    break;
	default:
	    break;
	}
	
	/* Rendering */
	world.render(batch);
	batch.end();
	
	world.stage.draw();
    }
    
    @Override
    public void resize(int width, int height) {
	world.camera.resize(width, height);
    }

    @Override
    public void dispose() {
	batch.dispose();
	world.assets.getManager().dispose();
    }
}
