package com.flizzet.assets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;

/**
 * Puts all assets into a dirs.txt file to be read later.
 * This dirs.txt file is located in the assets folder, in the android directory.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class AssetLister {

    public static final String DIRECTORY = "/Users/Flizzet/Documents/JavaWorkspace/DodgeCard/android/assets/";
    //public static File DIRECTORIES_FILE = ;//Gdx.files.internal("dirs.txt").file();///new File("/Users/Flizzet/Documents/JavaWorkspace/DodgeCard/android/assets/dirs.txt");
    
    /** Default instantiable constructor */
    public AssetLister() {}

    /**
     * Goes through a directory and finds every file. These file directories are
     * put into an ArrayList of Strings to be used for listing in text files.
     * 
     * @return all String values of the directories in the directory defined
     */
    public static ArrayList<String> search(String directoryName, ArrayList<File> files) {

	/* Create Array of all files */
	File dir = new File(directoryName);
	File[] fList = dir.listFiles();

	for (File f : fList) {
	    if (f.isFile()) {
		files.add(f);
	    } else if (f.isDirectory()) {
		search(f.getAbsolutePath(), files);
	    }
	}

	/* Build ArrayList of Strings of file names to return */
	ArrayList<String> names = new ArrayList<String>();
	for (File f : files) {
	    /* Add images */
	    for (String ext : Assets.IMAGE_FILE_EXTENSIONS) {
		if (f.getName().endsWith(ext)) {
		    String name = f.getPath();
		    name = name.replaceAll(DIRECTORY, "");
		    names.add(name);
		}
	    }
	    /* Add sounds */
	    for (String ext : Assets.SOUND_FILE_EXTENSIONS) {
		if (f.getName().endsWith(ext)) {
		    String name = f.getPath();
		    name = name.replaceAll(DIRECTORY, "");
		    names.add(name);
		}
	    }
	}

	return names;
    }

    /**
     * Writes the Array of Strings to the dirs file used for finding all files
     * in the project.
     * 
     * @param arrayList
     *            - The ArrayList where you would like all to be stored
     */
    public static void populate(ArrayList<String> arrayList) {
	try {
	    PrintWriter writer = new PrintWriter(Gdx.files.internal("dirs.txt").file());//DIRECTORIES_FILE);
	    for (String s : arrayList) {
		writer.println(s);
	    }
	    writer.close();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
    }
    
}
