package com.flizzet.interfaces;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Render method.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public interface Renderable {

    /**
     * Render the object.
     * 
     * @param batch
     *            - The {@link SpriteBatch} which everything is drawn upon
     */
    public void render(SpriteBatch batch);

}
