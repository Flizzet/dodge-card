package com.flizzet.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.dodgecard.GameWorld;
import com.flizzet.entity.Entity;

/**
 * Holds and displays health for the {@link Character} object.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class CharacterHealthBar extends Entity {

    private final Character character;
    
    private int health = 3;
    private HealthPoint[] healthPoints = new HealthPoint[] {
	new HealthPoint(), new HealthPoint(), new HealthPoint()
    };
    private int[] margins = new int[] {
	    -8, 0, 8
    };
    private final int MARGIN = 5;
    
    /** Default instantiable constructor */
    public CharacterHealthBar(Character character) {
	this.character = character;
    }
    
    @Override
    public void update(float delta) {
	/* Set the position of all health points */
	for (int i = 0; i < health; i++) {
	    healthPoints[i].setCenterX(character.getCenterX() + margins[i]);
	    healthPoints[i].setCenterY(character.getY() + character.getHeight() + MARGIN);
	}
    }
    
    @Override
    public void render(SpriteBatch batch) {
	/* Render all health points */
	for (HealthPoint h : healthPoints) {
	    h.render(batch);
	}
    }
    
    @Override
    public void reset() {
	health = 3;
    }
    
    public void setHealth(int newHealth)	{ this.health = newHealth; }
    public int getHealth()			{ return this.health; }

}

/**
 * 
 * Health point for display in the {@link CharacterHealthBar}.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
class HealthPoint extends Entity {
    
    private Texture healthPointImage = GameWorld.INSTANCE.assets.get("player/healthpoint.png");
    
    public HealthPoint() {
	this.adjustBoundsToImage(healthPointImage);
    }

    @Override
    public void update(float delta) {}

    @Override
    public void render(SpriteBatch batch) {
	batch.draw(healthPointImage, bounds.x, bounds.y);
    }

    @Override
    public void reset() {}
    
}





