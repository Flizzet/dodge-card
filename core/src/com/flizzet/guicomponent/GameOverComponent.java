package com.flizzet.guicomponent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.flizzet.dodgecard.GameWorld;
import com.flizzet.player.Player;
import com.flizzet.states.State;
import com.flizzet.utils.FontUtils;

/**
 * Tells the player that the game is over and who has won.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class GameOverComponent extends GuiComponent {

    private ScreenDarkener darkener = new ScreenDarkener();
    
    private BitmapFont gameOverFont = FontUtils.UPHEAVAL_175;
    private GlyphLayout gameOverLayout = new GlyphLayout(gameOverFont, "game\nover");
    private Vector2 gameOverPosition = new Vector2(0, 0);
    private BitmapFont winnerFont = FontUtils.UPHEAVAL_120;
    private GlyphLayout winnerLayout = new GlyphLayout(winnerFont, "winner");
    private Vector2 winnerPosition = new Vector2(0, 0);
    private BitmapFont tapToStartFont = FontUtils.UPHEAVAL_75;
    private GlyphLayout tapToStartLayout = new GlyphLayout(tapToStartFont, "tap to restart");
    private Vector2 tapToStartPosition = new Vector2(0, 0);
    
    private int cooldown = 1;
    
    private final int STACK_MARGIN = 5;
    
    /** Default instantiable constructor */
    public GameOverComponent() {
	gameOverFont.setUseIntegerPositions(false);
	winnerFont.setUseIntegerPositions(false);
	tapToStartFont.setUseIntegerPositions(false);
    }

    @Override
    public void update(float delta) {
	/* Cooldown for tap to restart */
	cooldown -= delta;
	if (cooldown <= 0) {
	    if (Gdx.input.isTouched()) {
		GameWorld.INSTANCE.states.enterState(State.RESTARTING);
	    }
	}
	
	/* Set winner text */
	if (Player.INSTANCE.getHealthBar().getHealth() != 0) {
	    winnerLayout.setText(winnerFont, "you win!");
	} else {
	    winnerLayout.setText(winnerFont, "you lose");
	}
	
	/* Position text */
	gameOverPosition.x = GameWorld.INSTANCE.camera.getCenterX() - (gameOverLayout.width / 2);
	gameOverPosition.y = GameWorld.INSTANCE.camera.getCenterY() + gameOverLayout.height + STACK_MARGIN;
	winnerPosition.x = GameWorld.INSTANCE.camera.getCenterX() - (winnerLayout.width / 2);
	winnerPosition.y = GameWorld.INSTANCE.camera.getCenterY() - STACK_MARGIN;
	tapToStartPosition.x = GameWorld.INSTANCE.camera.getCenterX() - (tapToStartLayout.width / 2);
	tapToStartPosition.y = winnerPosition.y - winnerLayout.height - STACK_MARGIN;
	
	/* Update darkener */
	darkener.update(delta);
    }

    @Override
    public void render(SpriteBatch batch) {
	darkener.render(batch);
	gameOverFont.draw(batch, gameOverLayout, gameOverPosition.x, gameOverPosition.y);
	winnerFont.draw(batch, winnerLayout, winnerPosition.x, winnerPosition.y);
	tapToStartFont.draw(batch, tapToStartLayout, tapToStartPosition.x, tapToStartPosition.y);
    }

    @Override
    public void reset() {}

    @Override
    public void triggered() {}

}
