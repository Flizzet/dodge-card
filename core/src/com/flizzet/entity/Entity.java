package com.flizzet.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.flizzet.interfaces.Renderable;
import com.flizzet.interfaces.Resetable;
import com.flizzet.interfaces.Updatable;

/**
 * Abstract Entity class for concrete entities to extend from.
 *
 * @author Pedro Dutra (2017)
 * @version 1.1
 */
public abstract class Entity implements Updatable, Renderable, Resetable {
    
    protected Rectangle bounds = new Rectangle(0, 0, 16, 16);
    
    public Rectangle getBounds()		{ return bounds; }
    public float getX() 			{ return bounds.x; }
    public float getY() 			{ return bounds.y; }
    public float getCenterX()			{ return bounds.x + (bounds.width / 2); }
    public float getCenterY()			{ return bounds.y + (bounds.height / 2); }
    public float getWidth() 			{ return bounds.width; }
    public float getHeight() 			{ return bounds.height; }
    public float getHalfWidth()			{ return bounds.width / 2; }
    public float getHalfHeight()		{ return bounds.height / 2; }
    public void setX(float newX) 		{ this.bounds.x = newX; }
    public void setY(float newY) 		{ this.bounds.y = newY; }
    public void addToX(float amt)		{ this.bounds.x += amt; }
    public void addToY(float amt)		{ this.bounds.y += amt; }
    public void subtractFromX(float amt)	{ this.bounds.x -= amt; }
    public void subtractFromY(float amt)	{ this.bounds.y -= amt; }
    public void setCenterX(float newX)		{ this.bounds.x = newX - (bounds.width / 2);	 }
    public void setCenterY(float newY)		{ this.bounds.y = newY - (bounds.height / 2); }
    public void setWidth(float newWidth) 	{ this.bounds.width = newWidth; }
    public void setHeight(float newHeight) 	{ this.bounds.height = newHeight; }
    
    /** Sets bounds to image width and height */
    public void adjustBoundsToImage(Texture image) {
	this.setWidth(image.getWidth());
	this.setHeight(image.getHeight());
    }
    /** Sets bounds to image width and height */
    protected void adjustBoundsToImage(TextureRegion image) {
	this.setWidth(image.getRegionWidth());
	this.setHeight(image.getRegionHeight());
    }

}
