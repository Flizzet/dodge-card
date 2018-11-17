package com.flizzet.states;

import java.io.File;
import java.util.ArrayList;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.assets.AssetLister;
import com.flizzet.assets.Text;
import com.flizzet.assets.TextLoader;
import com.flizzet.dodgecard.GameWorld;

/**
 * Loading state.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class LoadingState extends GameState {

    /** Default instantiable constructor */
    public LoadingState() {
    }

    @Override
    public void entered() {
	/* Build list of all files */
	if (Gdx.app.getType() == ApplicationType.Desktop) {
	    AssetLister.populate(AssetLister.search(AssetLister.DIRECTORY, new ArrayList<File>()));
	}
	
	/* Load all assets */
	GameWorld.INSTANCE.assets.getManager().setLoader(Text.class, new TextLoader(new InternalFileHandleResolver()));
    }

    @Override
    public void update(float delta) {
	GameWorld.INSTANCE.assets.getManager().load("dirs.txt", Text.class);
	if (GameWorld.INSTANCE.assets.getManager().isLoaded("dirs.txt")) {
	    assets.loadAll(GameWorld.INSTANCE.assets.get("dirs.txt", Text.class));
	}
	
	if (assets.getManager().update()) {
	    states.enterState(State.PLAY);
	}
    }

    @Override
    public void render(SpriteBatch batch) {}

    @Override
    public void exited() {}

    @Override
    public void dispose() {}

}
