package com.flizzet.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;

import com.flizzet.assets.Text;

/**
 * Loads {@link Text} files.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class TextLoader extends AsynchronousAssetLoader<Text, TextLoader.TextParameter> {

    private Text text;
    
    public TextLoader(FileHandleResolver resolver) {
	super(resolver);
    }

    @Override
    public void loadAsync(AssetManager manager, String fileName, FileHandle file, TextParameter parameter) {
	this.text = null;
	this.text = new Text(file);
    }

    @Override
    public Text loadSync(AssetManager manager, String fileName, FileHandle file, TextParameter parameter) {
	Text text = this.text;
	this.text = null;
	return text;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, TextParameter parameter) {
	return null;
    }

    public static class TextParameter extends AssetLoaderParameters<Text> {}

}