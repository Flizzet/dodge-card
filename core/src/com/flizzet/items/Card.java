package com.flizzet.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.character.Character;
import com.flizzet.dodgecard.GameWorld;
import com.flizzet.enemy.Enemy;
import com.flizzet.entity.Entity;
import com.flizzet.map.Map;
import com.flizzet.player.Player;

/**
 * Card that spawns and is thrown.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class Card extends Entity {

    private final CardSpawner spawner;
    private final CardCollision collision = new CardCollision(this);
    private Texture cardImage = GameWorld.INSTANCE.assets.get("items/card.png");
    private Character character = null;
    private boolean thrown = false;
    private float angle;
    private float xVelocity, yVelocity;
    private float speed = 200;
    private float throwItCooldown = 750;
    
    /** Default instantiable constructor */
    public Card(CardSpawner spawner) {
	this.spawner = spawner;
	this.adjustBoundsToImage(cardImage);
    }
    
    @Override
    public void update(float delta) {
	
	collision.update(delta);
	
	speed += ((75 - speed) / 2) * delta;
	/* Get speed from angle */
	xVelocity = (float) (speed * Math.cos(angle));
	if (character instanceof Player) {
	    yVelocity = (float) (speed * Math.sin(-angle));
	} else {
	    yVelocity = (float) (speed * Math.sin(angle));
	}
	
	/* Ease towards player if connected to one,
	 * Otherwise fly towards angle */
	if (character != null && !thrown) {
	    bounds.x += ((character.getCenterX() - this.getHalfWidth()) - bounds.x) / 2;
	    bounds.y += ((character.getCenterY() - this.getHalfWidth()) - bounds.y) / 2;
	} else if (thrown) {
	    this.addToX(xVelocity * delta);
	    this.addToY(yVelocity * delta);
	}
	
	/* Fix the disgusting bug in the enemy throwing etc */
	if (character instanceof Enemy) {
	    throwItCooldown--;
	    if (throwItCooldown <= 0) {
		CardSpawner.INSTANCE.removeCard(this);
		character.setHeldCard(null);
		((Enemy)character).getController().setTargetCard(null);
		this.character = null;
	    }
	}
	
    }

    @Override
    public void render(SpriteBatch batch) {
	batch.draw(cardImage, bounds.x, bounds.y);
    }
    
    @Override
    public void reset() {}
    
    /** Decides a random position on the center line */
    public void assignRandomPosition() {
	this.setCenterY(Map.INSTANCE.getSeparator().getCenterY());
	this.setCenterX((float) (Math.random() * (Map.INSTANCE.getSeparator().getX() 
		+ Map.INSTANCE.getSeparator().getWidth())));
    }
    
    public void setThrown(boolean isThrown)	{ this.thrown = isThrown; }
    public void setCharacter(Character newChar)	{ this.character = newChar; }
    public void setXVel(float newXVel)		{ this.xVelocity = newXVel; }
    public void setYVel(float newYVel)		{ this.yVelocity = newYVel; }
    public void setAngle(float newAngle)	{ this.angle = newAngle; }
    public boolean isThrown()			{ return this.thrown; }
    public CardSpawner getSpawner()		{ return this.spawner; }
    public Character getCharacter()		{ return this.character; }

}
