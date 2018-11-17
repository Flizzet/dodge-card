package com.flizzet.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 * Loads and gets assets. </br>
 * Image files are loadable as long as they are of png, gif, jpg/jpeg, or bmp formats. </br>
 * Sound files are loadable as long as they are of ogg, wav, flac, aiff, or mp3 formats. </br>
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class Assets {
    
    /* All possible image file extensions */
    public final static String[] IMAGE_FILE_EXTENSIONS = new String[] 
	    { ".png", ".gif", ".jpg", ".jpeg", ".bmp" };
    
    /* All possible sound file extensions */
    public final static String[] SOUND_FILE_EXTENSIONS = new String[]
	    { ".ogg", ".wav", ".flac", ".aiff", ".mp3" };
    
    private AssetManager manager = new AssetManager();

    /** Default instantiable constructor */
    public Assets() {
    }
    
    /**
     * Load all assets based on a file. The file is to be a list of every single
     * asset used throughout the program. It goes through every directory on the
     * list, and puts it into an ArrayList of Strings. This ArrayList is later
     * used to load every file. This is to prevent finding every file in a
     * directory using a dir.list(). The .list() method is slow on Android
     * devices, and this prevents that.
     * 
     * @param dir
     *            - Directory of the file with all asset directories in it
     */
    public void loadAll(Text file) {
	//FileHandle assetList = new FileHandle(dir);
	//FileHandle assetList = new FileHandle(file);
	
	/* Put the file into a String, then split that String line by line to create a list of Asset directories */
	String listOfFiles = file.getString();//assetList.readString();
	String[] everyFileDir = listOfFiles.split("\n");
	
	for (String s : everyFileDir) {
	    /* Load all texture files, if they end with an image extensions */
	    for (String ext : IMAGE_FILE_EXTENSIONS) {
		if (s.endsWith(ext)) {
		    manager.load(s, Texture.class);
		}
	    }
	    
	    /* Load all sound files, if they end with a sound extension */
	    for (String ext : SOUND_FILE_EXTENSIONS) {
		if (s.endsWith(ext)) {
		    manager.load(s, Sound.class);
		}
	    }
	}
    }
    
    /**
     * Loads an asset using the specific file directory.
     * 
     * @param dir
     *            - Directory of the file
     */
    public void load(String dir) {
	/* Loads the file as an image file if it is one */
	for (String ext : IMAGE_FILE_EXTENSIONS) {
	    if (dir.endsWith(ext)) {
		manager.load(dir, Texture.class);
	    }
	}
	/* Loads the file as a sound file if it is one */
	for (String ext : SOUND_FILE_EXTENSIONS) {
	    if (dir.endsWith(ext)) {
		manager.load(dir, Sound.class);
	    }
	}
    }

    /**
     * @return A file that has been loaded and stored in the AssetManager.
     * @param <E>
     *            - The file type
     * @param directory
     *            - The directory of the file
     * @param classType
     *            - The file type
     */
    public <E> E get(String directory, Class<E> classType) {
	return manager.get(directory, classType);
    }
    
    /** @return A file that has been loaded and stored in the AssetManager */
    public <E> E get(String directory) {
	return manager.get(directory);
    }
    
    public AssetManager getManager()	{ return this.manager; }

}
