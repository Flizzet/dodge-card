package com.flizzet.sound;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.flizzet.settings.CurrentSettings;

/**
 * Holds all sounds needed through the game.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class Sounds {
    
    public static Sounds INSTANCE = new Sounds();
    
    /* Play */
    public Sound shootSound;
    
    private List<Sound> sounds = new ArrayList<Sound>();
    
    /** Single-use constructor */
    private Sounds() {}
    
    /** Loads all sounds into memory */
    public void loadSounds() {
	/* Play */
	sounds.add(shootSound = Gdx.audio.newSound(Gdx.files.internal("sounds/gun/gunshot.ogg")));
    }
    
    /** Removes all sounds from memory */
    public void dispose() {
	for (Sound s : sounds) {
	    s.dispose();
	}
    }
    
    /** Plays a sound multiplied by the game volume */
    public static void play(Sound sound, float volume) {
	if (CurrentSettings.sound) {
	    sound.play(volume * CurrentSettings.soundVolume);
	}
    }
    /** Plays a sound multiplied by the game volume with pitch */
    public static void play(Sound sound, float volume, float pitch) {
	if (CurrentSettings.sound) {
	    long id = sound.play(volume * CurrentSettings.soundVolume);
	    sound.setPitch(id, pitch);
	}
    }
    /** Plays a sound multiplied by the game volume with looping */
    public static void play(Sound sound, float volume, boolean looping) {
	if (CurrentSettings.sound) {
	    long id = sound.play(volume * CurrentSettings.soundVolume);
	    sound.setLooping(id, looping);
	}
    }

}
