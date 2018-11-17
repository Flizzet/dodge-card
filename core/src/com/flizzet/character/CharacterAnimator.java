package com.flizzet.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.dodgecard.GameWorld;
import com.flizzet.interfaces.Renderable;
import com.flizzet.interfaces.Updatable;

/**
 * Animates and displays the {@link Character} object.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class CharacterAnimator implements Updatable, Renderable {

    private Texture characterImage = GameWorld.INSTANCE.assets.get("player/back.png");
    private final Character character;
    
    /** Default instantiable constructor */
    public CharacterAnimator(Character character) {
	this.character = character;
	
	character.adjustBoundsToImage(characterImage);
    }
    

    @Override
    public void update(float delta) {}

    @Override
    public void render(SpriteBatch batch) {
	batch.draw(characterImage, character.getX(), character.getY());
    }
    
    public void setImage(Texture newImage)	{ this.characterImage = newImage; }

}
