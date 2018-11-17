package com.flizzet.interfaces;

/**
 * Update method.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public interface Updatable {

    /**
     * Update the object.
     * 
     * @param delta
     *            - The time between the current frame and the previous frame
     */
    public void update(float delta);

}
