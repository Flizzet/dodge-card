package com.flizzet.enemy;

import java.util.Random;

import com.flizzet.dodgecard.GameWorld;
import com.flizzet.interfaces.Updatable;
import com.flizzet.items.Card;
import com.flizzet.items.CardSpawner;
import com.flizzet.player.Player;

/**
 * Controls the {@link Enemy} Character.
 *
 * @author Pedro Dutra (2017)
 * @version 1.0
 */
public class EnemyController implements Updatable {

    private final Enemy enemy;
    private EnemyState state = EnemyState.THROWING;
    /* Moving around/Throwing state */
    private float targetX, targetY;
    private float changePositionCooldown = 2;
    private float throwCooldown = 3;
    private float speed = 50;
    private float xVelocity = speed, yVelocity = speed;
    private Random rand = new Random();
    /* Chasing card/Grabbing state */
    private Card targetCard;
    
    /** Default instantiable constructor */
    public EnemyController(Enemy enemy) {
	this.enemy = enemy;
	targetX = GameWorld.INSTANCE.camera.getCenterX();
	targetY = GameWorld.INSTANCE.camera.getHeight() - 20 - enemy.getHeight();
    }
    
    @Override
    public void update(float delta) {
	
	switch (state) {
	case GRABBING:
	    break;
	case THROWING:
	    /* Move around randomly */
	    changePositionCooldown -= delta;
	    if (changePositionCooldown <= 0) {
		findRandomPosition();
	    }
	    throwCooldown -= delta;
	    if (throwCooldown <= 0) {
		throwCard();
	    }
	    break;
	default:
	    break;
	}
	
	/* Move towards target points and update speed */
	speed += 0.01f;
	moveAround(delta);
    }
    
    /** Lay a _____ down */
    public void moveAround(float delta) {
	float distanceX = 0;
	float distanceY = 0;
	
	if (state == EnemyState.THROWING) {
	    distanceX = targetX - enemy.getX();
	    distanceY = targetY - enemy.getY();
	} else if (state  == EnemyState.GRABBING) {
	    if (targetCard.getCharacter() != null && targetCard.getCharacter() != enemy) {
		this.setState(EnemyState.THROWING);
		this.targetCard = null;
		return;
	    }
	    if (enemy.getY() > targetCard.getY()) {
		distanceX = targetCard.getCenterX() - enemy.getHalfWidth() - enemy.getX();
		distanceY = targetCard.getCenterY() - enemy.getHalfHeight() - enemy.getY();
	    } else {
		this.setState(EnemyState.THROWING);
	    }
	}
	
	/* Find angle of enemy to target position */
	float angle = (float) (Math.atan2(distanceY, distanceX) * 180 / Math.PI);
	/* Move towards newly established angle */
	if (Math.abs(distanceX) > 5)
	    xVelocity = (float) (Math.cos(angle * Math.PI / 180) * speed);
	else
	    
	    xVelocity = 0;
	if (Math.abs(distanceY) > 5)
	    yVelocity = (float) (Math.sin(angle * Math.PI / 180) * speed);
	else
	    yVelocity = 0;
	/* Add new velocity to enemy */
	enemy.setX(enemy.getX() + xVelocity * delta);
	enemy.setY(enemy.getY() + yVelocity * delta);
    }
    
    /** Finds a new target position to move towards */
    private void findRandomPosition() {
	System.out.println("finding random position");
	this.targetX = (float) (Math.random() * GameWorld.INSTANCE.camera.getWidth());
	this.targetY = (float) (Math.random() * ((GameWorld.INSTANCE.camera.getHeight() / 2) - enemy.getHeight())
						+ (GameWorld.INSTANCE.camera.getHeight() / 2));
	changePositionCooldown = .5f;
	/* Randomly decide to grab a card */
	if (rand.nextInt(5) == 2 && targetCard == null) findRandomCard();
    }
    
    /** Finds a new random target card to grab for throwing */
    private void findRandomCard() {
	if (targetCard != null) return;
	
	System.out.println("Found random card!");
	targetCard = null;
	this.setState(EnemyState.GRABBING);
	for (Card c : CardSpawner.INSTANCE.getAll()) {
	    if (c.getCharacter() == null) {
		targetCard = c;
		break;
	    }
	}
	
	/* Check if no cards were available */
	if (targetCard == null) {
	    this.setState(EnemyState.THROWING);
	}
    }
    
    /** Throws whatever card has been grabbed */
    private void throwCard() {
	System.out.println("Throwing card");
	/* Reset cooldown */
	throwCooldown = rand.nextFloat() * 5;
	
	/* Throw the card if it isn't null */
	if (targetCard != null && targetCard.getBounds().overlaps(enemy.getBounds())) {
	    Player player = Player.INSTANCE;
	    
	    /* Figure out the distance and angle */
	    float distanceX = player.getCenterX() - targetCard.getCenterX();
	    float distanceY = player.getCenterY() - targetCard.getCenterY();
	    float angle = (float) (Math.atan2(distanceY, distanceX) * 180 / Math.PI);
	    targetCard.setThrown(true);
	    targetCard.setAngle((float) (angle / 180 * Math.PI));
	    /* Reset the target card */
	    enemy.setHeldCard(null);
	}
	targetCard = null;
    }
    
    public void reset() {
	this.speed = 50;
    }
    
    public EnemyState getEnemyState()		{ return this.state; }
    public void setState(EnemyState newState)	{ this.state = newState; }
    public void setSpeed(float newSpeed)	{ this.speed = newSpeed; }
    public void setTargetCard(Card newCard)	{ this.targetCard = newCard; }

}