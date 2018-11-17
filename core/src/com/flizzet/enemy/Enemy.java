package com.flizzet.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.flizzet.character.Character;
import com.flizzet.dodgecard.GameWorld;

/**
 * Enemy {@link Character}.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class Enemy extends Character {
    
    public static final Enemy INSTANCE = new Enemy();
    private EnemyController controller = new EnemyController(this);
    
    public Enemy() {
	this.getAnimator().setImage(GameWorld.INSTANCE.assets.get("player/front.png", Texture.class));
    }
    
    @Override
    public void update(float delta) {
	super.update(delta);
	controller.update(delta);
    }
    
    @Override
    public void reset() {
	this.setCenterX(GameWorld.INSTANCE.camera.getCenterX());
	this.setY(GameWorld.INSTANCE.camera.getHeight() - 20 - this.getHeight());
	controller.reset();
	healthBar.reset();
    }
    
    public EnemyController getController()	{ return this.controller; }

}
