package com.flizzet.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.flizzet.dodgecard.GameWorld;
import com.flizzet.entity.Entity;

/**
 * The line displayed between the map.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class Separator extends Entity {
    
    private ShapeRenderer sr = new ShapeRenderer();
    private Vector2 point1, point2;
    
    public Separator() {
	this.setX(0);
	this.setY(GameWorld.INSTANCE.camera.getCenterY());
	this.setWidth(GameWorld.INSTANCE.camera.getWidth());
	this.setHeight(1);
    }

    @Override
    public void update(float delta) {
	point1 = new Vector2(bounds.x, bounds.y);
	point2 = new Vector2(bounds.x + bounds.width, bounds.y);
    }
    
    @Override
    public void render(SpriteBatch batch) {
	batch.end();
	Gdx.gl.glEnable(GL20.GL_BLEND);
	Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	sr.setProjectionMatrix(batch.getProjectionMatrix());
	sr.begin(ShapeType.Filled);
	/* Shadow */
	sr.setColor(new Color(0, 0, 0, 0.2f));
	sr.rectLine(point1, point2, bounds.height + 5);
	sr.setColor(new Color(0, 0, 0, 0.4f));
	sr.rectLine(point1, point2, bounds.height + 2);
	/* Line */
	sr.setColor(Color.BLACK);
	sr.rectLine(point1, point2, bounds.height);
	sr.end();
	Gdx.gl.glDisable(GL20.GL_BLEND);
	batch.begin();
    }
    
    @Override
    public void reset() {}
    
}