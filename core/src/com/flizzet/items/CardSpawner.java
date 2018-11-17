package com.flizzet.items;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.entity.Entity;

/**
 * Handles spawning of any object.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class CardSpawner extends Entity {

    public static final CardSpawner INSTANCE = new CardSpawner();
    private ArrayList<Card> all = new ArrayList<Card>();
    private int limit = 5;
    private int millisCooldown = 5000;
    private boolean stopped = false;
    private Queue<Card> toBeRemoved = new LinkedList<Card>(),
	    	  	toBeAdded = new LinkedList<Card>();
    
    /** Default instantiable constructor */
    public CardSpawner() {}
    
    @Override
    public void update(float delta) {
	/* Add and remove cards */
	all.addAll(toBeAdded);
	all.removeAll(toBeRemoved);
	toBeAdded.clear();
	toBeRemoved.clear();
	
	for (Card c : all) {
	    c.update(delta);
	}
    }
    
    @Override
    public void render(SpriteBatch batch) {
	for (Card c : all) {
	    c.render(batch);
	}
    }

    @Override
    public void reset() {
	all.clear();
    }

    /** Spawns a new {@link Card} */
    private void addNew() {
	Card newCard = new Card(this);
	newCard.assignRandomPosition();
	toBeAdded.add(newCard);
    }

    /**
     * Starts the spawner. Calls a spawn method every (limit) seconds if the
     * limit of cards is not exceeded/met
     */
    public void start() {
	stopped = false;
	new Timer().schedule(new TimerTask() {
	    @Override
	    public void run() {
		if (!stopped) {
		    if (all.size() < limit) addNew();
		    start();
		} else {
		    this.cancel();
		}
	    }
	}, millisCooldown);
    }
    
    public void stop() {
	stopped = true;
    }

    public int getLimit()		{ return this.limit; }
    public ArrayList<Card> getAll()	{ return this.all; }
    
    public void setLimit(int newLimit)	{ this.limit = newLimit; }
    public void setCooldown(int millis)	{ this.millisCooldown = millis; }
    public void removeCard(Card oldCard){ this.toBeRemoved.add(oldCard); }

}
