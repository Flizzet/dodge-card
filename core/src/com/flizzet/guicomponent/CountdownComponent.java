package com.flizzet.guicomponent;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.dodgecard.GameWorld;
import com.flizzet.dodgecard.PlayState;
import com.flizzet.utils.FontUtils;

/**
 * 3, 2, 1...
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class CountdownComponent extends GuiComponent {
    
    private int start = 4;
    private float currentTime = start;
    
    private ScreenDarkener darkener = new ScreenDarkener();
    private BitmapFont font = FontUtils.UPHEAVAL_175;
    private GlyphLayout layout = new GlyphLayout(font, "3");

    /** Default instantiable constructor */
    public CountdownComponent() {}

    @Override
    public void update(float delta) {
	darkener.update(delta);
	currentTime -= delta;
	layout.setText(font, "" + (int) currentTime); 
	
	if (currentTime <= 0) {
	    GameWorld.INSTANCE.guiContainer.remove(this);
	    GameWorld.INSTANCE.setPlayState(PlayState.PLAY);
	}
    }

    @Override
    public void render(SpriteBatch batch) {
	darkener.render(batch);
	
	font.setColor(Color.WHITE);
	font.draw(batch, String.valueOf((int) currentTime), 
		GameWorld.INSTANCE.camera.getCenterX() - (layout.width / 2),
		GameWorld.INSTANCE.camera.getCenterY() + (layout.height / 2));
    }

    @Override
    public void reset() { currentTime = start; }

    @Override
    public void triggered() {}
    
    public int getCurrentTime()	{ return (int) this.currentTime; }

}
