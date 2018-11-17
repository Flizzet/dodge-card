package com.flizzet.states;

import java.util.ConcurrentModificationException;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flizzet.enemy.Enemy;
import com.flizzet.guicomponent.CountdownComponent;
import com.flizzet.guicomponent.Joystick;
import com.flizzet.items.CardSpawner;
import com.flizzet.map.Map;
import com.flizzet.player.Player;

/**
 * Concrete play state.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class PlayState extends GameState {

    private CountdownComponent countdown;
    private Map map;
    private Player player;
    private Enemy enemy;
    private CardSpawner cardSpawner;
    private Joystick joystick;
    
    /** Default instantiable constructor */
    public PlayState() {}

    @Override
    public void entered() {
	world.setPlayState(com.flizzet.dodgecard.PlayState.COUNTDOWN);
	
	map = Map.INSTANCE;
	entityContainer.add(map);
	
	player = Player.INSTANCE;
	player.reset();
	entityContainer.add(player);
	
	enemy = Enemy.INSTANCE;
	enemy.reset();
	entityContainer.add(enemy);
	
	cardSpawner = CardSpawner.INSTANCE;
	cardSpawner.reset();
	cardSpawner.setCooldown(2000);
	cardSpawner.setLimit(3);
	cardSpawner.start();
	entityContainer.add(cardSpawner);
	
	joystick = Joystick.INSTANCE;
	world.stage.addActor(joystick.getTouchpad());
	
	countdown = new CountdownComponent();
	countdown.reset();
	guiContainer.add(countdown);
	
	try {
	    world.update(0);
	} catch (ConcurrentModificationException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public void update(float delta) {}

    @Override
    public void render(SpriteBatch batch) {}

    @Override
    public void exited() {
	entityContainer.clear();
	guiContainer.clear();
	joystick.getTouchpad().remove();
    }

    @Override
    public void dispose() {
    }

}
