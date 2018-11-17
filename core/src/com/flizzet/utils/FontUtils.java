package com.flizzet.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

/**
 * Holds fonts and font utilities.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class FontUtils {
    
    private static final String upheavalDir = "fonts/upheaval.ttf";
    private static final String ALL_CHARS = "abcdefghijklmnopqrstuvwxyz1234567890!@#$%^&*()-_=+[]{};:,'`./<>?";
    
    public static final BitmapFont UPHEAVAL_175 = newFont(175, false, Color.WHITE);
    public static final BitmapFont UPHEAVAL_150 = newFont(150, false, Color.WHITE);
    public static final BitmapFont UPHEAVAL_120 = newFont(120, false, Color.WHITE);
    public static final BitmapFont UPHEAVAL_90 = newFont(90, false, Color.WHITE);
    public static final BitmapFont UPHEAVAL_85 = newFont(85, false, Color.WHITE);
    public static final BitmapFont UPHEAVAL_75 = newFont(75, false, Color.WHITE);
    public static final BitmapFont UPHEAVAL_70 = newFont(70, false, Color.WHITE);
    public static final BitmapFont UPHEAVAL_50 = newFont(50, false, Color.WHITE);
    
    /** Suppress default constructor */
    private FontUtils() {
	throw new AssertionError();
    }
    
    /** Create a new BitmapFont of the defined size */
    public static BitmapFont newFont(int size, boolean bordered, Color color) {
	BitmapFont newFont;
	FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(upheavalDir));
	FreeTypeFontParameter parameter = new FreeTypeFontParameter();
	
	parameter.size = size;
	parameter.color = color;
	parameter.characters = ALL_CHARS;
	/* Build a border if defined */
	if (bordered) {
	    parameter.borderColor = Color.BLACK;
	    parameter.borderStraight = true;
	    parameter.borderWidth = 10;
	}
	newFont = generator.generateFont(parameter);	// Create the font
	newFont.getData().setScale(0.15f);		// Scale down for blur, inital size is massive
	newFont.getData().setScale(0.15f);
	generator.dispose();				// Prevent memory leak
	return newFont;
    }

}
