package com.flizzet.input;

import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.flizzet.guicomponent.Joystick;
import com.flizzet.player.Player;

/**
 * Handles gestures and other inputs.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class InputController implements GestureListener {

    /** Default instantiable constructor */
    public InputController() {
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
	return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
	return false;
    }

    @Override
    public boolean longPress(float x, float y) {
	return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
	float jx = Joystick.INSTANCE.getTouchpad().getX();
	float jy = Joystick.INSTANCE.getTouchpad().getY();
	float jw = Joystick.INSTANCE.getTouchpad().getWidth();
	float jh = Joystick.INSTANCE.getTouchpad().getHeight();
	//if (new Rectangle(jx, jy, jw, jh).contains(Gdx.input.getX(pointer), Gdx.input.getY(pointer)));
	float angle = (float) Math.atan2(velocityY, velocityX);
	Player.INSTANCE.throwCard(angle);
	return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
	return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
	return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
	return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
	return false;
    }

    @Override
    public void pinchStop() {}

}
