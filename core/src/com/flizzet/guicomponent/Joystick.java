package com.flizzet.guicomponent;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.flizzet.dodgecard.GameWorld;

/**
 * Touchpad joystick for player movement.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class Joystick extends GuiComponent {

    public static final Joystick INSTANCE = new Joystick();
    private Touchpad touchpad;
    private TouchpadStyle touchpadStyle;
    private Skin touchpadSkin;
    private Drawable touchBackground;
    private Drawable touchKnob;
    
    /** Default instantiable constructor */
    public Joystick() {
	touchpadStyle = getTouchpadStyle();
	touchpad = new Touchpad(2, touchpadStyle);
	touchpad.setBounds(5, 5, 32, 32);
    }

    @Override
    public void update(float delta) {
	touchpad.act(delta);
    }

    @Override
    public void render(SpriteBatch batch) {
	touchpad.draw(batch, 0.5f);
    }

    /** Returns a new {@link TouchpadStyle} for drawing the Touchpad object. */
    private TouchpadStyle getTouchpadStyle() {

	touchpadSkin = new Skin();
        touchpadSkin.add("touchBackground", GameWorld.INSTANCE.assets.get("gui/joystick/background.png"));
        touchpadSkin.add("touchKnob", GameWorld.INSTANCE.assets.get("gui/joystick/stick.png"));
        
        touchpadStyle = new TouchpadStyle();
        touchBackground = touchpadSkin.getDrawable("touchBackground");
        touchKnob = touchpadSkin.getDrawable("touchKnob");
        touchpadStyle.background = touchBackground;
        touchpadStyle.knob = touchKnob;

        return touchpadStyle;
    }
    
    @Override
    public void reset() {
    }

    @Override
    public void triggered() {}
    
    public Touchpad getTouchpad()	{ return this.touchpad; }

}
