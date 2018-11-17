package com.flizzet.settings;

/**
 * Handles constant settings throughout the game.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class CurrentSettings {
    
    public static boolean sound = true;
    public static float soundVolume = 0.5f;

    /** Suppress constructor for noninstantiability */
    private CurrentSettings() {
	throw new AssertionError();
    }

}
