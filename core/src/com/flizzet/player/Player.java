package com.flizzet.player;

import com.flizzet.character.Character;
import com.flizzet.dodgecard.GameWorld;

/**
 * Player.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class Player extends Character {

    public static final Player INSTANCE = new Player();
    
    private PlayerController controller = new PlayerController(this);
    
    /** Default instantiable constructor */
    public Player() {
	this.setCollision(new PlayerCollision(this));
    }
    
    @Override
    public void update(float delta) {
	controller.update(delta);
	super.update(delta);
    }

    @Override
    public void reset() {
	this.setCenterX(GameWorld.INSTANCE.camera.getCenterX());
	this.setY(20);
	healthBar.reset();
    }
    
}
