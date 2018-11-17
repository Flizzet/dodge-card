package com.flizzet.player;

import com.flizzet.character.CharacterCollision;
import com.flizzet.map.Map;

/**
 * Handles collision for the {@link Player} object.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class PlayerCollision extends CharacterCollision {
    
    /** Default instantiable constructor */
    public PlayerCollision(Player player) {
	super(player);
    }
    
    @Override
    public void update(float delta) {
	super.update(delta);
	
	/* Stop at the separator */
	if (character.getY() + character.getHeight() > Map.INSTANCE.getSeparator().getCenterY()) {
	    character.setY(Map.INSTANCE.getSeparator().getCenterY() - character.getHeight());
	}
    }
}
