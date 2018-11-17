package com.flizzet.items;

import com.flizzet.character.Character;
import com.flizzet.dodgecard.GameWorld;
import com.flizzet.entity.Entity;
import com.flizzet.interfaces.Updatable;

/**
 * Handles colliison for the card object.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class CardCollision implements Updatable {

    private final Card card;
    
    /** Default instantiable constructor */
    public CardCollision(Card card) {
	this.card = card;
    }
    
    @Override
    public void update(float delta) {
	/* Remove at characters */
	if (card.isThrown()) {
	    for (Entity e : GameWorld.INSTANCE.entityContainer.getAll()) {
		if (e instanceof Character && card.getCharacter() != e) {
		    if (card.getBounds().overlaps(e.getBounds())) {
			card.getSpawner().removeCard(card);
			((Character) e).getHealthBar().setHealth(
			((Character) e).getHealthBar().getHealth() - 1);
		    }
		}
	    }
	}
	
	/* Remove after passing edges */
	//* x
	if ((card.getX() + card.getWidth()) < 0) {
	    card.getSpawner().removeCard(card);
	}
	if (card.getX() > GameWorld.INSTANCE.camera.getWidth()) {
	    card.getSpawner().removeCard(card);
	}
	//* y
	if ((card.getY() + card.getHeight()) < 0) {
	    card.getSpawner().removeCard(card);
	}
	if (card.getY() > GameWorld.INSTANCE.camera.getHeight()) {
	    card.getSpawner().removeCard(card);
	}
    }
    
}
