package com.flizzet.character;

import com.flizzet.dodgecard.GameWorld;
import com.flizzet.interfaces.Updatable;
import com.flizzet.items.Card;
import com.flizzet.items.CardSpawner;

/**
 * Handles collision for the {@link Character} object.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class CharacterCollision implements Updatable {

    protected final Character character;
    
    /** Default instantiable constructor */
    public CharacterCollision(Character character) {
	this.character = character;
    }
    
    @Override
    public void update(float delta) {
	
	/* Grab cards */
	for (Card c : CardSpawner.INSTANCE.getAll()) {
	    if (character.getBounds().overlaps(c.getBounds())) {
		if (character.getHeldCard() == null && !c.isThrown()) character.setHeldCard(c);
	    }
	}
	
	/* Stopping on edges */
	//* x
	if (character.getX() < 0) { 
	    character.setX(0);
	} else if (character.getX() + character.getWidth() > GameWorld.INSTANCE.camera.getWidth()) {
	    character.setX(GameWorld.INSTANCE.camera.getWidth() - character.getWidth());
	}
	//* y
	if (character.getY() < 0) {
	    character.setY(0);
	} else if (character.getY() + character.getHeight() > GameWorld.INSTANCE.camera.getHeight()) {
	    character.setY(GameWorld.INSTANCE.camera.getHeight() - character.getHeight());
	}
	
    }

}
