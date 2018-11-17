package com.flizzet.character;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.dodgecard.GameWorld;
import com.flizzet.dodgecard.PlayState;
import com.flizzet.enemy.Enemy;
import com.flizzet.entity.Entity;
import com.flizzet.guicomponent.GameOverComponent;
import com.flizzet.items.Card;
import com.flizzet.items.CardSpawner;
import com.flizzet.player.Player;

/**
 * Character. Used as both the {@link Player}, and the {@link Enemy}.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class Character extends Entity {

    private CharacterAnimator animator = new CharacterAnimator(this);
    private CharacterCollision collision = new CharacterCollision(this);
    protected CharacterHealthBar healthBar = new CharacterHealthBar(this);
    
    private Card heldCard;
    
    /** Default instantiable constructor */
    public Character() {}

    @Override
    public void update(float delta) {
	/* Update other components */
	collision.update(delta);
	animator.update(delta);
	healthBar.update(delta);
	
	/* Update card */
	if (heldCard != null) {
	    heldCard.update(delta);
	}
	
	/* Die */
	if (healthBar.getHealth() <= 0) {
	    die();
	}
    }
    
    @Override
    public void render(SpriteBatch batch) {
	animator.render(batch);
	healthBar.render(batch);
	
	if (heldCard != null) {
	    heldCard.render(batch);
	}
    }
    
    @Override
    public void reset() {}

    public void throwCard(float radians) {
	if (heldCard != null) {
	    heldCard.setThrown(true);
	    heldCard.setAngle(radians);
	    heldCard = null;
	}
    }
    
    public void die() {
	CardSpawner.INSTANCE.stop();
	GameOverComponent gameOverComponent = new GameOverComponent();
	GameWorld.INSTANCE.guiContainer.add(gameOverComponent);
	GameWorld.INSTANCE.setPlayState(PlayState.GAMEOVER);
    }
    
    public void setAnimator(CharacterAnimator newAnimator)	{ this.animator = newAnimator; }
    public void setCollision(CharacterCollision newCollision)	{ this.collision = newCollision; }
    public void setHeldCard(Card newCard)			{ this.heldCard = newCard; if (heldCard != null) heldCard.setCharacter(this); }
    
    public CharacterAnimator getAnimator()			{ return this.animator; }
    public CharacterCollision getCollision()			{ return this.collision; }
    public CharacterHealthBar getHealthBar()			{ return this.healthBar; }
    public Card getHeldCard()					{ return this.heldCard; }

}
