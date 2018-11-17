package com.flizzet.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.dodgecard.GameWorld;
import com.flizzet.entity.Entity;

/**
 * The background object for displaying the map and line used on the stage.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class Map extends Entity {

    public static final Map INSTANCE = new Map();
    private Texture backgroundImage = GameWorld.INSTANCE.assets.get("map/background.png");
    private Separator separator = new Separator();
    
    /** Suppressed constructor for singleton use */
    private Map() {
	this.adjustBoundsToImage(backgroundImage);
    }

    @Override
    public void update(float delta) {
	separator.update(delta);
    }
    
    @Override
    public void render(SpriteBatch batch) {
	
	/* Drawing background */
	batch.draw(backgroundImage, bounds.x, bounds.y, bounds.width, bounds.height);
	
	/* Drawing middle line */
	separator.render(batch);
	
    }
    
    @Override
    public void reset() {}

    public Separator getSeparator()	{ return this.separator; }
    
}