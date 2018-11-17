package com.flizzet.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.flizzet.guicomponent.Joystick;
import com.flizzet.interfaces.Updatable;

/**
 * Controls the {@link Player} object.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class PlayerController implements Updatable {

    private final Player player;
    
    private boolean leftPressed, rightPressed, upPressed, downPressed;
    private float xVelocity = 0, yVelocity = 0;
    private float topXVelocity = 100, topYVelocity = 100;
    
    /** Default instantiable constructor */
    public PlayerController(Player player) {
	this.player = player;
    }

    @Override
    public void update(float delta) {
	/* Key press controls */
	/* Reset all key presses */
	leftPressed = rightPressed = upPressed = downPressed = false;
	/* Check all presses */
	if (Gdx.input.isKeyPressed(Keys.A)) 	{ leftPressed = true; }
	if (Gdx.input.isKeyPressed(Keys.D))	{ rightPressed = true; }
	if (Gdx.input.isKeyPressed(Keys.W))	{ upPressed = true; }
	if (Gdx.input.isKeyPressed(Keys.S))	{ downPressed = true; }
	/* Convert keypresses to movement methods */
	/* Horizontal */
	if (leftPressed && rightPressed)	slowDownHorizontal();
	else if (leftPressed)			moveLeft();
	else if (rightPressed)			moveRight();
	else					slowDownHorizontal();
	/* Vertical */
	if (upPressed && downPressed)		slowDownVertical();
	else if (downPressed)			moveDown();
	else if (upPressed)			moveUp();
	else 					slowDownVertical();
	/* Add velocity to player position */
	player.addToX(xVelocity * delta);
	player.addToY(yVelocity * delta);
	
	/* Joystick controls */
	if (Gdx.input.isTouched()) {
	    xVelocity = topXVelocity * Joystick.INSTANCE.getTouchpad().getKnobPercentX();
	    yVelocity = topYVelocity * Joystick.INSTANCE.getTouchpad().getKnobPercentY();
	}
    }
    
    /** Move left */
    public void moveLeft() 		{ xVelocity += (-topXVelocity - xVelocity) / 3; }
    /** Move right */
    public void moveRight() 		{ xVelocity += (topXVelocity - xVelocity) / 3; }
    /** Move up */
    public void moveUp() 		{ yVelocity += (topYVelocity - yVelocity) / 3; }
    /** Move down */
    public void moveDown() 		{ yVelocity += (-topYVelocity - yVelocity) / 3; }
    /** Slows down the player horizontally */
    public void slowDownHorizontal() 	{ xVelocity += (0 - xVelocity) / 3; }
    /** Slows down the player vertically */
    public void slowDownVertical()	{ yVelocity += (0 - yVelocity) / 3; }
    
}
