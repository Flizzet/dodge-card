package com.flizzet.camera;

import com.badlogic.gdx.utils.viewport.ExtendViewport;

/**
 * Main OrthographicCamera.
 *
 * @author Pedro Dutra (2017)
 * @version 1.2
 */
public class MainCamera extends Camera {

    /**
     * Default instantiable constructor
     */
    public MainCamera() {}

    @Override
    public void createCamera() {
        super.createCamera();
        //cam.zoom = 0.5f;
        viewport = new ExtendViewport(width, height, cam);
        viewport.apply();
    }

}
